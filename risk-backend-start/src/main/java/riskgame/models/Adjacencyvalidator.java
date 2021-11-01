package riskgame.models;

import java.util.ArrayList;
import java.util.List;

public class Adjacencyvalidator {

    private boolean adjMatrix[][];
    private int numVertices = 42;

    public Adjacencyvalidator() {
        adjMatrix = new boolean[numVertices][numVertices];
    }

    public void setup(){
//        ALASKA: 0
        addEdge(0,31);
        addEdge(0,1);
        addEdge(0,2);

//        NW_TERRITORY: 1
        addEdge(1,0);
        addEdge(1,2);
        addEdge(1,5);
        addEdge(1,7);

//        ALBERTA: 2
        addEdge(2,0);
        addEdge(2,1);
        addEdge(2,5);
        addEdge(2,3);

//        WestUS: 3
        addEdge(3,2);
        addEdge(3,5);
        addEdge(3,4);
        addEdge(3,8);

//        EastUS: 4
        addEdge(4,8);
        addEdge(4,3);
        addEdge(4,5);
        addEdge(4,6);

//        ONTARIO: 5
        addEdge(5,1);
        addEdge(5,2);
        addEdge(5,3);
        addEdge(5,4);
        addEdge(5,6);
        addEdge(5,7);

//        QUEBEC: 6
        addEdge(6,7);
        addEdge(6,5);
        addEdge(6,4);

//        GREENLAND: 7
        addEdge(7,1);
        addEdge(7,5);
        addEdge(7,6);
        addEdge(7,19);

//        CENTRAL_AMERICA: 8
        addEdge(8,3);
        addEdge(8,4);
        addEdge(8,9);

// NORTH AMERICA DONE: NEXT IS SOUTH AMERICA
//        VENEZUELA: 9
        addEdge(9,8);
        addEdge(9,11);
        addEdge(9,10);

//        PERU: 10
        addEdge(10,9);
        addEdge(10,11);
        addEdge(10,12);

//        BRAZIL: 11
        addEdge(11,9);
        addEdge(11,10);
        addEdge(11,12);
        addEdge(11,13);

//        ARGENTINA: 12
        addEdge(12,11);
        addEdge(12,10);

// SOUTH AMERICA DONE: NEXT IS AFRICA
//        N_AFRICA: 13
        addEdge(13,22);
        addEdge(13,11);
        addEdge(13,14);
        addEdge(13,15);
        addEdge(13,16);

//        EGYPT: 14
        addEdge(14,13);
        addEdge(14,23);
        addEdge(14,15);
        addEdge(14,28);

//        E_AFRICA: 15
        addEdge(15,14);
        addEdge(15,13);
        addEdge(15,16);
        addEdge(15,18);
        addEdge(15,17);
        addEdge(15,28);

//        CONGO: 16
        addEdge(16,13);
        addEdge(16,15);
        addEdge(16,17);

//        S_AFRICA: 17
        addEdge(17,16);
        addEdge(17,15);
        addEdge(17,18);

//        MADAGASCAR: 18
        addEdge(18,17);
        addEdge(18,15);

// AFRICA IS DONE: NEXT IS EUROPE
//        ICELAND: 19
        addEdge(19,7);
        addEdge(19,20);
        addEdge(19,21);

//        G_BRITAIN: 20
        addEdge(20,19);
        addEdge(20,21);
        addEdge(20,22);
        addEdge(20,24);

//        SCANDINAVIA: 21
        addEdge(21,19);
        addEdge(21,20);
        addEdge(21,22);
        addEdge(21,25);

//        W_EUROPE: 22
        addEdge(22,20);
        addEdge(22,24);
        addEdge(22,23);
        addEdge(22,13);

//        S_EUROPE: 23
        addEdge(23,24);
        addEdge(23,25);
        addEdge(23,22);
        addEdge(23,14);
        addEdge(23,13);
        addEdge(23,28);

//        N_EUROPE: 24
        addEdge(24,20);
        addEdge(24,21);
        addEdge(24,22);
        addEdge(24,23);
        addEdge(24,25);

//        UKRAINE: 25
        addEdge(25,21);
        addEdge(25,24);
        addEdge(25,23);
        addEdge(25,26);
        addEdge(25,27);
        addEdge(25,28);

// EUROPE IS DONE: NEXT IS ASIA
//        URAL: 26
        addEdge(26,25);
        addEdge(26,27);
        addEdge(26,35);
        addEdge(26,29);

//        AFGHANISTAN: 27
        addEdge(27,25);
        addEdge(27,26);
        addEdge(27,35);
        addEdge(27,36);
        addEdge(27,28);

//        MID_EAST: 28
        addEdge(28,25);
        addEdge(28,23);
        addEdge(28,14);
        addEdge(28,15);
        addEdge(28,27);
        addEdge(28,36);

//        SIBERIA: 29
        addEdge(29,26);
        addEdge(29,35);
        addEdge(29,34);
        addEdge(29,32);
        addEdge(29,30);

//        YAKURSK: 30
        addEdge(30,29);
        addEdge(30,32);
        addEdge(30,31);

//        KAMCHATKA: 31
        addEdge(31,0);
        addEdge(31,30);
        addEdge(31,32);
        addEdge(31,33);
        addEdge(31,34);

//        IRKUTSK: 32
        addEdge(32,31);
        addEdge(32,30);
        addEdge(32,29);
        addEdge(32,34);


//        JAPAN: 33
        addEdge(33,31);
        addEdge(33,34);

//        MONGOLIA: 34
        addEdge(34,33);
        addEdge(34,31);
        addEdge(34,32);
        addEdge(34,29);
        addEdge(34,35);

//        CHINA: 35
        addEdge(35,34);
        addEdge(35,29);
        addEdge(35,26);
        addEdge(35,27);
        addEdge(35,36);
        addEdge(35,37);

//        INDIA: 36
        addEdge(36,27);
        addEdge(36,28);
        addEdge(36,35);
        addEdge(36,37);

//        SIAM: 37
        addEdge(37,35);
        addEdge(37,36);
        addEdge(37,38);

// ASIA IS DONE: NEXT IS AUSTRALIA
//        INDONESIA: 38
        addEdge(38,37);
        addEdge(38,39);
        addEdge(38,41);

//        NEW_GUINEA: 39
        addEdge(39,38);
        addEdge(39,41);
        addEdge(39,40);

//        E_AUSTRALIA: 40
        addEdge(40,39);
        addEdge(40,41);


//        W_AUSTRALIA: 41
        addEdge(41,38);
        addEdge(41,39);
        addEdge(41,40);

    }

