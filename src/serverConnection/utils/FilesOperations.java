/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverConnection.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateusz Olczak
 */
public class FilesOperations {

    private File file;

    public static void saveDataToFile(String dataToSave) {
        try {
            FileWriter fstream = new FileWriter("/home/dev/serverLog.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(dataToSave);
            out.newLine();
            out.close();
        } catch (Exception e) {
            System.err.println("Błąd podczas zapisu: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Zapis logu nie powiódł się");
        }
    }
}
