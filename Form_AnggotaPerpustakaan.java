
package Tugas5_Perpustakaan_Anggreany_221141;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Form_AnggotaPerpustakaan extends javax.swing.JFrame {

    Koneksi koneksi=new Koneksi();
    private Connection con;
    Statement stm;
    ResultSet rst;
    String sql;
    DefaultTableModel dtm;
    
    public Form_AnggotaPerpustakaan() {
        initComponents();
        TampilDataMhsPadaTabel();
        KosongkanObjek();
        loadDataCari();
        loadDataNomor();
    }
    
    void KosongkanObjek() {
        jTextField1.setText("");
        jTextField2.setText("");
        
        jTextField1.requestFocus();
    }
    
    private void loadDataNomor(){
        String Kode = "";
        try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "select * from data_mahasiswa";
            rst = stm.executeQuery(sql);
            while (rst.next()) {
                jComboBox1.addItem(rst.getString("nim"));
            }
        } catch (SQLException e) {
            System.out.println("koneksi gagal 5" + e.toString());
        }
    }
    
    

    private void aturJTable(JTable Lihat, int Lebar[]){
        try{
            Lihat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            int banyak=Lihat.getColumnCount();
            for(int i = 0; i < banyak; i++){
            TableColumn Kolom = Lihat.getColumnModel().getColumn(i);
            Kolom.setPreferredWidth(Lebar[i]);
            Lihat.setRowHeight(20);
        }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Salah" + e);
        }
    }
    
    private void loadDataCari(){
    try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "select * from anggota_perpustakaan";
            rst = stm.executeQuery(sql);
            
            ResultSetMetaData metaData = rst.getMetaData();
            int columnCount = metaData.getColumnCount();
                    
            for (int i=1; i<=columnCount; i++){
                String columnName = metaData.getColumnName(i);
                jComboBox2.addItem(columnName);
            }
        } catch (SQLException e) {
            System.out.println("koneksi gagal 5" + e.toString());
        }
    }
    
    
    private void tampilModelJTabel() {
        try {
            String[] kolom = {"Nomor Anggota", "Nama-Nama Anggota", "Tanggal Masuk"};
            dtm = new DefaultTableModel(null, kolom) {
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {

                    return false;
                }
            };

            jTable1.setModel(dtm);
            aturJTable(jTable1, new int[]{100, 300, 300});
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "salah" + e);
        }

    }
    
    private void tampilModelJTabel1() {
        try {
            String[] kolom = {"Nomor Anggota", "Tanggal Masuk"};
            dtm = new DefaultTableModel(null, kolom) {
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {

                    return false;
                }
            };

            jTable1.setModel(dtm);
            aturJTable(jTable1, new int[]{100, 300});
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "salah" + e);
        }

    }
    
    void TampilDataMhsPadaTabel() {
        try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            tampilModelJTabel();
            try {

                sql = "SELECT anggota_perpustakaan.nomor, data_mahasiswa.nama as namamhs, anggota_perpustakaan.tanggal_masuk as tanggal_masuk_anggota from anggota_perpustakaan "
                        + "LEFT OUTER JOIN data_mahasiswa ON (anggota_perpustakaan.nomor=data_mahasiswa.nim) ";
                  rst = stm.executeQuery(sql);
                  while (rst.next()) {
                    dtm.addRow(new Object[]{
                        rst.getString("nomor"),
                        rst.getString("namamhs"), 
                        rst.getString("tanggal_masuk_anggota"), 
                        });
                    jTable1.setModel(dtm);
                }
            } catch (Exception e) {
                System.out.println("Ada Kesalahan 1" + e.toString());
            }
        } catch (SQLException e) {
            System.out.println("koneksi gagal 1" + e.toString());
        }
    }
    
    void cariDataMahasiswa(){
        try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            tampilModelJTabel1();
            try {

                sql = "SELECT anggota_perpustakaan.nomor, data_mahasiswa.nama as namamhs, anggota_perpustakaan.tanggal_masuk as tanggal_masuk_anggota from anggota_perpustakaan "
                        + "LEFT OUTER JOIN data_mahasiswa ON (anggota_perpustakaan.nomor=data_mahasiswa.nim) WHERE anggota_perpustakaan."+ jComboBox2.getSelectedItem() +" LIKE '%" + jTextField2.getText() + "%' ";
                System.out.println(jComboBox2.getSelectedItem()); 
                  rst = stm.executeQuery(sql);
                  while (rst.next()) {
                    dtm.addRow(new Object[]{
                        rst.getString("nomor"),
                        rst.getString("namamhs"),
                        rst.getString("tanggal_masuk"), 
                        });
                    jTable1.setModel(dtm);
                }
            } catch (Exception e) {
                System.out.println("Ada Kesalahan " + e.toString());
            }
        } catch (SQLException e) {
            System.out.println("koneksi gagal 2" + e.toString());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Batal");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilihan]" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Nomor Anggota");

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Tanggal Masuk");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(176, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 255, 255));

        jLabel3.setText("Cari");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilihan]", "nama", "nim", " " }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField2.setText("jTextField2");

        jButton6.setText("Cari");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Refresh");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton5.setText("Exit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String Tampilan = "yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String TanggalLahir = String.valueOf(Fm.format(jDateChooser1.getDate()));
        try {
            con = koneksi.getKoneksiDatabase();
            stm = null;
            sql = "insert into anggota_perpustakaan (id, nomor, tanggal_masuk, last_update, userid) VALUES (NULL,'" + jComboBox1.getSelectedItem() + "','" + TanggalLahir + "',NOW(),'ADMIN')";
            stm = con.createStatement();
            int AdaPenambahanRecord = stm.executeUpdate(sql);
            TampilDataMhsPadaTabel();
            if (AdaPenambahanRecord > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Tersimpan",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Data Gagal Tersimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
            stm.close();
            KosongkanObjek();
            jComboBox1.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal 3" + e.toString());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String Tampilan = "yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String TanggalLahir = String.valueOf(Fm.format(jDateChooser1.getDate()));
        try {

            con = koneksi.getKoneksiDatabase();
            stm = null;
            
            sql = "update anggota_perpustakaan set tanggal_masuk = '" + TanggalLahir + "' where nomor = '" + jComboBox1.getSelectedItem() + "' ";

            stm = con.createStatement();
            int AdaPerubahanRecord = stm.executeUpdate(sql);
            TampilDataMhsPadaTabel();
            if (AdaPerubahanRecord > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Di Edit",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
                jButton1.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Data Gagal Di Edit",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
            stm.close();
            KosongkanObjek();
            jComboBox2.setSelectedIndex(0);

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal 7" + e.toString());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            con = koneksi.getKoneksiDatabase();
            stm = null;
            sql = "delete from anggota_perpustakaan where nomor = '" + jComboBox1.getSelectedItem() +"' ";
 

            stm = con.createStatement();
            int AdaPerubahanRecord = stm.executeUpdate(sql);
            TampilDataMhsPadaTabel();
            if (AdaPerubahanRecord > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Di Hapus",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
                jButton1.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Data Gagal Di Hapus",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
            stm.close();
            KosongkanObjek();
            jComboBox1.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal 8" + e.toString());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        KosongkanObjek();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        try {
            con = koneksi.getKoneksiDatabase();
            

            sql = "Select * from anggota_perpustakaan where nomor = '" + jTextField1.getText() + "' ";
            stm = con.createStatement();
            rst = stm.executeQuery(sql);
            if (rst.next()) {
                JOptionPane.showMessageDialog(this, "Nomor Anggota Tersebut Sudah Ada Silahkan Input Nomor Anggota Lain Atau Data Mau Di Edit", "Informasi ", JOptionPane.INFORMATION_MESSAGE);

                jButton1.setEnabled(false);
                jTextField1.setText(rst.getString("nama_anggota"));
                jDateChooser1.setDate(rst.getDate("tanggal_masuk"));
                jComboBox1.setSelectedItem(rst.getString("nomor"));
                
            } else {
                jButton1.setEnabled(true);
                jTextField2.setText("");
                jComboBox1.setSelectedIndex(0);
                jComboBox2.setSelectedIndex(0);
                jTextField1.requestFocus();
            }

        } catch (SQLException e) {
            System.out.println("koneksi gagal 6" + e.toString());
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        cariDataMahasiswa();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        TampilDataMhsPadaTabel();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String Kode = "";
        try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "select * from data_mahasiswa where nim = '"+jComboBox1.getSelectedItem().toString()+"'";
            rst = stm.executeQuery(sql);
            while (rst.next()) {
                Kode = rst.getString("nama");
            }
            jTextField1.setText(Kode);
            jTextField1.setEditable(false);
        } catch (SQLException e) {
            System.out.println("koneksi gagal 5" + e.toString());
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    
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
            java.util.logging.Logger.getLogger(Form_AnggotaPerpustakaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_AnggotaPerpustakaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_AnggotaPerpustakaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_AnggotaPerpustakaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_AnggotaPerpustakaan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
