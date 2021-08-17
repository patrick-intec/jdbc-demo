package be.infernalwhale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// A good programmer is a lazy programmer

// JDBC is a specification... Regels/rules/afspraken die we gaan we volgen om
// te communiceren met een database
// MySQL >> JDBC MySQL Connector (Driver)

// Connection connection; // Object represents the connection to the db
// Statement statement; // Object represents the instruction to the db
// ResultSet resultSet; // Object represents the answer from the db

// statement.executeQuery("SELECT");
// statement.executeUpdate("INSERT/UPDATE/DELETE");

// executeUpdate x
// PreparedStatements x
// Transactions
// DAO's - Data Access Objects - Application Layering/Application Design

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://moktok.intecbrussel.org:33306/beers", "beer", "SecurePassword");
            System.out.println("Connection created!");

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Brewers");

            int recordsChanged = statement.executeUpdate("");
            // executeUpdate based on an ID (PK) >> 123  UPDATE table SET col = newValue WHERE Price = 123
            // 0: record does not exist
            // 1: exactly 1 record changed.. >> "all is well"
            // 5: (W)OOPSIE / WTF / All is NOT well

            System.out.println("Looping over ResultSet");
            System.out.println("======================");

            List<Brewer> brewers = new ArrayList<>();
            while (rs.next()) {
                Brewer brewer = new Brewer();
                brewer.setId(rs.getInt("Id"));
                brewer.setName(rs.getString("Name"));
                // ...
                brewers.add(brewer);
            }

            brewers.forEach(System.out::println);

        } catch (SQLException ignored) {
            System.out.println("Something went wrong trying to connect to the database....");
            ignored.printStackTrace();
        }
    }

    public void preparedStatementExample() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://moktok.intecbrussel.org:33306/beers", "beer", "SecurePassword");

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE table_name SET col = ? WHERE id = ?");
            preparedStatement.setDouble(1, 4.2);
            preparedStatement.setString(2, "123");

            // UPDATE table_name SET col = 4.2 WHERE id = "123"

            preparedStatement.executeQuery();
            preparedStatement.executeUpdate();

            // Why PreparedStatements?
            // Prevent SQL injection attacks

            Statement statement = conn.createStatement();

            // Select all beers with alcohol > (user input value)
            String userInputtedValue = "10;SELECT * FROM information_schema.users";

            // to sanitize
            if (userInputtedValue.contains(";")) throw new IllegalAccessException("You are not allowed to use semi-colons in this field");

            String sql = "SELECT * FROM beers WHERE alcohol > " + userInputtedValue + ";";
            // SELECT * FROM beers WHERE Alcohol > 10;DROP TABLE beers;

            ResultSet rs = statement.executeQuery(sql);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void transactionExample() {
        // What and why of transactions

        // Table: BankAccounts
        /*
            BankAccountNumber          Balance           UserId
            123                        1000              1
            321                        1000              2

            UPDATE BankAccounts SET Balance = Balance - 100 WHERE BankAccountNumber = 123
            UPDATE BankAccounts SET Balance = Balance + 100 WHERE BankAccountNumber = 321
         */

        Connection conn = null;
        Statement addStatement = null;
        Statement substractStatement = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://moktok.intecbrussel.org:33306/beers", "beer", "SecurePassword");

            addStatement = conn.createStatement();
            substractStatement = conn.createStatement();

            substractStatement.executeUpdate("UPDATE BankAccounts SET Balance = Balance - 100 WHERE BankAccountNumber = 123");
            // Application crash - Hardware problems
            // Murphy's Law >> If something can go wrong, IT WILL
            // Extended Murphy's Law >> If something can go wrong, it will, but if it can cause more harm at a certain time, it will happen at THAT time
            addStatement.executeUpdate("UPDATE BankAccounts SET Balance = Balance + 100 WHERE BankAccountNumber = 321");
        } catch (SQLException ignored) {

        }

        // Solution: Transactions!!!
        // Transaction: 2 or more sql instructions that will be executed as 1 instruction.
        // in other words: either ALL instructions are executed.. OR NONE are.

        try {
            // begin transaction
            conn.setAutoCommit(false);
            // sql-instruction 1
            substractStatement.executeUpdate("UPDATE BankAccounts SET Balance = Balance - 100 WHERE BankAccountNumber = 123");
            // sql-instruction 2
            addStatement.executeUpdate("UPDATE BankAccounts SET Balance = Balance + 100 WHERE BankAccountNumber = 321");
            // commit transaction
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}











