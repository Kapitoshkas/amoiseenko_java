package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class NewContactCreationTests extends TestBase{


  @Test
  public void testNewContactCreation() {
    app.goTo().returnToHomePage();
    Set<ContactData> before = app.contact().all();
    app.gotoContactPage();
    ContactData contact = new ContactData().withName("first name").withLastName("last name").withGroup("test1");
    app.contact().createContact(contact);
    app.goTo().returnToHomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);



  }


}
