package app;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CsvHandler {
    public static boolean chunkCsv(String path, String chunkLinesPerFile, String originalFilename, String savePath, String headerRowStart, String prefix) {
        try {
            Reader in                   = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
            ArrayList<String> csvRows   = new ArrayList<>();

            for (CSVRecord record : records) {
                String[] split = record.toString().split(Pattern.quote("values=["));
                csvRows.add(split[1].replace("]]", "") + "\n");
            }

            int headerRow               = Integer.parseInt(headerRowStart);
            String header               = csvRows.get(headerRow);
            int i                       = 0;
            int chunkFileId             = 0;
            int chunkSize               = Integer.parseInt(chunkLinesPerFile);
            BufferedWriter csvWriter    = new BufferedWriter(new FileWriter(savePath + "/" + prefix + Integer.toString(chunkFileId) + "-" + originalFilename, true));

            for (String row : csvRows) {
                csvWriter.append(row);
                i++;
                if (i == chunkSize) {
                    i = 0;
                    chunkFileId++;
                    csvWriter.close();
                    csvWriter = new BufferedWriter(new FileWriter(savePath + "/" + prefix + Integer.toString(chunkFileId) + "-" + originalFilename, true));
                    csvWriter.append(header);
                }
            }
            csvWriter.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
