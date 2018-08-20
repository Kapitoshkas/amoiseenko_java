package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends Helperbase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillNewContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastName());
    click(By.name("nickname"));
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[3]")).isSelected()) {
      click(By.xpath("//div[@id='content']/form/select[1]//option[3]"));
    }
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[2]")).isSelected()) {
      click(By.xpath("//div[@id='content']/form/select[2]//option[2]"));
    }
    type(By.name("byear"), contactData.getByear());
    type(By.name("address2"), contactData.getAddress2());
    type(By.name("phone2"), contactData.getPhone2());

      if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void selectContactById (int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

    public void clickDeleteButton () {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void clickDeletePopup () {
      wd.switchTo().alert().accept();
    }

    public void initContactModification (int id) {
      wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification () {
      click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void returnToHomePage () {
      click(By.linkText("home"));
    }

 /* public void createContact(ContactData contact, boolean creation) {
    fillNewContactForm(contact,creation);
    submitNewContactCreation();
    returnToHomePage();
  }*/

  public void createContact(ContactData contact) {
    fillNewContactForm(contact,true);
    submitNewContactCreation();
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModification(contact.getId());
    fillNewContactForm(contact,false);
    submitContactModification();
    returnToHomePage();
  }


  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    clickDeleteButton();
    clickDeletePopup();
    returnToHomePage();

  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    return new ContactData().withId(contact.getId()).withName(firstname).withLastName(lastname)
            .withAddress(address)
            .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
            .withEmail(email).withEmail2(email2).withEmail3(email3);

  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String name = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String address = cells.get(3).getText();
      String allEmail = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      contacts.add(new ContactData().withId(id).withName(name).withLastName(lastname)
              .withAddress(address).withAllPhones(allPhones).withAllEmail(allEmail));
    }
    return contacts;
  }

}



