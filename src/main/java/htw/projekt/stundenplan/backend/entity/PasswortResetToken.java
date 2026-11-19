
package htw.projekt.stundenplan.backend.entity;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
@Table(name = "passwort_reset_token")
public class PasswortResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "token_hash",
            nullable = false,
            unique = true,
            length = 64
    )
    private String tokenHash;

    @Column(nullable = false)
    private Instant ablaufzeit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "benutzerkonto_id",
            nullable = false
    )
    private Benutzerkonto benutzerkonto;

    protected PasswortResetToken() {
    }

    public PasswortResetToken(
            String tokenHash,
            Instant ablaufzeit,
            Benutzerkonto benutzerkonto) {

        this.tokenHash = tokenHash;
        this.ablaufzeit = ablaufzeit;
        this.benutzerkonto = benutzerkonto;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public Instant getAblaufzeit() {
        return ablaufzeit;
    }

    public Benutzerkonto getBenutzerkonto() {
        return benutzerkonto;
    }
}
