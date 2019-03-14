package demo.mpp.Response;

public class LoginResponse {
    private Boolean success;
    private String message;
    private Boolean isAdmin;

    public LoginResponse(Boolean success, String message, Boolean isAdmin) {
        this.success = success;
        this.message = message;
        this.isAdmin = isAdmin;
    }

    public LoginResponse(String message) {
        this.success = false;
        this.message = message;
        this.isAdmin = false;
    }

    public LoginResponse(Boolean success) {
        this.success = success;
        this.isAdmin = false;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
