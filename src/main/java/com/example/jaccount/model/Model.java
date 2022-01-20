package com.example.jaccount.model;

import com.example.jaccount.Transaction;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model implements IModel {
  private Connection connection = null;
  private PreparedStatement preparedStatement = null;
  //private ResultSet resultSet = null;
  private final String database = "accounting_sheet";
  private final String table = "transactions";

  public void addTransaction(Transaction transactionIn) {
    createConnection(database, System.getenv("SQL_USER"), System.getenv("SQL_PASSWORD"));
    String name = transactionIn.getName();
    double amount = transactionIn.getAmount();
    Date date = transactionIn.getDate();
    try {
      preparedStatement = connection
          .prepareStatement("insert into " + database + "." + table +
              "(name, amount, date) values (?, ?, ?)");
      preparedStatement.setString(1, name);
      preparedStatement.setDate(3, date);
      preparedStatement.setBigDecimal(2, BigDecimal.valueOf(amount));
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      System.out.println(e);
    } finally {
      close(null);
    }
  }

  @Override
  public ArrayList<String> getCategories() {
    ArrayList<String> categories = new ArrayList<>();
    createConnection(database, System.getenv("SQL_USER"), System.getenv("SQL_PASSWORD"));
    ResultSet resultSet = null;
    try {
      resultSet = query("SELECT name from " + database + "." + "categories");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (resultSet != null) {
      try {
        while (resultSet.next()) {
          String name = resultSet.getString("name");
          categories.add(name);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return categories;
  }

  public List<Transaction> getTransactions() {
    ArrayList<Transaction> transactions = new ArrayList<>();
    createConnection(database, System.getenv("SQL_USER"), System.getenv("SQL_PASSWORD"));
    ResultSet resultSet = null;
    try {
      resultSet = query(
  "SELECT name, amount, date, category " +
          "from " + database + "." + table
          + " ORDER BY date DESC");
    } catch (SQLException e) {
      System.out.println(e);
    }
    if (resultSet != null) {
      try {
        while (resultSet.next()) {
          String name = resultSet.getString("name");
          double amount = resultSet.getInt("amount");
          Date date = resultSet.getDate("date");
          String category = resultSet.getString("category");
          transactions.add(new Transaction(name, amount, date, category));
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    close(resultSet);
    return transactions;
  }

  private ResultSet query(String queryIn) throws SQLException {
    ResultSet resultSet;
    createConnection(database, System.getenv("SQL_USER"), System.getenv("SQL_PASSWORD"));
    preparedStatement = connection.prepareStatement(queryIn);
    resultSet = preparedStatement.executeQuery();
    return resultSet;
  }

  private final String genericAmountSelect = ("SELECT amount FROM " + database + "." + table);

  public double sumAllTransactions() {
    return sumQuery(genericAmountSelect);
  }

  @Override
  public double sumForCategory(String category) {
    return sumQuery(genericAmountSelect + " WHERE category='" + category + "'");
  }

  public double sumQuery(String query) {
    double sum = 0;
    ResultSet resultSet = null;
    try {
      resultSet = query(query);
      sum = sumResults(resultSet);
    } catch (SQLException e) {
      System.out.println(e);
    } finally {
      close(resultSet);
    }
    return Math.round(sum);
  }


  private double sumResults(ResultSet results) throws SQLException {
    double sum = 0;
    while (results.next()) {
      double amount = results.getBigDecimal("amount").doubleValue();
      sum += amount;
    }
    return sum;
  }


  private void createConnection(String databaseIn, String userIn, String passwordIn) {
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseIn +
          "?user=" + userIn + "&password=" + passwordIn);
    } catch (SQLException e) {
      System.out.println(e);
    }
  }


  private void writeMetaData(ResultSet resultSet) throws SQLException {

    //  Now get some metadata from the database
    // Result set get the result of the SQL query
    System.out.println("The columns in the table are: ");

    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
      System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    System.out.println("-----------------------------");
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      String name = resultSet.getString("name");
      int amount = resultSet.getInt("amount");
      Date date = resultSet.getDate("date");

      System.out.println("Name: " + name);
      System.out.println("Amount: $" + amount);
      System.out.println("Date: " + date);
      System.out.println(" ");
    }
  }

  private void close(ResultSet resultSet) {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (preparedStatement != null) {
        preparedStatement.close();
      }

      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

}
