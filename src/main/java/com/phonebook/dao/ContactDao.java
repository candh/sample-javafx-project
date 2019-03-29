package com.phonebook.dao;

import com.phonebook.models.Contact;
import com.phonebook.services.SQLConnectionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactDao implements IContactDao {

  private void _finally(Connection connection) {
    if (connection != null) {
      SQLConnectionService.closeConnection(connection);
    }
  }

  @Override
  public ObservableList<Contact> getAllContacts() {
    Connection connection = SQLConnectionService.getConnection();
    List<Contact> list = new ArrayList<>();
    ObservableList<Contact> contacts = FXCollections.observableList(list);

    String query = "SELECT * FROM contact";

    try {
      PreparedStatement preparedStatement;
      preparedStatement = SQLConnectionService.prepareAStatement(connection, query, null);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet != null) {
        while(resultSet.next()) {
          contacts.add(new Contact(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
        }
        return contacts;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      this._finally(connection);
    }
    return null;
  }

  @Override
  public Contact getContact(int id) {
    return null;
  }

  @Override
  public void addContact(Contact contact) {
    Connection connection = SQLConnectionService.getConnection();
    HashMap<Integer, Object> params = new HashMap<>();
    String query = "INSERT INTO contact VALUES(?, ?)";

    params.put(1, contact.getName());
    params.put(2, contact.getPhone());

    try {
      PreparedStatement preparedStatement = SQLConnectionService.prepareAStatement(connection, query, params);

      int affectedRows = preparedStatement.executeUpdate();

      if (affectedRows == 0) {
        throw new SQLException("New contact insertion failed");
      }

      ResultSet resultSet = preparedStatement.getGeneratedKeys();

      if (resultSet.next()) {
        contact.setId((int) resultSet.getLong(1));
      } else {
        throw new SQLException("New contact insertion failed.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      this._finally(connection);
    }
  }

  @Override
  public void updateContact(Contact contact) {
    Connection connection = SQLConnectionService.getConnection();
    HashMap<Integer, Object> params = new HashMap<>();
    String query = "UPDATE contact SET contact_name = ?, contact_phone = ? WHERE contact_id = ?";
    params.put(1, contact.getName());
    params.put(2, contact.getPhone());
    params.put(3, contact.getId());

    try {
      int affectedRows = SQLConnectionService.prepareAStatement(connection, query, params).executeUpdate();

      if (affectedRows == 0) {
        throw new SQLException("Contact update failed");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      this._finally(connection);
    }
  }

  @Override
  public void deleteContact(Contact contact) {

  }
}
