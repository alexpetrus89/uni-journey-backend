package com.alex.unijourneybackend.modules.student.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.domain.valueobject.Register;
import com.alex.unijourneybackend.modules.student.port.StudentRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;

@Component
public class StudentRepositoryAdapter implements StudentRepositoryPort {

    private final StudentRepository repository;

    public StudentRepositoryAdapter(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("null")
    public PageResult<Student> findAll(PageQuery query) {
        PageRequest pageRequest = PageRequest.of(query.page(), query.size());

        Page<Student> page = repository.findAll(pageRequest);

        return new PageResult<>(page.getContent(), page.getTotalElements());
    }

    @Override
    @SuppressWarnings("null")
    public Optional<Student> findById(UserId id) {
        return repository.findById(id);
    }

    @Override
    @SuppressWarnings("null")
    public Student create(Student student) {
        return repository.save(student);
    }

    @Override
    @SuppressWarnings("null")
    public void delete(Student student) {
        repository.delete(student);
    }

    @Override
    @SuppressWarnings("null")
    public boolean existsByRegister(Register register) {
        return repository.existsByRegister(register);
    }


}
