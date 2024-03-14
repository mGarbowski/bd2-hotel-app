package pl.mgarbowski;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        var databaseProperties = DatabaseProperties.loadFromFile("/database.properties");
        try (
                Connection connection = DriverManager.getConnection(
                        databaseProperties.url(),
                        databaseProperties.username(),
                        databaseProperties.password()
                )
        ) {
            String sql = "SELECT id, name FROM hotels";
            try (
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql)
            ) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    System.out.println("Hotel ID: " + id + ", Name: " + name);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
