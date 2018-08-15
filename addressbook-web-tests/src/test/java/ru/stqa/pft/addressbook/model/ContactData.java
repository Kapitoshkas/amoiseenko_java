package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String name;
  private final String middlename;
  private final String lastName;
  private final String title;
  private final String company;
  private final String address;
  private final String home;
  private final String mobile;
  private final String work;
  private final String email;
  private final String email2;
  private final String byear;
  private final String address2;
  private final String phone2;
  private String group;


  public ContactData(String name, String middlename, String lastName, String title, String company, String address, String home, String mobile, String work, String email, String email2, String byear, String address2, String phone2, String group) {
    this.id =Integer.MAX_VALUE;
    this.name = name;
    this.middlename = middlename;
    this.lastName = lastName;
    this.title = title;
    this.company = company;
    this.address = address;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
    this.email = email;
    this.email2 = email2;
    this.byear = byear;
    this.address2 = address2;
    this.phone2 = phone2;
    this.group = group;
  }

  public ContactData(String name, String lastName, String group){
    this.id =Integer.MAX_VALUE;;
    this.name = name;
    this.middlename = "";
    this.lastName = lastName;
    this.title = "";
    this.company = "";
    this.address = "";
    this.home = "";
    this.mobile = "";
    this.work = "";
    this.email = "";
    this.email2 = "";
    this.byear = "";
    this.address2 = "";
    this.phone2 = "";
    this.group = group;
  }

  public ContactData(int id, String name,  String lastName){
    this.id = id;
    this.name = name;
    this.middlename = "";
    this.lastName = lastName;
    this.title = "";
    this.company = "";
    this.address = "";
    this.home = "";
    this.mobile = "";
    this.work = "";
    this.email = "";
    this.email2 = "";
    this.byear = "";
    this.address2 = "";
    this.phone2 = "";
    this.group = "";
      }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastName() {
    return lastName;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHome() {
    return home;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getByear() {
    return byear;
  }

  public String getAddress2() {
    return address2;
  }

  public String getPhone2() {
    return phone2;
  }

  public String getGroup() {
    return group;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }
}


