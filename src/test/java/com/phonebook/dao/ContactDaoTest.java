package com.phonebook.dao;

import com.phonebook.models.Contact;
import org.junit.Test;

public class ContactDaoTest {
  @Test
  public void getAllContacts() {

    ContactDao contactDao = new ContactDao();

    // yeah i know, printing is not a good "test"
    // FIXME: write better test

    for(Contact c: contactDao.getAllContacts()){
      System.out.println(c);
    }
  }

  @Test
  public void addContact() {
//    ContactDao contactDao = new ContactDao();
//    contactDao.addContact(new Contact(4,"John", "444-444-444"));
  }

  @Test
  public void updateContact() {
    ContactDao contactDao = new ContactDao();
    contactDao.updateContact(new Contact(1, "John", "123"));
  }
}
