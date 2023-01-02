package messdatabase;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {

    public Connection connection = null;
    private final String dbUrl = "jdbc:postgresql://localhost/mess";
    private final String user = "dbmsprj";
    private final String pass = "secret";

    public static String[] Tables = {
        "Inmates",
        "Products",
        "Workers",
        "Stocks",
        "Outgoing"
    };

    public void establishConnection() {
        try {
            connection = DriverManager.getConnection(dbUrl, user, pass);
        } catch (SQLException e) {
            System.out.println("Exception during establishing database connection");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Connected to db");
        } else {
            System.out.println("Could not establish connection");
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException ex) {
            System.out.println("Excepition during closing connection");
        }
    }

    public void insertIntoTable(String tableName, String... values) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "INSERT INTO " + tableName + " values ('";

        for (int i = 0; i < values.length - 1; i++) {
            query += values[i];
            query += "', '";
        }

        query += values[values.length - 1] + "');";
        System.out.println(query);
        int a = stmt.executeUpdate(query);

        if (a > 0) {
            System.out.println("additon success");
        } else {
            System.out.println("Failed");
        }
    }

    public void printTable(String tableName) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + ";");
            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnLabel(i + 1) + "\t");
            }
            System.out.println();

            while (rs.next()) {
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    System.out.print(rs.getString(i + 1) + "\t");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Exception during select(printing all data)");
        }
    }

    public void updateTable(String tableName, String columnName, String newValue, String condition) {
        try {
            Statement stmt = connection.createStatement();
            String updateQuery = "UPDATE " + tableName + " SET " + columnName + " = '" + newValue + "' WHERE "
                    + condition + " ;";
            int a = stmt.executeUpdate(updateQuery);
            if (a > 0) {
                System.out.println("Updation of " + tableName + " successfull");
            } else {
                System.out.println("Updation failed");
            }
        } catch (SQLException ex) {
            System.out.println("Exception during updation");
        }
    }

    public void deleteRows(String tableName, String condition) {
        try {
            Statement stmt = connection.createStatement();
            String deleteQuery = "DELETE FROM " + tableName + " WHERE " + condition + " ;";
            int a = stmt.executeUpdate(deleteQuery);
            if (a > 0) {
                System.out.println("Deletion in " + tableName + " successfull");
            } else {
                System.out.println("Deletion failed");
            }
        } catch (SQLException ex) {
            System.out.println("Exception during deletion");
        }
    }

    public ResultSet getEntireRows(String tableName) {
        ResultSet rs = null;
        try {
            Statement stmt = connection.createStatement();
            System.out.println("SELECT * FROM " + tableName + ";");
            rs = stmt.executeQuery("SELECT * FROM " + tableName + ";");

        } catch (SQLException ex) {
            System.out.println("Exception during select(printing all data)");
        }

        return rs;
    }

    public ResultSet getEntireRows(String tableName, String orderByColumn, String ascOrDesc) {
        ResultSet rs = null;
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM " + tableName + " ORDER BY " + orderByColumn + " " + ascOrDesc + " ;";
            System.out.println(query);
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception during select(printing all data)");
        }

        return rs;
    }

    public String[] getAllColums(String tableName) {
        ArrayList<String> columnNames = new ArrayList<>();
        int i = 0;

        try {
            Statement stmt = connection.createStatement();
            String query = "select column_name from INFORMATION_SCHEMA.COLUMNS where table_name = '" + tableName.toLowerCase() + "';";
            System.out.println(query);

            ResultSet rs = stmt.executeQuery(query);

            System.out.println(query);
            while (rs.next()) {
                columnNames.add(rs.getString(1));
                i++;
            }

        } catch (SQLException ex) {
            System.out.println("Exception during select(printing all data)");
        }
        String[] names = new String[i];
        names = columnNames.toArray(names);
        return names;
    }

    public String getSingleResult(String tableName, String targetColumn, String whereClause) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select " + targetColumn + " from " + tableName + " where " + whereClause + ";";
        System.out.println(query);
        ResultSet rs = stmt.executeQuery(query);
        String result = null;

        while (rs.next()) {
            result = rs.getString(1);
        }

        return result;
    }

    public ResultSet getSingleRow(String tableName, String whereClause) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from " + tableName + " where " + whereClause + ";";
        System.out.println(query);
        ResultSet rs = stmt.executeQuery(query);

        return rs;
    }

    public String getBillAmount(String admnNo, int month) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select calc(" + String.valueOf(month) + ", '" + admnNo + "');";
        System.out.println(query);

        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        float amt = rs.getFloat(1);

        return String.valueOf(amt);
    }
}
