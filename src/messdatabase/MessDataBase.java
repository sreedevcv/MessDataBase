package messdatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Connection;

public class MessDataBase extends DataBase {

    public MessDataBase(Connection c) {
        super(c);
    }

    public void addNewInmate(String inmateName, char block, int roomNo, String admnNo) throws SQLException {
        this.insertIntoTable("Inmates", String.valueOf(block), String.valueOf(roomNo), admnNo);
    }

    public void addNewProduct(String PName, int price) throws SQLException {
        this.insertIntoTable("Products", PName, String.valueOf(price));
    }
    
    public ArrayList<String> getProductList() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        ResultSet rs = this.getEntireRows("Products", "PName", "asc");
        
        while(rs.next()) {
            list.add(rs.getString(2));
        }
        
        return list;
    }
    
    public void addNewItemToStocks(String pName, int quantity) throws SQLException {
        String pidStr = this.getSingleResult(pName, "PId", "PName = " + pName);
        this.insertIntoTable("Stocks", pidStr, String.valueOf(quantity));
    }
    
    public void removeStock(String pName, int quantity) throws SQLException {
        String pid = this.getSingleResult("Outgoing", "PId", "PName = " + pName);
        this.insertIntoTable("Outgoing", pid, String.valueOf(quantity));
    }
    
    public String generatePreview(String admnNo) throws SQLException {
        ResultSet details = this.getSingleRow("Inmates", "admnNo = " + admnNo);
        
        if(details != null) {
            String preview = "Name: ";
            preview += details.getString(2);
            preview += "\nRoom No: ";
            preview += details.getString(3);
            preview += "\nBlock: ";
            preview += details.getString(4);
            preview += "\nAttendence: ";
            preview += details.getString(5);
            preview += "\n\nMess Bill: ";

            return preview;
        }
        else
            return null;
    }
    
}
