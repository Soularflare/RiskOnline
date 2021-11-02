package learn.risk_online.models;

import java.util.List;

public class Game {

    private int gameId;
    private int timeElapsed;
    private int playerTurn;

    private List<Country> countryList;
    private List<Player> players;

    public Game() {
    }

    public Game(int gameId, int timeElapsed, int playerTurn,
                List<Country> countryList, List<Player> players) {
        this.gameId = gameId;
        this.timeElapsed = timeElapsed;
        this.playerTurn = playerTurn;
        this.countryList = countryList;
        this.players = players;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
