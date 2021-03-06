import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { useHistory } from "react-router";
import MapSVG from './MapSVG.js';
import { saveGame, loadGame, deleteGame } from './apiServices/gameApi';
import { fetchPts, fetchUserInfo, savePts, saveUserInfo } from './apiServices/userApi';
import { checkAfrica, checkAsia, checkAustralia, checkEurope, checkNAmerica, checkSAmerica } from "./calculations/checkCountries";



function Game({ userData }) {
    const history = useHistory();
    const [playerTurn, setPlayerTurn] = useState([]);
    const [time, setTime] = useState(0);
    const [gameTime, setGameTime] = useState(0);
    const [userId, setUserId] = useState(0);
    const [avatarId, setAvatarId] = useState(0);
    const [actionState, setActionState] = useState("");
    const [countrySelect, setCountrySelect] = useState(null);
    const [countryTarget, setCountryTarget] = useState(null);
    const [playerList, setPlayerList] = useState([{ color: "color", countries: [] }]);
    const { numPlayers, chosenColor, gameId } = useParams();
    const [troopCount, setTroopCount] = useState(0);
    const [reinforcements, setReinforcements] = useState(0);
    const [clickableCountries, setClickableCountries] = useState([]);
    const [attackercountry, setAttacker] = useState({ id: '0', army: 0 });
    const [defendercountry, setDefender] = useState({ id: '0', army: 0 });

    const [CPUattacker, setCPUAttacker] = useState({ id: '0', army: 0 });
    const [CPUdefender, setCPUdefender] = useState({ id: '0', army: 0 });

    useEffect(() => {
        if (CPUdefender.army != 0) {
            cpuAttackfunc();
        }
    }, [CPUdefender]);

    useEffect(() => {
    }, [clickableCountries]);

    useEffect(() => {
        if (playerTurn[0] === 0 || playerTurn.length === 0) {
            console.log("test");
            console.log(playerTurn);
            document.getElementById("start").removeAttribute("disabled");
            document.getElementById("start").style.opacity = "1.0";
            //setup reinforcement phase
            setActionState("reinforce");
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
            //set reinforcement amount



            if (playerTurn.length > 0) {
                let clickable = [];
                let pCountries = [...playerList[playerTurn[0]].countries];
                for (let y = 0; y < pCountries.length; y++) {
                    let cID = pCountries[y].id;
                    clickable.push(cID);
                }
                setClickableCountries([...clickable]);

                const currentPlayer = playerList[playerTurn[0]];
                let total = Math.floor(currentPlayer.countries.length / 3);

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

            let total;
            if (playerTurn.length > 0) {
                const currentPlayer = playerList[playerTurn[0]];
                total = Math.floor(currentPlayer.countries.length / 3);

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

            cpuTurn(total);
        }
    }, [playerTurn]);

    useEffect(() => {
        document.getElementById("action").innerHTML = actionState;
    }, [actionState]);

    useEffect(() => {
        const infoData = document.getElementById("info");
        if (playerList[0].countries.length == 0 && playerTurn.length > 1) {
            //loss condition 
            document.getElementById("start").setAttribute("disabled", "disabled");
            document.getElementById("start").style.opacity = "0.4";
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
            infoData.innerHTML = "You Lose"
            saveLossInfo();
            if (gameId != 0) {
                deleteGame(gameId)
                    .catch((err) => console.log(err.toString()));
            }
        }
        else if (playerList[0].countries.length == 42) {
            //win condition 
            document.getElementById("start").setAttribute("disabled", "disabled");
            document.getElementById("start").style.opacity = "0.4";
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
            infoData.innerHTML = "You Win! +10 Points"
            saveWinInfo();
            if (gameId != 0) {
                deleteGame(gameId)
                    .catch((err) => console.log(err.toString()));
            }
        }

    }, [playerList]);

    const saveWinInfo = () => {
        fetchUserInfo(userData)
            .then((info) => {
                info.totalGames += 1;
                info.wins += 1;
                info.gameTime += gameTime + Math.floor((Date.now() - time) / 60000);
                info.points += 10;
                saveUserInfo(userData, info)
                    .then()
                    .catch((err) => console.log(err.toString()));
            })
            .catch((err) => console.log(err.toString()));
    };

    const saveLossInfo = () => {
        fetchUserInfo(userData)
            .then((info) => {
                info.totalGames += 1;
                info.gameTime += gameTime + Math.floor((Date.now() - time) / 60000);
                saveUserInfo(userData, info)
                    .then()
                    .catch((err) => console.log(err.toString()));
            })
            .catch((err) => console.log(err.toString()));
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
        let countriesy = playerList[playerTurn[0]].countries;
        return countriesy;

    }

    const randomlyReinforce = (total, reinforceCountries, userPlayer, playList) => {
        let rfs = total;

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
                        if (userPlayer.countries[i].id == reinforceCountries[x].id) {
                            userPlayer.countries[i].army += randAdd;
                        }
                    }
                    playList[playerTurn[0]] = userPlayer;

                }
            }
        } while (rfs > 0);

        setPlayerList([...playList]);

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

    function cpuAttackfunc() {
        let bool = false;

        do {
            if (CPUattacker.army <= 1) {
                bool = true;
            }
            else {
                attack(CPUattacker, CPUdefender);
                //getEnemy(CPUattacker);
            }
        } while (!bool);
        //attack(CPUattacker,CPUdefender);
        let playSize = playerTurn;
        playSize.push(playSize.shift());

        setPlayerTurn([...playSize]);
    }

    async function getEnemy(atkCountry) {
        let owned = [];
        owned.push(parseInt(atkCountry.id));
        let toAttack = [];
        countriesToAttackCPU(owned);

    }

    const cpuTurn = (total) => {
        let reinforceCountries = [];
        const playList = playerList;
        const userPlayer = playList[playerTurn[0]];

        reinforceCountries = countriesToReinforce();


        randomlyReinforce(total, reinforceCountries, userPlayer, playList);

        let firstAttack = true;
        let atkCountry = { id: 0, army: 0 };
        let enemyCountry = { id: 0, army: 0 };

        let newRCountries = [];
        for (let x = 0; x < reinforceCountries.length; x++) {
            newRCountries[x] = reinforceCountries[x];
        }

        for (let x = 0; x < reinforceCountries.length; x++) {
            let a = reinforceCountries[x].army;
            if (a < 2) {
                newRCountries.splice(x, 1);
            }
        }

        atkCountry = newRCountries[Math.floor(Math.random() * (newRCountries.length - 1))]
        setCPUAttacker(atkCountry);
        getEnemy(atkCountry);

    };

    const addTroops = evt => {
        if (troopCount < 3) {
            const tmp = troopCount + 1;
            setTroopCount(tmp);
        }
    }

    const subTroops = evt => {
        if (troopCount > 0) {
            const tmp = troopCount - 1;
            setTroopCount(tmp);
        }
    }
    const getUserId = (userData) => {
        fetchUserInfo(userData)
            .then((user) => {
                setUserId(user.userId);
                if(user.microtransactions.length > 0){
                    for (let i = 0; i < user.microtransactions.length; i++) {
                        if(user.microtransactions[i].equipped){
                            setAvatarId(user.microtransactions[i].microtransaction.id);
                        }
                        
                    }
                }
            })
            .catch((err) => console.log(err.toString()));
    };

    const start = evt => {
        document.getElementById("start").style.border = "none";
        if (playerTurn.length === 0) {
            setTime(Date.now());
            const startbtn = document.getElementById("start");
            startbtn.innerHTML = "End Turn";
            setActionState("reinforce");
            document.getElementById("action").innerHTML = actionState;
            if (userData) {
                getUserId(userData);
            }
            //setup
            if (gameId == 0) {
                startGame(numPlayers, chosenColor);

            } else {
                load();
            }
            const playSize = [];
            for (let i = 0; i < playerList.length; i++) {
                playSize.push(i);
            }
            setPlayerTurn([...playSize]);


        } else if (playerTurn[0] === 0) {
            const playSize = [];
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.0";

            for (let i = 0; i < playerList.length; i++) {
                playSize.push(i);
            }

            playSize.push(playSize.shift());
            setPlayerTurn(playSize);

        }

    };

    function findCountry(id) {
        for (let i = 0; i < playerList.length; i++) {
            let countries = playerList[i].countries;

            for (let j = 0; j < countries.length; j++) {
                if (countries[j].id == id.toString()) {

                    return countries[j];
                }
            }
        }
    }

    const onCountrySelect = (id) => {
        const playercountries = playerList[0].countries;
        if (actionState === "reinforce" || actionState === "attack" || actionState === "move") {
            //validate
            console.log("Clicked");
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
            for (let i = 0; i < playercountries.length; i++) {
                if (playercountries[i].id == id) {
                    setCountrySelect(id);
                    setAttacker(playercountries[i]);
                    document.getElementById("action").removeAttribute("disabled");
                    document.getElementById("action").style.opacity = "1.0";
                }

            }


        }

        else if (actionState === "confirm attack" || actionState === "confirm move") {

            if (actionState === "confirm attack") {
                //attack validation
                setCountryTarget(id);
                document.getElementById("action").removeAttribute("disabled");
                document.getElementById("action").style.opacity = "1.0";
                setDefender(findCountry(id));

            } else {
                setCountryTarget(id);
                document.getElementById("action").removeAttribute("disabled");
                document.getElementById("action").style.opacity = "1.0";
                //document.getElementById("action").setAttribute("disabled", "disabled");
                //document.getElementById("action").style.opacity = "0.4";
            }
        }

    };


    const save = (evt) => {

        let players = [];
        players.push({
            'gameId': gameId,
            'userId': userId,
            'turnOrder': 0
        });
        for (let i = 1; i < playerList.length; i++) {
            players.push({
                'gameId': gameId,
                'userId': null,
                'turnOrder': i
            });
        }

        let countryList = [];
        for (let i = 0; i < playerList.length; i++) {
            let countries = playerList[i].countries;
            for (let j = 0; j < countries.length; j++) {
                countryList.push({ 'gameId': gameId, "playerPossession": i, 'countryId': countries[j].id, 'army': countries[j].army });
            }
        }

        const gameObj = {
            "gameId": gameId,
            "timeElapsed": Math.floor((Date.now() - time) / 60000),
            "playerTurn": playerTurn[0],
            "countryList": countryList,
            "players": players
        };
        saveGame(gameId, gameObj)
            .then(() => history.push("/"))
            .catch((err) => console.log(err.toString()));
    };

    const load = () => {
        let click = [];
        loadGame(gameId)
            .then((game) => {
                const pL = game.players;
                const cL = game.countryList;
                setGameTime(game.timeElapsed);

                let colors = ["blue", "green", "purple", "red", "white", "black", "yellow", "orange"];
                let color = "";
                let pList = [];
                let pTurns = [];
                for (let i = 0; i < pL.length; i++) {
                    let cList = [];
                    pTurns.push(i);
                    color = colors.splice(Math.floor(Math.random() * colors.length), 1);

                    for (let j = 0; j < cL.length; j++) {
                        let pos = cL[j].playerPossession;
                        if (pos === i) {
                            cList.push({
                                id: `${cL[j].countryId}`,
                                army: cL[j].army
                            });
                        }
                    }
                    const player = {
                        "color": color[0],
                        "countries": [...cList]
                    }
                    pList.push(player);
                }
                for (let x = 0; x < pList[0].countries.length; x++) {
                    click.push(pList[0].countries[x].id.toString());
                }

                setPlayerList([...pList]);
                setPlayerTurn([...pTurns]);
                setClickableCountries([...click]);
            }).catch((err) => console.log(err.toString()));
    };

    function getCountrylist(ourid) {
        let playList = playerList;
        let list = [];
        for (let i = 0; i < playList[ourid].countries.length; i++) {
            list[i] = parseInt(playList[ourid].countries[i].id);
        }
        return list;
    }

    async function countriesToAttack(owned) {
        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(owned) // 2.
        };
        const response = await fetch("http://localhost:8080/api", init);
        if (response.status !== 201) {
            console.log("Countries sent not valid.");
            return Promise.reject("response is not 200 OK");
        }

        const json = await response.json().then(data => {

            let array = changeArray(data);
            setClickableCountries(array);
        });
        return json;

    }

    async function countriesToAttackCPU(owned) {
        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(owned) // 2.
        };
        const response = await fetch("http://localhost:8080/api", init);
        if (response.status !== 201) {
            console.log("Countries sent not valid.");
            return Promise.reject("response is not 200 OK");
        }

        const json = await response.json().then(data => {
            let enemyid = data[Math.floor(Math.random() * (data.length - 1))]
            let enemy = findCountry(enemyid);
            setCPUdefender(enemy);
        });

        return json;

    }

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

    function changeOwner(defendingCountry, attackerid, defenderid) {
        let playList = playerList;
        let newcountries = playerList[attackerid].countries;

        defendingCountry.army = 1;
        newcountries.push(defendingCountry);
        let player = playerList[attackerid];
        player.countries = newcountries;
        playList[attackerid] = player;


        newcountries = playerList[defenderid].countries;
        let index;
        for (let i = 0; i < newcountries.length; i++) {
            if (newcountries[i].id == defendingCountry.id) {
                index = i;
            }
        }
        newcountries.splice(index, 1);
        player = playerList[defenderid];
        player.countries = newcountries;
        playList[defenderid] = player;

        setPlayerList([...playList]);
    }

    function updateTroops(Country, troopsLost, defender, attackerid) {

        let playList = playerList;

        if (defender && Country.army <= troopsLost) {
            let defenderid = getplayerid(Country);
            changeOwner(Country, attackerid, defenderid);
        }
        else {
            Country.army = Country.army - troopsLost;

            let id;
            if (defender) {
                id = getplayerid(Country);
            }
            else {
                id = attackerid;
            }
            let newcountries = playerList[id].countries;

            let index;
            for (let i = 0; i < newcountries.length; i++) {
                if (newcountries[i].id == Country.id) {
                    index = i;
                }
            }
            newcountries.splice(index, 1);
            newcountries.splice(index, 0, Country);
            let player = playerList[id];
            player.countries = newcountries;
            playList[id] = player;
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

                highestattack = attackerrolls[attackerrolls.length - 1];

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

                if (attackerrolls[attackerrolls.length - 1] > defenderrolls[defenderrolls.length - 1]) {
                    if (attackerrolls[attackerrolls.length - 2] > defenderrolls[defenderrolls.length - 2]) {
                        return 4;
                    }
                    else {
                        return 0;
                    }
                }
                else {
                    if (attackerrolls[attackerrolls.length - 2] > defenderrolls[defenderrolls.length - 2]) {
                        return 0;
                    }
                    else {
                        return 2;
                    }
                }
            }
        }
    }

    function attack(attackingCountry, defendingCountry) {
        let defenderdice;
        let attackdice;
        const attackerid = getplayerid(attackingCountry);
        if (attackingCountry.army >= 4) {
            attackdice = 3;
        }
        else if (attackingCountry.army = 3) {
            attackdice = 2;
        }
        else {
            attackdice = 1;
        }
        if (defendingCountry.army >= 2) {
            defenderdice = 2;
        }
        else {
            defenderdice = 1;
        }
        const result = rolldice(attackdice, defenderdice);

        if (result == 0) {
            updateTroops(attackingCountry, 1, false, attackerid);
            updateTroops(defendingCountry, 1, true, attackerid);
        }
        else if (result == 1) {
            updateTroops(attackingCountry, 1, false, attackerid);
        }
        else if (result == 2) {
            updateTroops(attackingCountry, 2, false, attackerid);
        }
        else if (result == 3) {
            updateTroops(defendingCountry, 1, true, attackerid);
        }
        else if (result == 4) {
            updateTroops(defendingCountry, 2, true, attackerid);
        }

    }

    function validateAttack() {
        if (attackercountry.id == 0 && attackercountry.army == 0) {
            return false;
        }
        else if (defendercountry.id == 0 && defendercountry.army == 0) {
            return false;
        }
        else if (attackercountry.army == 1) {
            return false;
        }
        else {
            return true;
        }
    }


    function changeArray(array) {
        let newarray = [];
        for (let i = 0; i < array.length; i++) {
            newarray[i] = array[i].toString();
        }
        return newarray;

    }


    const action = evt => {

        if (actionState === "attack") {
            let pCountries = [...playerList[playerTurn[0]].countries];

            let arrayowned = getCountrylist(0);


            countriesToAttack(arrayowned);

            setActionState("confirm attack");
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";


        }
        else if (actionState === "confirm attack") {

            let bool = validateAttack(attackercountry, defendercountry);
            if (bool) {
                attack(attackercountry, defendercountry);
            }

            let newclickable = [...playerList[playerTurn[0]].countries];
            let actual = [];
            for (let i = 0; i < newclickable.length; i++) {
                actual.push(newclickable[i].id);
            }
            setClickableCountries(actual);


            setActionState("attack");

            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
        }
        else if (actionState === "reinforce") {
            // set reinforcements
            if (reinforcements >= troopCount) {
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

                if (tmp > 0) {

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
                    if (userPlayer.countries[i].army > tmpTroops) {
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
            document.getElementById("start").style.border = "2px solid blue";
        }
    };

    return (
        <div className="container-fluid mx-0">
            <div className="row">
                <div className="col-2">
                    <div className="row border border-dark border-2">
                        <img className="col-6 ps-0" src={avatarId != 0 ? require(`./avatars/avatar${avatarId}.png`).default : require('./risk-map.png').default} height="100px" alt="avatar" />
                        <h2 className="col-6 ps-0" style={{ color: '#f7544d' }}>{userData ? userData.userName : "Guest"}</h2>
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