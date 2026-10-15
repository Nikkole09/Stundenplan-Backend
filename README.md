# Stundenplan-Backend
Für Stundenplanprojekt

Das Backend unserer Stundenplan-Anwendung wurde mit Java 25, Spring Boot 4.1.1 und Maven erstellt. Es stellt REST-Endpunkte bereit, die über Swagger UI dokumentiert und getestet werden können.

## Startanleitung für das Backend (Spring Boot)

Diese Anleitung beschreibt, wie das Backend des Stundenplan-Projekts lokal eingerichtet und gestartet werden kann.

### Voraussetzungen

Für die lokale Entwicklung werden folgende Programme benötigt:

- **Git** – zum Herunterladen und Aktualisieren des Projekts.
- **Java JDK** – passende Version gemäß `pom.xml`.
- **Visual Studio Code** – als Entwicklungsumgebung.
- **Maven** – wird über den im Projekt enthaltenen Maven Wrapper bereitgestellt.

Die Installation kann im Terminal überprüft werden mit: java -version und mvn -version

### Backend installieren und starten

1. Das Repository `Stundenplan-Backend` von GitHub herunterladen:

   `git clone https://github.com/Nikkole09/Stundenplan-Backend.git`

2. Den Ordner `Stundenplan-Backend` in Visual Studio Code/ IntelliJ IDEA wechseln.

3. Ein Terminal im Backend-Ordner öffnen.

4. Das Spring-Boot-Backend starten:

   `.\mvnw.cmd spring-boot:run`

5. Nach erfolgreichem Start ist das Backend standardmäßig unter folgender Adresse erreichbar:

   `http://localhost:8080`

   Dies gilt, sofern kein anderer Port konfiguriert wurde.

6. Backend testen
Folgende Adressen können im Browser geöffnet werden:

Test-Endpunkt:   http://localhost:8080/api/test

Swagger UI:   http://localhost:8080/swagger-ui.html

OpenAPI-Dokumentation:   http://localhost:8080/v3/api-docs

Beim Aufrufen des Test-Endpunkts wird "Hallo vom Stundenplan-Backend!" angezeigt.

7. Endpunkt mit Swagger testen:
- Swagger UI unter http://localhost:8080/swagger-ui.html öffnen.
- Den Endpunkt GET /api/test auswählen.
- Auf Try it out klicken.
- Auf Execute klicken.

## Git- und Branch-Regeln

Die Git- und Branch-Regeln für unser Stundenplan-Projekt sind im GitHub Wiki dokumentiert.

Dort werden die Branch-Namenskonventionen, wichtigsten Git-Befehle, Commit-Regeln und der Pull-Request-Ablauf erklärt.

[Git- und Branch-Regeln im Wiki](https://github.com/Nikkole09/Stundenplan-Backend/wiki)

### Hinweise

- Das Terminal muss während der Ausführung geöffnet bleiben.
- Der Maven Wrapper lädt benötigte Abhängigkeiten bei Bedarf herunter.
- Zum Beenden des Backends `Strg + C` im Terminal drücken.
- Port 8080 muss frei sein, damit die Anwendung starten kann.
