package com.alex.unijourneybackend.modules.student.domain.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.student.domain.model.Student;
import com.alex.unijourneybackend.modules.student.port.StudentRepositoryPort;
import com.alex.unijourneybackend.modules.user.domain.model.User;
import com.alex.unijourneybackend.modules.user.domain.service.UserResolver;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;

@Component
public class StudentResolver {

    private final StudentRepositoryPort port;
    private final UserResolver resolver;

    public StudentResolver(StudentRepositoryPort port, UserResolver resolver) {
        this.port = port;
        this.resolver = resolver;
    }

    public Student resolveByUsername(String principalName) {
        User user = resolver.resolveFromPrincipalName(principalName);
        return port
            .findById(new UserId(user.getId().getId()))
            .orElseThrow(() -> new UsernameNotFoundException("Student not found"));
    }


}
