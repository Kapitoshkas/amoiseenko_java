package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("first name", "middle name", "last name", "new title", "new company", "first address", "first home", "first mobile", "first work", "first mail", "second mail", "1900", "second address", "second home", null), false);
    }
   app.getContactHelper().initContactModification();
   app.getContactHelper().fillNewContactForm(new ContactData("first name", "middle name", "last name", "new title", "new company", "first address", "first home", "first mobile", "first work", "first mail", "second mail", "1900", "second address", "second home", null), false);
   app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }

}