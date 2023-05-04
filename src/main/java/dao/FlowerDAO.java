package dao;

import model.Flower;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * This DAO class provides CRUD database operations for the table flowers in the database.
 */

public class FlowerDAO implements AbstractDAO<Flower> {
    private static final Logger logger = Logger.getLogger(FlowerDAO.class.getName());
//    private String jdbcURL = "jdbc:mysql://localhost:3306/keeper?useSSL=false";
    private String jdbcURL = "jdbc:mysql://localhost:3306/keeper";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String SAVE_FLOWERS_SQL = "INSERT INTO flowers" + "  (variety, alias, height, price) VALUES " +
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
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public Optional<Flower> get(int id) throws SQLException {
        Flower flower = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FLOWER_BY_ID)) {
            statement.setLong(1, id);
            // TODO: change to logger
            System.out.println(statement + "\n\n");
            logger.info(statement.toString());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String variety = rs.getString("variety");
                String alias = rs.getString("alias");
                int height = rs.getInt("height");
                int price = rs.getInt("price");
                flower = new Flower(id, variety, alias, height, price);
            }
        }
        return Optional.ofNullable(flower);
    }

    @Override
    public List<Flower> getAll() throws SQLException {
        List<Flower> flowers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FLOWERS)) {
//            TODO: change to logging
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String variety = rs.getString("variety");
                String alias = rs.getString("alias");
                int height = rs.getInt("height");
                int price = rs.getInt("price");
                flowers.add(new Flower(id, variety, alias, height, price));
            }
        }
        return flowers;
    }

    @Override
    public void save(Flower flower) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_FLOWERS_SQL)) {
            statement.setString(1, flower.getVariety());
            statement.setString(2, flower.getAlias());
            statement.setInt(3, flower.getHeight());
            statement.setInt(4, flower.getPrice());

            // TODO: change to logger
            System.out.println(statement);
            statement.executeUpdate();
        }
    }

    @Override
    public boolean update(Flower flower) throws SQLException {
        boolean isUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FLOWERS_SQL)) {
            preparedStatement.setString(1, flower.getVariety());
            preparedStatement.setString(2, flower.getAlias());
            preparedStatement.setInt(3, flower.getHeight());
            preparedStatement.setInt(4, flower.getPrice());
            preparedStatement.setInt(5, flower.getId());
            isUpdated = preparedStatement.executeUpdate() > 0;
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Flower flower) throws SQLException {
        boolean isDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FLOWERS_SQL)) {
            preparedStatement.setInt(1, flower.getId());
            isDeleted = preparedStatement.execute();
        }
        return isDeleted;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
//                TODO: change to logger
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
