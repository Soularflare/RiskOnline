package learn.risk_online.models;

public class AppUser {

    private String userId;
    private String userName;
    private String password;
    private boolean disabled;

    public AppUser() {
    }

    public AppUser(String userId, String userName, String password, boolean disabled) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.disabled = disabled;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
