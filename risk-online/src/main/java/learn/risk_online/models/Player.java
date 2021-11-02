package learn.risk_online.models;

public class Player {
    private String player;
    private int turnOrder;

    public Player() {
    }

    public Player(String player, int turnOrder) {
        this.player = player;
        this.turnOrder = turnOrder;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(int turnOrder) {
        this.turnOrder = turnOrder;
    }
}
