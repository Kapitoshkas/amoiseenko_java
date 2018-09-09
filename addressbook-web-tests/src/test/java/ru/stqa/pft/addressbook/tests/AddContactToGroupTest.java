package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase {

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

 /* @Test
  public void testAddContactToGroup() {
    ContactData addingToGroupContact = new ContactData().withName("first name")
            .withLastName("last name")
            .withAddress("Moscow")
            .withMobilePhone("+71234567890")
            .withEmail("test@test.com");
    app.gotoContactPage();
    app.contact().createContact(addingToGroupContact);

    Groups beforeGroups = app.db().groups();
    GroupData groupForAddingContact = beforeGroups.iterator().next();
    app.returnToHomePage();

    int id = app.db().getContactLastId(addingToGroupContact);
    app.contact().selectContactById(id);
    app.group().selectGroupToIncludeContact(groupForAddingContact);
    app.contact().addContactToGroup();
    Contacts afterContacts = app.db().contacts();
    ContactData contactToCompare = new ContactData();
    for(ContactData contact: afterContacts){
      if (contact.getId() == id){
        contactToCompare = contact;
        break;
      }
    }
    assertThat(contactToCompare.getGroups(), equalTo(addingToGroupContact.inGroup(groupForAddingContact).getGroups()));
  }*/

  @Test
  public void testAddContactToGroup() {
    Contacts contacts = app.db().contacts();
    ContactData contact = contacts.iterator().next();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    int oldContacts;

    for (ContactData c: contacts) {
      System.out.println("c " + c);
      if (groups.size() != c.getGroups().size()) {
        contact = c;
        System.out.println("contact" + contact);
        break;
      }
    }
    int before = contact.getGroups().size();

    if(groups.size() == contact.getGroups().size()){
      app.contact().removeGroup(contact, group);
      group = app.db().groups().iterator().next();
      oldContacts = app.db().groups().iterator().next().withId(group.getId()).getContacts().size();
      app.contact().addContactToGroup(contact, group);
    } else {
      groups.removeAll(contact.getGroups());
      oldContacts = group.getContacts().size();
      app.contact().addContactToGroup(contact, group);
    }

    int after = contact.getGroups().size();
    int newContacts =  app.db().groups().iterator().next().withId(group.getId()).getContacts().size();

    assertThat(after, equalTo(before));
    assertThat(newContacts, equalTo(oldContacts +  1));
  }

}
