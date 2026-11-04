package htw.projekt.stundenplan.backend.security;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwtSecret}")
    private String jwtSecret; // Liest Secret aus application.properties -> Geheimer Stempel zum Unterschreiben

    @Value("${app.jwtExpirationMs}")
    private long jwtExpirationMs; // Liest Ablaufzeit aus application.properties -> Wie lange Token gültig (86400000 = 24h)
    /**
     * Macht aus Text Secret einen echten Schlüssel (Key Objekt)
     * HS512 braucht Key Objekt, nicht nur Text!
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
    /**
     * Erzeugt neues Token (Ticket) für User
     * Wird bei jedem Login aufgerufen -> Jedes Mal anderes Token wegen Zeit
     *
     * @param email Email des Users
     * @return Token String
     */
    public String generateToken(String email) {
        Date now = new Date(); // Aktuelle Zeit = Wann Token erzeugt wird
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs); // Ablaufzeit = Jetzt + 24h (86400000ms) -> Wann Token abläuft
        // Baut Token zusammen
        return Jwts.builder()
                .setSubject(email) // Wer darf rein? Email reinschreiben
                .setIssuedAt(now) // Wann erzeugt?
                .setExpiration(expiryDate) // Wann läuft ab?
                .signWith(getSigningKey(), SignatureAlgorithm.HS512) // Mit geheimem Stempel unterschreiben
                .compact(); // Alles zu einem String zusammenpacken: header.payload.signature
    }
    /**
     * Liest Email aus Token raus
     * Prüft dabei ob Unterschrift echt ist!
     *
     * @param token Token String
     * @return Email aus Token
     */
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Mit eigenem Stempel prüfen ob Unterschrift echt
                .build()
                .parseClaimsJws(token) // Token öffnen und prüfen
                .getBody() // Inhalt holen
                .getSubject(); // Email rauslesen
    }
    /**
     * Prüft ob Token gültig ist
     *
     * @param token Token String
     * @return true = gültig, false = ungültig/abgelaufen
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token); // Versucht Token mit eigenem Stempel zu öffnen und zu prüfen
            return true; // Hat geklappt -> Unterschrift richtig + Zeit noch nicht abgelaufen -> Gültig
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Fehler -> Unterschrift falsch oder Zeit abgelaufen -> Ungültig
        }
    }
}
