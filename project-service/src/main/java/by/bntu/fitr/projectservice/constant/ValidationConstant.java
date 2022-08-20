package by.bntu.fitr.projectservice.constant;

public interface ValidationConstant {

    interface Constraint {
        int PROJECT_NAME_MIN_CONSTRAINT = 6;
        int PROJECT_NAME_MAX_CONSTRAINT = 49;
        int ROLE_NAME_MIN_CONSTRAINT = 4;
        int ROLE_NAME_PROJECT_CONSTRAINT = 49;
        int PROJECT_DESCRIPTION_MAX_CONSTRAINT = 1024;
        int WORKSPACE_NAME_MIN_CONSTRAINT = 4;
        int WORKSPACE_NAME_MAX_CONSTRAINT = 49;

    }


    interface ValidationMsg {
        String PROJECT_NAME_VALIDATION_MSG = "Project size should be less then 50 letters and greater then 5 letters";
        String ROLE_NAME_VALIDATION_MSG = "Role name should be less then 50 letters and greater then 3 letters";
        String PROJECT_DESCRIPTION_VALIDATION_MSG = "Project description should be less then 1025 letters";
        String WORKSPACE_NAME_VALIDATION_MSG = "Workspace name should be less then 50 letters and greater then 50 letters";
    }
}
