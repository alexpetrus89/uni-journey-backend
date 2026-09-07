package com.alex.unijourneybackend.modules.degree_course.domain.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.Assert;

import com.alex.unijourneybackend.modules.course.domain.model.Course;
import com.alex.unijourneybackend.modules.degree_course.domain.valueobject.DegreeCourseId;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.study.domain.enums.DegreeType;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "DEGREE_COURSES",
    schema = "academic",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_degree_name", columnNames = "name")
    }
)
@Access(AccessType.FIELD)
public class DegreeCourse implements Serializable {

    // =========================
    // Instance Variables
    // =========================
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private DegreeCourseId id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "graduation_class", nullable = false)
    private DegreeType graduationClass;

    @Column(name = "duration")
    private int duration;

    // DegreeCourse is the owner of the relationship
    @OneToMany(
        mappedBy = "degreeCourse",
        fetch = FetchType.LAZY, // carica i corsi con ritardo
        cascade = CascadeType.PERSIST, // persiste i corsi quando il degree course è persistito
        orphanRemoval = false // non rimuove gli orfani
    )
    private final Set<Course> courses = new HashSet<>();

    // reverse side
    @OneToMany(
        mappedBy = "degreeCourse",
        fetch = FetchType.LAZY, // carica gli studenti con ritardo
        cascade = CascadeType.PERSIST,
        orphanRemoval = false
    )
    private final Set<Student> students = new HashSet<>();


    // =========================
    // Constructors
    // =========================
    protected DegreeCourse() {}

    public DegreeCourse(String name, DegreeType graduationClass, int duration) {
        this.id = DegreeCourseId.newId();
        this.name = validateName(name);
        this.graduationClass = Objects.requireNonNull(graduationClass, "graduationClass must not be null");
        this.duration = validateDuration(duration);
    }


    // =========================
    // Getters
    // =========================
    public DegreeCourseId getId() { return id; }
    public String getName() { return name; }
    public DegreeType getGraduationClass() { return graduationClass; }
    public int getDuration() { return duration; }
    public Set<Course> getCourses() { return Collections.unmodifiableSet(courses); }
    public Set<Student> getStudents() { return Collections.unmodifiableSet(students); }


    // =========================
    // Setters (domain commands)
    // =========================
    public void setName(String name) {
        this.name = validateName(name);
    }

    public void setGraduationClass(DegreeType graduationClass) {
        this.graduationClass = Objects.requireNonNull(graduationClass, "graduationClass must not be null");
    }

    public void setDuration(int duration) {
        this.duration = validateDuration(duration);
    }


    // Bi-directional helpers
    public void addCourse(Course course) {
        Objects.requireNonNull(course, "course must not be null");
        if (!courses.add(course))
            throw new IllegalArgumentException( "Course already present in degree course: " + course.getName());
        course.setDegreeCourse(this);
    }

    public void removeCourse(Course course) {
        Objects.requireNonNull(course, "course must not be null");
        if (!courses.remove(course))
            throw new IllegalArgumentException("Course not found in degree course: " + course.getName());
        course.setDegreeCourse(null);
    }

    public void addStudent(Student student) {
        Objects.requireNonNull(student, "student must not be null");
        if (!students.add(student))
            throw new IllegalArgumentException( "Student already enrolled in degree course: " + student.getUsername());
        student.setDegreeCourse(this);
    }

    public void removeStudent(Student student) {
        Objects.requireNonNull(student, "student must not be null");
        if (!students.remove(student))
            throw new IllegalArgumentException("Student not found in degree course: " + student.getUsername());
        student.setDegreeCourse(null);
    }


    // =========================
    // Validation
    // =========================

    private static String validateName(String name) {
        Assert.hasText(name, "name must not be blank");
        return name.trim();
    }

    private static int validateDuration(int duration) {
        Assert.isTrue(duration > 0, "duration must be positive");
        return duration;
    }


    // =========================
    // Object methods
    // =========================

    @Override
    public String toString() {
        return "DegreeCourse{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", graduationClass=" + graduationClass +
            ", duration=" + duration +
            ", coursesCount=" + courses.size() +
            ", studentsCount=" + students.size() +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DegreeCourse dc)) return false;
        return Objects.equals(id, dc.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
