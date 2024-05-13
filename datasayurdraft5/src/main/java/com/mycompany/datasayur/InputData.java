//InputData.java (Program Utama)

package com.mycompany.datasayur;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
    
// Kelas utama
public class InputData extends javax.swing.JFrame {
    
    //koneksi untuk akses database
    public final Connection conn = new Datasayur().connect();
    
    //untuk mengelola data dalam tabel
    private DefaultTableModel tabmode;
    private DefaultTableModel tabmode2;

    private void aktif(){
        jNamaSayuran.setEnabled(true);
        jKodeSayuran.setEnabled(true);
        jJenisSayuran.setEnabled(true);
        jJumlahStok.setEnabled(true);
        jSatuanSayur.setEnabled(true);
        jHargaAwal.setEnabled(true);
        jHargaAkhir.setEnabled(true);
        jSupplier.setEnabled(true);
        jDataSupplier.setEnabled(true);
    }
    
    protected void kosong(){
        jNamaSayuran.setText(null);
        jKodeSayuran.setText(null);
        jJenisSayuran.setSelectedItem(null);
        jJumlahStok.setText(null);
        jSatuanSayur.setSelectedItem(null);
        jHargaAwal.setText(null);
        jSupplier.setSelectedItem(null);
        jHargaAkhir.setText(null);
        jDataSupplier.setText(null);
    }
    
    //menetapkan nomor baris di tabel
    public void noTable(){
        int Baris = tabmode.getRowCount();
        for (int a=0; a<Baris; a++)
        {
            String nomor = String.valueOf(a+1);
            tabmode.setValueAt(nomor +".",a,0);
        }
    }
    
    //menetapkan tanggal saat ini di kolom tanggal
    public void tanggal(){
        Date tgl = new Date();
        jTanggal.setDate(tgl);
        Date tgl1 = new Date();
        jTanggalExp.setDate(tgl1);
    }
    
