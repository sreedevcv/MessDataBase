package messdatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class MessDataBase extends DataBase {

    public MessDataBase() {
//        this.establishConnection();
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
//        int pid = Integer.parseInt(pidStr);
        this.insertIntoTable("Stocks", pidStr, String.valueOf(quantity));
    }

}
