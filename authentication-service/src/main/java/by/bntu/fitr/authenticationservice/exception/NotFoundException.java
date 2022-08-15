package by.bntu.fitr.authenticationservice.exception;

import by.bntu.fitr.authenticationservice.constant.ErrorMessageConstant;

public abstract class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super(msg + " " + ErrorMessageConstant.NOT_FOUND_EXCEPTION_MSG);
    }
}
