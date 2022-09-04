package by.bntu.fitr.authenticationservice.constant;

public interface CommonConstant {
    String EMAIL = "email";
    String USER_ID = "userId";
    String TASK_MANAGER_AUTH_SERVICE = "task-manager-auth-service";
    String ROLE_NAME = "role_name";
    String EMPTY_STRING = "";
    String PERMISSIONS_FOR_ROLE = "role_permissions";
    String ROLE = "Role";
    String USER = "User";

    public interface FetchType {
        String EAGER = "EAGER";
        String LAZY = "LAZY";
    }

    public interface InheritLvl {
        int USER_WITH_NONE = 0;
        int USER_WITH_ROLE = 1;
        int USER_WITH_ROLE_AND_PERMISSION = 2;

        int ROLE_WITH_NONE = 0;
        int ROLE_WITH_PERMISSION = 1;
    }
}
