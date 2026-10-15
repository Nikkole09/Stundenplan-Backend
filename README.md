# Stundenplan-Backend
Für Stundenplanprojekt

Das Backend unserer Stundenplan-Anwendung wurde mit Java 25, Spring Boot 4.1.1 und Maven erstellt. Es stellt REST-Endpunkte bereit, die über Swagger UI dokumentiert und getestet werden können.

1. Voraussetzungen: 
Folgende Programme müssen installiert sein:
- Java JDK 25
- Apache Maven 3.10.0
- Git (zum Klonen des Projekts)

Die Installation kann im Terminal überprüft werden mit: java -version und mvn -version

2. Projekt herunterladen:
Man muss das Repository von GitHub klonen (https://github.com/Nikkole09/Stundenplan-Backend.git),
dann in den Projektordner wechseln (mit cd Stundenplan-Backend)

3. Backend starten:
Im Projektordner folgenden Befehl ausführen:  mvn spring-boot:run

Maven lädt bei Bedarf die erforderlichen Abhängigkeiten herunter und startet die Spring-Boot-Anwendung.

Wenn im Terminal Started Application erscheint, wurde das Backend erfolgreich gestartet.
Die Anwendung läuft auf Port 8080.

4. Backend testen
Folgende Adressen können im Browser geöffnet werden:

Test-Endpunkt:   http://localhost:8080/api/test

Swagger UI:   http://localhost:8080/swagger-ui.html

OpenAPI-Dokumentation:   http://localhost:8080/v3/api-docs

Beim Aufrufen des Test-Endpunkts wird "Hallo vom Stundenplan-Backend!" angezeigt.

5. Endpunkt mit Swagger testen:
- Swagger UI unter http://localhost:8080/swagger-ui.html öffnen.
- Den Endpunkt GET /api/test auswählen.
- Auf Try it out klicken.
- Auf Execute klicken.

Bei erfolgreicher Ausführung erscheint der HTTP-Statuscode 200 OK und die Antwort "Hallo vom Stundenplan-Backend!".

6. Backend beenden:
- Im Terminal, in dem das Backend läuft, Strg + C drücken.

Hinweis: Port 8080 muss frei sein, damit die Anwendung starten kann.