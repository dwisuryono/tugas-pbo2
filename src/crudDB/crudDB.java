/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudDB;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.mkyong.xml.sax.handler.PrintAllHandlerSax;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.File;
import java.util.HashMap;
//Berfungsi mengambil file laporan yang dibuat dari IReport
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager; 
import net.sf.jasperreports.engine.JasperFillManager; 
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery; 
import net.sf.jasperreports.engine.design.JasperDesign; 
import net.sf.jasperreports.engine.xml.JRXmlLoader; 
import net.sf.jasperreports.view.JasperViewer;
/*Semua  library  bagia  jasperreport  berfungsi  untuk  penampilan  laporan  file
.jrxml baik berupa compile, query pada laporan, preview laporan, dll*/


/**
 *
 * @author WIN10
 */
public class crudDB {
    
    private String jdbcURL = "jdbc:mysql://localhost:3306/2310010690_pertanahan";
    private String username = "root";
    private String password = "";
    
    private DefaultTableModel Model;
    private TableColumn Kolomnya;
    
    public crudDB() {}
    
    public Connection getKoneksiDB() throws SQLException {
        try {
            Driver mysqldriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(mysqldriver);
            System.out.println("Koneksi DB Berhasil");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return DriverManager.getConnection(jdbcURL, username, password);
    }
    
    public boolean DuplicateKey( String namaTabel, String primaryKey, String isiData ) {
        boolean hasil = false;
        int jumlah = 0;
        
        try {
            String SQL = "SELECT * FROM " + namaTabel + " WHERE " + primaryKey + " = '" + isiData + "'";
            Statement perintah = getKoneksiDB().createStatement();
            ResultSet hasilData = perintah.executeQuery(SQL);
            while( hasilData.next() ) {
                jumlah++;
            }
            
            if( jumlah == 1 ) {
                hasil = true;
            }else {
                hasil = false;
            }
            
            System.out.println(jumlah);
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return hasil;
    }
    
    public String getFieldTabel(String[] FieldTabel) {
        String hasil = "";
        int deteksiIndexAkhir = FieldTabel.length-1;
        
        try {
            for( int i = 0; i < FieldTabel.length; i++ ) {
                
                if( i == deteksiIndexAkhir ) {
                    hasil = hasil + FieldTabel[i];
                }else {
                    hasil = hasil + FieldTabel[i] + ",";
                }
                
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return "(" + hasil + ")";
    }
    
    public String getIsiTabel(String[] isiTabel) {
        String hasil = "";
        int deteksiIndex = isiTabel.length-1;
        
        try {
            for( int i = 0; i < isiTabel.length; i++ ) {
                
                if( i == deteksiIndex ) {
                    hasil = hasil + "'" + isiTabel[i] + "'";
                }else {
                    hasil = hasil + "'" + isiTabel[i] + "',";
                }
                
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return "(" + hasil + ")";
    }
    
    public void simpanDinamis(String namaTabel, String[] field, String[] isi) {
        try {
            String SQLSave = "INSERT INTO " + namaTabel + " " + getFieldTabel(field) + " VALUES " + getIsiTabel(isi);
            Statement perintah = getKoneksiDB().createStatement();
            perintah.executeUpdate(SQLSave);
            perintah.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
     public String getFieldValueEdit(String[] Field, String[] value){
        String hasil = "";
        int deteksi = Field.length-1;
        try {
            for (int i = 0; i < Field.length; i++) {
                if (i==deteksi){
                   hasil = hasil +Field[i]+" ='"+value[i]+"'";
                }else{
                   hasil = hasil +Field[i]+" ='"+value[i]+"',";  
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return hasil;
    }

    public void UbahDinamis(String NamaTabel, String PrimaryKey, String IsiPrimary,String[] Field, String[] Value){
        
        try {
           String SQLUbah = "UPDATE " + NamaTabel + " SET " + getFieldValueEdit(Field, Value) + " WHERE " + PrimaryKey + "='"
                   + IsiPrimary + "'";
           Statement perintah = getKoneksiDB().createStatement();
           perintah.executeUpdate(SQLUbah);
           perintah.close();
           getKoneksiDB().close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        
    }

    public void HapusDinamis(String NamaTabel, String PK, String isi){
        try {
            String SQL="DELETE FROM " + NamaTabel + " WHERE " + PK + "='" + isi + "'";
            Statement perintah = getKoneksiDB().createStatement();
            perintah.executeUpdate(SQL);
            perintah.close();
            JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void settingJudulTabel(JTable Tabelnya, String[] JudulKolom){
        try {
            Model = new DefaultTableModel();
            Tabelnya.setModel(Model);
            for (int i = 0; i < JudulKolom.length; i++) {
                Model.addColumn(JudulKolom[i]);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void settingLebarKolom(JTable Tabelnya,int[] Kolom){
      try {
            Tabelnya.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            for (int i = 0; i < Kolom.length; i++) {
                Kolomnya = Tabelnya.getColumnModel().getColumn(i);
                Kolomnya.setPreferredWidth(Kolom[i]);   
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public Object[][] isiTabel(String SQL, int jumlah){
        Object[][] data =null;
        try {
            Statement perintah = getKoneksiDB().createStatement();
            ResultSet dataset = perintah.executeQuery(SQL);
            dataset.last();
            int baris = dataset.getRow();
            dataset.beforeFirst();
            int j = 0;
         
            data = new Object[baris][jumlah];
         
            while (dataset.next()){
                for (int i = 0; i < jumlah; i++) {
                    data[j][i]=dataset.getString(i+1);
                }
            j++;
         }
         
      } catch (Exception e) {
      }
      
      return data;
    }
    
    public void tampilTabel(JTable Tabel, String SQL, String[] Judul){
        try {
          Tabel.setModel(new DefaultTableModel(isiTabel(SQL,Judul.length), Judul));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void tampilLaporan(String laporanFile, String SQL){
        try {
//          File file = new File(laporanFile);
//          JasperDesign jasDes = JRXmlLoader.load(file);
//
//          JRDesignQuery sqlQuery = new JRDesignQuery();
//          sqlQuery.setText(SQL);
//          jasDes.setQuery(sqlQuery);
//
//          JasperReport JR = JasperCompileManager.compileReport(jasDes);
//          JasperPrint JP = JasperFillManager.fillReport(JR,null,getKoneksiDB()); 
//          JasperViewer.viewReport(JP,false);

            String reportPath = laporanFile;

            HashMap<String, Object> parameters = new HashMap<>();

            JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, getKoneksiDB());

            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null,e.toString());       

        }
    }
    
}
