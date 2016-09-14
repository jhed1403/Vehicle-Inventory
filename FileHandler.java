/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicle.inventory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Jhed
 */
public class FileHandler {

    public static ArrayList<String> readFile(ArrayList<String> record) throws FileNotFoundException, IOException {
        FileReader one = new FileReader("cars.dat");
        BufferedReader buf = new BufferedReader(one);
        String line = buf.readLine();
        while (line != null) {
            record.add(line);
            line = buf.readLine();
        }
        return record;
    }

    public static void writeToFile(ArrayList<String> a) {
        try {
            FileWriter writer = new FileWriter("cars.dat");
            BufferedWriter buf = new BufferedWriter(writer);
            for (String x : a) {
                buf.write(x);
                buf.newLine();
            }
            buf.close();
        } catch (IOException e) {

        }
    }

    public static void removeData(ArrayList<String> record, int index) {
        for (int i = 0; i < record.size(); i++) {
            if (record.get(i).equals(record.get(index))) {
                record.remove(i);
            }
        }
        FileHandler.writeToFile(record);
    }

}
