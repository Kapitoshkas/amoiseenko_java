package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("first name",  "last name", "test1"));
    }


    List<ContactData> before = app.getContactHelper().getContactList();

    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"first name",  "last name");
    app.getContactHelper().initContactModification(before.size()-1);
    app.getContactHelper().fillNewContactForm(contact,false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare (g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before,after);
  }

}