# Csv Chunker
Verkleinert CSV Dateien in kleinere Häppchen.

![myimage-alt-tag](docs/screen.png)

## Benutzung
Über den Button "CSV Auswählen" die CSV Datei auswählen, anschließend
den Speicherort auswählen, in denen die verkleinerten CSV Dateien abgelegt werden.

### Einstellungen
Es können zusätzliche Einstellungen definiert werden, u.a.
- **Anzahl der Zeilen Pro Chunk:** Wie viele Zeilen jeder Chunk hat (Default bei 1000)
- **Kopfzeile**: In Welcher Zeile sich der Header befindet (wird in jeden Chunk anschließend reingeschrieben)
- **Präfix**: Mit welcher Prefix die Chunk Dateinamen beginnen sollen (Default ist "Chunk")

## Kompilieren
Das Projekt ganz normal mit Maven bauen. Falls es aufgrund von Signed Jars zu
Problemen kommt, folgenden Konsolen Befehl ausführen:

```
zip -d CSV_Chunker.jar 'META-INF/*.SF' 'META-INF/*.RSA' 'META-INF/*SF'
```