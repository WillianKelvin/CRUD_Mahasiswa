/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud_mahasiswa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import javax.swing.*;

/**
 *
 * @author WILLIAN KELVIN NATA -123180004
 * 
 */
class InputDataMahasiswa extends JFrame {
    JLabel ltitle,lnama,lnim,lalamat;
    JTextField txnama,txnim,txalamat;
    JButton btnsimpan, btnkembali;
    Statement statement;
    
    public InputDataMahasiswa() {
        setTitle("INPUT DATA MAHASISWA");
        ltitle = new JLabel("Input Data Mahasiswa");
        lnama = new JLabel("Nama   : ");
        lnim = new JLabel("NIM      :");
        lalamat = new JLabel ("Alamat : ");
        
        txnama = new JTextField("");
        txnim = new JTextField("");
        txalamat = new JTextField("");
        
        btnsimpan = new JButton("Simpan");
        btnkembali = new JButton("Kembali");
        
        setLayout(null);
        add(ltitle);
        add(lnama);
        add(lnim);
        add(lalamat);
        
        add(txnama);
        add(txnim);
        add(txalamat);
        
        add(btnsimpan);
        add(btnkembali);
        
        ltitle.setBounds(150, 50, 150, 30);
        lnama.setBounds(150, 90, 50, 20);
        lnim.setBounds(150, 130, 50, 20);
        lalamat.setBounds(150, 170, 50, 20);
        txnama.setBounds(200, 90, 120, 20);
        txnim.setBounds(200, 130, 120, 20);
        txalamat.setBounds(200, 170, 120, 50);
        btnsimpan.setBounds(150, 230, 90, 20);
        btnkembali.setBounds(250, 230, 90, 20);

        setSize(500,400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        btnsimpan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String a1 = txnama.getText();
                    int a2 = Integer.parseInt(txnim.getText());
                    String a3 = txalamat.getText();
                    
                    KoneksiDB koneksi = new KoneksiDB();
                    try {
                        statement = koneksi.getKoneksi().createStatement();
                        statement.executeUpdate("INSERT INTO data_mhs VALUES('"
                                + a2 + "','" +a1+ "','" + a3 + "')");
                        JOptionPane.showMessageDialog(rootPane, "Data Tersimpan");
                    } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(InputDataMahasiswa.class.getName()).log(Level.SEVERE, null, ex);
                    }catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(InputDataMahasiswa.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "TIPE DATA SALAH");
                } catch (Error ext) {
                    JOptionPane.showMessageDialog(rootPane, "SALAH");

                }
                
            }
        });
        
        btnkembali.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new Menu();
            }
        });

    }
    
}
