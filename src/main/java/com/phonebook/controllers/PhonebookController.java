package com.phonebook.controllers;

import com.phonebook.dao.ContactDao;
import com.phonebook.models.Contact;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javax.swing.plaf.basic.BasicButtonUI;
import java.net.URL;
import java.util.ResourceBundle;

public class PhonebookController implements Initializable {

  @FXML
  private TableView<Contact> contactTable;
  @FXML
  private TableColumn<Contact, Integer> idCol;
  @FXML
  private TableColumn<Contact, String> contact_nameCol;
  @FXML
  private TableColumn<Contact, String> contact_phoneCol;
  @FXML
  private TextField nameTextField;
  @FXML
  private TextField phoneTextField;
  @FXML
  private Button addButton;

  private ContactDao contactDao = new ContactDao();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    contact_nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    contact_phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

    // to make it really editable
    contact_nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
    contact_phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());

    contactTable.getItems().addAll(contactDao.getAllContacts());
  }

  // *****
  // Click Handlers
  //

  public void addContact() {
    String name = nameTextField.getText();
    String phone = phoneTextField.getText();

    // TODO: display error messages
    if (name.isEmpty()) {
      return;
    } else if (phone.isEmpty()) {
      return;
    }

    Contact c = new Contact(name, phone);

    contactDao.addContact(c);

    // add to the table
    contactTable.getItems().add(c);

    // clear the textfields
    nameTextField.clear();
    phoneTextField.clear();
  }

  public void nameColumnEditCommit(TableColumn.CellEditEvent<Contact, String> t) {
    Contact c = t.getTableView().getItems().get(
            t.getTablePosition().getRow());
    c.setName(t.getNewValue());
    // update the contact
    contactDao.updateContact(c);
  }

  public void phoneColumnEditCommit(TableColumn.CellEditEvent<Contact, String> t) {
    Contact c = t.getTableView().getItems().get(
            t.getTablePosition().getRow());
    c.setPhone(t.getNewValue());
    // update the contact
    contactDao.updateContact(c);
  }
}
