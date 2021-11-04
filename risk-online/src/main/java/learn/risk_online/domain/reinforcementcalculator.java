package learn.risk_online.domain;

import java.util.List;

public class reinforcementcalculator {



    // The number of countries should be taken and divided by 3 and rounded in
    // order to come up with the number of troops that a player has to defend with.

//    The number of extra armies is different for each continent:
//
//    Asia: 7
//    Nort America: 5
//    Europe: 5
//    Africa: 3
//    South America: 2
//    Australia: 2

    public reinforcementcalculator() {
    }

    public int calculatetroops(List<Integer> playerOwnedCountries){
        int total = Math.round(playerOwnedCountries.size() / 3);
        if(checkAsia(playerOwnedCountries))
        {
            total = total + 7;
        }
        if(checkNAmerica(playerOwnedCountries))
        {
            total = total + 5;
        }
        if(checkEurope(playerOwnedCountries))
        {
            total = total + 5;
        }
        if(checkAfrica(playerOwnedCountries))
        {
            total = total + 3;
        }
        if(checkSAmerica(playerOwnedCountries))
        {
            total = total + 2;
        }
        if(checkAustralia(playerOwnedCountries))
        {
            total = total + 2;
        }

        return total;

    }

    private boolean checkAustralia(List<Integer> playerOwnedCountries) {
        if(playerOwnedCountries.indexOf(38)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(39)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(40)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(41)<0){
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean checkSAmerica(List<Integer> playerOwnedCountries) {
        if(playerOwnedCountries.indexOf(9)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(10)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(11)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(12)<0){
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean checkAfrica(List<Integer> playerOwnedCountries) {
        if(playerOwnedCountries.indexOf(13)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(14)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(15)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(16)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(17)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(18)<0){
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean checkEurope(List<Integer> playerOwnedCountries) {
        if(playerOwnedCountries.indexOf(19)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(20)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(21)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(22)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(23)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(24)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(25)<0){
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean checkNAmerica(List<Integer> playerOwnedCountries) {
        if(playerOwnedCountries.indexOf(0)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(1)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(2)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(3)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(4)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(5)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(6)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(7)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(8)<0){
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean checkAsia(List<Integer> playerOwnedCountries) {
        if(playerOwnedCountries.indexOf(26)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(27)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(28)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(29)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(30)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(31)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(32)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(33)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(34)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(35)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(36)<0){
            return false;
        }
        if(playerOwnedCountries.indexOf(37)<0) {
            return false;
        }
        else
        {
            return true;
        }
    }

}
