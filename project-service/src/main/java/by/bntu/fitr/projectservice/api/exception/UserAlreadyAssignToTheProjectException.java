package by.bntu.fitr.projectservice.api.exception;

public class UserAlreadyAssignToTheProjectException extends RuntimeException {

    public UserAlreadyAssignToTheProjectException(String msg) {
        super(msg);
    }
}
