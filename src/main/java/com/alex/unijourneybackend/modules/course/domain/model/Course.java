package com.alex.unijourneybackend.modules.course.domain.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.Assert;

import com.alex.unijourneybackend.modules.course.domain.valueobject.CourseId;
import com.alex.unijourneybackend.modules.course.domain.valueobject.MiurCourseCode;
import com.alex.unijourneybackend.modules.degree_course.domain.model.DegreeCourse;
import com.alex.unijourneybackend.modules.professor.domain.model.Professor;
import com.alex.unijourneybackend.modules.study.domain.enums.CourseType;
import com.alex.unijourneybackend.modules.study.domain.enums.MiurAcronymType;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "COURSES", schema = "academic")
@Access(AccessType.FIELD)
public class Course implements Serializable {

    // =========================
    // Instance Variables
    // =========================
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CourseId id;

    @Embedded
    @AttributeOverride(
        name = "value",
        column = @Column(name = "miur_course_code", unique = true, nullable = false)
    )
    private MiurCourseCode code;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CourseType type;

    @Column(name = "cfu", nullable = false)
    private Integer cfu;

    @Column(name = "year_of_study", nullable = false)
    private Integer yearOfStudy;

    @Column(name = "mandatory_course", nullable = false)
    private boolean mandatoryCourse = false;

    // owning side
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "professor_id", foreignKey = @ForeignKey(name = "fk_course_professor"))
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "degree_course_id", foreignKey = @ForeignKey(name = "fk_course_degree_course"))
    private DegreeCourse degreeCourse;


    // =========================
    // Constructors
    // =========================
    protected Course() {}

    public Course(MiurAcronymType acronym, String name, CourseType type, Integer cfu, boolean mandatoryCourse, Integer yearOfStudy, Professor professor, DegreeCourse degreeCourse) {
        this.id = CourseId.newId();
        this.code = MiurCourseCode.generate(Objects.requireNonNull(acronym, "miur course code must not be null"));
        this.name = validateName(name);
        this.type = Objects.requireNonNull(type, "course type must not be null");
        this.cfu = validateCfu(cfu);
        this.yearOfStudy = validateYearOfStudy(yearOfStudy);
        this.mandatoryCourse = mandatoryCourse;
        this.professor = professor; // nullable — può non essere ancora assegnato
        this.degreeCourse = Objects.requireNonNull(degreeCourse, "degree course must not be null");
    }


    // =========================
    // Getters
    // =========================
    public CourseId getId() { return id; }
    public MiurCourseCode getCode() { return code; }
    public String getName() { return name; }
    public CourseType getType() { return type; }
    public Integer getCfu() { return cfu; }
    public Integer getYearOfStudy() { return yearOfStudy; }
    public boolean isMandatoryCourse() { return mandatoryCourse; }
    public Professor getProfessor() { return professor; }
    public DegreeCourse getDegreeCourse() { return degreeCourse; }


    // =========================
    // Setters (domain commands)
    // =========================
    public void setName(String name) {
        this.name = validateName(name);
    }

    public void setType(CourseType type) {
        this.type = Objects.requireNonNull(type, "type must not be null");
    }

    public void setCfu(Integer cfu) {
        this.cfu = validateCfu(cfu);
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = validateYearOfStudy(yearOfStudy);
    }

    public void setMandatoryCourse(boolean mandatoryCourse) {
        this.mandatoryCourse = mandatoryCourse;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor; // nullable
    }

    public void setDegreeCourse(DegreeCourse degreeCourse) {
        this.degreeCourse = Objects.requireNonNull(degreeCourse, "degreeCourse must not be null");
    }


    // =========================
    // Validation
    // =========================

    private static String validateName(String name) {
        Assert.hasText(name, "name must not be blank");
        return name.trim();
    }

    private static Integer validateCfu(Integer cfu) {
        Objects.requireNonNull(cfu, "cfu must not be null");
        Assert.isTrue(cfu > 0, "cfu must be positive");
        return cfu;
    }

    private static Integer validateYearOfStudy(Integer yearOfStudy) {
        Objects.requireNonNull(yearOfStudy, "yearOfStudy must not be null");
        Assert.isTrue(yearOfStudy > 0, "yearOfStudy must be positive");
        return yearOfStudy;
    }


    // =========================
    // Object methods
    // =========================

    @Override
    public String toString() {
        return "Course{" +
            "id=" + id +
            ", code=" + code +
            ", name='" + name + '\'' +
            ", type=" + type +
            ", cfu=" + cfu +
            ", yearOfStudy=" + yearOfStudy +
            ", professorId=" + (professor != null ? professor.getId() : null) +
            ", degreeCourseId=" + (degreeCourse != null ? degreeCourse.getId() : null) +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course c)) return false;
        return Objects.equals(code, c.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }


}
