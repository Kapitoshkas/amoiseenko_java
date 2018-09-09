package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase{
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
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("Header").withFooter("Footer"));
    }
  }


  @Test
  public void testAddContactToGroup() {
    Contacts contacts = app.db().contacts();
    ContactData contact = contacts.iterator().next();

    int beforeGroups = contact.getGroups().size();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    int beforeContacts = group.getContacts().size();

    for (ContactData c: contacts) {
      System.out.println("c " + c);
      if (c.getGroups().size() != 0) {
        contact = c;
        System.out.println("contact" + contact);
        break;
      }
    }

    if (beforeGroups == 0) {
      app.contact().addContactToGroup(contact, group);
      beforeContacts = app.db().groups().iterator().next().withId(group.getId()).getContacts().size();
      app.contact().removeGroup(contact, group);
    } else {
      group = contact.getGroups().iterator().next();
      app.contact().removeGroup(contact, group);
    }
    int afterGroups = contact.getGroups().size();
    int afterContacts = app.db().groups().iterator().next().withId(group.getId()).getContacts().size();

    assertThat(afterGroups, equalTo(beforeGroups));
    assertThat(afterContacts, equalTo(beforeContacts - 1));
  }


  /*@Test (enabled = false)
  public void testRemoveContactFromGroup() {
    Groups groups = app.db().groups();
    GroupData ourGroup = groups.iterator().next();
    app.returnToHomePage();
    ContactData removingContact = new ContactData().withName("first")
            .withLastName("last name")
            .withAddress("Moscow")
            .withMobilePhone("+71234567890")
            .withEmail("test@test.com").inGroup(ourGroup);
    app.gotoContactPage();
    app.contact().createContact(removingContact);

    int id = app.db().getContactLastId(removingContact);
    app.group().selectGroupToCheckIncludedContacts(ourGroup);
    app.contact().selectContactById(id);
    app.contact().removeContactFromGroup();
    ContactData contactToCompare = new ContactData();
    Contacts afterContacts = app.db().contacts();
    for(ContactData contact: afterContacts){
         if (contact.getId() == id){
        contactToCompare = contact;
        break;
      }
    }
        assertThat(contactToCompare.inGroup(ourGroup).getGroups(), equalTo(removingContact.getGroups()));
  }*/
}
