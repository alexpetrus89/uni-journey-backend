package com.alex.unijourneybackend.modules.examination.domain.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.alex.unijourneybackend.modules.course.domain.model.Course;
import com.alex.unijourneybackend.modules.examination.domain.valueobject.ExaminationId;
import com.alex.unijourneybackend.modules.student.domain.model.Student;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "EXAMINATIONS", schema = "academic")
@Access(AccessType.FIELD)
public class Examination implements Serializable {

    // =========================
    // Instance Variables
    // =========================
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ExaminationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "fk_examination_course"))
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Course course;

    /** Snapshot del nome corso al momento dell'esame — non aggiornato se il corso cambia nome */
    @Column(name = "course_name_snapshot", nullable = false, length = 255)
    private String courseNameSnapshot;

    /** Snapshot del registro studente al momento dell'esame */
    @Column(name = "register", nullable = false, length = 20)
    private String register; // es: matricola

    @Column(name = "student_first_name", nullable = false, length = 50)
    private String studentFirstName;

    @Column(name = "student_last_name", nullable = false, length = 50)
    private String studentLastName;

    @NotNull
    @Min(18)
    @Max(30)
    @Column(name = "grade")
    private int grade;

    @Column(name = "with_honors")
    private boolean withHonors;

    @NotNull
    @Column(name = "examination_date", nullable = false)
    private LocalDate date;


    // =========================
    // Constructors
    // =========================
    protected Examination() {}

    public Examination(Course course, Student student, int grade, boolean withHonors, LocalDate date) {
        this.id = ExaminationId.newId();
        this.course = Objects.requireNonNull(course,   "course must not be null");
        this.courseNameSnapshot = course.getName();
        this.register  = Objects.requireNonNull(student,  "student must not be null").getRegister().toString();
        this.studentFirstName = student.getFirstName();
        this.studentLastName = student.getLastName();
        this.date = Objects.requireNonNull(date,     "date must not be null");
        initializeGrade(grade);
        initializeWithHonors(withHonors);
    }

    // Factory method for easier creation
    public static Examination of(Course course, Student student, int grade, boolean withHonors, LocalDate date) {
        return new Examination(course, student, grade, withHonors, date);
    }


    // =========================
    // Getters
    // =========================
    public ExaminationId getId() { return id; }
    public Course getCourse() { return course; }
    public String getCourseNameSnapshot() { return courseNameSnapshot; }
    public String getRegister() { return register; }
    public String getStudentFirstName() { return studentFirstName; }
    public String getStudentLastName() { return studentLastName; }
    public Integer getGrade() { return grade; }
    public boolean isWithHonors() { return withHonors; }
    public LocalDate getDate() { return date; }


    // =========================
    // Setters (domain command)
    // =========================
    public void setCourse(Course course) {
        this.course = Objects.requireNonNull(course, "course must not be null");
        this.courseNameSnapshot = course.getName();
    }

    public void setGrade(int grade) {
        if (grade < 18 || grade > 30)
            throw new IllegalArgumentException("Grade must be between 18 and 30");
        this.grade = grade;
    }

    public void setWithHonors(boolean withHonors) {
        this.withHonors = (grade == 30) && withHonors;
    }

    public void setDate(LocalDate date) {
        this.date = Objects.requireNonNull(date, "date must not be null");
    }

    /** Aggiorna lo snapshot dello studente — usare solo in caso di correzione dati */
    public void setStudentSnapshot(Student student) {
        Objects.requireNonNull(student, "student must not be null");
        this.register         = student.getRegister().toString();
        this.studentFirstName = student.getFirstName();
        this.studentLastName  = student.getLastName();
    }


    // =========================
    // Object methods
    // =========================
    @Override
    public String toString() {
        return "Examination [id=" + id +
            ", course=" + course +
            ", student=" + studentFirstName + " " + studentLastName +
            ", register=" + register +
            ", grade=" + grade +
            ", withHonors=" + withHonors
            + ", date=" + date + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Examination)) return false;
        Examination other = (Examination) o;
        return Objects.equals(id, other.id);
    }


    // =========================
    // Initialization methods
    // =========================
    private void initializeGrade(int grade) {
        setGrade(grade);
    }

    private void initializeWithHonors(boolean withHonors) {
        setWithHonors(withHonors);
    }


}
