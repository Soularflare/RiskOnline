package riskgame;

import riskgame.models.Adjacencyvalidator;

import java.util.ArrayList;
import java.util.List;

public class app {

    public static void main(String[] args) {
        Adjacencyvalidator model = new Adjacencyvalidator();
        model.setup();
        System.out.println(model.toString());

        List<Integer> playerOwnedCountries = new ArrayList<>();
        playerOwnedCountries.add(0);
        playerOwnedCountries.add(31);
        System.out.println(model.countriesToAttack(playerOwnedCountries));
    }
}
