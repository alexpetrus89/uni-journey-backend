package  com.alex.unijourneybackend.common.validation.validator;

import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.common.validation.annotation.ValidProfessorCode;
import com.alex.unijourneybackend.modules.professor.domain.valueobject.ProfessorCode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ProfessorCodeValidator implements ConstraintValidator<ValidProfessorCode, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ProfessorCode.isValid(value);
    }


}

