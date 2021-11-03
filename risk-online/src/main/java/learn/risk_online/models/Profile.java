package learn.risk_online.models;

import java.util.List;

public class Profile {
    private String profileId;
    private String userId;
    private String userName; //maybe change to Appuser user
    private int totalGames;
    private int wins;
    private int gameTime;
    private int points;
    private List<ProfileMicrotransaction> microtransactions;

    public List<ProfileMicrotransaction> getMicrotransactions() {
        return microtransactions;
    }

    public void setMicrotransactions(List<ProfileMicrotransaction> microtransactions) {
        this.microtransactions = microtransactions;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
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

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
