package messdatabase;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Main extends javax.swing.JFrame {
//    public MessDataBase mdb;
    private final String dbUrl = "jdbc:postgresql://localhost/testdb2";
    private final String user = "dbmsprj";
    private final String pass = "secret";
    private Connection connection = null;
    
    public Main() {
        establishConnection();
//        mdb = new MessDataBase(connection);
        initComponents();
    }
    
    public Connection getConnection() {
        return connection;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        addInmate2 = new messdatabase.AddInmate();
        addProduct1 = new messdatabase.AddProduct();
        addStock2 = new messdatabase.AddStock();
        removeStock1 = new messdatabase.RemoveStock();
        calculateMessBill1 = new messdatabase.CalculateMessBill();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 700));

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.addTab("tab1", addInmate2);
        jTabbedPane1.addTab("tab3", addProduct1);
        jTabbedPane1.addTab("tab4", addStock2);
        jTabbedPane1.addTab("tab5", removeStock1);
        jTabbedPane1.addTab("tab5", calculateMessBill1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void establishConnection() {
        try {
            connection = DriverManager.getConnection(dbUrl, user, pass);
        } catch (SQLException e) {
            System.out.println("Exception during establishing database connection");
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
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private messdatabase.AddInmate addInmate2;
    private messdatabase.AddProduct addProduct1;
    private messdatabase.AddStock addStock2;
    private messdatabase.CalculateMessBill calculateMessBill1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private messdatabase.RemoveStock removeStock1;
    // End of variables declaration//GEN-END:variables
}
