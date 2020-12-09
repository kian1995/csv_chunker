package app;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CsvHandler {
    private static final String CSV_BASENAME = "CHUNK-";

    public static void chunkCsv(String path, String chunkLinesPerFile) {
        try {
            Reader in                   = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
            ArrayList<String> csvRows   = new ArrayList<>();

            for (CSVRecord record : records) {
                String[] split = record.toString().split(Pattern.quote("values=["));
                csvRows.add(split[1].replace("]]", "") + "\n");
            }

            int headerRow               = 0;
            String header               = csvRows.get(headerRow);
            int i                       = 0;
            int chunkFileId             = 0;
            int chunkSize               = Integer.parseInt(chunkLinesPerFile);
            BufferedWriter csvWriter    = new BufferedWriter(new FileWriter(CSV_BASENAME + Integer.toString(chunkFileId) + ".csv", true));

            for (String row : csvRows) {
                csvWriter.append(row);
                i++;
                if (i == chunkSize) {
                    i = 0;
                    chunkFileId++;
                    csvWriter.close();
                    csvWriter = new BufferedWriter(new FileWriter(CSV_BASENAME + Integer.toString(chunkFileId) + ".csv", true));
                    csvWriter.append(header);
                }
            }
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
