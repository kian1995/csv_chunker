package app;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Layout {

    public String csvFolderPath;
    public String savePath;

    public static void init()
    {
        // Init component objects
        JFrame frame                        = new JFrame("Csv Chunker");
        JPanel topPanel                     = new JPanel();
        JPanel topFileChooserPanel          = new JPanel(new GridLayout(1, 3));
        JPanel topFileSavePanel             = new JPanel(new GridLayout(1, 3));

        JPanel centerPanel                  = new JPanel(new GridBagLayout());

        JLabel openFileLabel                = new JLabel("CSV Datei auswählen: ");
        JButton openFileButton              = new JButton("CSV Auswählen");
        JTextField openFileLocationField    = new JTextField("Keine Datei ausgewählt...", 25);
        JLabel saveDestinationLabel         = new JLabel("Speicherort Auswählen: ");
        JButton saveDestinationButton       = new JButton("Öffnen");
        JTextField saveDestinationTextField = new JTextField("...", 25);
        JLabel chunkPrefixLabel             = new JLabel("Präfix der Chunk Dateien:", SwingConstants.LEFT);
        JTextField chunkPrefixField         = new JTextField("CHUNK-", 5);

        JPanel bottomPanel                  = new JPanel();
        JButton chunkCsvButton              = new JButton("CSV Aufteilen");
        JLabel chunkLineSizeLabel           = new JLabel("Anzahl der Zeilen pro Chunk: ");
        JTextField chunkLineSizeField       = new JTextField("1000", 5);
        JLabel headerRowLabel               = new JLabel("Kopfzeile (Headerzeile, von Gezählt): ");
        JTextField headerRow                = new JTextField("0", 5);

        // Add event listeners to components
        Listeners listeners = new Listeners();
        listeners.addListenerCsvFileSelect(openFileButton, openFileLocationField);
        listeners.addListenerSaveDestination(saveDestinationButton, saveDestinationTextField);
        listeners.addListenerChunkButton(chunkCsvButton, chunkLineSizeField, headerRow, chunkPrefixField);

        topPanel.setLayout(new GridLayout(2,1));
        topPanel.add(topFileChooserPanel);
        topPanel.add(topFileSavePanel);

        topFileChooserPanel.add(openFileLabel);
        topFileChooserPanel.add(openFileLocationField);
        topFileChooserPanel.add(openFileButton);

        topFileSavePanel.add(saveDestinationLabel);
        topFileSavePanel.add(saveDestinationTextField);
        topFileSavePanel.add(saveDestinationButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 90;
        gbc.weightx = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(chunkLineSizeLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        centerPanel.add(chunkLineSizeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(headerRowLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        centerPanel.add(headerRow, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(chunkPrefixLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        centerPanel.add(chunkPrefixField, gbc);

        // Sizes & Alignments
        headerRow           .setPreferredSize(new Dimension(2, 27));
        headerRowLabel      .setHorizontalAlignment(SwingConstants.LEFT);
        chunkLineSizeLabel  .setHorizontalAlignment(SwingConstants.LEFT);
        chunkLineSizeLabel  .setPreferredSize(new Dimension(200, 15));
        headerRowLabel      .setPreferredSize(new Dimension(200, 15));
        chunkPrefixLabel    .setPreferredSize(new Dimension(200, 15));
        chunkPrefixField    .setPreferredSize(new Dimension(2, 27));
        chunkCsvButton      .setPreferredSize(new Dimension(580, 30));

        // Borders
        topPanel    .setBorder(BorderFactory.createTitledBorder("CSV und Speicherort Auswählen"));
        centerPanel .setBorder(BorderFactory.createTitledBorder("Einstellungen"));
        bottomPanel .setBorder(new EmptyBorder(0, 10, 5, 10));

        bottomPanel.add(chunkCsvButton);

        frame.add(topPanel, BorderLayout.PAGE_START);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.PAGE_END);

        frame.pack();
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
