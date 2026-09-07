package com.alex.unijourneybackend.modules.examination_outcome.domain.model;

import java.io.Serial;
import java.io.Serializable;

import com.alex.unijourneybackend.modules.examination_appeal.domain.model.ExaminationAppeal;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "EXAMINATION_OUTCOMES", schema = "academic")
@Access(AccessType.FIELD)
public class ExaminationOutcome implements Serializable {

    // =========================
    // Instance Variables
    // =========================
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outcome_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examination_appeal_id", nullable = false)
    private ExaminationAppeal appeal;

    /** Snapshot del registro studente al momento della creazione */
    @NotBlank
    @Pattern(regexp = "\\d{6}", message = "Register must be a 6-digit string")
    @Column(name = "register", nullable = false, length = 6)
    private String register;

    @Column(name = "present", nullable = false)
    private boolean present;

    @Min(0)
    @Max(30)
    @Column(name = "grade", nullable = false)
    private int grade;

    @Column(name = "with_honors", nullable = false)
    private boolean withHonors;

    /** true se l'outcome è stato accettato dallo studente */
    @Column(name = "accepted", nullable = false)
    private boolean accepted;

    /** true se il voto è stato assegnato dal professore */
    @Column(name = "graded", nullable = false)
    private boolean graded = false;

    @Size(max = 255)
    @Column(name = "message")
    private String message;


    // =========================
    // Constructors
    // =========================
    protected ExaminationOutcome() {}

    private ExaminationOutcome(ExaminationAppeal appeal, String register, boolean present, int grade, boolean withHonors) {
        this.appeal = appeal;
        this.register = register;
        this.present = present;
        initializeGrade(grade);
        initializeWithHonors(withHonors);
    }


    /** Crea un outcome in attesa di valutazione */
    public static ExaminationOutcome pending(ExaminationAppeal appeal, String register) {
        return new ExaminationOutcome(appeal, register, false, 0, false);
    }

    /** Crea un outcome già valutato */
    public static ExaminationOutcome graded(ExaminationAppeal appeal, String register, boolean present, int grade, boolean withHonors) {
        ExaminationOutcome outcome = new ExaminationOutcome(appeal, register, present, grade, withHonors);
        outcome.graded = true;
        return outcome;
    }


    // =========================
    // Getters
    // =========================
    public Long getId() { return id;  }
    public ExaminationAppeal getAppeal() { return appeal; }
    public String getRegister() { return register; }
    public boolean isPresent() { return present; }
    public int getGrade() { return grade; }
    public boolean isWithHonors() { return withHonors; }
    public boolean isAccepted() { return accepted; }
    public boolean isGraded() { return graded; }
    public String getMessage() { return message; }


    // =========================
    // Setters (domain commands)
    // =========================
    public void setPresent(boolean present) { this.present = present; }

    public void setGrade(int grade) {
        if (grade < 0 || grade > 30)
            throw new IllegalArgumentException("Grade must be between 0 and 30");
        this.grade  = grade;
        this.graded = grade > 0;
    }

    public void setWithHonors(boolean withHonors) { this.withHonors = (grade == 30) && withHonors; }
    public void setAccepted(boolean accepted) { this.accepted = accepted; }
    public void setMessage(String message) { this.message = message; }


    // =========================
    // Initializations
    // =========================
    private void initializeGrade(int grade) {
        setGrade(grade);
    }

    private void initializeWithHonors(boolean withHonors) {
        setWithHonors(withHonors);
    }


}
