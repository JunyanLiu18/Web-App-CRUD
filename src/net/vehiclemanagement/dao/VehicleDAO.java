package net.vehiclemanagement.dao;

import net.vehiclemanagement.model.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 *
 * @author Ramesh Fadatare
 */
public class VehicleDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "B5d89d30.";

    private static final String INSERT_VEHICLE_SQL = "INSERT INTO vehicles" + "  (year, make, model) VALUES "
            + " (?, ?, ?);";

    private static final String SELECT_VEHICLE_BY_ID = "select id,year,make,model from vehicles where id =?";
    private static final String SELECT_ALL_VEHICLES = "select * from vehicles";
    private static final String DELETE_VEHICLES_SQL = "delete from vehicles where id = ?;";
    private static final String UPDATE_VEHICLES_SQL = "update vehicles set year = ?,make= ?, model =? where id = ?;";
    private static final String SEARCH_VEHICLE_YEAR = "select id,year,make,model from vehicles where year =?";
    private static final String SEARCH_VEHICLE_MAKE = "select id,year,make,model from vehicles where make =?";
    private static final String SEARCH_VEHICLE_MODEL = "select id,year,make,model from vehicles where model =?";

    public VehicleDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertVehicle(Vehicle vehicle) throws SQLException {
        System.out.println(INSERT_VEHICLE_SQL);
        //connect
        try (Connection connection = getConnection();
             //create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VEHICLE_SQL)) {
            preparedStatement.setInt(1, vehicle.getYear());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            //System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Vehicle selectVehicle(int id) {
        Vehicle vehicle = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VEHICLE_BY_ID);) {
            preparedStatement.setInt(1, id);
            //System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int year = rs.getInt("year");
                String make = rs.getString("make");
                String model = rs.getString("model");
                vehicle = new Vehicle(id, year, make, model);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return vehicle;
    }

    public List<Vehicle> searchVehicleYear(int year) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_VEHICLE_YEAR);) {
            preparedStatement.setInt(1, year);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String make = rs.getString("make");
                String model = rs.getString("model");
                vehicles.add(new Vehicle(id, year, make, model));
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return vehicles;
    }

    public List<Vehicle> searchVehicleMake(String make) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_VEHICLE_MAKE);) {
            preparedStatement.setString(1, make);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int year = rs.getInt("year");
                String model = rs.getString("model");
                vehicles.add(new Vehicle(id, year, make, model));
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return vehicles;
    }

    public List<Vehicle> searchVehicleModel(String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_VEHICLE_MODEL);) {
            preparedStatement.setString(1, model);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int year = rs.getInt("year");
                String make = rs.getString("make");
                vehicles.add(new Vehicle(id, year, make, model));
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return vehicles;
    }


    public List<Vehicle> selectAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
       try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VEHICLES);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int year = rs.getInt("year");
                String make = rs.getString("make");
                String model = rs.getString("model");
                vehicles.add(new Vehicle(id, year, make, model));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return vehicles;
    }

    public boolean deleteVehicle(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_VEHICLES_SQL);) {
            statement.setInt(1, id);
            //at least one row updated
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateYear(Vehicle vehicle) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_VEHICLES_SQL);) {
            statement.setInt(1, vehicle.getYear());
            statement.setString(2, vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setInt(4, vehicle.getId());
            //at least one row updated
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
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
