package learn.risk_online.models;

import java.util.Objects;

public class Player {
    private int gameId;
    private String userName;
    private String userId;
    private int turnOrder;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(int turnOrder) {
        this.turnOrder = turnOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return gameId == player.gameId && turnOrder == player.turnOrder && Objects.equals(userName, player.userName) && Objects.equals(userId, player.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, userName, userId, turnOrder);
    }
}
