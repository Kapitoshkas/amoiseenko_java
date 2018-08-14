package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String middlename;
  private final String lastname;
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


  public ContactData(String name, String middlename, String lastname, String title, String company, String address, String home, String mobile, String work, String email, String email2, String byear, String address2, String phone2, String group) {
    this.name = name;
    this.middlename = middlename;
    this.lastname = lastname;
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

  public ContactData(String name,  String lastname){
    this.name = name;
    this.middlename = "";
    this.lastname = lastname;
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


  public String getName() {
    return name;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
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

  }

