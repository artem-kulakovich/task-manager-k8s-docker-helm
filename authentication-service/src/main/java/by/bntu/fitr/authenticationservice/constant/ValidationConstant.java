package by.bntu.fitr.authenticationservice.constant;

public interface ValidationConstant {

    interface Constraint {
        int USER_NAME_MIN_CONSTRAINT = 4;
        int USER_NAME_MAX_CONSTRAINT = 50;

        int LAST_NAME_MIN_CONSTRAINT = 2;
        int LAST_NAME_MAX_CONSTRAINT = 100;

        int FIRST_NAME_MIN_CONSTRAINT = 2;
        int FIRST_NAME_MAX_CONSTRAINT = 50;

        int PASSWORD_MIN_CONSTRAINT = 5;
        int PASSWORD_MAX_CONSTRAINT = 25;

        int EMAIL_MIN_CONSTRAINT = 6;
        int EMAIL_MAX_CONSTRAINT = 100;
    }


    interface ValidationMsg {
        String USER_NAME_VALIDATION_MSG = "Username size should be less then 51 letters and greater then 3 letters";
        String LAST_NAME_VALIDATION_MSG = "Lastname size should be less then 101 letters and greater then 1 letter";
        String FIRST_NAME_VALIDATION_MSG = "Firstname size should be less then 51 letters and greater then 1 letter";
        String PASSWORD_VALIDATION_MSG = "Password size should be less then 26 letters and greater then 4 letters";
        String EMAIL_VALIDATION_MSG = "Email size should be less then 101 letters and greater then 5 letters";
    }
}
