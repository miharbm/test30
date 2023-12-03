package web.test30.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import web.test30.model.User;
import web.test30.service.UserService;

import java.util.Objects;
import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals( clazz );
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;
        Long id = user.getId();

        Optional<User> userByEmail = userService.findOneByEmail( user.getEmail());
        if(userByEmail.isPresent() && !Objects.equals( userByEmail.get().getId(), id )){
            errors.rejectValue( "email", "", "This email is already in use" );
        }

        Optional<User> userByAdr = userService.findOneByAddress( user.getEmail());
        if(userByAdr.isPresent() && !Objects.equals( userByAdr.get().getId(), id )){
            errors.rejectValue( "address", "", "This address is already in use" );
        }

        if (!Character.isUpperCase(user.getName().codePointAt(0))){
            errors.rejectValue("name", "", "Name should start with a capital letter");
        }
    }
}
