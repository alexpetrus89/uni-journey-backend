package com.alex.unijourneybackend.modules.examination_appeal.domain.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.alex.unijourneybackend.modules.course.domain.model.Course;
import com.alex.unijourneybackend.modules.professor.domain.valueobject.ProfessorCode;
import com.alex.unijourneybackend.modules.student.domain.valueobject.Register;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EXAMINATION_APPEALS", schema = "academic")
@Access(AccessType.FIELD)
public class ExaminationAppeal implements Serializable {

    // =========================
    // Instance Variables
    // =========================
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "examination_appeal_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    /** Snapshot del codice professore al momento della creazione — non aggiornato se cambia */
    @Embedded
    @AttributeOverride(
        name = "value",
        column = @Column(name = "professor_unique_code", nullable = false)
    )
    private ProfessorCode professorCode;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "date", nullable = false )
    private LocalDate date;

    @ElementCollection(targetClass = Register.class)
    @CollectionTable(
        name = "EXAMINATION_APPEAL_REGISTERS",
        schema = "academic",
        joinColumns = @JoinColumn(name = "examination_appeal_id")
    )
    @Column(name = "register", nullable = false, length = 6)
    private Set<Register> registers = new HashSet<>();


    // =========================
    // Constructors
    // =========================
    protected ExaminationAppeal() {}

    private ExaminationAppeal(Course course, String description, LocalDate date, Set<Register> registers) {
        Objects.requireNonNull(course,"course must not be null");
        Objects.requireNonNull(course.getProfessor(),"course must have an assigned professor");

        this.course = course;
        this.professorCode = course.getProfessor().getProfessorCode();
        this.description = description;
        this.date = Objects.requireNonNull(date, "date must not be null");
        initializeRegisters(registers);
    }

    public static ExaminationAppeal of(Course course, String description, LocalDate date) {
        return new ExaminationAppeal(course, description, date, null);
    }

    public static ExaminationAppeal of(Course course, String description, LocalDate date, Set<Register> registers) {
        return new ExaminationAppeal(course, description, date, registers);
    }


    // =========================
    // Getters
    // =========================
    public Long getId() { return id; }
    public Course getCourse() { return course; }
    public ProfessorCode getProfessorCode() { return professorCode; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }
    public Set<Register> getRegisters() { return registers; }


    // =========================
    // Setters (domain commands)
    // =========================
    public void setId(Long id) { this.id = id; }
    public void setCourse(Course course) { this.course = course; }
    public void setProfessorCode(ProfessorCode professorCode) { this.professorCode = professorCode; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setRegisters(Set<Register> registers) {
        this.registers = (registers != null) ? new HashSet<>(registers) : new HashSet<>();
    }


    // =========================
    // Bi-directional helpers
    // =========================
    public void addRegister(Register register) {
        Objects.requireNonNull(register, "register must not be null");
        if (!this.registers.add(register))
            throw new IllegalArgumentException("Register already present: " + register);
    }

    public void removeRegister(Register register) {
        Objects.requireNonNull(register, "register must not be null");
        if (!this.registers.remove(register))
            throw new IllegalArgumentException("Register not found: " + register);
    }


    // =========================
    // Business rules
    // =========================
    /** Vero se l'appello è scaduto e nessuno studente si è iscritto */
    public boolean deleteIfExpiredAndNoRegisters() {
        return LocalDate.now().isAfter(getDate()) && getRegisters().isEmpty();
    }


    // =========================
    // Object methods
    // =========================
    @Override
    public String toString() {
        return "ExaminationAppeal [course=" + course.getName() +
            ", professorCode=" + professorCode +
            ", description=" + description +
            ", date=" + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
            "]";
    }

    @Override
    public int hashCode() {
            return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExaminationAppeal ea)) return false;
        return Objects.equals(id, ea.id);
    }


    // =========================
    // Initializations
    // =========================
    private void initializeRegisters(Set<Register> registers) {
        setRegisters(registers);
    }

}
