package com.autospa.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class FilesOperations {
	private File file;
	private static FileWriter fileWriter;

	public static void saveDataToFile(String dataToSave) {
		try {
			if (System.getProperty("os.name").split(" ")[0].equals("Windows")) {
				fileWriter = new FileWriter(ServerProperties.LOG_PATH_WINDOWS
						+ "serverLog.txt", true);
			} else if (System.getProperty("os.name").split(" ")[0]
					.equals("Linux")) {
				fileWriter = new FileWriter(ServerProperties.LOG_PATH_LINUX
						+ "serverLog.txt", true);
			}
			BufferedWriter out = new BufferedWriter(fileWriter);
			out.write(dataToSave);
			out.newLine();
			out.close();
		} catch (Exception e) {
			System.err.println("Błąd podczas zapisu: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Zapis logu nie powiódł się");
		}
	}
}
