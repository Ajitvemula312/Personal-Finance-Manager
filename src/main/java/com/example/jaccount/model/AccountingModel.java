package com.example.jaccount.model;

import com.example.jaccount.Transaction;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountingModel implements IModel {
  private Connection connection = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;
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
      close();
    }
  }

  public List<Transaction> getTransactions() {
    ArrayList<Transaction> transactions = new ArrayList<>();
    createConnection(database, System.getenv("SQL_USER"), System.getenv("SQL_PASSWORD"));
    try {
      preparedStatement = connection
          .prepareStatement("SELECT name, amount, date from " + database + "." + table);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String name = resultSet.getString("name");
        double amount = resultSet.getInt("amount");
        Date date = resultSet.getDate("date");
        transactions.add(new Transaction(name, amount, date));
      }
    } catch (SQLException e) {
      System.out.println(e);
    } finally {
      close();
    }
    return transactions;
  }

  public double sumAllTransactions(){
    double sum = 0;
    createConnection(database, System.getenv("SQL_USER"), System.getenv("SQL_PASSWORD"));
    try {
      preparedStatement = connection
          .prepareStatement("SELECT amount from " + database + "." + table);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        double amount = resultSet.getBigDecimal("amount").doubleValue();
        sum += amount;
      }
    } catch (SQLException e) {
      System.out.println(e);
    } finally {
      close();
    }
    return Math.round(sum);
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

  private void close() {
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
