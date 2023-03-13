package messdatabase;

public class Main extends javax.swing.JFrame {

    public MessDataBase mdb;

    public Main() {
        mdb = new MessDataBase();
        mdb.establishConnection();
        initComponents();

        init();
    }
    
    private void init() {
        jTabbedPane1.removeAll();
        jTabbedPane1.addTab("Inmates", addInmate1);
        jTabbedPane1.addTab("Products", addProduct1);
        jTabbedPane1.addTab("Add Stock", addStock1);
        jTabbedPane1.addTab("Remove Stock",removeStock1);
        jTabbedPane1.addTab("Mess Bill", calculateMessBill1);
        jTabbedPane1.addTab("Tables", viewTables1);
        
        addInmate1.mdb.connection = mdb.connection;
        addProduct1.mdb.connection = mdb.connection;
        calculateMessBill1.mdb.connection = mdb.connection;
        addStock1.mdb.connection = mdb.connection;
        removeStock1.mdb.connection = mdb.connection;
        viewTables1.mdb.connection = mdb.connection;
        
        addProduct1.addStock = addStock1;
        addProduct1.removeStock = removeStock1;
        
        addStock1.loadProducts();
        removeStock1.loadProducts();
        viewTables1.updateColumnSelectionBox();
        viewTables1.createNewJTable();
        
        this.setTitle("Mess Inventory Management System");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        addProduct1 = new messdatabase.AddProduct();
        calculateMessBill1 = new messdatabase.CalculateMessBill();
        addInmate1 = new messdatabase.AddInmate();
        removeStock1 = new messdatabase.RemoveStock();
        viewTables1 = new messdatabase.ViewTables();
        addStock1 = new messdatabase.AddStock();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        addProduct1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.lightGray));
        jTabbedPane1.addTab("tab3", addProduct1);
        jTabbedPane1.addTab("tab5", calculateMessBill1);
        jTabbedPane1.addTab("tab4", addInmate1);
        jTabbedPane1.addTab("tab5", removeStock1);
        jTabbedPane1.addTab("tab6", viewTables1);
        jTabbedPane1.addTab("tab7", addStock1);

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


    private void removeStock1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_removeStock1ComponentShown
        removeStock1.loadProducts();
    }//GEN-LAST:event_removeStock1ComponentShown

    private void addStock1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_addStock1ComponentShown
        System.out.println("Hello");
        addStock1.loadProducts();
    }//GEN-LAST:event_addStock1ComponentShown

    public static void main(String args[]) {

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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private messdatabase.AddInmate addInmate1;
    private messdatabase.AddProduct addProduct1;
    private messdatabase.AddStock addStock1;
    private messdatabase.CalculateMessBill calculateMessBill1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private messdatabase.RemoveStock removeStock1;
    private messdatabase.ViewTables viewTables1;
    // End of variables declaration//GEN-END:variables
}
