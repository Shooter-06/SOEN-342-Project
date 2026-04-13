package service;

import java.io.*;
import java.util.*;
import model.Task;

public class CSVService {

    public List<String[]> readFile(String filePath) throws Exception {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.split(","));
            }
        }
        return rows;
    }

    public void writeFile(List<Task> tasks, String filePath) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task t : tasks) {
                bw.write(t.getTitle() + "," + t.getDescription());
                bw.newLine();
            }
        }
    }
}
