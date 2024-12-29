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

public class Form_Biodata_Mahasiswa extends javax.swing.JFrame {

    Koneksi koneksi=new Koneksi();
    private Connection con;
    Statement stm;
    ResultSet rst;
    String sql;
    DefaultTableModel dtm;
    
    public Form_Biodata_Mahasiswa() {
        initComponents();
        TampilDataMhsPadaTabel();
        KosongkanObjek();
        loadDataCari();
        loadDataProdi();
        loadDataAgama();
        loadDataProvinsi();
        loadDataKelamin();
        loadDataTahunMasuk();
    }
    
    void KosongkanObjek() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField18.setText("");
        
        jTextField1.requestFocus();
    }
    
    private void loadDataProdi(){
        String kd="";
        try{
            con=koneksi.getKoneksiDatabase();
            stm=con.createStatement();
            sql="SELECT * from program_studi";
            rst=stm.executeQuery(sql);
            while(rst.next()){
                jComboBox1.addItem(rst.getString("kode_jurusan"));
                
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal " + e.toString());
        }
    }
    
    private void loadDataAgama(){
        String kd="";
        try{
            con=koneksi.getKoneksiDatabase();
            stm=con.createStatement();
            sql="SELECT * from agama";
            rst=stm.executeQuery(sql);
            while(rst.next()){
                jComboBox2.addItem(rst.getString("kode"));
                
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal " + e.toString());
        }
    }
    
    private void loadDataProvinsi(){
        String kd="";
        try{
            con=koneksi.getKoneksiDatabase();
            stm=con.createStatement();
            sql="SELECT * from propinsi";
            rst=stm.executeQuery(sql);
            while(rst.next()){
                jComboBox4.addItem(rst.getString("kode_propinsi"));
                
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal " + e.toString());
        }
    }
    
    private void loadDataKelamin(){
        jComboBox3.addItem("L");
        jComboBox3.addItem("P");
    }
    
    private void loadDataTahunMasuk(){
        jComboBox5.addItem("2019");
        jComboBox5.addItem("2020");
        jComboBox5.addItem("2021");
        jComboBox5.addItem("2022");
        jComboBox5.addItem("2023");
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
            String[] kolom = {"Stambuk", "Nama Mahasiswa", "Program Studi", "Agama", "Tempat Lahir", "Tgl Lahir", "Jenis Kelamin", "Alamat", "Kota", "Provinsi", "Kode Pos", "Telepon", "Hanphone", "Hobi", "Wali", "Alamat Wali", "Telepon Wali", "Tahun Masuk"};
            dtm = new DefaultTableModel(null, kolom) {
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {

                    return false;
                }
            };

            jTable2.setModel(dtm);
            aturJTable(jTable2, new int[]{100, 300, 300, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90});
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

                sql = "SELECT biodata_mahasiswa.nim ,biodata_mahasiswa.nama AS NamaMahasiswa, "
                        + "program_studi.nama AS ProgramStudi, agama.nama AS NamaAgama, biodata_mahasiswa.tempat_lahir AS TempatLahir, "
                        + "biodata_mahasiswa.tanggal_lahir AS TanggalLahir, biodata_mahasiswa.jenis_kelamin AS JenisKelamin, biodata_mahasiswa.alamat AS Alamat, biodata_mahasiswa.kota AS Kota, "
                        + "propinsi.nama_propinsi AS NamaProvinsi, biodata_mahasiswa.kode_pos AS KodePos, biodata_mahasiswa.telpon AS Telpon, biodata_mahasiswa.handphone AS HandPhone, " 
                        + "biodata_mahasiswa.hobi AS Hobi, biodata_mahasiswa.wali AS Wali, biodata_mahasiswa.alamat_wali AS AlamatWali, biodata_mahasiswa.telpon_wali AS TelponWali, biodata_mahasiswa.tahun_masuk AS TahunMasuk FROM "
                        + "biodata_mahasiswa "
                        + "LEFT OUTER JOIN program_studi ON(biodata_mahasiswa.kode_program_studi=program_studi.kode_jurusan) "
                        + "LEFT OUTER JOIN agama ON(biodata_mahasiswa.kode_agama = agama.kode) "
                        + "LEFT OUTER JOIN propinsi ON(biodata_mahasiswa.kode_propinsi = propinsi.kode_propinsi)";
                  rst = stm.executeQuery(sql);
                  while (rst.next()) {
                    dtm.addRow(new Object[]{
                        rst.getString("nim"),
                        rst.getString("NamaMahasiswa"), 
                        rst.getString("ProgramStudi"), 
                        rst.getString("NamaAgama"), 
                        rst.getString("TempatLahir"), 
                        rst.getString("TanggalLahir"), 
                        rst.getString("JenisKelamin"), 
                        rst.getString("Alamat"), 
                        rst.getString("Kota"), 
                        rst.getString("NamaProvinsi"),
                        rst.getString("KodePos"), 
                        rst.getString("Telpon"), 
                        rst.getString("Handphone"), 
                        rst.getString("Hobi"), 
                        rst.getString("Wali"), 
                        rst.getString("AlamatWali"), 
                        rst.getString("TelponWali"), 
                        rst.getString("TahunMasuk"),
                        });
                    jTable2.setModel(dtm);
                }
            } catch (Exception e) {
                System.out.println("Ada Kesalahan " + e.toString());
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

                sql = "SELECT biodata_mahasiswa.nim ,biodata_mahasiswa.nama AS NamaMahasiswa, "
                        + "program_studi.nama AS ProgramStudi, " + "agama.nama AS NamaAgama, " + "biodata_mahasiswa.tempat_lahir AS TempatLahir, "
                        + "biodata_mahasiswa.tanggal_lahir AS TanggalLahir, " + "biodata_mahasiswa.jenis_kelamin AS JenisKelamin, " + "biodata_mahasiswa.alamat AS Alamat, " + "biodata_mahasiswa.kota AS Kota, "
                        + "propinsi.nama_propinsi AS NamaProvinsi, " + "biodata_mahasiswa.kode_pos AS KodePos, " + "biodata_mahasiswa.telpon AS Telpon, " + "biodata_mahasiswa.handphone AS HandPhone, " + "biodata_mahasiswa.hobi AS Hobi, " + "biodata_mahasiswa.wali AS Wali, " + "biodata_mahasiswa.alamat_wali AS AlamatWali, " + "biodata_mahasiswa.telpon_wali AS TelponWali, " + "biodata_mahasiswa.tahun_masuk AS TahunMasuk " + " FROM "
                        + " biodata_mahasiswa "
                        + " LEFT OUTER JOIN program_studi ON(biodata_mahasiswa.kode_program_studi=program_studi.kode_jurusan)" + " LEFT OUTER JOIN agama ON(biodata_mahasiswa.kode_agama = agama.kode)" + " LEFT OUTER JOIN propinsi ON(biodata_mahasiswa.kode_propinsi = propinsi.kode_propinsi)"
                        + " where biodata_mahasiswa."+jComboBox6.getSelectedItem()+" LIKE '%"+jTextField17.getText()+"%' ";
                  rst = stm.executeQuery(sql);
                  while (rst.next()) {
                    dtm.addRow(new Object[]{
                        rst.getString("nim"),
                        rst.getString("NamaMahasiswa"), 
                        rst.getString("ProgramStudi"), 
                        rst.getString("NamaAgama"), 
                        rst.getString("TempatLahir"), 
                        rst.getString("TanggalLahir"), 
                        rst.getString("JenisKelamin"), 
                        rst.getString("Alamat"), 
                        rst.getString("Kota"), 
                        rst.getString("NamaProvinsi"), 
                        rst.getString("KodePos"), 
                        rst.getString("Telpon"), 
                        rst.getString("Handphone"), 
                        rst.getString("Hobi"), 
                        rst.getString("Wali"), 
                        rst.getString("AlamatWali"), 
                        rst.getString("TelponWali"), 
                        rst.getString("TahunMasuk"),
                        });
                    jTable2.setModel(dtm);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField18 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jTextField17 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));

        jLabel1.setText("Stambuk");

        jLabel2.setText("Nama");

        jLabel3.setText("Kode Prodi");

        jLabel4.setText("Kode Agama");

        jLabel5.setText("T4 Lahir");

        jLabel6.setText("Tanggal Lahir");

        jLabel7.setText("Jenis Kelamin");

        jLabel8.setText("Alamat");

        jLabel9.setText("Kota");

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setText("jTextField2");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilihan]" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField3.setText("jTextField3");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilihan]" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField4.setText("jTextField4");

        jTextField5.setText("jTextField5");

        jTextField7.setText("jTextField7");

        jTextField8.setText("jTextField8");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilihan]" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel10.setText("Kode Provinsi");

        jLabel11.setText("Kode Pos");

        jLabel12.setText("Telepon");

        jLabel13.setText("Handphone");

        jLabel14.setText("Hobi");

        jLabel15.setText("Wali");

        jLabel16.setText("Alamat Wali");

        jLabel17.setText("Telpon Wali");

        jLabel18.setText("Tahun Masuk");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilihan]" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jTextField9.setText("jTextField9");

        jTextField10.setText("jTextField10");

        jTextField11.setText("jTextField11");

        jTextField12.setText("jTextField12");

        jTextField13.setText("jTextField13");
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jTextField14.setText("jTextField14");

        jTextField15.setText("jTextField15");

        jTextField16.setText("jTextField16");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilihan]" }));

        jTextField18.setText("jTextField18");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel17)
                            .addGap(8, 8, 8))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel16)
                            .addGap(8, 8, 8))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel13)
                            .addGap(14, 14, 14))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField3))
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField5)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel14))
                            .addGap(52, 52, 52))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                    .addComponent(jTextField11)
                    .addComponent(jTextField12)
                    .addComponent(jTextField13)
                    .addComponent(jTextField14)
                    .addComponent(jTextField15)
                    .addComponent(jTextField16)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel15)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 255, 255));

        jLabel19.setText("Cari");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[Pilihan]" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jTextField17.setText("jTextField17");

        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15", "Title 16", "Title 17", "Title 18"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(135, 135, 135))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setText("Simpan");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Edit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Hapus");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Batal");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Exit");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String Tampilan = "yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String TanggalLahir = String.valueOf(Fm.format(jDateChooser1.getDate()));
        try {
            con = koneksi.getKoneksiDatabase();
            stm = null;
            sql = "insert into biodata_mahasiswa (id, nim, nama, kode_program_studi, kode_agama, tempat_lahir, tanggal_lahir, jenis_kelamin, alamat, kota, kode_propinsi, kode_pos, telpon, handphone, hobi, wali, alamat_wali, telpon_wali, tahun_masuk, last_update, userid ) VALUES (NULL,'" +
                    jTextField1.getText() + "','" +
                    jTextField2.getText() + "', '" +
                    jComboBox1.getSelectedItem() + "', '" +
                    jComboBox2.getSelectedItem() + "', '" +
                    jTextField5.getText() + "','" +
                    TanggalLahir + "','"+
                    jComboBox3.getSelectedItem() + "', '" +
                    jTextField7.getText() + "', '" +
                    jTextField8.getText() + "', '" +
                    jComboBox4.getSelectedItem() + "', '" +
                    jTextField10.getText() + "','"+
                    jTextField11.getText() + "','" +
                    jTextField12.getText() + "','" +
                    jTextField13.getText() + "','" +
                    jTextField14.getText() + "','" +
                    jTextField15.getText() + "', '"+
                    jTextField16.getText() + "','" +
                    jComboBox5.getSelectedItem() + "',NOW(),'ADMIN')";
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
            jComboBox4.setSelectedIndex(0);
            jComboBox5.setSelectedIndex(0);

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal 3" + e.toString());
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String Kode = "";
        try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "select * from program_studi where kode_jurusan='"
                    + jComboBox1.getSelectedItem().toString() + "'";
            rst = stm.executeQuery(sql);
            while (rst.next()) {
                Kode = rst.getString("nama");
            }
            jTextField3.setText(Kode);
        } catch (SQLException e) {
            System.out.println("koneksi gagal" + e.toString());
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String Kode = "";
        try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "select * from agama where kode='"
                    + jComboBox2.getSelectedItem().toString() + "'";
            rst = stm.executeQuery(sql);
            while (rst.next()) {
                Kode = rst.getString("nama");
            }
            jTextField4.setText(Kode);
        } catch (SQLException e) {
            System.out.println("koneksi gagal 4" + e.toString());
        }

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        if (jComboBox3.getSelectedItem().equals("L")) {
            jTextField18.setText("Laki - Laki");

        } else if(jComboBox3.getSelectedItem()=="[Pilihan]") {
            jTextField18.setText("");
        }
        else{
            jTextField18.setText("Perempuan");
        }

    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        String Kode = "";
        try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "select * from propinsi where kode_propinsi='"
                    + jComboBox4.getSelectedItem().toString() + "'";
            rst = stm.executeQuery(sql);
            while (rst.next()) {
                Kode = rst.getString("nama_propinsi");
            }
            jTextField9.setText(Kode);
        } catch (SQLException e) {
            System.out.println("koneksi gagal 5" + e.toString());
        }

    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        try {
            con = koneksi.getKoneksiDatabase();
            sql = "Select * from data_mahasiswa where nim = '" + jTextField1.getText() + "' ";
            stm = con.createStatement();
            rst = stm.executeQuery(sql);
            if (rst.next()) {
                JOptionPane.showMessageDialog(this, "Stambuk Tersebut Sudah Ada Silahkan Input Stb Lain Atau Data Mau Di Edit", "Informasi ", JOptionPane.INFORMATION_MESSAGE);

                jButton1.setEnabled(false);
                jTextField2.setText(rst.getString("nama"));
                jTextField5.setText(rst.getString("tempat_lahir"));
                jDateChooser1.setDate(rst.getDate("tanggal_lahir"));
                jTextField7.setText(rst.getString("alamat"));
                jTextField8.setText(rst.getString("kota"));
                jTextField10.setText(rst.getString("kode_pos"));
                jTextField11.setText(rst.getString("telpon"));
                jTextField12.setText(rst.getString("handphone"));
                jTextField13.setText(rst.getString("hobi"));
                jTextField14.setText(rst.getString("wali"));
                jTextField15.setText(rst.getString("alamat_wali"));
                jTextField16.setText(rst.getString("telpon_wali"));
                jComboBox1.setSelectedItem(rst.getString("kode_program_studi")); 
                try {
                    con = koneksi.getKoneksiDatabase();
                    sql = "Select * from data_mahasiswa where nim = '" + jTextField1.getText() + "' ";
                    stm = con.createStatement();
                    rst = stm.executeQuery(sql);
                    if (rst.next()) {
                        jComboBox5.setSelectedItem(rst.getString("tahun_masuk"));
                    }
                }
                catch(SQLException e){
                    System.out.println("gagal "+e);
                }
                
                try {
                    con = koneksi.getKoneksiDatabase();
                    sql = "Select * from data_mahasiswa where nim = '" + jTextField1.getText() + "' ";
                    stm = con.createStatement();
                    rst = stm.executeQuery(sql);
                    if (rst.next()) {
                        jComboBox2.setSelectedItem(rst.getString("kode_agama"));
                    }
                }
                catch(SQLException e){
                    System.out.println("gagal "+e);
                }
            
                try {
                    con = koneksi.getKoneksiDatabase();
                    sql = "Select * from data_mahasiswa where nim = '" + jTextField1.getText() + "' ";
                    stm = con.createStatement();
                    rst = stm.executeQuery(sql);
                    if (rst.next()) {
                        jComboBox3.setSelectedItem(rst.getString("jenis_kelamin"));
                    }
                }
                catch(Exception e){
                    System.out.println("gagal "+e);
                }

                try {
                    con = koneksi.getKoneksiDatabase();
                    sql = "Select * from data_mahasiswa where nim = '" + jTextField1.getText() + "' ";
                    stm = con.createStatement();
                    rst = stm.executeQuery(sql);
                    if (rst.next()) {
                        jComboBox4.setSelectedItem(rst.getString("kode_propinsi"));
                    }
                }
                catch(Exception e){
                    System.out.println("gagal "+e);
                }
            
          
            } else {
                jButton1.setEnabled(true);
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextField18.setText("");
                jTextField7.setText("");
                jTextField8.setText("");
                jTextField9.setText("");
                jTextField10.setText("");
                jTextField11.setText("");
                jTextField12.setText("");
                jTextField13.setText("");
                jTextField14.setText("");
                jTextField15.setText("");
                jTextField16.setText("");
                jTextField17.setText("");
                jComboBox1.setSelectedIndex(0);
                jComboBox2.setSelectedIndex(0);
                jComboBox3.setSelectedIndex(0);
                jComboBox4.setSelectedIndex(0);
                jComboBox5.setSelectedIndex(0);
                jTextField2.requestFocus();
            }
            
            
            

        } catch (SQLException e) {
            System.out.println("koneksi gagal 6" + e.toString());
        }

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String Tampilan = "yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String TanggalLahir = String.valueOf(Fm.format(jDateChooser1.getDate()));
        try {

            con = koneksi.getKoneksiDatabase();
            stm = null;
            sql = "update data_mahasiswa set nama = '" + jTextField2.getText() + "', kode_program_studi ='" + jComboBox1.getSelectedItem() + "' , kode_agama = '" + jComboBox2.getSelectedItem() + "', tempat_lahir = '" + jTextField5.getText() + "', tanggal_lahir= '" + TanggalLahir + 
                    "', jenis_kelamin = '" + jComboBox3.getSelectedItem() + "', alamat = '" + jTextField7.getText() + "', kota='" + jTextField8.getText() + "', kode_propinsi='" + jComboBox4.getSelectedItem() + "', kode_pos = '" + jTextField10.getText() + "', telpon = '" + jTextField11.getText() + "', handphone = '" + jTextField12.getText() + "', hobi = '" + jTextField13.getText() +
                    "', wali = '" + jTextField14.getText() + "', alamat_wali = '" + jTextField15.getText() + "', telpon_wali='" + jTextField16.getText() + "' , tahun_masuk='" + jComboBox5.getSelectedItem() + "', last_update= NOW(), userid='ADMIN' where nim = '" + jTextField1.getText() + "' ";

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
            jComboBox3.setSelectedIndex(0);
            jComboBox4.setSelectedIndex(0);
            jComboBox5.setSelectedIndex(0);

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal 7" + e.toString());
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            con = koneksi.getKoneksiDatabase();
            stm = null;
            sql = "delete from biodata_mahasiswa where nim = '" + jTextField1.getText() +"' ";
 

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
            jComboBox4.setSelectedIndex(0);
            jComboBox5.setSelectedIndex(0);

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal 8" + e.toString());
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        KosongkanObjek();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        
    }//GEN-LAST:event_jTable2MouseClicked
    
    private void loadDataCari(){
    try {
            con = koneksi.getKoneksiDatabase();
            stm = con.createStatement();
            sql = "select * from biodata_mahasiswa";
            rst = stm.executeQuery(sql);
            
            ResultSetMetaData metaData = rst.getMetaData();
            int columnCount = metaData.getColumnCount();
                    
            for (int i=1; i<=columnCount; i++){
                String columnName = metaData.getColumnName(i);
                jComboBox6.addItem(columnName);
            }
        } catch (SQLException e) {
            System.out.println("koneksi gagal 5" + e.toString());
        }
    }
    
    
    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed

        
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cariDataMahasiswa();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        TampilDataMhsPadaTabel();
    }//GEN-LAST:event_jButton2ActionPerformed

    
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
            java.util.logging.Logger.getLogger(Form_Biodata_Mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Biodata_Mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Biodata_Mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Biodata_Mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Biodata_Mahasiswa().setVisible(true);
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
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

    private void AturJTable(JTable jTable2, int[] i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
