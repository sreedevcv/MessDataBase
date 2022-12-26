package messdatabase;

public class MessDataBase extends DataBase {

    public MessDataBase() {
//        this.establishConnection();
    }
    
    public void addNewInmate(String inmateName, char block, int roomNo, String admnNo) {
        this.insertIntoTable("Inmates", String.valueOf(block), String.valueOf(roomNo), admnNo);
    }
    
    public void addNewProduct(String PName, int price) {
        this.insertIntoTable("Products", PName, String.valueOf(price));
    }
    
}
