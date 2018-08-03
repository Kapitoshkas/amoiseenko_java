package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactCreationTests extends TestBase{


  @Test
  public void testNewContactCreation() {
    app.gotoContactPage();
    app.getContactHelper().createContact(new ContactData("first name", "middle name", "last name", "new title", "new company", "first address", "first home", "first mobile", "first work", "first mail", "second mail", "1900", "second address", "second home", "test33"), true);
  }

}
