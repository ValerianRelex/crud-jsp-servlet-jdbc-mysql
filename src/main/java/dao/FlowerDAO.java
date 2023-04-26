package dao;

import model.Flower;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * This DAO class provides CRUD database operations for the table flowers in the database.
 */

public class FlowerDAO implements AbstractDAO<Flower> {
    private String jdbcURL = "jdbc:mysql://localhost:3306/keeper?useSSL=false";
    private String jdbcUsername = "admin";
    private String jdbcPassword = "admin";

    private static final String INSERT_FLOWERS_SQL = "INSERT INTO flowers" + "  (variety, alias, height, price) VALUES " +
            " (?, ?, ?, ?);";
    private static final String SELECT_FLOWER_BY_ID = "SELECT id,variety,alias,height,price FROM flowers WHERE id = ?";
    private static final String SELECT_ALL_FLOWERS = "SELECT * FROM flowers";
    private static final String DELETE_FLOWERS_SQL = "DELETE FROM flowers WHERE id = ?;";
    private static final String UPDATE_FLOWERS_SQL = "UPDATE flowers SET variety = ?,alias = ?,height = ?,price = ? " +
            "WHERE id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public Optional<Flower> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Flower> getAll() {
        return null;
    }

    @Override
    public void save(Flower flower) {

    }

    @Override
    public void update(Flower flower, String[] params) {

    }

    @Override
    public void delete(Flower flower) {

    }
}
