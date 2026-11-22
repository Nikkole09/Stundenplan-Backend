
package htw.projekt.stundenplan.backend.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PasswortResetMailService {

    private static final Logger log =
            LoggerFactory.getLogger(
                    PasswortResetMailService.class
            );

    private final JavaMailSender mailSender;
    private final String frontendUrl;
    private final String absender;

    public PasswortResetMailService(
            JavaMailSender mailSender,
            @Value("${app.passwort-reset.frontend-url}")
            String frontendUrl,
            @Value("${app.passwort-reset.absender}")
            String absender) {

        this.mailSender = mailSender;
        this.frontendUrl = frontendUrl;
        this.absender = absender;
    }

    @Async
    public void sendeResetMail(String email, String token) {

        // Link mit Reset-Token erstellen
        String link = frontendUrl
                + "?token="
                + URLEncoder.encode(
                        token,
                        StandardCharsets.UTF_8
                );

        SimpleMailMessage nachricht =
                new SimpleMailMessage();

        nachricht.setFrom(absender);
        nachricht.setTo(email);
        nachricht.setSubject("Passwort zurücksetzen");

        nachricht.setText(
                "Du hast angefordert, dein Passwort "
                + "zurückzusetzen.\n\n"
                + "Öffne diesen Link:\n"
                + link
                + "\n\nDer Link ist 30 Minuten gültig.\n"
                + "Falls du die Anfrage nicht gestellt hast, "
                + "ignoriere diese E-Mail."
        );

        try {
            mailSender.send(nachricht);

        } catch (MailException e) {
            log.error(
                    "Passwort-Reset-Mail konnte nicht versendet werden.",
                    e
            );
        }
    }
}
