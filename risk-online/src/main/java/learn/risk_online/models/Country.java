package learn.risk_online.models;

import java.util.Objects;

public class Country {

    private int gameId;
    private int countryId;
    private String countryName;
    private int playerPossession;
    private int army;

    public Country(){}

    public Country(int countryId, String countryName, int playerPossession, int army) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.playerPossession = playerPossession;
        this.army = army;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getPlayerPossession() {
        return playerPossession;
    }

    public void setPlayerPossession(int playerPossession) {
        this.playerPossession = playerPossession;
    }

    public int getArmy() {
        return army;
    }

    public void setArmy(int army) {
        this.army = army;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return countryId == country.countryId && playerPossession == country.playerPossession && army == country.army && Objects.equals(countryName, country.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId, countryName, playerPossession, army);
    }
}
