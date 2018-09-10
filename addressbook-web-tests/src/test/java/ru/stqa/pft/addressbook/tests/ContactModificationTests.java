package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0)  {
      app.goTo().returnToHomePage();
      app.contact().createContact(new ContactData().withName("first name")
              .withLastName("last name")
              .withAddress("Moscow")
              .withMobilePhone("+71234567890")
              .withEmail("test@test.com"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withId(modifiedContact.getId()).withName("first name").withLastName("last name")
            .withLastName("last name")
            .withAddress("Moscow")
            .withMobilePhone("+71234567890")
            .withEmail("test@test.com");

    app.contact().modify(contact);
    assertThat(app.contact().getContactCount(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }



}