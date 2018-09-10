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
  @Test
  public void contactToGroup() throws InterruptedException {
  Contacts before = app.db().contacts();//app.getContactHelper().all();
  ContactData modContact = before.iterator().next();
  //int before = app.getContactHelper().getContactCount();
  String curgroup = app.getContactHelper().getCurrentGroup();

    app.getContactHelper().clickSelectedById(modContact);

    app.getContactHelper().clickAddToGroup();

    Thread.sleep(500);
    app.goTo().returnToHomePage();

  Contacts after = app.db().contacts();
  Groups groups = new Groups();
  String aftergroup="";
    for ( ContactData contact :  after ) {
    if (contact.equals(modContact))
      groups = contact.getGroups();
    for (GroupData group: groups) {
      if (group.getGroupName().equals(curgroup))
        aftergroup = group.getGroupName();
    }
  }
  assertThat(curgroup, equalTo(aftergroup));
}

/*
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
*/

}
