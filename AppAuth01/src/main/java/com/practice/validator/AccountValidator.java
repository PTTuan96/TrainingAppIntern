package com.practice.validator;

import com.practice.model.Account;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account) target;
        if(account.getPassword().length() < 6){
            errors.rejectValue("password", "length", "Password must be at least 6 character");
        }

//        if(!account.getPassword().equals(account.getConfirmPassword())){
//            errors.rejectValue("confirmPassword", "Match", "Password must match");
//        }
    }
}
