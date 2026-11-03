
package htw.projekt.stundenplan.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "studienordnung")
public class Studienordnung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false, columnDefinition = "text")
    private String name;

    @Column(name = "standard_semesters")
    private Integer standardSemesters;

    @Column(name = "total_ects")
    private Integer totalEcts;

    @Column(name = "elective_rule_coverage")
    private String electiveRuleCoverage;

    public Studienordnung() {
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStandardSemesters() {
        return standardSemesters;
    }

    public void setStandardSemesters(Integer standardSemesters) {
        this.standardSemesters = standardSemesters;
    }

    public Integer getTotalEcts() {
        return totalEcts;
    }

    public void setTotalEcts(Integer totalEcts) {
        this.totalEcts = totalEcts;
    }

    public String getElectiveRuleCoverage() {
        return electiveRuleCoverage;
    }

    public void setElectiveRuleCoverage(String electiveRuleCoverage) {
        this.electiveRuleCoverage = electiveRuleCoverage;
    }
}