    // Add edges
    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        //adjMatrix[j][i] = true;
    }

    // Remove edges
    public void removeEdge(int i, int j) {
        adjMatrix[i][j] = false;
        //adjMatrix[j][i] = false;
    }

    // If we need to print the matrix
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numVertices; i++) {
            s.append(i + ": ");
            for (boolean j : adjMatrix[i]) {
                s.append((j ? 1 : 0) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }


    // So the basic strategy is to loop over every country a player currently owns
    // and add every adjacent country to the countries to attack (but only those
    // counties that are not also owned by the player)

    public List<Integer> countriesToAttack(List<Integer> playerOwnedCountries){
        List<Integer> listToAttack = new ArrayList<>();
        for(int country : playerOwnedCountries) {
            //set the correct adjacency matrix for specific country
            boolean[] adjacent = adjMatrix[country];
            //iterate over it to find which are adjacent
            for(int i = 0; i<adjacent.length;i++)
            {
                //are adjacent if true in the matrix
                if (adjacent[i]==true) {
                    //check if country is owned by player
                    if(!inList(i,playerOwnedCountries))
                    {
                       listToAttack.add(i);
                    }
                }
            }
            //If not owned by a player, add to list
        }
        return listToAttack;


    }
    public boolean inList(int i, List<Integer> list)
    {
       if(list.contains(i)){
           return true;
       }
       else {
           return false;
       }
    }

}


