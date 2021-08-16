package be.infernalwhale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// A good programmer is a lazy programmer

// JDBC is a specification... Regels/rules/afspraken die we gaan we volgen om
// te communiceren met een database
// MySQL >> JDBC MySQL Connector (Driver)

// Connection connection; // Object represents the connection to the db
// Statement statement; // Object represents the instruction to the db
// ResultSet resultSet; // Object represents the answer from the db

// statement.executeQuery("SELECT");
// statement.executeUpdate("INSERT/UPDATE/DELETE");

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://moktok.intecbrussel.org:33306/beers", "beer", "SecurePassword");
            System.out.println("Connection created!");

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Brewers WHERE id > 100");

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
}
