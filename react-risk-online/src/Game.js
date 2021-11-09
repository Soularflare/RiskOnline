import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import MapSVG from './MapSVG.js';
import { saveGame, loadGame } from './apiServices/gameApi';
import { fetchPts, savePts } from './apiServices/userApi';
import { useHistory } from "react-router";
import {checkAfrica, checkAsia, checkAustralia, checkEurope, checkNAmerica, checkSAmerica} from "./calculations/checkCountries";



function Game({userData}) {
    const history = useHistory();
    const [playerTurn, setPlayerTurn] = useState([]);
    const [userId, setUserId] = useState(0);
    const [actionState, setActionState] = useState("");
    const [countrySelect, setCountrySelect] = useState(null);
    const [countryTarget, setCountryTarget] = useState(null);
    const [playerList, setPlayerList] = useState([{ color: "color", countries: [] }]);
    const { numPlayers, chosenColor, gameId } = useParams();
    const [troopCount, setTroopCount] = useState(0);
    const [reinforcements, setReinforcements] = useState(0);
    const [clickableCountries, setClickableCountries] = useState([]);

    useEffect(() => {
        
        if (playerTurn[0] === 0 || playerTurn.length === 0) {
            
            document.getElementById("start").removeAttribute("disabled");
            document.getElementById("start").style.opacity = "1.0";
            //setup reinforcement phase
            setActionState("reinforce");
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
            //set reinforcement amount


            
            if(playerTurn.length > 0){
                let clickable = [];
                let pCountries = [...playerList[playerTurn[0]].countries];
                for (let y = 0; y < pCountries.length; y++) {
                    let cID = pCountries[y].id;
                    clickable.push(cID);
                }
                setClickableCountries(clickable);

                const currentPlayer = playerList[playerTurn[0]];
                const total = Math.floor(currentPlayer.countries.length / 3);

                if (checkAsia(currentPlayer)) {
                    total = total + 7;
                }
                if (checkNAmerica(currentPlayer)) {
                    total = total + 5;
                }
                if (checkEurope(currentPlayer)) {
                    total = total + 5;
                }
                if (checkAfrica(currentPlayer)) {
                    total = total + 3;
                }
                if (checkSAmerica(currentPlayer)) {
                    total = total + 2;
                }
                if (checkAustralia(currentPlayer)) {
                    total = total + 2;
                }

                if (total <= 3) {
                    setReinforcements(3);
                } else {
                    setReinforcements(total);
                }
            }
            
        } else {
            document.getElementById("start").setAttribute("disabled", "disabled");
            document.getElementById("start").style.opacity = "0.4";
            setClickableCountries([]);
            cpuTurn();
        }
    }, [playerTurn]);

    useEffect(() => {

        document.getElementById("action").innerHTML = actionState;

    }, [actionState]);

    useEffect(() => {
        
        const infoData = document.getElementById("info");
        if(playerList[0].countries.length == 0 && playerTurn.length > 1){
            //loss condition 
            document.getElementById("start").setAttribute("disabled", "disabled");
            document.getElementById("start").style.opacity = "0.4";
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
            infoData.innerHTML = "You Lose"
            //delete game
        }
        else if (playerList[0].countries.length == 42){
            //win condition 
            document.getElementById("start").setAttribute("disabled", "disabled");
            document.getElementById("start").style.opacity = "0.4";
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
            infoData.innerHTML = "You Win! +10 Points"
            addPts(10);
            //delete game
        }

    }, [playerList]);

    const addPts = (num) => {
        num += fetchPts(userData);
        savePts(userData, num);
    };


    function startGame(playerNumber, colorChoice) {

        const player1 = { color: "color", countries: [] };
        const player2 = { color: "color", countries: [] };
        const player3 = { color: "color", countries: [] };
        const player4 = { color: "color", countries: [] };
        const player5 = { color: "color", countries: [] };
        const player6 = { color: "color", countries: [] };

        const playList = [];

        let y = 2;
        let cIndex;
        let assignableTroops = 21;
        let randArmy = 0;
        const allIndexNum = [];
        const colors = ["blue", "green", "purple", "red", "white", "black", "yellow", "orange"];
        playerNumber = parseInt(playerNumber);

        const randomArmy = () => {
            if (assignableTroops === 0) {
                randArmy = 0;
            } else {
                randArmy = Math.floor(Math.random() * 3);
                if (randArmy > assignableTroops) {
                    randArmy = assignableTroops;
                }
                assignableTroops = assignableTroops - randArmy;
            }
            randArmy++;
        }

        const distributeArmyAndCountries = () => {
            switch (y) {
                case 1:
                    randomArmy();
                    player1.countries.push({ id: `${cIndex}`, army: randArmy });
                    y++;
                    break;
                case 2:
                    randomArmy();
                    player2.countries.push({ id: `${cIndex}`, army: randArmy });
                    y++;
                    break;
                case 3:
                    randomArmy();
                    player3.countries.push({ id: `${cIndex}`, army: randArmy });
                    y++;
                    if (playerNumber === 3) {
                        y = 1;
                    }
                    break;
                case 4:
                    randomArmy();
                    player4.countries.push({ id: `${cIndex}`, army: randArmy });
                    y++;
                    if (playerNumber === 4) {
                        y = 1;
                    }
                    break;
                case 5:
                    randomArmy();
                    player5.countries.push({ id: `${cIndex}`, army: randArmy });
                    y++;
                    if (playerNumber === 5) {
                        y = 1;
                    }
                    break;
                case 6:
                    randomArmy();
                    player6.countries.push({ id: `${cIndex}`, army: randArmy })
                    y++;
                    if (playerNumber === 6) {
                        y = 1;
                    }
                    break;
            }
        }

        const generateGamestate = () => {
            cIndex = Math.floor(Math.random() * 42);
            allIndexNum.push(cIndex);

            randomArmy();
            player1.countries.push({ id: `${cIndex}`, army: randArmy })

            for (let x = 0; x < 41; x++) {
                do {
                    cIndex = Math.floor(Math.random() * 42);
                } while (allIndexNum.indexOf(cIndex) >= 0);

                allIndexNum.push(cIndex);
                distributeArmyAndCountries();
            }
        }

        const assignColors = () => {

            player1.color = colorChoice;
            let colorIndex = colors.indexOf(colorChoice);
            colors.splice(colorIndex, 1);

            colorIndex = Math.floor(Math.random() * colors.length);
            player2.color = colors[colorIndex];
            colors.splice(colorIndex, 1);

            colorIndex = Math.floor(Math.random() * colors.length);
            player3.color = colors[colorIndex];
            colors.splice(colorIndex, 1);

            if (playerNumber >= 4) {
                colorIndex = Math.floor(Math.random() * colors.length);
                player4.color = colors[colorIndex];
                colors.splice(colorIndex, 1);
            }
            if (playerNumber >= 5) {
                colorIndex = Math.floor(Math.random() * colors.length);
                player5.color = colors[colorIndex];
                colors.splice(colorIndex, 1);
            }
            if (playerNumber === 6) {
                colorIndex = Math.floor(Math.random() * colors.length);
                player6.color = colors[colorIndex];
                colors.splice(colorIndex, 1);
            }
        }

        switch (playerNumber) {
            case 3:
                // each player gets 35 troops and 14 countries
                //35-14 = 21 >>> every country gets at least one
                assignableTroops = 21;
                generateGamestate();
                assignColors();
                playList.push(player1);
                playList.push(player2);
                playList.push(player3);
                break;

            case 4:
                // each player gets 30 troops 
                // and two players get 11 countries and two players get 10 countries
                // 30 - 11 = 19 >>> every country gets at least one
                assignableTroops = 19;
                generateGamestate();
                assignColors();
                playList.push(player1);
                playList.push(player2);
                playList.push(player3);
                playList.push(player4);
                break;

            case 5:
                // each player gets 25 troops 
                // two players get 9 countries and three get 8 countries
                // 25 - 9 = 16 >>> every country gets at least one
                assignableTroops = 16;
                generateGamestate();
                assignColors();
                playList.push(player1);
                playList.push(player2);
                playList.push(player3);
                playList.push(player4);
                playList.push(player5);
                break;
            case 6:
                // each player gets 20 troops 
                // players get 7 countries 
                // 20 - 7 = 13 >>> every country gets at least one

                assignableTroops = 13;
                generateGamestate();
                assignColors();
                playList.push(player1);
                playList.push(player2);
                playList.push(player3);
                playList.push(player4);
                playList.push(player5);
                playList.push(player6);
                break;

            default:
                break;
        }
        setPlayerList([...playList]);
    }

    const countriesToReinforce = () => {
        // need to run countries owned through matrix to get countries to attack
        // then run countries to attack through matrix again to get countries adj.
        // then --->
        // for (let x = 0; x < adjCountries.length; x++) { // loop potential reinforce countries and get owned countries by comparing to owned list
        //     if (ownedCountries.indexOf(adjCountries[x]) >= 0) {
        //         reinforceCountries.push(adjCountries[x]);
        //     }
        // }
    }

    const randomlyReinforce = (reinforceCountries, userPlayer, playList) => {
        let rfs = reinforcements;
        do {
            let randAdd;
            for (let x = 0; x < reinforceCountries.length; x++) {
                if (rfs === 0) {
                    randAdd = 0;
                } else {
                    randAdd = Math.floor(Math.random() * 3); // 0, 1, 2 
                    if (randAdd > rfs) {
                        randAdd = rfs;
                    }
                    rfs = rfs - randAdd;

                    for (let i = 0; i < userPlayer.countries.length; i++) {
                        if (userPlayer.countries[i].id == reinforceCountries[x]) {
                            userPlayer.countries[i].army += rfs;
                        }
                    }
                    playList[playerTurn[0]] = userPlayer;
                }
            }
        } while (rfs > 0);

    }

    const cpuAttack = (reinforceCountries, atkCountry, enemyCountry, firstAttack) => {
        let atkArmy;
        let attackNumber;

        if (!firstAttack) {
            atkArmy = parseInt(atkCountry.army);
        }

        if (firstAttack === true || Math.random() <= 0.2 || atkArmy <= 1) {
            do {
                let num = Math.floor(Math.random() * reinforceCountries.length);
                atkCountry = reinforceCountries[num];
                atkArmy = parseInt(atkCountry.army);
            } while (atkArmy <= 1); //chooses from border countries with armies > 1
        } //else use atkCountry again for attack

        do {
            attackNumber = Math.floor(Math.random() * 3) + 1;
        } while (atkArmy - attackNumber <= 1); //chooses appropriate dice number based on army avaliable

        // enemyCountry; //need to get from matrix function

        // feed attackNumber, attackCountry, defendCountry into Attack function
    }

    const cpuTurn = () => {
        const reinforceCountries = [];
        const playList = playerList;
        const userPlayer = playList[playerTurn[0]];
        countriesToReinforce(); // gets list of owned border countries -- needs finishing
        randomlyReinforce(reinforceCountries, userPlayer, playList); // reinforce computer countries

        setPlayerList([...playList]); //maybe move ???

        let firstAttack = true;
        let atkCountry;
        let enemyCountry;

        do {
            for (let x = 0; x < reinforceCountries.length; x++) {
                let a = parseInt(reinforceCountries[x].army);
                if (a < 2) {
                    reinforceCountries.slice(x, 1);
                }
            }

            if (reinforceCountries.length !== 0) {
                cpuAttack(reinforceCountries, atkCountry, enemyCountry, firstAttack);
            }

            firstAttack = false;

            // need to evaluate whether attacked country was taken to update attacks
            // setPlayerList??? 

        } while (Math.random() <= 0.75 && reinforceCountries.length > 0);

        //switch to next player
        playerTurn.push(playerTurn.shift());
    };

    const addTroops = evt => {
        if(troopCount < 3){
            const tmp = troopCount + 1;
            setTroopCount(tmp);
        }
    }

    const subTroops = evt => {
        if(troopCount > 0){
            const tmp = troopCount - 1;
            setTroopCount(tmp);
        }
    }

    const start = evt => {
        if (playerTurn.length === 0) {
            const startbtn = document.getElementById("start");
            startbtn.innerHTML = "End Turn";
            setActionState("reinforce");
            document.getElementById("action").innerHTML = actionState;
            // if(userData){
            //     getUserId(userData);
            // }
            //setup
            if(gameId == 0){
                startGame(numPlayers, chosenColor);
            } else {
                loadGame();
            }
            const playSize = []
            for (let i = 0; i < playerList.length; i++) {
                playSize.push(i);
            }
            setPlayerTurn([...playSize]);
         

        } else if (playerTurn[0] === 0){
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.0";
            // switch to cpu turns
            playerTurn.push(playerTurn.shift());
            
        }
    };

    const onCountrySelect = (id) => {
       

        const playercountries = playerList[0].countries;
        if(actionState === "reinforce" || actionState === "attack" || actionState === "move"){
            //validate 
            
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
            for (let i = 0; i < playercountries.length; i++) {
                if(playercountries[i].id == id){
                    setCountrySelect(id);
                    document.getElementById("action").removeAttribute("disabled");
                    document.getElementById("action").style.opacity = "1.0";
                }
                
            }
            

        }
        
        else if(actionState === "confirm attack" || actionState === "confirm move"){
            
            if(actionState === "confirm attack"){
                //attack validation
                setCountryTarget(id);
                document.getElementById("action").removeAttribute("disabled");
                document.getElementById("action").style.opacity = "1.0";
            } else {
                document.getElementById("action").setAttribute("disabled", "disabled");
                document.getElementById("action").style.opacity = "0.4";  
            for (let i = 0; i < playercountries.length; i++) {
                if(playercountries[i].id == id){
                    setCountryTarget(id);
                    document.getElementById("action").removeAttribute("disabled");
                    document.getElementById("action").style.opacity = "1.0";
                }
            }
           
        }
    }

    };

    const save = (evt) => {

        let players = [];
        players.push({
            'gameId': gameId,
            'userId': userId,
            'turnOrder': 0
        })
        
        let countryList = [];
        for (let i = 0; i < playerList.length; i++) {
            for (let j = 0; j < playerList[i].length; j++) {
                countryList.push({'gameId': gameId, "playerPossession": i, ...playerList[i].countries});
                
            }
            
        }
        const gameObj = {
            "gameId" : gameId,
            "timeElapsed" : 0,
            "playerTurn": playerTurn[0],
            "countryList": countryList,
            "players": players
        };
            saveGame(gameId, gameObj)
            .then(() => history.push("/"))
            .catch((err) => console.log(err.toString()));
    }; 

    const load = () => {
       loadGame()
       .then((game) => {
        const [gameState, players, countries] = game;
        setPlayerTurn([...gameState.players.length]);

        let pList = []
        let colors = ["blue", "green", "purple", "red", "white", "black", "yellow", "orange"];
        let color = "";
        let countryList = [];
        for (let i = 0; i < gameState.players.length; i++) {
            color = colors.splice( Math.floor(Math.random() * colors.length),1)

            for (let j = 0; j < countries.length; j++) {
                if(countries[j].playerPossession == i){
                    countryList.push({
                        id: countries[j].countryId,
                        army: countries[j].army
                    });
                }
                
            }
            const player = {
                "color": color,
                "countries": countryList
            }
            pList.push(player);
        }
        setPlayerList(pList);
       }).catch((err) => console.log(err.toString())); 
    };



    function getplayerid(country) {
        const id = country.id;
        for (let i = 0; i < playerList.length; i++) {
            const playercountries = playerList[i].countries;
            for (let j = 0; j < playercountries.length; j++) {
                if (playercountries[j].id == id) {
                    return i;
                }
            }
        }
        return -1;
    }


//On change Owner I am taking in the defending country and an attacker id. The defending country
 //will be added to the other countries at the player index attackerid. The defending country must then
 //also be matched to its owner and removed

 function changeOwner(defendingCountry,attackerid, defenderid){
    console.log("reaches here");
    let playList = playerList;
    console.log(playList);

    newcountries = playerList[attackerid].countries; 
    newcountries[newcountries.length] = defendingCountry;
    player = playerList[attackerid];
    player.countries = newcountries;
    playList[attackerid] = player;


    let newcountries = playerList[defenderid].countries;
    let index; 
    for(let i = 0; i<newcountries; i++)
    {
        if(newcountries[i].id = defendingCountry.id)
        {
            index = i;
        }
    }
    newcountries.splice(index,1);
    let player = playerList[defenderid];
    player.countries = newcountries;
    playList[defenderid] = player;

    setPlayerList(playList);
 }

 function updateTroops(Country, troopsLost,defender,attackerid)
{     
    console.log("Country: " + Country.id + " " + Country.army);
    console.log("troopsLost: " + troopsLost);
    console.log("defender: " + defender);
    console.log("attackerid" + attackerid);
    let playList = playerList;
    console.log(playList);

    if(defender && Country.army <= troopsLost)
    {
        let defenderid = getplayerid(Country);
        changeOwner(Country,attackerid,defenderid);
    }
    else{
        Country.army = Country.army-troopsLost;
        console.log("Country army is: ", Country.army);
        let id;
        if(defender)
        {
            id = getplayerid(Country);
        }
        else{
            id = attackerid;
        }
        let newcountries = playerList[id].countries; 
        
        let index;
        for(let i = 0;i<newcountries.length;i++)
        {
            if(newcountries[i].id ==Country.id)
            {
                index = i;
                console.log("updates");
            }
        }
        newcountries.splice(index,1);
        newcountries.splice(index,0,Country);
        console.log("New countries");
        console.log(newcountries);
        let player = playerList[id];
        player.countries = newcountries;
        playList[id] = player;
        console.log("This is next playlist: ");
        console.log(playList);
        setPlayerList([...playList]);
    }
    
}

//Will return an integer corresponding to the result of the attack 
// 0 : both lose one troop
// 1 : attacker loses one
// 2 : attacker loses two 
// 3 : defender loses one
// 4 : defender loses two 
function rolldice(attackdice, defenderdice) {
    let highestattack = 0;
    let highestdefend = 0;
    let attackerrolls = [];
    let defenderrolls = [];
    if (attackdice < 2) {
        highestattack = Math.floor(Math.random() * 6);
        if (highestdefend < 2) {
            highestdefend = Math.floor(Math.random() * 6);
        }
        else {
            highestdefend = Math.max(Math.floor(Math.random() * 6), Math.floor(Math.random() * 6));
        }
        if (highestattack > highestdefend) {
            return 3;
        }
        else {
            return 1;
        }
    }
    else {
        for (let i = 0; i < attackdice; i++) {
            let random = Math.floor(Math.random() * 6);
            attackerrolls[i] = random;
        }
        if (defenderdice < 2) {
            highestdefend = Math.floor(Math.random() * 6);
            attackerrolls.sort();
            console.log("Attacker rolls: "+ attackerrolls);
            highestattack = attackerrolls[attackerrolls.length-1];
            if (highestattack > highestdefend) {
                return 3;
            }
            else {
                return 2;
            }
        }
        else {
            for (let i = 0; i < defenderdice; i++) {
                let random = Math.floor(Math.random() * 6);
                defenderrolls[i] = random;
            }
            attackerrolls.sort();
            defenderrolls.sort();
            console.log("Attacker rolls: "+ attackerrolls);
            console.log("Defender rolls: "+ defenderrolls);
            if (attackerrolls[attackerrolls.length - 1] > defenderrolls[defenderrolls.length - 1]) {
                if (attackerrolls[attackerrolls.length - 2] > defenderrolls[defenderrolls.length - 2]) {
                    return 4;
                }
                else
                {
                    return 0;
                }
            }
            else
            {
                if (attackerrolls[attackerrolls.length - 2] > defenderrolls[defenderrolls.length - 2]) {
                    return 0;
                }
                else
                {
                    return 2;
                }
            }
        }
    }
}

 function attack(attackingCountry, defendingCountry,attackdice) { 
    let defenderdice;
    const attackerid = getplayerid(attackingCountry);
    if(defendingCountry.army>=2)
    {
        defenderdice = 2;
    } 
    else{
        defenderdice =1;
    }
    const result = rolldice(attackdice, defenderdice);
    console.log("Result is " + result);
    if(result == 0)
    {
        updateTroops(attackingCountry,1,false,attackerid);
        updateTroops(defendingCountry,1,true,attackerid);
    }
    else if(result == 1)
    {
        updateTroops(attackingCountry,1,false,attackerid);
    }
    else if(result == 2)
    {
        updateTroops(attackingCountry,2,false,attackerid);
    }
    else if(result == 3)
    {
        updateTroops(defendingCountry,1,true,attackerid);
    }
    else if(result == 4)
    {
        updateTroops(defendingCountry,2,true,attackerid);
    }

}      



    const action = evt => {

        if (actionState === "attack") {
            let attackingCountry = { id: 0, army: 10 };
            let defendingCountry = { id: 1, army: 4 };
            let attackdice = 3;
            //attack functionality
            console.log("Attacking beginning");
            attack(attackingCountry, defendingCountry, attackdice);
            //select country to attack from

            //api call to get countries for which to attack 

            //player must select number of die to roll as an attacker 
            setActionState("confirm attack");
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
            
        }
        else if (actionState === "confirm attack") {
            let attackerid = 0;
            let defenderid = 0;
            //call attack(attackingCountry, defendingCountry,attackdice)

            //save attack state

            //if(attacks > 0){
            //     setActionState("attack");
            // }
            // else {
            setActionState("move");
            //}
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
        } 
        else if(actionState === "reinforce"){
            // set reinforcements
            if(reinforcements >= troopCount){
            const tmp = reinforcements - troopCount;
            setReinforcements(tmp)
            const playList = playerList;
            const userPlayer = playList[0];
            for (let i = 0; i < userPlayer.countries.length; i++) {
                if (userPlayer.countries[i].id == countrySelect) {
                    userPlayer.countries[i].army += troopCount;

                }
            }
            playList[0] = userPlayer;

            setPlayerList([...playList]);
            setTroopCount(0);
            
            if(tmp > 0){
                
                setActionState("reinforce");
            }
            else {
                setActionState("attack");
            }
            }
            else {
                setActionState("reinforce");
            }
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
        }
        else if (actionState === "move") {

            setActionState("confirm move");
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
        }
        else if (actionState === "confirm move") {
            const playList = playerList;
            const userPlayer = playList[0];
            let tmpTroops = troopCount;
            for (let i = 0; i < userPlayer.countries.length; i++) {
                if (userPlayer.countries[i].id == countrySelect) {
                    if(userPlayer.countries[i].army > tmpTroops){
                    userPlayer.countries[i].army -= tmpTroops;
                    } else {
                        tmpTroops = 0;
                    }

                }

            }
            for (let i = 0; i < userPlayer.countries.length; i++) {
                if (userPlayer.countries[i].id == countryTarget) {
                    userPlayer.countries[i].army += tmpTroops;

                }

            }
            playList[0] = userPlayer;

            setPlayerList([...playList]);
            setTroopCount(0);
            setActionState("move");
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
        }
    };

    const done = evt => {
        //skip immediately to next phase


        if (actionState === "reinforce" || actionState === "confirm reinforce") {
            setActionState("attack");
        }
        else if (actionState === "attack" || actionState === "confirm attack") {
            setActionState("move");
        }
        else {
            //highlight endturn btn

        }
    };

    return (
        <div className="container-fluid mx-0">
            <div className="row">
                <div className="col-2">
                    <div className="row border border-dark border-2">
                        <img className="col-6 ps-0" src={require('./risk-map.png').default} height="100px" alt="user_avatar" />
                        <h2 className="col-6 ps-0" style={{ color: '#f7544d' }}>{userData? userData.username : "Username"}</h2>
                    </div>
                    <div className="row">
                        {userData && <button className="btn btn-primary col-5" onClick={save}>Save Game</button>}
                        <Link to="/" className="btn btn-secondary col-5" >Quit Game</Link>
                    </div>
                    <div className="row">
                        <h2 className="offset-2 mt-4">{playerTurn[0] === 0 ? "User's" : "Player" + playerTurn[0] + "'s"} Turn</h2>
                        <h5 className="offset-2 mt-">{actionState}</h5>
                        <h5 className="offset-1 mt-4">Reinforcements/Troops: {reinforcements}</h5>
                        <p className="border border-dark" id="info" style={{ marginTop: '100px' }}></p>
                        <p className="border col-1 offset-5" id="troopNum">{troopCount}</p>
                        <div>
                            <button className="col-1 btn btn-primary me-2 offset-4" id="troopMinus" onClick={subTroops}>-</button>
                            <button className="col-1 btn btn-primary ms-2" id="troopPlus" onClick={addTroops}>+</button>
                        </div>
                    </div>
                </div>
                <div className="col-7 pe-0" style={{ marginTop: '-150px' }}>
                    <MapSVG key="mapSVG" id="mapSVG" playerList={playerList} onCountrySelect={onCountrySelect} clickableCountries={clickableCountries}></MapSVG>

                </div>
                <div className="col-2 ps-5 ms-2">
                    <div id="playerChat">
                        <p></p>
                    </div>
                    
                    <table className="table table-striped table-bordered" style={{ marginTop: '400px' }}>
                        <thead>
                            <tr>
                                RISK
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th>North America</th>
                                <td>5</td>
                            </tr>
                            <tr>
                                <th>South America</th>
                                <td>2</td>
                            </tr>
                            <tr>
                                <th>Europe</th>
                                <td>5</td>
                            </tr>
                            <tr>
                                <th>Africa</th>
                                <td>3</td>
                            </tr>
                            <tr>
                                <th>Asia</th>
                                <td>7</td>
                            </tr>
                            <tr>
                                <th>Australia</th>
                                <td>2</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

            </div>
            <div className="row">
                <div className="col-4">
                    <button className="btn btn-primary" id="action" onClick={action}>Action</button>
                    <button className="btn btn-secondary" onClick={done}>Done</button>
                </div>
                <div className="col-4 offset-1">
                    <button className="btn btn-primary" id="start" onClick={start}>Start</button>
                </div>
            </div>
        </div>
    );
};

export default Game;