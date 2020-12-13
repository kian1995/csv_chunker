package app;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listeners {

    private final String[] csvFilePath   = new String[2];
    private final String[] savePath      = new String[1];

    public void addListenerCsvFileSelect(JButton button, JTextField textField)
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser csvFileDialog = new JFileChooser();
                csvFileDialog.setFileFilter(new FileNameExtensionFilter("Csv File", "csv"));
                int open = csvFileDialog.showOpenDialog(null);
                if (open == JFileChooser.APPROVE_OPTION) {
                    csvFilePath[0] = csvFileDialog.getSelectedFile().getAbsolutePath();
                    csvFilePath[1] = csvFileDialog.getSelectedFile().getName();
                    textField.setText(csvFilePath[0]);
                }
            }
        });

    }

    public void addListenerSaveDestination(JButton button, JTextField textField)
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser csvFileDialog = new JFileChooser();
                csvFileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int opend = csvFileDialog.showOpenDialog(null);
                if (opend == JFileChooser.APPROVE_OPTION) {
                    savePath[0] = csvFileDialog.getSelectedFile().getAbsolutePath();
                    textField.setText(savePath[0]);
                }
            }
        });
    }

    public void addListenerChunkButton(JButton button, JTextField chunkLineSizeField, JTextField headerRow, JTextField chunkPrefixField)
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (csvFilePath[0] == null) {
                    JOptionPane.showMessageDialog(null, "Es muss eine CSV Datei ausgewählt werden!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (savePath[0] == null) {
                    JOptionPane.showMessageDialog(null, "Es muss ein Speicherort angegeben werden!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (CsvHandler.chunkCsv(csvFilePath[0], chunkLineSizeField.getText(), csvFilePath[1], savePath[0], headerRow.getText(), chunkPrefixField.getText())) {
                    JOptionPane.showMessageDialog(null, "Ausgangs Csv Datei erfolgreich verkleinert.", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Es gab beim verkleinern einen Fehler. Operation nicht erfolgreich.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
