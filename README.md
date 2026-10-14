# Stundenplan-Backend
Für Stundenplanprojekt

## Git- und Branch-Regeln

### Branch-Regeln

- main enthält den freigegebenen Projektstand.
- Für jede User Story wird ein eigener Branch erstellt.
- Branch-Namen enthalten die Nummer und eine kurze Beschreibung der User Story.
- Änderungen werden über Pull Requests in main übernommen.

### Branch-Namenskonvention

Jeder Branch enthält die Nummer und eine kurze Beschreibung der User Story.

**Beispiel:**

`US-1.3-klare-anleitung-fuer-die-entwicklungsumgebung`

### Wichtige Git-Befehle

| Befehl | Beschreibung |
|---|---|
| `git clone URL` | Repository von GitHub herunterladen |
| `git status` | Aktuelle Änderungen anzeigen |
| `git switch main` | Zum Hauptbranch wechseln |
| `git pull origin main` | Aktuelle Änderungen von main herunterladen |
| `git switch -c BRANCHNAME` | Neuen Branch erstellen |
| `git add DATEINAME` | Datei für einen Commit vormerken |
| `git commit -m "Nachricht"` | Änderungen lokal speichern |
| `git push` | Commits auf GitHub hochladen |

### Commit-Regeln

- Jeder Commit soll eine verständliche Nachricht haben.
- Ein Commit soll zusammengehörige Änderungen enthalten.
- Änderungen werden auf dem eigenen User-Story-Branch vorgenommen.

**Beispiel:**

`git commit -m "docs: Git- und Branch-Regeln dokumentieren"`

### Pull-Request-Regeln

- Nach Abschluss der Arbeit wird ein Pull Request erstellt.
- Der Pull Request führt vom User-Story-Branch nach `main`.
- Im Pull Request werden die Änderungen beschrieben.
- Die Änderungen werden vor dem Merge überprüft.
- Erst nach erfolgreicher Prüfung werden sie in `main` übernommen.