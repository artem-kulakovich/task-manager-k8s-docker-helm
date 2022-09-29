package by.bntu.fitr.notificationservice.constant;

public interface ValidationConstant {

    interface ValidationConstraint {
        int MESSAGE_MAX_CONSTRAINT = 1024;

        int SUBJECT_MAX_CONSTRAINT = 512;
    }

    interface ValidationMsg {
        String MESSAGE_VALIDATION_MSG = "Message should be less than 1025 symbols.";
        String SUBJECT_VALIDATION_MSG = "Subject should be less than 513 symbols.";
    }
}
