package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.File;
import java.sql.SQLOutput;

public class NewContactCreationTests extends TestBase{


  @Test
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

  }

 }
