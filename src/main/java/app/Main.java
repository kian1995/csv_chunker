package app;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LafManager.install();
            LafManager.install(new DarculaTheme());

            JFrame frame                        = new JFrame("Csv Chunker");
            JPanel topPanel                     = new JPanel();
            JPanel centerPanel                  = new JPanel();
            JLabel openFileLabel                = new JLabel("CSV Datei auswählen: ");
            JButton openFileButton              = new JButton("CSV Auswählen");
            JTextField openFileLocationField    = new JTextField("Keine Datei ausgewählt...", 25);
            JButton chunkCsvButton              = new JButton("CSV verkleinern");
            JLabel chunkLineSizeLabel           = new JLabel("Anzahl der Zeilen pro Chunk: ");
            JTextField chunkLineSizeField       = new JTextField("100", 5);

            final String[] csvFilePath = {""};

            openFileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser csvFileDialog = new JFileChooser();
                    int opend = csvFileDialog.showOpenDialog(null);
                    if (opend == JFileChooser.APPROVE_OPTION) {
                        csvFilePath[0] = csvFileDialog.getSelectedFile().getAbsolutePath();
                        openFileLocationField.setText(csvFilePath[0]);
                    }
                }
            });

            chunkCsvButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CsvHandler.chunkCsv(csvFilePath[0], chunkLineSizeField.getText());
                }
            });

            topPanel.setBorder(new EmptyBorder(20, 10, 10, 10));
            centerPanel.setBorder(new EmptyBorder(100, 10, 10, 10));

            topPanel.add(openFileLabel);
            topPanel.add(openFileLocationField);
            topPanel.add(openFileButton);

            centerPanel.add(chunkLineSizeLabel);
            centerPanel.add(chunkLineSizeField);

            frame.add(topPanel, BorderLayout.PAGE_START);
            frame.add(centerPanel, BorderLayout.CENTER);
            frame.add(chunkCsvButton, BorderLayout.PAGE_END);

            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}
