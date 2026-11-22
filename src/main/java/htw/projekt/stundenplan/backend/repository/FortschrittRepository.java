package htw.projekt.stundenplan.backend.repository;

import htw.projekt.stundenplan.backend.entity.Fortschritt;
import htw.projekt.stundenplan.backend.entity.FortschrittId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Repository für Speichern und Suchen.
public interface FortschrittRepository
        extends JpaRepository<Fortschritt, FortschrittId> {
        /**
        * Lädt nur Fortschritte der angemeldeten Benutzerin.
        * Das Modul wird direkt mitgeladen.
        *
        * @param benutzerId Id des Benutzerkontos
        * return Fortschritte der Benutzerin
        */
       @Query ("""
        Select f from Fortschritt f
        join fetch f.modul m
        where f.benutzer.id = :benutzerId
        """)
        List<Fortschritt> findAllForUser(
                @Param("benutzerId") Long benutzerId
        );
}