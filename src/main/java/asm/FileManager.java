package asm;

import model.Employee;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private static File fileSelected;

    private static FileManager instance;

    public static FileManager getInstance() {  // Singleton
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    public static File chooseFile() { //pick file
        String userhome = System.getProperty("user.home");
        JFileChooser fileChooser = new JFileChooser(userhome + "\\Desktop");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Choose file");
        fileChooser.setApproveButtonText("Choose");
        fileChooser.setApproveButtonToolTipText("Choose file");
        fileChooser.showOpenDialog(null);
        return fileChooser.getSelectedFile();
    }

    public static ArrayList<Employee> readFile(File file) {
        ArrayList<Employee> employees = new ArrayList<>();
        Scanner scanner = null;

        try {
            if (file == null) {
               scanner = new Scanner(new File("data.txt"));
                fileSelected = new File("data.txt");
            } else {
                scanner = new Scanner(file);
                fileSelected = file;
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split("\\|");
                Employee employee = new Employee(data[0], data[1], Integer.parseInt(data[2]), data[3], Float.parseFloat(data[4]));
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public void writeFile(ArrayList<Employee> employees) {
        try {
            FileWriter fileWriter = new FileWriter("data.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Employee employee : employees) {
                bufferedWriter.write(employee.getMaNV() + "|" + employee.getHoTen() + "|" + employee.getTuoi() + "|" + employee.getEmail() + "|" + employee.getLuong() + "|" + employee.getTuoi() + "\n");
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
