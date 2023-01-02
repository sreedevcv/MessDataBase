package messdatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import org.postgresql.util.PSQLException;

public class MessDataBase extends DataBase {

    public void addNewInmate(String inmateName, char block, int roomNo, String admnNo) throws SQLException {
        this.insertIntoTable("Inmates", admnNo, inmateName, String.valueOf(block), String.valueOf(roomNo));
    }

    public void addNewProduct(String PName, int price) throws SQLException {
        Statement stmt = super.connection.createStatement();
        stmt.execute("insert into products (pname, price) values ('" + PName + "', " + String.valueOf(price) + ");");
    }

    public ArrayList<String> getProductList() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        ResultSet rs = this.getEntireRows("Products", "PName", "asc");

        while (rs.next()) {
            list.add(rs.getString(2));
        }

        return list;
    }

    public ArrayList<String> getInmateList() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        ResultSet rs = this.getEntireRows("Inmates", "Name", "asc");

        while (rs.next()) {
            list.add(rs.getString(1));
        }

        return list;
    }

    public void addNewItemToStocks(String pName, int quantity) throws SQLException {
        String pidStr = this.getSingleResult("Products", "PId", "PName = '" + pName + "'");
        this.insertIntoTable("Stocks", pidStr, String.valueOf(quantity));
    }

    public void removeStock(String pName, int quantity, String date) throws SQLException {
        String pid = this.getSingleResult("Products", "PId", "PName = '" + pName + "'");
        Statement stmt = super.connection.createStatement();
        stmt.execute("insert into Outgoing (PId, outdate, quantity) values ('" + pid + "', '" + date + "', " + String.valueOf(quantity) + ");");
    }


    public String[] generatePreview(String admnNo, int month) throws SQLException, PSQLException {
        ResultSet details = this.getSingleRow("Inmates", "admnNo = '" + admnNo + "'");

        details.next();
        if (details != null) {

            String[] preview = new String[5];
            preview[0] = details.getString(2);  // Name
            preview[1] = details.getString(4);  // Room No.
            preview[2] = details.getString(3);  // Block
            preview[3] = details.getString(5);  // Attendence
            preview[4] = this.getBillAmount(admnNo, month);  // Bill Amount
            return preview;
        } else {
            return null;
        }
    }

    public ArrayList<String[]> generateBillDetailsForAll() throws SQLException {
        ResultSet rs = this.getEntireRows("Inmates");
        ArrayList<String[]> details = new ArrayList<>();
        String[] row = new String[4];

        while (rs.next()) {
            row[0] = rs.getString(2);
            row[1] = rs.getString(4);
            row[2] = rs.getString(3);
            row[3] = rs.getString(5);
            details.add(row);
        }

        return details;
    }
}