    //Data utama tabel
    public void dataTable(){ 
        
        Object[] Baris = {"No","Tanggal","Nama Sayuran","Kode Sayuran","Jenis Sayuran","Jumlah Stok","Satuan","Harga Awal", "Tanggal Expired", "Harga Akhir", "Supplier"};
        tabmode = new DefaultTableModel(null, Baris);
        jTableSayuran.setModel(tabmode);
        
        //mengambil data dari database
        String sql = "select * from input_Data order by kodeSayur asc";

        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String tanggal = hasil.getString("tanggal");
                String namaSayur = hasil.getString("namaSayur");
                String kodeSayur = hasil.getString("kodeSayur");
                String jenisSayur = hasil.getString("jenisSayur");
                String jumlahStok = hasil.getString("jumlahStok");
                String satuan = hasil.getString("satuan");
                String hargaAwal = hasil.getString("hargaAwal");
                String tanggalExp = hasil.getString("tanggalExp");
                String hargaAkhir = hasil.getString("hargaAkhir");
                String supplier = hasil.getString("supplier");             
                
                String[] data = {"",tanggal,namaSayur,kodeSayur,jenisSayur,jumlahStok,satuan,hargaAwal,tanggalExp,hargaAkhir,supplier};
                tabmode.addRow(data);
                noTable();            
            }
        } catch (Exception e){
        }
       }
    
    //data tabel supplier
    public void dataTable2(){
        Object[] Baris2 = {"Nama Sayuran","Jenis Sayuran"};
        tabmode2 = new DefaultTableModel(null, Baris2);
        jListSupplier.setModel(tabmode2);
        
        String sql = "select * from input_data order by namaSayur asc";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String namaSayur = hasil.getString("namaSayur");
                String jenisSayur = hasil.getString("jenisSayur");
                String[] data = {namaSayur,jenisSayur};
                tabmode2.addRow(data);
            }
        } catch (Exception e){
        }
    }
    
    //mengatur ukuran kolom
    public void lebarKolom(){
        TableColumn column;
        jTableSayuran.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = jTableSayuran.getColumnModel().getColumn(0);
        column.setPreferredWidth(35);
        column = jTableSayuran.getColumnModel().getColumn(1);
        column.setPreferredWidth(125);
        column = jTableSayuran.getColumnModel().getColumn(2);
        column.setPreferredWidth(125);
        column = jTableSayuran.getColumnModel().getColumn(3);
        column.setPreferredWidth(125);
        column = jTableSayuran.getColumnModel().getColumn(4);
        column.setPreferredWidth(125);
        column = jTableSayuran.getColumnModel().getColumn(5);
        column.setPreferredWidth(125);
        column = jTableSayuran.getColumnModel().getColumn(6);
        column.setPreferredWidth(125);
        column = jTableSayuran.getColumnModel().getColumn(7);
        column.setPreferredWidth(125);
        column = jTableSayuran.getColumnModel().getColumn(8);
        column.setPreferredWidth(125);
        column = jTableSayuran.getColumnModel().getColumn(9);
        column.setPreferredWidth(125);
        column = jTableSayuran.getColumnModel().getColumn(10);
        column.setPreferredWidth(125);
        
        JTableHeader header = jTableSayuran.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer());

    }
    
    public void lebarKolom2(){
        TableColumn column2;
        jListSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column2 = jListSupplier.getColumnModel().getColumn(0);
        column2.setPreferredWidth(205);
        column2 = jListSupplier.getColumnModel().getColumn(1);
        column2.setPreferredWidth(218);
        
        JTableHeader header2 = jListSupplier.getTableHeader();
        header2.setDefaultRenderer(new HeaderRenderer());
    }

    //Mengganti warna heeader
    class HeaderRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBackground(new Color(66, 163, 167)); 
        return this;
    }
}
    
    //pencarian tabel utama
    public void pencarian(String sql){
        Object[] Baris = {"No","Tanggal","Nama Sayuran","Kode Sayuran","Jenis Sayuran","Jumlah Stok","Satuan","Harga Awal", "Tanggal Expired", "Harga Akhir", "Supplier"};
        tabmode = new DefaultTableModel(null, Baris);
        jTableSayuran.setModel(tabmode);
        int brs = jTableSayuran.getRowCount();
        for (int i = 0; 1 < brs; i++){
            tabmode.removeRow(1);
        }
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String tanggal = hasil.getString("tanggal");
                String namaSayur = hasil.getString("namaSayur");
                String kodeSayur = hasil.getString("kodeSayur");
                String jenisSayur = hasil.getString("jenisSayur");
                String jumlahStok = hasil.getString("jumlahStok");
                String satuan = hasil.getString("satuan");
                String hargaAwal = hasil.getString("hargaAwal");
                String tanggalExp = hasil.getString("tanggalExp");
                String hargaAkhir = hasil.getString("hargaAkhir");
                String supplier = hasil.getString("supplier");                  
                String[] data = {"",tanggal,namaSayur,kodeSayur,jenisSayur,jumlahStok,satuan,hargaAwal,tanggalExp,hargaAkhir,supplier};
                tabmode.addRow(data);
                noTable();
            }
        } catch(Exception e){
        }
    }
    
    //pencarian tabel supplier
    public void pencarian2(String sql){
        Object[] Baris2 = {"Nama Sayuran","Jenis Sayuran"};
        tabmode2 = new DefaultTableModel(null, Baris2);
        jListSupplier.setModel(tabmode2);
        int brs = jListSupplier.getRowCount();
        for (int i = 0; 1 < brs; i++){
            tabmode2.removeRow(1);
        }
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String namaSayur = hasil.getString("namaSayur");
                String jenisSayur = hasil.getString("jenisSayur");
                String[] data = {namaSayur,jenisSayur};
                tabmode2.addRow(data);
            }
        } catch(Exception e){   
        }
    }
    
    //menghitung harga akhir
    public double hitungHargaAkhir(double hargaAwal, Date tanggalMasuk, Date tanggalExpired) {
    long selisihHari = hitungSelisihHari(tanggalMasuk, tanggalExpired);
    double potongan = 0;

    if (selisihHari >= 15) {
        potongan = 0;
    } else if (selisihHari >= 12) {
        potongan = 0.05;
    } else if (selisihHari >= 10) {
        potongan = 0.07;
    } else if (selisihHari >= 9) {
        potongan = 0.09;
    } else if (selisihHari >= 8) {
        potongan = 0.15;
    } else if (selisihHari >= 7) {
        potongan = 0.20;
    } else if (selisihHari >= 6) {
        potongan = 0.25;
    } else if (selisihHari >= 5) {
        potongan = 0.30;
    } else if (selisihHari >= 4) {
        potongan = 0.35;
    } else if (selisihHari >= 3) {
        potongan = 0.40;
    } else if (selisihHari >= 2) {
        potongan = 0.45;
    } else if (selisihHari >= 1) {
        potongan = 0.50;
    } else if (selisihHari == 0) {
        potongan = 0.65;
    }

    return (hargaAwal - (hargaAwal * potongan));
}
    //hitung selisih hari
    private long hitungSelisihHari(Date tanggalMasuk, Date tanggalExpired) {
    long selisihMillis = tanggalExpired.getTime() - tanggalMasuk.getTime();
    return selisihMillis / (24 * 60 * 60 * 1000);
}

    public InputData() {
        initComponents();
        aktif();
        dataTable();
        tanggal();
        lebarKolom();
        dataTable2();
        lebarKolom2();
        jKodeSayuran.requestFocus(); 
    }
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        judul = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTInput = new javax.swing.JButton();
        jTHarian = new javax.swing.JButton();
        YurSayur = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        tanggal = new javax.swing.JLabel();
        jTanggal = new com.toedter.calendar.JDateChooser();
        tanggalExp = new javax.swing.JLabel();
        jTanggalExp = new com.toedter.calendar.JDateChooser();
        namaSayuran = new javax.swing.JLabel();
        jNamaSayuran = new javax.swing.JTextField();
        kodeSayuran = new javax.swing.JLabel();
        jKodeSayuran = new javax.swing.JTextField();
        jenisSayuran = new javax.swing.JLabel();
        jumlahStok = new javax.swing.JLabel();
        jJumlahStok = new javax.swing.JTextField();
        satuanSayur = new javax.swing.JLabel();
        jScrollTabel = new javax.swing.JScrollPane();
        jTableSayuran = new javax.swing.JTable();
        hargaJual = new javax.swing.JLabel();
        jHargaAwal = new javax.swing.JTextField();
        hargaAkhir = new javax.swing.JLabel();
        jHargaAkhir = new javax.swing.JTextField();
        supplier = new javax.swing.JLabel();
        jCari = new javax.swing.JTextField();
        jTambah = new javax.swing.JButton();
        jUbah = new javax.swing.JButton();
        jHapus = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        dataSupplier = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListSupplier = new javax.swing.JTable();
        jDataSupplier = new javax.swing.JTextField();
        jClear = new javax.swing.JButton();
        jJenisSayuran = new javax.swing.JComboBox<>();
        jSatuanSayur = new javax.swing.JComboBox<>();
        jSupplier = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        judul.setFont(new java.awt.Font("Coolvetica Rg", 1, 24)); // NOI18N
        judul.setForeground(new java.awt.Color(255, 255, 255));
        judul.setText("APLIKASI INVENTORIS DATA SAYURAN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(645, 645, 645)
                .addComponent(judul)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jTInput.setBackground(new java.awt.Color(0, 102, 102));
        jTInput.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jTInput.setForeground(new java.awt.Color(255, 255, 255));
        jTInput.setText("Input Data");
        jTInput.setPreferredSize(new java.awt.Dimension(131, 29));
        jTInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTInputActionPerformed(evt);
            }
        });

        jTHarian.setBackground(new java.awt.Color(0, 102, 102));
        jTHarian.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jTHarian.setForeground(new java.awt.Color(255, 255, 255));
        jTHarian.setText("Laporan Harian");
        jTHarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTHarianActionPerformed(evt);
            }
        });

        YurSayur.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir")+"\\src\\main\\java\\Images\\Yursayur130.png"));
        YurSayur.setText("Q");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(YurSayur, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTHarian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jTInput, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTHarian, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(YurSayur, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(66, 163, 167));
        jPanel5.setForeground(new java.awt.Color(0, 153, 255));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Input Data Masuk Sayuran");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tanggal.setFont(new java.awt.Font("Coolvetica Rg", 0, 14)); // NOI18N
        tanggal.setText("Tanggal                          :");

        jTanggal.setBackground(new java.awt.Color(235, 235, 235));
        jTanggal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTanggalPropertyChange(evt);
            }
        });

        tanggalExp.setFont(new java.awt.Font("Coolvetica Rg", 0, 14)); // NOI18N
        tanggalExp.setText("Tanggal Expired           :");

        jTanggalExp.setBackground(new java.awt.Color(235, 235, 235));

        namaSayuran.setFont(new java.awt.Font("Coolvetica Rg", 0, 14)); // NOI18N
        namaSayuran.setText("Nama Sayuran              :");

        jNamaSayuran.setBackground(new java.awt.Color(235, 235, 235));

        kodeSayuran.setFont(new java.awt.Font("Coolvetica Rg", 0, 14)); // NOI18N
        kodeSayuran.setText("Kode Sayuran               :");

        jKodeSayuran.setBackground(new java.awt.Color(235, 235, 235));

        jenisSayuran.setFont(new java.awt.Font("Coolvetica Rg", 0, 14)); // NOI18N
        jenisSayuran.setText("Jenis Sayuran               :");

        jumlahStok.setFont(new java.awt.Font("Coolvetica Rg", 0, 14)); // NOI18N
        jumlahStok.setText("Jumlah Stok                  :");

        jJumlahStok.setBackground(new java.awt.Color(235, 235, 235));
        jJumlahStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jJumlahStokActionPerformed(evt);
            }
        });
        jJumlahStok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jJumlahStokKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jJumlahStokKeyTyped(evt);
            }
        });

        satuanSayur.setFont(new java.awt.Font("Coolvetica Rg", 0, 14)); // NOI18N
        satuanSayur.setText("Satuan Sayur                :");

        jTableSayuran.setBackground(new java.awt.Color(147, 218, 220));
        jTableSayuran.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTableSayuran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tanggal", "Nama Sayuran", "Kode Sayuran", "Jenis Sayuran", "Jumlah Stok", "Satuan Sayur", "Harga Awal", "Tanggal Expired", "Harga Akhir", "Supplier"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSayuran.setSelectionBackground(new java.awt.Color(0, 204, 204));
        jTableSayuran.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTableSayuran.setShowGrid(false);
        jTableSayuran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSayuranMouseClicked(evt);
            }
        });
        jScrollTabel.setViewportView(jTableSayuran);

        hargaJual.setFont(new java.awt.Font("Coolvetica Rg", 0, 14)); // NOI18N
        hargaJual.setText("Harga Awal                    :");
        hargaJual.setVerifyInputWhenFocusTarget(false);

        jHargaAwal.setBackground(new java.awt.Color(235, 235, 235));
        jHargaAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jHargaAwalKeyTyped(evt);
            }
        });

        hargaAkhir.setFont(new java.awt.Font("Coolvetica Rg", 0, 14)); // NOI18N
        hargaAkhir.setText("Harga Akhir                    :");

        jHargaAkhir.setEditable(false);
        jHargaAkhir.setBackground(new java.awt.Color(235, 235, 235));
        jHargaAkhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHargaAkhirActionPerformed(evt);
            }
        });

        supplier.setFont(new java.awt.Font("Coolvetica Rg", 0, 14)); // NOI18N
        supplier.setText("Supplier                           :");

        jCari.setBackground(new java.awt.Color(235, 235, 235));
        jCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jCariKeyTyped(evt);
            }
        });

        jTambah.setBackground(new java.awt.Color(66, 163, 167));
        jTambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTambah.setForeground(new java.awt.Color(255, 255, 255));
        jTambah.setText("Tambah");
        jTambah.setPreferredSize(new java.awt.Dimension(72, 23));
        jTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTambahActionPerformed(evt);
            }
        });

        jUbah.setBackground(new java.awt.Color(66, 163, 167));
        jUbah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jUbah.setForeground(new java.awt.Color(255, 255, 255));
        jUbah.setText("Ubah");
        jUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUbahActionPerformed(evt);
            }
        });

        jHapus.setBackground(new java.awt.Color(255, 0, 0));
        jHapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jHapus.setForeground(new java.awt.Color(255, 255, 255));
        jHapus.setText("Hapus");
        jHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHapusActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(66, 163, 167));

        dataSupplier.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        dataSupplier.setForeground(new java.awt.Color(255, 255, 255));
        dataSupplier.setText("Data Supplier");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dataSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dataSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        jListSupplier.setBackground(new java.awt.Color(147, 218, 220));
        jListSupplier.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jListSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jListSupplier.setSelectionBackground(new java.awt.Color(0, 204, 204));
        jListSupplier.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jListSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListSupplier);

        jDataSupplier.setBackground(new java.awt.Color(235, 235, 235));
        jDataSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jDataSupplierKeyTyped(evt);
            }
        });

        jClear.setBackground(new java.awt.Color(255, 255, 102));
        jClear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jClear.setText("Bersihkan");
        jClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearActionPerformed(evt);
            }
        });

        jJenisSayuran.setBackground(new java.awt.Color(190, 190, 190));
        jJenisSayuran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sayuran Daun", "Sayuran Batang", "Sayuran Akar", "Sayuran Kacang Kacangan", "Sayuran Buah", "Sayuran Bunga", "Sayuran Jamur", "Umbi Batang", "Umbi Lapis" }));

        jSatuanSayur.setBackground(new java.awt.Color(190, 190, 190));
        jSatuanSayur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kilogram (Kg)", "Gram (g)", "Ons", "Pcs" }));

        jSupplier.setBackground(new java.awt.Color(190, 190, 190));
        jSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rayhan", "Raja", "Salwa", "Kartika" }));

        jPanel7.setBackground(new java.awt.Color(66, 163, 167));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PENCARIAN");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(66, 163, 167));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cari Supplier");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel2)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollTabel)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jenisSayuran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(260, 260, 260))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(kodeSayuran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(207, 207, 207))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tanggal)
                                    .addComponent(tanggalExp, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTanggalExp, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                                    .addComponent(jTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(63, 63, 63))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jCari, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(hargaJual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(supplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(satuanSayur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jumlahStok, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(hargaAkhir, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                            .addComponent(namaSayuran, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jSupplier, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jSatuanSayur, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jJumlahStok, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jJenisSayuran, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jKodeSayuran)
                                                    .addComponent(jNamaSayuran)))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jHargaAkhir))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jHargaAwal)))))
                                .addGap(63, 63, 63)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jClear, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jDataSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(25, 25, 25))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tanggal)
                                    .addComponent(jTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTanggalExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tanggalExp, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(namaSayuran)
                                    .addComponent(jNamaSayuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(kodeSayuran)
                                    .addComponent(jKodeSayuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jenisSayuran)
                                    .addComponent(jJenisSayuran, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jumlahStok)
                                    .addComponent(jJumlahStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(satuanSayur)
                                    .addComponent(jSatuanSayur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(supplier)
                                    .addComponent(jSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hargaJual)
                                    .addComponent(jHargaAwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hargaAkhir)
                                    .addComponent(jHargaAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCari, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDataSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jClear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollTabel, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog (null," Apakah Anda Yakin Ingin "
            + "Menghapus Data","Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql="delete from input_data where kodeSayur='"+jKodeSayuran.getText()+"'";
            try {
                PreparedStatement stat=conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                kosong();
                dataTable();
                lebarKolom();
                jKodeSayuran.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus"+e);
            }
        }
    }//GEN-LAST:event_jHapusActionPerformed

    private void jTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTambahActionPerformed
        if(jNamaSayuran.getText().equals("")){
    JOptionPane.showMessageDialog(null, "Nama sayuran tidak boleh kosong");
    } else if (jKodeSayuran.getText().equals("")){
    JOptionPane.showMessageDialog(null, "Kode Sayuran tidak boleh kosong");
    } else if (jJumlahStok.getText().equals("")){
    JOptionPane.showMessageDialog(null, "Jumlah Stok tidak boleh kosong");
    } else if (jHargaAwal.getText().equals("")){
    JOptionPane.showMessageDialog(null, "Harga Awal tidak boleh kosong");
    } else {
    String sql = "insert into input_data (tanggal, namaSayur, kodeSayur, jenisSayur, jumlahStok, satuan, hargaAwal, tanggalExp, hargaAkhir, supplier) values (?,?,?,?,?,?,?,?,?,?)";
    
    Date tanggalMasuk = jTanggal.getDate();
    Date tanggalExpired = jTanggalExp.getDate();
    double hargaAwal = Double.parseDouble(jHargaAwal.getText());
    
    if (hitungSelisihHari(tanggalMasuk, tanggalExpired) < 0) {
        JOptionPane.showMessageDialog(this, "Data sudah expired, tidak bisa diinput.");
        return; // Berhenti eksekusi jika sudah expired
    }    

    int hargaAkhir = (int) hitungHargaAkhir(hargaAwal, tanggalMasuk, tanggalExpired);
    jHargaAkhir.setText(String.valueOf(hargaAkhir));
    
    try {
        PreparedStatement stat = conn.prepareStatement(sql);
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        stat.setString(1, fm.format(jTanggal.getDate()));
        stat.setString(2, jNamaSayuran.getText());
        stat.setString(3, jKodeSayuran.getText());
        stat.setString(4, jJenisSayuran.getSelectedItem().toString());
        stat.setString(5, jJumlahStok.getText());
        stat.setString(6, jSatuanSayur.getSelectedItem().toString());
        stat.setString(7, jHargaAwal.getText());
        stat.setString(8, fm.format(jTanggalExp.getDate()));
        stat.setString(9, jHargaAkhir.getText());
        stat.setString(10, jSupplier.getSelectedItem().toString());   
        stat.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");     
        kosong();
        dataTable();
        lebarKolom();
        jKodeSayuran.requestFocus();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
    }
}
    }//GEN-LAST:event_jTambahActionPerformed

    private void jUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUbahActionPerformed
        String sql = "update input_data set tanggal=?,namaSayur=?,kodeSayur=?,jenisSayur=?,jumlahStok=?,satuan=?, hargaAwal=?, tanggalExp=?, hargaAkhir=?, supplier=? where kodeSayur='"+jKodeSayuran.getText()+"'";
        String tampilan = "dd-MM-yyyy";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(jTanggal.getDate()));
        String tanggal1 = String.valueOf( fm.format(jTanggalExp.getDate()));
        
        Date tanggalMasuk = jTanggal.getDate();
        Date tanggalExpired = jTanggalExp.getDate();
        double hargaAwal = Double.parseDouble(jHargaAwal.getText());
    
        if (hitungSelisihHari(tanggalMasuk, tanggalExpired) < 0) {
        JOptionPane.showMessageDialog(this, "Data sudah expired, tidak bisa diinput.");
        return; // Berhenti eksekusi jika sudah expired
        }

        int hargaAkhir = (int) hitungHargaAkhir(hargaAwal, tanggalMasuk, tanggalExpired);
        jHargaAkhir.setText(String.valueOf(hargaAkhir));
    
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tanggal.toString());
            stat.setString(2, jNamaSayuran.getText());
            stat.setString(3, jKodeSayuran.getText());
            stat.setString(4, jJenisSayuran.getSelectedItem().toString());
            stat.setString(5, jJumlahStok.getText());
            stat.setString(6, jSatuanSayur.getSelectedItem().toString());
            stat.setString(7, jHargaAwal.getText());
            stat.setString(8, tanggal1.toString());
            stat.setString(9, jHargaAkhir.getText());
            stat.setString(10, jSupplier.getSelectedItem().toString());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            String refresh = "select * from input_data";
            
            kosong();
            dataTable();
            lebarKolom();
            jKodeSayuran.requestFocus();
            } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
            }        
    }//GEN-LAST:event_jUbahActionPerformed

    private void jClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearActionPerformed
        tanggal();
        jKodeSayuran.requestFocus();
        jNamaSayuran.setText(null);
        jKodeSayuran.setText(null);
        jJumlahStok.setText(null);
        jHargaAwal.setText(null);
        jHargaAkhir.setText(null);
    }//GEN-LAST:event_jClearActionPerformed

    private void jTableSayuranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSayuranMouseClicked
            int bar = jTableSayuran.getSelectedRow();
            String k = tabmode.getValueAt(bar, 0).toString();
            String a = tabmode.getValueAt(bar, 1).toString();
            String b = tabmode.getValueAt(bar, 2).toString();
            String c = tabmode.getValueAt(bar, 3).toString();
            String d = tabmode.getValueAt(bar, 4).toString();
            String e = tabmode.getValueAt(bar, 5).toString();
            String f = tabmode.getValueAt(bar, 6).toString();
            String g = tabmode.getValueAt(bar, 7).toString();
            String h = tabmode.getValueAt(bar, 8).toString();
            String i = tabmode.getValueAt(bar, 9).toString();
            String j = tabmode.getValueAt(bar, 10).toString();

            SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
            Date dateValue = null;
            try{
                dateValue = date.parse((String)jTableSayuran.getValueAt(bar, 1));
            } catch (ParseException ex){
                Logger.getLogger(InputData.class.getName()).log(Level.SEVERE, null, ex);
            }
            Date dateValue1 = null;
            try{
                dateValue1 = date.parse((String)jTableSayuran.getValueAt(bar, 8));
            } catch (ParseException ex){
                Logger.getLogger(InputData.class.getName()).log(Level.SEVERE, null, ex);
            }

            jTanggal.setDate(dateValue);
            jNamaSayuran.setText(b);
            jKodeSayuran.setText(c);
            jJenisSayuran.setSelectedItem(d);
            jJumlahStok.setText(e);
            jSatuanSayur.setSelectedItem(f);
            jHargaAwal.setText(g);
            jTanggalExp.setDate(dateValue1);
            jHargaAkhir.setText(i);
            jSupplier.setSelectedItem(j);

    }//GEN-LAST:event_jTableSayuranMouseClicked

    private void jListSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListSupplierMouseClicked
        int bar = jListSupplier.getSelectedRow();
        String a = tabmode2.getValueAt(bar, 0).toString();
        String b = tabmode2.getValueAt(bar, 1).toString();
        jNamaSayuran.setText(a);
        jJenisSayuran.requestFocus();
    }//GEN-LAST:event_jListSupplierMouseClicked

    private void jDataSupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDataSupplierKeyTyped
        String sqlpencarian2 = "select * from input_data where supplier like '%"+jDataSupplier.getText()+"%'";
        pencarian2(sqlpencarian2);
        lebarKolom2();
    }//GEN-LAST:event_jDataSupplierKeyTyped

    private void jCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCariKeyTyped
        String sqlpencarian = "select * from input_data where namaSayur like '%"+jCari.getText()+"%' or kodeSayur like '%"+jCari.getText()+"%'or jenisSayur like '%"+jCari.getText()+"%'or jumlahStok like '%"+jCari.getText()+"%'or satuan like '%"+jCari.getText()+"%'or hargaAwal like '%"+jCari.getText()+"%'or tanggal like '%"+jCari.getText()+"%'or tanggalExp like '%"+jCari.getText()+"%'or hargaAkhir like '%"+jCari.getText()+"%'or supplier like '%"+jCari.getText()+"%'";
        pencarian(sqlpencarian);
        lebarKolom();
    }//GEN-LAST:event_jCariKeyTyped

    private void jJumlahStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJumlahStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jJumlahStokActionPerformed

    private void jJumlahStokKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jJumlahStokKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jJumlahStokKeyReleased

    private void jJumlahStokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jJumlahStokKeyTyped
    char inputChar = evt.getKeyChar();
    int keyCode = evt.getKeyCode();
    
    //mengecek huruf yg tidak bisa di tekan
    if (!(Character.isDigit(inputChar) || keyCode == KeyEvent.VK_BACK_SPACE)) {
    evt.consume();
    
        if (inputChar != KeyEvent.VK_BACK_SPACE) {
        JOptionPane.showMessageDialog(null, "Masukkan hanya angka", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }
}
    }//GEN-LAST:event_jJumlahStokKeyTyped

    private void jHargaAwalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jHargaAwalKeyTyped
    char inputChar = evt.getKeyChar();
    int keyCode = evt.getKeyCode();

    //mengecek huruf yg tidak bisa di tekan
    if (!(Character.isDigit(inputChar) || keyCode == KeyEvent.VK_BACK_SPACE)) {
    evt.consume();
    
        if (inputChar != KeyEvent.VK_BACK_SPACE) {
        JOptionPane.showMessageDialog(null, "Masukkan hanya angka", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }
}
    }//GEN-LAST:event_jHargaAwalKeyTyped

    private void jHargaAkhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHargaAkhirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jHargaAkhirActionPerformed

    private void jTInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTInputActionPerformed

    }//GEN-LAST:event_jTInputActionPerformed

    private void jTHarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTHarianActionPerformed
        LaporanHarian gui2 = new LaporanHarian();
        gui2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jTHarianActionPerformed

    private void jTanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTanggalPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTanggalPropertyChange

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
            java.util.logging.Logger.getLogger(InputData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel YurSayur;
    private javax.swing.JLabel dataSupplier;
    private javax.swing.JLabel hargaAkhir;
    private javax.swing.JLabel hargaJual;
    private javax.swing.JTextField jCari;
    private javax.swing.JButton jClear;
    private javax.swing.JTextField jDataSupplier;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JButton jHapus;
    private javax.swing.JTextField jHargaAkhir;
    private javax.swing.JTextField jHargaAwal;
    private javax.swing.JComboBox<String> jJenisSayuran;
    private javax.swing.JTextField jJumlahStok;
    private javax.swing.JTextField jKodeSayuran;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTable jListSupplier;
    private javax.swing.JTextField jNamaSayuran;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JComboBox<String> jSatuanSayur;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollTabel;
    private javax.swing.JComboBox<String> jSupplier;
    private javax.swing.JButton jTHarian;
    private javax.swing.JButton jTInput;
    private javax.swing.JTable jTableSayuran;
    private javax.swing.JButton jTambah;
    private com.toedter.calendar.JDateChooser jTanggal;
    private com.toedter.calendar.JDateChooser jTanggalExp;
    private javax.swing.JButton jUbah;
    private javax.swing.JLabel jenisSayuran;
    private javax.swing.JLabel judul;
    private javax.swing.JLabel jumlahStok;
    private javax.swing.JLabel kodeSayuran;
    private javax.swing.JLabel namaSayuran;
    private javax.swing.JLabel satuanSayur;
    private javax.swing.JLabel supplier;
    private javax.swing.JLabel tanggal;
    private javax.swing.JLabel tanggalExp;
    // End of variables declaration//GEN-END:variables
}