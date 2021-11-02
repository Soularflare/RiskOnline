package learn.risk_online.models;

public class Country {

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
}
