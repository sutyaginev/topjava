package ru.javawebinar.topjava.web.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.to.UserTo;

@Component
public class EmailValidator implements Validator {

    private final UserRepository userRepository;


    public EmailValidator(@Qualifier("dataJpaUserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserTo.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserTo userTo = (UserTo) target;
        String email = userTo.getEmail();
        if (email != null && userRepository.getByEmail(email) != null) {
            errors.rejectValue("email", "Valid.userTo.email", "User with this email already exists");
        }
    }
}
