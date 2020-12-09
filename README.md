# Csv Chunker
Verkleinert CSV Dateien in kleinere Häppchen.

![myimage-alt-tag](docs/screen.png)

## Benutzung
Über den Button "CSV Auswählen" die CSV Datei auswählen, anschließend
kann die Anzahl der Zeilen pro Chunk file festgelegt werden.

Die verkleinerten CSV Dateiein sind dann im selben Ordner wie die .jar und
und fangen mit der Präfix "chunk-", gefolgt von einer Id und dem Dateinamen an.

## Kompilieren
Das Projekt ganz normal mit Maven bauen. Falls es aufgrund von Signed Jars zu
Problemen kommt, folgenden Konsolen Befehl ausführen:

```
zip -d CSV_Chunker.jar 'META-INF/*.SF' 'META-INF/*.RSA' 'META-INF/*SF'
```