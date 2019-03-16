package demo.mpp.Response;

public class LoginResponse {
    private Boolean success;
    private String message;

    public LoginResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public LoginResponse(String message) {
        this.success = false;
        this.message = message;
    }

    public LoginResponse(Boolean success) {
        this.success = success;
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

}
