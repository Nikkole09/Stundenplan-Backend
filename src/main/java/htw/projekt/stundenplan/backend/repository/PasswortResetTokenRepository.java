
package htw.projekt.stundenplan.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.entity.PasswortResetToken;
import jakarta.persistence.LockModeType;

public interface PasswortResetTokenRepository
        extends JpaRepository<PasswortResetToken, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<PasswortResetToken> findByTokenHash(
            String tokenHash
    );

    void deleteByBenutzerkonto(
            Benutzerkonto benutzerkonto
    );
}
