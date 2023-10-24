package elec5619.sydney.edu.au.mental_health_support_website.controller;

public enum ErrorsEnum {
    PARAMETER_ERROR("parameter error"),
    DATABASE_ERROR("database error"),
    PASSWORD_FORMAT_ERROR("password format error"),
    PASSWORD_WRONG_ERROR("password wrong error"),
    USER_ALREADY_EXISTS("Username already exists");

    private final String errorMessage;

    ErrorsEnum(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
