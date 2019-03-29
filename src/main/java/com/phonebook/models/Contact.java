package com.phonebook.models;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
  public int id;
  public SimpleStringProperty name;
  public SimpleStringProperty phone;

  public Contact(int id, String name, String phone) {
    this.id = id;
    this.name = new SimpleStringProperty(name);
    this.phone = new SimpleStringProperty(phone);
  }

  public Contact(String name, String phone) {
    this.name = new SimpleStringProperty(name);
    this.phone = new SimpleStringProperty(phone);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name.get();
  }

  public SimpleStringProperty nameProperty() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public String getPhone() {
    return phone.get();
  }

  public SimpleStringProperty phoneProperty() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone.set(phone);
  }

  @Override
  public String toString() {
    return "Contact{" +
            "id=" + id +
            ", name=" + name.toString() +
            ", phone=" + phone.toString() +
            '}';
  }
}
