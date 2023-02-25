package messdatabase;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class CalculateMessBill extends javax.swing.JPanel {

    public MessDataBase mdb;
    String[] billDetails;
    LocalDate date;

    public CalculateMessBill() {
        mdb = new MessDataBase();
        initComponents();
        datePicker1.setDateToToday();
    }

    public void generateMessBill(String[] data) throws NullPointerException {
        try {
            LocalDate d = datePicker1.getDate();
            String dateString = " " + d.getMonth().toString().substring(0, 3) + d.getYear() ;
            System.out.println(dateString);
            Document document = new Document();
            File f = new File("/home/sreedev/Downloads/bills/" + data[0] + dateString + ".pdf");
            PdfWriter.getInstance(document, new FileOutputStream(f));

            document.open();
            Font font1 = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, BaseColor.RED);
            Font font2 = FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.BLACK);
            Paragraph p1 = new Paragraph("Mess Bill For " + date.getMonth() + " " + date.getYear(), font1);
            String details = "";
            details += "\nName          : " + data[0] + "\n";
            details += "Room No.   : " + data[1] + "\n";
            details += "Block          : " + data[2] + "\n";
            details += "Attendence : " + data[3] + "\n";
            details += "Total bill     : " + data[4] + "\n";
            Paragraph p2 = new Paragraph(details, font2);

            document.add(p1);
            document.add(p2);

            document.close();

        } catch (FileNotFoundException ex) {
            System.err.println("PDF error: FOF");
        } catch (DocumentException ex) {
            System.err.println("PDF error: FOF");

        }
    }

    public void generateReport() {
        try {
            LocalDate d = datePicker1.getDate();
            Document document = new Document();
            String dateString = " " + d.getMonth().toString().substring(0, 3) + d.getYear() ;
            System.out.println(dateString);
            File f = new File("/home/sreedev/Downloads/bills/REPORT" + dateString + ".pdf");
            PdfWriter.getInstance(document, new FileOutputStream(f));
            String[] colHeader = {"Name", "Room", "Block", "Attendence", "Amount"};

            String reportHeader = "MESS BILL FOR " + d.getMonth() + " " + d.getYear() + "\n";
            Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, BaseColor.RED);
            Paragraph p = new Paragraph(reportHeader, font);
            PdfPTable table = new PdfPTable(5);
            float sum = 0;

            font.setSize((float) 15);
            table.setWidthPercentage((float) 95.0);
            document.open();

            Stream.of(colHeader)
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setBorderWidth(1);
                        header.setPhrase(new Phrase(columnTitle, font));
                        table.addCell(header);
                    });

            Iterator<String> iter = mdb.getInmateList().iterator();
            String[] row;

            while (iter.hasNext()) {
                row = mdb.generatePreview(iter.next(), d.getMonthValue());
                for (String s : row) {
                    table.addCell(s);
                }
                sum += Float.parseFloat(row[4]);
            }

            document.add(p);
            document.add(new Paragraph("\n\n"));
            document.add(table);
            font.setColor(BaseColor.BLACK);
            document.add(new Paragraph("\n\nTotal cost for the month:"));
            font.setColor(BaseColor.RED);
            document.add(new Paragraph("Rs. " + String.valueOf(sum), font));
            document.close();

        } catch (FileNotFoundException ex) {
            System.err.println("PDF error: FOF");
        } catch (DocumentException ex) {
            System.err.println("PDF error: FOF");
        } catch (SQLException ex) {
            Logger.getLogger(CalculateMessBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        admnNoField = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        billCalculateBtn = new javax.swing.JButton();
        billGenerateBtn = new javax.swing.JButton();
        generateForAllBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        nameLabel = new javax.swing.JLabel();
        blockLabel = new javax.swing.JLabel();
        roomLabel = new javax.swing.JLabel();
        attLabel = new javax.swing.JLabel();
        amountLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jLabel1.setText("Calculate Mess Bill");

        jLabel2.setText("Admn No.");

        billCalculateBtn.setText("Calculate");
        billCalculateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billCalculateBtnActionPerformed(evt);
            }
        });

        billGenerateBtn.setText("Generate");
        billGenerateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billGenerateBtnActionPerformed(evt);
            }
        });

        generateForAllBtn.setText("Generate Report");
        generateForAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateForAllBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Date");

        jLabel4.setText("Name");

        jLabel5.setText("Room");

        jLabel6.setText("Block");

        jLabel7.setText("Attendence");

        jLabel8.setText("Amount");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(generateForAllBtn)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(amountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(attLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(blockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(billCalculateBtn)
                    .addComponent(billGenerateBtn)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(admnNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(datePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(535, 661, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(admnNoField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(billCalculateBtn)
                .addGap(26, 26, 26)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blockLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(attLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(billGenerateBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(generateForAllBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void billCalculateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billCalculateBtnActionPerformed
        String admnNo = admnNoField.getText();
        date = datePicker1.getDate();
        try {
            if (admnNo != null) {
                billDetails = mdb.generatePreview(admnNo, date.getMonthValue());
            }
            String l = "";
            for (String s : billDetails) {
                l += s + "\n";
            }

            System.out.println(l);

            nameLabel.setText(billDetails[0]);
            roomLabel.setText(billDetails[1]);
            blockLabel.setText(billDetails[2]);
            attLabel.setText(billDetails[3]);
            amountLabel.setText(billDetails[4]);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No such Inmate");
//            ex.printStackTrace();
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(this, "No such Inmate 2");
        }

    }//GEN-LAST:event_billCalculateBtnActionPerformed

    private void generateForAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateForAllBtnActionPerformed
        generateReport();
    }//GEN-LAST:event_generateForAllBtnActionPerformed

    private void billGenerateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billGenerateBtnActionPerformed
        try {
            generateMessBill(billDetails);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid inmate admission number");
        }
    }//GEN-LAST:event_billGenerateBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField admnNoField;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JLabel attLabel;
    private javax.swing.JButton billCalculateBtn;
    private javax.swing.JButton billGenerateBtn;
    private javax.swing.JLabel blockLabel;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private javax.swing.JButton generateForAllBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel roomLabel;
    // End of variables declaration//GEN-END:variables
}
