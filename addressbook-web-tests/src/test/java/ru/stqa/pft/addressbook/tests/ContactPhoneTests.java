package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() ==0) {
      app.contact().createContact(new ContactData().withName("first name")
              .withLastName("last name")
              .withAddress("Moscow")
              .withMobilePhone("+71234567890")
              .withEmail("test@test.com"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().returnToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo((mergePhones(contactInfoFromEditForm))));
    assertThat(contact.getAllEmail(), equalTo((mergeEmails(contactInfoFromEditForm))));
    assertThat(cleanedAddress(contact.getAddress().trim()), equalTo(cleanedAddress(contactInfoFromEditForm.getAddress().trim())));

  }

  private String mergePhones(ContactData contact) {
   return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
           .map(ContactPhoneTests::cleanedPhones)
            .collect(Collectors.joining("\n"));
      }


  public static String cleanedPhones (String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  public static String cleanedAddress(String address) {
    return address.replaceAll("\n\n", " \n")
            .replaceAll(" \n", " ")
            .replaceAll("\n", " ")
            .replaceAll("  ", " ");
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}
