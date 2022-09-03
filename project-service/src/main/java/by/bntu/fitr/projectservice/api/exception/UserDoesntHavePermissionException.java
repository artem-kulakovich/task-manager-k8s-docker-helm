package by.bntu.fitr.projectservice.api.exception;

public class UserDoesntHavePermissionException extends RuntimeException {

    public UserDoesntHavePermissionException(String msg) {
        super(msg);
    }
}
