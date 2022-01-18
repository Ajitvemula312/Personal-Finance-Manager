package com.example.jaccount.model;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class AccountingModel implements IModel {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private final String database = "accounting_sheet";
    private final String table = "transactions";

    public AccountingModel() {

    }

    public void addTransaction(String nameIn, double amountIn, LocalDate dateIn) {
        createConnection(database, System.getenv("SQL_USER"), System.getenv("SQL_PASSWORD"));
        try {
            preparedStatement = connection
                    .prepareStatement("insert into " + database + "." + table +
                            "(name, amount, date) values (?, ?, ?)");
            preparedStatement.setString(1, nameIn);
            preparedStatement.setBigDecimal(2, BigDecimal.valueOf(amountIn));
            preparedStatement.setDate(3, Date.valueOf(dateIn.toString()));
            preparedStatement.executeUpdate();
            //preparedStatement.executeQuery();
            //writeResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            close();
        }
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
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String name = resultSet.getString("name");
            int amount = resultSet.getInt("amount");
            Date date = resultSet.getDate("date");

            System.out.println("Name: " + name);
            System.out.println("Amount: " + amount);
            System.out.println("Date: " + date);
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {

        }
    }

}
