package com.alex.unijourneybackend.modules.study_plan.domain.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.alex.unijourneybackend.modules.course.domain.model.Course;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.study_plan.domain.valueobject.StudyPlanId;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDY_PLANS", schema = "study")
@Access(AccessType.FIELD)
public class StudyPlan implements Serializable {

    // =========================
    // Constants
    // =========================
    private static final String COURSES_NOT_NULL = "course must not be null";

    // =========================
    // Instance Variables
    // =========================
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @AttributeOverride(name = "id", column = @Column(name = "study_plan_id"))
    private StudyPlanId id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false, unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    @Column(name = "ordering", length = 255)
    private String ordering;

    @ManyToMany(targetEntity = Course.class, fetch = FetchType.LAZY)
    @JoinTable(
        name = "study_plan_courses",
        schema = "academic",
        joinColumns = @JoinColumn(name = "study_plan_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "courses")
    private Set<Course> courses = new HashSet<>();


    // =========================
    // Constructors
    // =========================
    protected StudyPlan() {}

    public StudyPlan(Student student, String ordering, Set<Course> courses) {
        this.id = StudyPlanId.newId();
        this.student = student;
        this.ordering = ordering;
        this.courses = courses;
    }


    // =========================
    // Getters
    // =========================
    public StudyPlanId getId() { return id; }
    public Student getStudent() { return student; }
    public String getOrdering() { return ordering; }
    public Set<Course> getCourses() { return Collections.unmodifiableSet(courses); }


    // =========================
    // Setters (domain commands)
    // =========================
    public void setOrdering(String ordering) {
        this.ordering = ordering;
    }

    public void setCourses(Set<Course> courses) {
        Objects.requireNonNull(courses, "courses must not be null");
        this.courses = new HashSet<>(courses); // copia difensiva
    }


    // =========================
    // Business methods
    // =========================
    public void addCourse(Course course) {
        Objects.requireNonNull(course, COURSES_NOT_NULL);
        if (!courses.add(course))
            throw new IllegalArgumentException("Course already present in study plan: " + course.getName());
    }

    public void removeCourse(Course course) {
        Objects.requireNonNull(course, COURSES_NOT_NULL);
        if (!courses.remove(course))
            throw new IllegalArgumentException("Course not found in study plan: " + course.getName());
    }

    public boolean containsCourse(Course course) {
        Objects.requireNonNull(course, COURSES_NOT_NULL);
        return courses.contains(course);
    }


    // =========================
    // Object methods
    // =========================
    @Override
    public String toString() {
        return "StudyPlan{" +
            "id=" + id +
            ", studentId=" + (student != null ? student.getId() : null) +
            ", ordering='" + ordering + '\'' +
            ", coursesCount=" + courses.size() +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudyPlan other)) return false;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
