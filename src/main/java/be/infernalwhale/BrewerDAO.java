package be.infernalwhale;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BrewerDAO {
    // Single Responsibility Principle - High Cohesion Loose Coupling
    // A class responsible for getting data objects/rows/records from the database.
    // BrewerDAO = A class responsible for getting Brewer data
    // CRUD-methods

    private Connection conn;

    public BrewerDAO() throws SQLException {
        conn = DriverManager
                .getConnection("connectionString", "username", "pwd");
    }

    /**
     * Responsible for getting ALL brewers
     */
    public List<Brewer> selectBrewers() throws SQLException {
        String query = "SELECT * FROM Brewers";
        Statement statement = conn.createStatement();

        ResultSet rs = statement.executeQuery(query);

        List<Brewer> brewerList = new ArrayList<>();
        while (rs.next()) {
            // Create Brewer objects from ResultSet
            // Put Brewer object in List
        }

        return brewerList;
    }

    // DBMS       >> Network     >> Java App
    // RAM-speed    0.0375 GB/s    RAM-speed DDR4-2133 >> 10GB/s
    // Limit the amount of data send over the network!!

    // LAN - Local Area Network, EAN Enterprise Area Network - 1Gb / 10Gb

    // Copy/Paste == !DRY  (Don't Repeat Yourself) >> WET

    public List<Brewer> selectBrewers(int zipcode) throws SQLException {
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM Brewers WHERE ZipCode = " + zipcode;

        ResultSet rs = statement.executeQuery(query);

        List<Brewer> brewerList = new ArrayList<>();
        while (rs.next()) {
            // Create Brewer objects from ResultSet
            // Put Brewer object in List
        }

        return brewerList;
    }

    public Optional<Brewer> selectBrewerByID(int id) {
        // The Dev has to remember/realise that the calling method has to do a null-check
        Optional<Brewer> optionalBrewer = Optional.of(new Brewer());


        return optionalBrewer;
        // no brewer found >> ok, optional handles this
        // 1 brewer found  >> ok, optional handles this
        // multiple brewers found >> this is exceptional
    }

    public Optional<Brewer> createBrewer(Brewer newBrewer) {
        // return int == new id
        // return int == -1 if something went wrong

        String sql = "INSERT INTO Brewers (col1, col2, col3) VALUES (?, ?, ?)";

        return Optional.empty();
    }

    public int updateBrewer() {
        return 0;
    }

    public boolean deleteBrewer(int id) {
        return false;
    }
}
