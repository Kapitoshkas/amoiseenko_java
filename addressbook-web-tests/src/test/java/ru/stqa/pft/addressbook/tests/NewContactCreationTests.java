package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class NewContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
    }.getType());

    return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }


/*  @Test
  public void testNewContactCreation() {
    app.goTo().returnToHomePage();
    Contacts before = app.contact().all();
    app.gotoContactPage();
    ClassLoader classLoader = getClass().getClassLoader();
    File photo = new File (classLoader.getResource("stru.png").getFile());
    ContactData contact = new ContactData().withName("first name").withLastName("last name").withGroup("test1").withPhoto(photo);
    app.contact().createContact(contact);
    app.goTo().returnToHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }*/

  @Test(dataProvider = "validContactsFromJson")
  public void testNewContactCreation(ContactData contact) {
    Groups groups = app.db().groups();
    File photo = new File ("src/test/resources/stru.png");
    ContactData contacts = new ContactData().withName("first name").withLastName("last name").withPhoto(photo)
            .inGroup(groups.iterator().next());
    app.goTo().returnToHomePage();
    Contacts before = app.db().contacts();
    app.gotoContactPage();
    app.contact().createContact(contact);
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();

     assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
 }
