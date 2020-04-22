/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud_mahasiswa;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author ASUS
 */
class HapusData extends JFrame {

    Statement statement;
    ResultSet resultSet;
    JButton btnhapus, btnkembali;
    JLabel lJudul;
    String[][] data = new String[500][4];
    String[] kolom = {"Nim", "Nama", "Alamat"};
    JTable tabel;
    JScrollPane scrollPane;
    String DBurl = "jdbc:mysql;//localhost/mahsiswa";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;

    public HapusData() throws ClassNotFoundException, SQLException {
        lJudul = new JLabel("Hapus Data Mahasiswa");
        btnhapus = new JButton("Hapus");
        btnkembali = new JButton("Kembali");
        tabel = new JTable(data, kolom);
        scrollPane = new JScrollPane(tabel);

        setTitle("Hapus Data Mahasiswa");
        setSize(700,600);
        lJudul.setHorizontalAlignment(SwingConstants.CENTER);
        KoneksiDB koneksi = new KoneksiDB();
        statement = koneksi.getKoneksi().createStatement();
        String sql = "SELECT *FROM data_mhs";
        resultSet = statement.executeQuery(sql);
        int p = 0;
        while (resultSet.next()) {
            data[p][0] = resultSet.getString("nim"); // disesuaikan nama tabel database yang dibuat
            data[p][1] = resultSet.getString("nama");
            // data[p][2] = resultSet.getString("jk");
            data[p][2] = resultSet.getString("alamat");
            p++;
        }
        setLayout(null);
        add(lJudul);
        add(btnhapus);
        add(btnkembali);

        lJudul.setBounds(50, 10, 150, 25);
        btnkembali.setBounds(30, 90, 100, 25);
        btnhapus.setBounds(140, 90, 100, 25);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

       // new LihatDataMahasiswa();
        btnkembali.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new Menu();
            }
        });

        btnhapus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                KoneksiDB koneksi = new KoneksiDB();
                try {
                    statement = koneksi.getKoneksi().createStatement();

                    statement.executeUpdate("DELETE FROM data_mhs");
                    JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
                    statement.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus!", "Hasil", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

}
