package messdatabase;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AddInmate extends javax.swing.JPanel {
    public MessDataBase mdb;
    
    public AddInmate() {
        mdb = new MessDataBase();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        inmateNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        blockComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        roomNoTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        admnNoTextField = new javax.swing.JTextField();
        addInmateBtn = new javax.swing.JButton();
        addInmateCancelBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        jLabel1.setText("Name");

        jLabel2.setText("Block");

        blockComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D" }));

        jLabel3.setText("Room No.");

        jLabel4.setText("Admn No.");

        addInmateBtn.setText("Add Inmate");
        addInmateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInmateBtnActionPerformed(evt);
            }
        });

        addInmateCancelBtn.setText("Cancel");
        addInmateCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInmateCancelBtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel5.setText("Add New Inmate");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addInmateBtn)
                .addGap(18, 18, 18)
                .addComponent(addInmateCancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(blockComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inmateNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(admnNoTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addComponent(roomNoTextField, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addGap(701, 701, 701))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inmateNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blockComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(admnNoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addInmateCancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(addInmateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void clearAllFields() {
        inmateNameTextField.setText("");
        admnNoTextField.setText("");
        roomNoTextField.setText("");
        blockComboBox.setSelectedIndex(0);
    }
    
    private void addInmateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInmateBtnActionPerformed
        try {
            String name = inmateNameTextField.getText();
            String admnNo = admnNoTextField.getText();
            char block = blockComboBox.getSelectedItem().toString().charAt(0);
            int roomNo = Integer.parseInt(roomNoTextField.getText().toString());
            mdb.addNewInmate(name, block, roomNo, admnNo);
            clearAllFields();
        } catch(NumberFormatException nfe) {
            System.err.println("Exception  during parsing int in room no field");
            JOptionPane.showMessageDialog(this, "Enter data in correct format");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Uanble to add data to database");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_addInmateBtnActionPerformed

    private void addInmateCancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInmateCancelBtnActionPerformed
        clearAllFields();
    }//GEN-LAST:event_addInmateCancelBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addInmateBtn;
    private javax.swing.JButton addInmateCancelBtn;
    private javax.swing.JTextField admnNoTextField;
    private javax.swing.JComboBox<String> blockComboBox;
    private javax.swing.JTextField inmateNameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField roomNoTextField;
    // End of variables declaration//GEN-END:variables
}
