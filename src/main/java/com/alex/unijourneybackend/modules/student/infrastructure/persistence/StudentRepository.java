package com.alex.unijourneybackend.modules.student.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.domain.valueobject.Register;
import com.alex.unijourneybackend.modules.user.domain.valueobject.FiscalCode;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;


public interface StudentRepository
    extends JpaRepository<Student, UserId> {

    /**
     * Retrieves all students user
     * @param pageable the pagination information
     * @return a page of students
     */
    @Override
    @NonNull
    Page<Student> findAll(@NonNull Pageable pageable);

    /**
     * Retrieves a student by then user id
     * @param id the user id of the student to retrieve
     * @return Optional<Student> with the student if found, or an empty
     *        Optional if no student is found
     */
    @Override
    @NonNull
    Optional<Student> findById(@NonNull UserId id);

    /**
     * Retrieves a student by register
     * @param register the register of the student
     * @return Optional<Student> with the student if found, or an empty
     *         Optional if no student is found
     * @see Register
     */
    @Query(value = "SELECT s FROM Student s WHERE s.register = ?1")
    Optional<Student> findByRegister(Register register);


    /**
     * Retrieves a Set of students by his registers
     * @param registers
     * @return Set<Student>
     * @see Register
     */
    @Query("SELECT s FROM Student s WHERE s.register IN ?1")
    Set<Student> findByRegisterIn(Set<Register> registers);


    /**
     * Retrieves a student by email
     * @param username of the student
     * @return Optional<Student> with the student if found, or an empty
     *         Optional if no student is found
     */
    @Query("SELECT s FROM Student s WHERE s.username = ?1")
    Optional<Student> findByUsername(String username);


    /**
     * Retrieves a student by name
     * @param name the name of the student to retrieve
     * @return a list of student with same fullname
     */
    List<Student> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(
        String firstName,
        String lastName
    );


    /**
     * Checks if a student exists by register
     * @param register the register of the student
     * @return true if the student exists, false otherwise
     * @see Register
     */
    boolean existsByRegister(Register register);


    /**
     * Checks if a student exists by fiscal code
     * @param fiscalCode the fiscal code of the student
     * @return true if the student exists, false otherwise
     * @see FiscalCode
     */
    boolean existsByFiscalCode(FiscalCode fiscalCode);


    /**
     * Deletes a student by register
     * @param register the register of the student
     * @see Register
     */
    @Modifying
    void deleteByRegister(Register register);

}
