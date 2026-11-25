/**
 * Lädt und formt Daten für die Dashboard-Antwort
 *
 * @author Israa Alnaddaf
*/

package htw.projekt.stundenplan.backend.service;

import htw.projekt.stundenplan.backend.dto.dashboard.DashboardModule;
import htw.projekt.stundenplan.backend.dto.dashboard.DashboardSemester;
import htw.projekt.stundenplan.backend.entity.Benutzerkonto;
import htw.projekt.stundenplan.backend.entity.Fortschritt;
import htw.projekt.stundenplan.backend.entity.Modul;
import htw.projekt.stundenplan.backend.enums.FortschrittStatus;
import htw.projekt.stundenplan.backend.repository.FortschrittRepository;
import htw.projekt.stundenplan.backend.repository.ModulRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    private final ModulRepository modulRepository;
    private final FortschrittRepository fortschrittRepository;

    public DashboardService( ModulRepository modulRepository, FortschrittRepository fortschrittRepository)
    {
        this.modulRepository = modulRepository;
        this.fortschrittRepository = fortschrittRepository;
    }

    /**
     * Lädt alle module aus der Studienordnung der Benutzerin.
     * Fortschritte werden nur für diese Benutzerin geladen.
     *
     * @param user angemeldete Benutzerin
     * return Module mit sichtbarem Status
    */
   @Transactional (readOnly = true)
   public List<DashboardModule> loadModules(Benutzerkonto user)
   {
     Long studienordnungId = user.getStudienordnung().getId();

     List <Modul> module = modulRepository
     .findByStudienordnungIdOrderBySemesterAscNummerAsc(studienordnungId);

     Map<Long, Fortschritt> fortschrittNachModul =
     fortschrittRepository.findAllForUser(user.getId())
     .stream()
     .collect(Collectors.toMap(f -> f.getModul().getId(), Function.identity()));

     return module.stream()
     .map(modul -> toDashboardModule
        (modul, fortschrittNachModul.get(modul.getId())))
        .toList();
   }

   // Übersetzt den Datenbankstatus in den verdtändlichen API-Status
   private String toApiStatus(FortschrittStatus status){
    if (status == null)
    {
        return "OFFEN";
    }
    return status == FortschrittStatus.NICHT_BESTANDEN ? "DURCHGEFALLEN" : status.name();
   }

   private DashboardModule toDashboardModule(Modul modul, Fortschritt fortschritt){
    return new DashboardModule(
        modul.getId(),
        modul.getNummer(),
        modul.getName(),
        modul.getEcts(),
        modul.getSemester(),
        fortschritt == null ? "OFFEN" : toApiStatus(fortschritt.getStatus()),
        fortschritt == null ? null : fortschritt.getNote());
   }

   // Gruppiert die Module nach ihrem Semester.
   public List<DashboardSemester> groupBySemester(List<DashboardModule> module) {
    Map<Integer, List<DashboardModule>> gruppiert = module.stream()
    .sorted(Comparator.comparing(DashboardModule::semester))
    .collect(Collectors.groupingBy(
        DashboardModule::semester,
        LinkedHashMap::new,
        Collectors.toList()));
        return gruppiert.entrySet().stream()
        .map(e -> new DashboardSemester(e.getKey(), e.getValue())).toList();
    }
}
