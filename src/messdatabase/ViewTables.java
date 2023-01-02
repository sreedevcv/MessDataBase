package messdatabase;

import javax.swing.JTable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ViewTables extends javax.swing.JPanel {

    private String selectedTable = "inmates";
    public MessDataBase mdb;

    public ViewTables() {
        mdb = new MessDataBase();
        initComponents();
        tableSelectionBox.setModel(new javax.swing.DefaultComboBoxModel<>(DataBase.Tables));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableSelectionBox = new javax.swing.JComboBox<>();
        columnSelectionBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        viewBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        tableSelectionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tableSelectionBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableSelectionBoxActionPerformed(evt);
            }
        });

        columnSelectionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("Tables");

        viewBtn.setText("View");
        viewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtnActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 483, Short.MAX_VALUE)
                        .addComponent(tableSelectionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(columnSelectionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(viewBtn)))
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tableSelectionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(columnSelectionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(viewBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableSelectionBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableSelectionBoxActionPerformed
        String newTable = tableSelectionBox.getSelectedItem().toString();

        if (selectedTable != newTable) {
            selectedTable = newTable;
            updateColumnSelectionBox();
        }
    }//GEN-LAST:event_tableSelectionBoxActionPerformed

    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
        createNewJTable();
    }//GEN-LAST:event_viewBtnActionPerformed

    public JTable resultSetToJTable(ResultSet rs) {
        String[] tableRow;
        String[][] tableData;
        ArrayList<String[]> tableBody = new ArrayList<>();
        JTable jTable = null;

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] tableHeader = new String[rsmd.getColumnCount()];

            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                tableHeader[i] = rsmd.getColumnLabel(i + 1);
            }

            while (rs.next()) {
                tableRow = new String[rsmd.getColumnCount()];
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    tableRow[i] = rs.getString(i + 1);
                }
                tableBody.add(tableRow);
            }

            tableData = new String[tableBody.size()][rsmd.getColumnCount()];
            tableData = tableBody.toArray(tableData);
            jTable = new JTable(tableData, tableHeader);
        } catch (SQLException ex) {
            System.out.println("Error during converting result set to JTable");
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jTable;
    }

    public void updateColumnSelectionBox() {
        String[] columnNames = mdb.getAllColums(selectedTable);
        columnSelectionBox.setModel(new javax.swing.DefaultComboBoxModel<>(columnNames));
    }

    public void createNewJTable() {
        String tableName = tableSelectionBox.getSelectedItem().toString();
        String columnName = columnSelectionBox.getSelectedItem().toString();
        ResultSet rs = mdb.getEntireRows(tableName, columnName, "asc");
        
        table = resultSetToJTable(rs);
        jScrollPane1.setViewportView(table);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> columnSelectionBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JComboBox<String> tableSelectionBox;
    private javax.swing.JButton viewBtn;
    // End of variables declaration//GEN-END:variables
}
