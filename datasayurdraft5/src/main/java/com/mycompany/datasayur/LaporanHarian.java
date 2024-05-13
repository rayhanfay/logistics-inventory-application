//LaporanHarian.java (Melihat data harian)

package com.mycompany.datasayur;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class LaporanHarian extends javax.swing.JFrame {

  public final Connection conn = new Datasayur().connect();
    
    private DefaultTableModel tabmode;
        
    public void noTable(){
        int Baris = tabmode.getRowCount();
        for (int a=0; a<Baris; a++)
        {
            String nomor = String.valueOf(a+1);
            tabmode.setValueAt(nomor +".",a,0);
        }
    }  
    
        public void dataTable(){
        Object[] Baris = {"No","Tanggal","Nama Sayuran","Kode Sayuran","Jenis Sayuran","Jumlah Stok","Satuan","Harga Awal", "Tanggal Expired", "Harga Akhir", "Supplier"};
        tabmode = new DefaultTableModel(null, Baris);
        jLaporanHarian.setModel(tabmode);
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
        
        public void pencarian(String sql){
        Object[] Baris = {"No","Tanggal","Nama Sayuran","Kode Sayuran","Jenis Sayuran","Jumlah Stok","Satuan","Harga Awal", "Tanggal Expired", "Harga Akhir", "Supplier"};
        tabmode = new DefaultTableModel(null, Baris);
        jLaporanHarian.setModel(tabmode);
        int brs = jLaporanHarian.getRowCount();
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
        
            public void lebarKolom(){
        TableColumn column;
        jLaporanHarian.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = jLaporanHarian.getColumnModel().getColumn(0);
        column.setPreferredWidth(34);
        column = jLaporanHarian.getColumnModel().getColumn(1);
        column.setPreferredWidth(130);
        column = jLaporanHarian.getColumnModel().getColumn(2);
        column.setPreferredWidth(130);
        column = jLaporanHarian.getColumnModel().getColumn(3);
        column.setPreferredWidth(130);
        column = jLaporanHarian.getColumnModel().getColumn(4);
        column.setPreferredWidth(130);
        column = jLaporanHarian.getColumnModel().getColumn(5);
        column.setPreferredWidth(130);
        column = jLaporanHarian.getColumnModel().getColumn(6);
        column.setPreferredWidth(130);
        column = jLaporanHarian.getColumnModel().getColumn(7);
        column.setPreferredWidth(130);
        column = jLaporanHarian.getColumnModel().getColumn(8);
        column.setPreferredWidth(130);
        column = jLaporanHarian.getColumnModel().getColumn(9);
        column.setPreferredWidth(130);
        column = jLaporanHarian.getColumnModel().getColumn(10);
        column.setPreferredWidth(130);
        
        JTableHeader header = jLaporanHarian.getTableHeader();
        header.setDefaultRenderer(new LaporanHarian.HeaderRenderer());
    }
    
    class HeaderRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBackground(new Color(66, 163, 167)); 
        return this;
    }
}
        
    public LaporanHarian() {
        initComponents();
        dataTable();
        lebarKolom();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        judul = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTInput1 = new javax.swing.JButton();
        jTHarian = new javax.swing.JButton();
        YurSayur = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        laporanHarianku = new javax.swing.JLabel();
        tanggal = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLaporanHarian = new javax.swing.JTable();
        jPilihan = new com.toedter.calendar.JDateChooser();

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
                .addGap(647, 647, 647)
                .addComponent(judul)
                .addContainerGap(463, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(judul, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jTInput1.setBackground(new java.awt.Color(0, 102, 102));
        jTInput1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jTInput1.setForeground(new java.awt.Color(255, 255, 255));
        jTInput1.setText("Input Data");
        jTInput1.setPreferredSize(new java.awt.Dimension(131, 29));
        jTInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTInput1ActionPerformed(evt);
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
                .addGap(19, 19, 19)
                .addComponent(YurSayur, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTInput1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTHarian, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jTInput1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTHarian, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(YurSayur, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(66, 163, 167));
        jPanel5.setForeground(new java.awt.Color(0, 153, 255));

        laporanHarianku.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        laporanHarianku.setForeground(new java.awt.Color(255, 255, 255));
        laporanHarianku.setText("Laporan Harian");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(laporanHarianku, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(laporanHarianku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tanggal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tanggal.setText("Tanggal :");

        jLaporanHarian.setBackground(new java.awt.Color(147, 218, 220));
        jLaporanHarian.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLaporanHarian.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tanggal", "Nama Sayuran", "Kode Sayuran", "Jenis Sayuran", "Jumlah Stok", "Satuan", "Harga Awal", "Tanggal Expired", "Harga Akhir", "Supplier"
            }
        ));
        jLaporanHarian.setSelectionBackground(new java.awt.Color(0, 204, 204));
        jLaporanHarian.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(jLaporanHarian);

        jPilihan.setBackground(new java.awt.Color(235, 235, 235));
        jPilihan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPilihanMouseClicked(evt);
            }
        });
        jPilihan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPilihanPropertyChange(evt);
            }
        });
        jPilihan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPilihanKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPilihanKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(tanggal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPilihan, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tanggal)
                    .addComponent(jPilihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPilihanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPilihanMouseClicked

    }//GEN-LAST:event_jPilihanMouseClicked

    private void jPilihanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPilihanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPilihanKeyPressed

    private void jPilihanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPilihanKeyTyped

    }//GEN-LAST:event_jPilihanKeyTyped

    private void jPilihanPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPilihanPropertyChange
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date selectedDate = jPilihan.getDate();

        if (selectedDate != null) {
        String formattedDate = sdf.format(selectedDate);
        String sqlpencarian = "SELECT * FROM input_data WHERE tanggal LIKE '%" + formattedDate + "%'";
    
        pencarian(sqlpencarian);
            lebarKolom();
        } else {
        System.out.println("");
}
    }//GEN-LAST:event_jPilihanPropertyChange

    private void jTInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTInput1ActionPerformed
        InputData gui = new InputData();
        gui.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jTInput1ActionPerformed

    private void jTHarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTHarianActionPerformed

    }//GEN-LAST:event_jTHarianActionPerformed


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
            java.util.logging.Logger.getLogger(LaporanHarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaporanHarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaporanHarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaporanHarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LaporanHarian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel YurSayur;
    private javax.swing.JTable jLaporanHarian;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private com.toedter.calendar.JDateChooser jPilihan;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jTHarian;
    private javax.swing.JButton jTInput1;
    private javax.swing.JLabel judul;
    private javax.swing.JLabel laporanHarianku;
    private javax.swing.JLabel tanggal;
    // End of variables declaration//GEN-END:variables
}
