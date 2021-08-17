package be.infernalwhale;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class BeersDAO {
    private Connection conn;

    private BrewerDAO brewerDAO = new BrewerDAO();

    public BeersDAO() throws SQLException {
        conn = DbConnector.getConnection();
    }

    public Optional<Beer> getBeerById(int id) throws SQLException {
        String sql = "SELECT * FROM beers WHERE id = " + id;
        Statement statement = conn.createStatement();

        ResultSet rs = statement.executeQuery(sql);

        Optional<Beer> beerOptional;
        if (rs.next()) {
            Beer beer = new Beer();
            beer.setId(rs.getInt("Id"));

            Optional<Brewer> optionalBrewer = brewerDAO.selectBrewerByID(rs.getInt("BrewerId"));
            if (optionalBrewer.isPresent()) beer.setBrewer(optionalBrewer.get());

            // Do some more parsing here...

            beerOptional = Optional.of(beer);
        } else {
            beerOptional = Optional.empty();
        }

        return beerOptional;
    }
}
