package by.bntu.fitr.projectservice.api.exception;

import by.bntu.fitr.projectservice.api.constant.ErrorMessageConstant;

public abstract class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String msg) {
        super(msg + " " + ErrorMessageConstant.ALREADY_EXISTS_EXCEPTION_MSG);
    }
}
