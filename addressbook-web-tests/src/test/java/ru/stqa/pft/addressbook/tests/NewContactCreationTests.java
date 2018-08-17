package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class NewContactCreationTests extends TestBase{


  @Test
  public void testNewContactCreation() {
    app.goTo().returnToHomePage();
    List<ContactData> before = app.contact().list();
    app.gotoContactPage();
    ContactData contact = new ContactData().withName("first name").withLastName("last name").withGroup("test1");
    app.contact().createContact(contact);
    app.goTo().returnToHomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare (g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);



  }


}
