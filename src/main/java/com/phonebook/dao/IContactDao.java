package com.phonebook.dao;

import com.phonebook.models.Contact;
import javafx.collections.ObservableList;

public interface IContactDao {
  public ObservableList<Contact> getAllContacts();
  public Contact getContact(int id);
  public void addContact(Contact contact);
  public void updateContact(Contact contact);
  public void deleteContact(Contact contact);
}
