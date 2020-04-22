/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud_mahasiswa;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

/**
 *
 * @author ASUS
 */
class LihatDataMahasiswa extends JFrame {

    String[][] data = new String[500][4];
    String[] kolom = {"Nama", "Nim", "Alamat"};
    JLabel ltitle;
    JTable tabel;
    JScrollPane scrollPane;
    JButton btnkembali;
    String DBurl = "jdbc:mysql;//localhost/mahasiswadata";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;
    Statement statement;
    ResultSet resultSet;

    public LihatDataMahasiswa() {
        setTitle("TAMPIL DATA MAHASISWA");

        ltitle = new JLabel("Data Seluruh Mahasiswa");
        btnkembali = new JButton("Kembali");
        tabel = new JTable(data, kolom);
        scrollPane = new JScrollPane(tabel);

        
        KoneksiDB koneksi = new KoneksiDB();
        try {
            statement = koneksi.getKoneksi().createStatement();
            String sql = "SELECT *FROM data_mhs";
            resultSet = statement.executeQuery(sql);

            int p = 0;
            while (resultSet.next()) {
                data[p][0] = resultSet.getString("nama");
                data[p][1] = resultSet.getString("nim");
                data[p][2] = resultSet.getString("alamat");
                p++;
            }
            statement.close();

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(rootPane, "Data gagal ditampilkan!" + sqle);
        } catch (ClassNotFoundException classe) {
            JOptionPane.showMessageDialog(rootPane, "Class Tidak ditemukan!");
        }
        setLayout(new FlowLayout());
//        add(ltitle);
        
//        add(tabel);
        add(btnkembali);
        add(scrollPane);
        
//        ltitle.setBounds(150, 50, 150, 30);
//        btnkembali.setBounds(200, 90, 100, 30);
  
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

        btnkembali.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new Menu();
            }
        });

    }

}
