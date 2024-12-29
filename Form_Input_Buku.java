
package Tugas5_Perpustakaan_Anggreany_221141;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Form_Input_Buku extends javax.swing.JFrame {

    Koneksi koneksi=new Koneksi();
    private Connection con;
    Statement stm;
    ResultSet rst;
    String sql;
    DefaultTableModel dtm;
    
    public Form_Input_Buku() {
        initComponents();
        loadDataKategoriBuku();
        TampilDataMhsPadaTabel();
        KosongkanObjek();
        
        for(int i=1923; i<=2023; i++){
            jComboBox2.addItem(String.format("%2d", i));
            jComboBox3.addItem(String.format("%2d", i));
        }
    }
    void KosongkanObjek() {
    
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        
        jTextField1.requestFocus();
    }
    

    private void loadDataKategoriBuku(){
        try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "select * from kategori_buku";
            rst = stm.executeQuery(sql);
            while (rst.next()) {
                jComboBox1.addItem(rst.getString("kode"));
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
            String[] kolom = {"Kode buku", "kategori", "Judul","Jumlah","stock","Pengarang","Penerbit","Tahun terbit","Tahun Pengadaan","Sumber","Nomor rak","ISBN"};
            dtm = new DefaultTableModel(null, kolom) {
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {

                    return false;
                }
            };

            jTable1.setModel(dtm);
            aturJTable(jTable1, new int[]{100, 100, 100,100,100,100,100,100,100,100,100,100,100});
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

                sql = "SELECT buku.kode as kodebuku, kategori_buku.nama, buku.judul,buku.jumlah, buku.stock, buku.pengarang, buku.penerbit, buku.tahun_terbit, buku.tahun_pengadaan, buku.sumber, buku.rak, buku.ISBN "
                        + "from buku LEFT OUTER JOIN kategori_buku ON(kategori_buku.kode=buku.kode_kategori)";
                  rst = stm.executeQuery(sql);
                  while (rst.next()) {
                    dtm.addRow(new Object[]{
                        rst.getString("kodebuku"),
                        rst.getString("nama"),
                        rst.getString("judul"),
                        rst.getString("jumlah"),
                        rst.getString("stock"),
                        rst.getString("pengarang"),
                        rst.getString("penerbit"),
                        rst.getString("tahun_terbit"),
                        rst.getString("tahun_pengadaan"),
                        rst.getString("sumber"),
                        rst.getString("rak"),
                        rst.getString("ISBN"),
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
            tampilModelJTabel();
            try {
                sql = "SELECT buku.kode as kodebuku, kategori_buku.nama, buku.judul,buku.jumlah, buku.stock, buku.pengarang, buku.penerbit, buku.tahun_terbit, buku.tahun_pengadaan, buku.sumber, buku.rak, buku.ISBN "
                        + "from buku LEFT OUTER JOIN kategori_buku ON(kategori_buku.kode=buku.kode_kategori) where buku."+jComboBox4.getSelectedItem()+" LIKE '%"+jTextField9.getText()+"%'";
                  rst = stm.executeQuery(sql);
                  while (rst.next()) {
                    dtm.addRow(new Object[]{
                        rst.getString("kodebuku"),
                        rst.getString("nama"),
                        rst.getString("judul"),
                        rst.getString("jumlah"),
                        rst.getString("stock"),
                        rst.getString("pengarang"),
                        rst.getString("penerbit"),
                        rst.getString("tahun_terbit"),
                        rst.getString("tahun_pengadaan"),
                        rst.getString("sumber"),
                        rst.getString("rak"),
                        rst.getString("ISBN"),
                        });
                    jTable1.setModel(dtm);
                }
            } catch (Exception e) {
                System.out.println("Ada Kesalahan 1" + e.toString());
            }
        } catch (SQLException e) {
            System.out.println("koneksi gagal 2" + e.toString());
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField9 = new javax.swing.JTextField();
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

        jLabel1.setText("Kode");

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Kategori Buku");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilihan]" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField2.setText("jTextField2");

        jLabel3.setText("Judul");

        jTextField3.setText("jTextField3");

        jLabel4.setText("Jumlah");

        jLabel5.setText("Stock");

        jLabel6.setText("Pengarang");

        jTextField4.setText("jTextField4");

        jLabel7.setText("Penerbit");

        jTextField5.setText("jTextField5");

        jLabel8.setText("Tahun Terbit");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilihan]" }));

        jLabel9.setText("Tahun Pengadaan");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilihan]" }));

        jLabel10.setText("Sumber");

        jTextField6.setText("jTextField6");

        jLabel11.setText("Nomor Rak");

        jTextField7.setText("jTextField7");

        jLabel12.setText("ISBN");

        jTextField8.setText("jTextField8");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(435, 435, 435))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                                    .addComponent(jTextField8)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField2))
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(57, 57, 57)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(23, 23, 23)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel9)
                                    .addGap(12, 12, 12)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(102, 255, 255));

        jLabel13.setText("Cari");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilihan]", "judul", "pengarang", "penerbit", "sumber" }));

        jTextField9.setText("jTextField9");

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 15, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String Kode = "";
        try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "select * from kategori_buku where kode = '"+jComboBox1.getSelectedItem().toString()+"'";
            rst = stm.executeQuery(sql);
            while (rst.next()) {
                Kode = rst.getString("nama");
            }
            jTextField2.setText(Kode);
            jTextField2.setEditable(false);
        } catch (SQLException e) {
            System.out.println("koneksi gagal 5" + e.toString());
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int stock=(int) jSpinner2.getValue(); 
        try {
            con = koneksi.getKoneksiDatabase();
            stm = null;
            sql = "insert into buku (id, kode, kode_kategori, judul, jumlah, stock, pengarang, penerbit, tahun_terbit, tahun_pengadaan, sumber, rak, isbn, last_update, userid) VALUES (NULL,'" + jTextField1.getText() + "','" + jComboBox1.getSelectedItem() + "','"+ jTextField3.getText() +"','"+ jSpinner1.getValue() +"','"+ jSpinner2.getValue() +"','"+ jTextField4.getText() + "','"+ jTextField5.getText() + "','"+ jComboBox2.getSelectedItem() +"','"+ jComboBox3.getSelectedItem() +"','"+ jTextField6.getText() +"','" + jTextField7.getText() +"','"+ jTextField8.getText() +"',NOW(),'ADMIN')";
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
            jComboBox3.setSelectedIndex(0);

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal 3" + e.toString());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        try {
            con = koneksi.getKoneksiDatabase();
            sql = "Select * from buku where kode = '" + jTextField1.getText() + "' ";
            stm = con.createStatement();
            rst = stm.executeQuery(sql);
            if (rst.next()) {
                JOptionPane.showMessageDialog(this, "kode buku Tersebut Sudah Ada Silahkan Input kode lain Atau Data Mau Di Edit", "Informasi ", JOptionPane.INFORMATION_MESSAGE);

                jButton1.setEnabled(false);
                jComboBox1.setSelectedItem(rst.getString("kode_kategori"));
                
                try{
                    con = koneksi.getKoneksiDatabase();
                    sql = "Select * from buku where kode = '" + jTextField1.getText() + "' ";
                    stm = con.createStatement();
                    rst = stm.executeQuery(sql);
                    if (rst.next()) {
                        jTextField3.setText(rst.getString("judul"));
                        jTextField4.setText(rst.getString("pengarang"));
                        jTextField5.setText(rst.getString("penerbit"));
                        jTextField6.setText(rst.getString("sumber"));
                        jTextField7.setText(rst.getString("rak"));
                        jTextField8.setText(rst.getString("isbn"));
                    }
                } catch (Exception e) {

                }
                
                try{
                    con = koneksi.getKoneksiDatabase();
                    sql = "Select * from buku where kode = '" + jTextField1.getText() + "' ";
                    stm = con.createStatement();
                    rst = stm.executeQuery(sql);
                    if (rst.next()) {
                        jComboBox2.setSelectedItem(rst.getString("tahun_terbit"));
                    }
                } catch (Exception e) {

                }
                
                try{
                    con = koneksi.getKoneksiDatabase();
                    sql = "Select * from buku where kode = '" + jTextField1.getText() + "' ";
                    stm = con.createStatement();
                    rst = stm.executeQuery(sql);
                    if (rst.next()) {
                        jComboBox3.setSelectedItem(rst.getString("tahun_pengadaan"));
                    }
                } catch (Exception e) {

                }
                
                jSpinner1.setValue(Integer.parseInt(rst.getString("jumlah")));
                jSpinner2.setValue(Integer.parseInt(rst.getString("stock")));
                
            } else {
                jButton1.setEnabled(true);
                jComboBox1.setSelectedIndex(0);
                jTextField3.setText("");
                jSpinner1.setValue(0);
                jSpinner2.setValue(0);
                jTextField4.setText("");
                jTextField5.setText("");
                jComboBox2.setSelectedIndex(0);
                jComboBox3.setSelectedIndex(0);
                jTextField6.setText("");
                jTextField7.setText("");
                jTextField8.setText("");
            }
        }
        catch (SQLException e){
            
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            con = koneksi.getKoneksiDatabase();
            stm = null;
            
            sql = "update buku set kode_kategori = '" + jComboBox1.getSelectedItem() + "', "
                    + "judul = '"+ jTextField3.getText() + "', "
                    + "jumlah = '"+ jSpinner1.getValue() +"', "
                    + "stock = '"+ jSpinner2.getValue() +"', "
                    + "pengarang = '"+ jTextField4.getText() +"', "
                    + "penerbit = '"+ jTextField5.getText() +"', "
                    + "tahun_terbit = '"+ jComboBox2.getSelectedItem() +"', "
                    + "tahun_pengadaan = '"+ jComboBox3.getSelectedItem() +"', "
                    + "sumber = '"+ jTextField6.getText() +"', "
                    + "rak = '"+ jTextField7.getText() +"', "
                    + "isbn = '"+ jTextField8.getText() +"'  "
                    + "where kode = '" + jTextField1.getText() + "' ";

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
            jComboBox1.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal 7" + e.toString());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            con = koneksi.getKoneksiDatabase();
            stm = null;
            sql = "delete from buku where kode = '" + jTextField1.getText() +"' ";
 

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
            jComboBox3.setSelectedIndex(0);

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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        TampilDataMhsPadaTabel();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        cariDataMahasiswa();
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
            java.util.logging.Logger.getLogger(Form_Input_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Input_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Input_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Input_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Input_Buku().setVisible(true);
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
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
