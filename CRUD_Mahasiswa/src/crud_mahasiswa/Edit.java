/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud_mahasiswa;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

/**
 *
 * @author ASUS
 */
public class Edit extends JFrame{
    JLabel ljudul;
    JButton btnedit,btnkembali;
    Statement statement;
    ResultSet resultSet;
    String[][] data = new String[500][4];
    String[] kolom = {"Nim","Nama","Alamat"};
    JTable tabel;
    JScrollPane scrollPane;
    
    public Edit() throws ClassNotFoundException, SQLException{
        ljudul = new JLabel ("Seluruh Data Mahasiswa");
        btnedit = new JButton ("Edit");
        btnkembali = new JButton ("Kembali");
        tabel = new JTable(data,kolom);
        scrollPane = new JScrollPane(tabel);
        
        setTitle("EDIT DATA MAHASISWA");
        setSize (700,600);
        ljudul.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        setLayout(null);
        add(ljudul);
        add(btnedit);
        add(btnkembali);
        
        ljudul.setBounds(150, 50, 250, 30);
        btnedit.setBounds(150, 90, 100, 20);
        btnkembali.setBounds(270, 90, 100, 20);
        
        setLayout(new FlowLayout());
        add(scrollPane);
        
        KoneksiDB koneksi = new KoneksiDB();
        statement = koneksi.getKoneksi().createStatement();
        String sql = "SELECT *FROM data_mhs";
        resultSet = statement.executeQuery(sql);
        int p = 0;
            while (resultSet.next()){
                data[p][0] = resultSet.getString("nim"); // disesuaikan nama tabel database yang dibuat
                data[p][1] = resultSet.getString("nama");
               // data[p][2] = resultSet.getString("jk");
                data[p][2] = resultSet.getString("alamat");
                p++;                           
            }
            statement.close();
            
            btnedit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new ProsesEdit();
            }
        });
        
    }
    
}
