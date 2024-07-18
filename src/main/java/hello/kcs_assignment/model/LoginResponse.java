package hello.kcs_assignment.model;

public class LoginResponse {
    private int status;
    private String message;
    private String sessionID;

    public LoginResponse(int status, String message, String sessionID) {
        this.status = status;
        this.message = message;
        this.sessionID = sessionID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
}
