package by.bntu.fitr.projectservice.exception;

import by.bntu.fitr.projectservice.constant.ErrorMessageConstant;

public abstract class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super(msg + " " + ErrorMessageConstant.ALREADY_EXISTS_EXCEPTION_MSG);
    }
}
