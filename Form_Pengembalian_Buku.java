package Tugas5_Perpustakaan_Anggreany_221141;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Form_Pengembalian_Buku extends javax.swing.JFrame {
    Koneksi koneksi=new Koneksi();
    private Connection con;
    Statement stm;
    ResultSet rst;
    String sql;
    DefaultTableModel dtm;
    
    public Form_Pengembalian_Buku() {
        initComponents();
        kosong();
        LoadNomor();
        TampilDataMhsPadaTabel();
        
        jDateChooser2.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if("date".equals(evt.getPropertyName())){
                    Hitunglambat();
                }
            }
        });
    }

    private void kosong(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
    }
    
    private void LoadNomor(){
        try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "SELECT DISTINCT nomor_anggota FROM peminjaman_buku buku WHERE tanggal_pengembalian is null";
            rst = stm.executeQuery(sql);
            while (rst.next()) {
                jComboBox1.addItem(rst.getString("nomor_anggota"));
            }
        } catch (SQLException e) {
            System.out.println("koneksi gagal 5" + e.toString());
        }
    }
    
    private void LoadKodeBuku(){
        try {
            int n = jComboBox2.getItemCount();
            for(int i=n-1; i>0; i--){
                jComboBox2.removeItemAt(i);
            }
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "SELECT * FROM peminjaman_buku buku WHERE nomor_anggota = '"+jComboBox1.getSelectedItem()+"' and tanggal_pengembalian is null";
            rst = stm.executeQuery(sql);
            while (rst.next()) {
                jComboBox2.addItem(rst.getString("kode_buku"));
            }
        } catch (SQLException e) {
            System.out.println("koneksi gagal 5" + e.toString());
        }
    }
    
    private void Hitunglambat(){
        String tglpinjam = "0000-00-00";
        String Tampilan = "yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String tglkembali  = String.valueOf(Fm.format(jDateChooser2.getDate()));
        try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "select * from peminjaman_buku where nomor_anggota = '"+jComboBox1.getSelectedItem()+"' and kode_buku = '"+jComboBox2.getSelectedItem()+"'";
            rst = stm.executeQuery(sql);
            if (rst.next()) {
                tglpinjam = rst.getString("tanggal_peminjaman");
            }
            jTextField1.setEditable(false);
            LocalDate date1 = LocalDate.parse(tglpinjam);
            LocalDate date2 = LocalDate.parse(tglkembali);
            if(ChronoUnit.DAYS.between(date1, date2)>6){
                int keterlambatan = (int) ChronoUnit.DAYS.between(date1, date2) - 6;
                jTextField3.setText(String.valueOf(keterlambatan));
                jTextField4.setText(String.valueOf(keterlambatan*10000));
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
    
    private void tampilModelJTabel() {
        try {
            String[] kolom = {"Nomor Anggota", "Nama Anggota", "Kode Buku", "Judul Buku", "Tanggal Pinjam", "tanggal pengembalian", "Denda"};
            dtm = new DefaultTableModel(null, kolom) {
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {

                    return false;
                }
            };

            jTable1.setModel(dtm);
            aturJTable(jTable1, new int[]{100, 100, 100, 100, 100, 100, 100});
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

                sql = "SELECT peminjaman_buku.nomor_anggota, data_mahasiswa.nama, peminjaman_buku.kode_buku, buku.judul, peminjaman_buku.tanggal_peminjaman, peminjaman_buku.tanggal_pengembalian, peminjaman_buku.denda  from peminjaman_buku "
                        + "left outer join data_mahasiswa on(peminjaman_buku.nomor_anggota=data_mahasiswa.nim) "
                        + "left outer join buku on(peminjaman_buku.kode_buku=buku.kode) where peminjaman_buku.tanggal_pengembalian is not null";
                  rst = stm.executeQuery(sql);
                  while (rst.next()) {
                    dtm.addRow(new Object[]{
                        rst.getString("nomor_anggota"),
                        rst.getString("nama"),
                        rst.getString("kode_buku"), 
                        rst.getString("judul"),
                        rst.getString("tanggal_peminjaman"),
                        rst.getString("tanggal_pengembalian"),
                        rst.getString("denda")
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));

        jLabel1.setText("Nomor Anggota");

        jTextField1.setText("jTextField1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Kode Buku");

        jLabel3.setText("Tanggal Pinjam");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField2.setText("jTextField2");

        jLabel4.setText("Tanggal Kembali");

        jLabel5.setText("Keterlambatan");

        jLabel6.setText("Denda");

        jTextField3.setText("jTextField3");

        jLabel7.setText("Hari");

        jTextField4.setText("jTextField4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                            .addComponent(jTextField2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addGap(0, 45, Short.MAX_VALUE))
                            .addComponent(jTextField4))))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 255, 255));

        jLabel8.setText("Cari");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "nama", "judul" }));

        jTextField5.setText("jTextField5");

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(9, 9, 9)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            con = koneksi.getKoneksiDatabase();
            stm = null;
            sql = "delete from peminjaman_buku where nomor_anggota = '" + jComboBox1.getSelectedItem() +"' and kode_buku='"+jComboBox2.getSelectedItem()+"' ";
 

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
            kosong();
            jComboBox1.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);
            jComboBox3.setSelectedIndex(0);

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal 8" + e.toString());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        LoadKodeBuku();
        String Kode = "";
        try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "SELECT peminjaman_buku.nomor_anggota,data_mahasiswa.nama FROM peminjaman_buku "
                    + "LEFT OUTER JOIN data_mahasiswa ON(peminjaman_buku.nomor_anggota=data_mahasiswa.nim) "
                    + "WHERE tanggal_pengembalian is null and nomor_anggota = '"+jComboBox1.getSelectedItem().toString()+"'";
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
        try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "select * from peminjaman_buku where nomor_anggota = '"+jComboBox1.getSelectedItem()+"' and kode_buku = '"+jComboBox2.getSelectedItem()+"'";
            rst = stm.executeQuery(sql);
            if (rst.next()) {;
                jDateChooser1.setDate(rst.getDate("tanggal_peminjaman"));
            }
            String Kode = "";
            try {
                con = koneksi.getKoneksiDatabase();
                stm = con.createStatement();
                sql = "select * from buku where kode = '"+jComboBox2.getSelectedItem()+"'";
                rst = stm.executeQuery(sql);
                while (rst.next()) {
                    Kode = rst.getString("judul");
                }
                jTextField2.setText(Kode);
                jTextField2.setEditable(false);
            } catch (SQLException e) {
                System.out.println("koneksi gagal 5" + e.toString());
            }
        } catch (SQLException e) {
            System.out.println("koneksi gagal 5" + e.toString());
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String Tampilan = "yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String Tanggal = String.valueOf(Fm.format(jDateChooser2.getDate()));
        try {
            con = koneksi.getKoneksiDatabase();
            stm = null;
            sql = "update peminjaman_buku set tanggal_pengembalian = '"+Tanggal+"', denda = '"+jTextField4.getText()+"' where nomor_anggota = '"+jComboBox1.getSelectedItem()+"' and kode_buku = '"+jComboBox2.getSelectedItem()+"'";
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
            kosong();
            jComboBox1.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal 3" + e.toString());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String Tampilan = "yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String Tanggal = String.valueOf(Fm.format(jDateChooser2.getDate()));
        try {
            con = koneksi.getKoneksiDatabase();
            stm = null;
            sql = "update peminjaman_buku set tanggal_pengembalian = '"+Tanggal+"', denda = '"+jTextField4.getText()+"' where nomor_anggota = '"+jComboBox1.getSelectedItem()+"' and kode_buku = '"+jComboBox2.getSelectedItem()+"'";
            stm = con.createStatement();
            int AdaPenambahanRecord = stm.executeUpdate(sql);
            TampilDataMhsPadaTabel();
            if (AdaPenambahanRecord > 0) {
                JOptionPane.showMessageDialog(this, "Berhasil mengedit", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Berhasil mengedit", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
            stm.close();
            kosong();
            jComboBox1.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal 3" + e.toString());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        TampilDataMhsPadaTabel();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {
            String cari="";
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            tampilModelJTabel();
            try {
                if(jComboBox3.getSelectedItem()=="nama"){
                    cari = "data_mahasiswa.nama";
                }
                else if(jComboBox3.getSelectedItem()=="judul"){
                    cari = "buku.judul";
                }

                sql = "SELECT peminjaman_buku.nomor_anggota, data_mahasiswa.nama, peminjaman_buku.kode_buku, buku.judul, peminjaman_buku.tanggal_peminjaman, peminjaman_buku.tanggal_pengembalian, peminjaman_buku.denda  from peminjaman_buku "
                        + "left outer join data_mahasiswa on(peminjaman_buku.nomor_anggota=data_mahasiswa.nim) "
                        + "left outer join buku on(peminjaman_buku.kode_buku=buku.kode) where peminjaman_buku.tanggal_pengembalian is not null and "+cari+" LIKE '%"+jTextField5.getText()+"%'";
                  rst = stm.executeQuery(sql);
                  while (rst.next()) {
                    dtm.addRow(new Object[]{
                        rst.getString("nomor_anggota"),
                        rst.getString("nama"),
                        rst.getString("kode_buku"), 
                        rst.getString("judul"),
                        rst.getString("tanggal_peminjaman"),
                        rst.getString("tanggal_pengembalian"),
                        rst.getString("denda")
                        });
                    jTable1.setModel(dtm);
                }
            } catch (Exception e) {
                System.out.println("Ada Kesalahan 1" + e.toString());
            }
        } catch (SQLException e) {
            System.out.println("koneksi gagal 1" + e.toString());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Form_Pengembalian_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Pengembalian_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Pengembalian_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Pengembalian_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Pengembalian_Buku().setVisible(true);
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
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
