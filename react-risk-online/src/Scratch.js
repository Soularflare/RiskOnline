
const player1 = { color: "color", countries: [] };
const player2 = { color: "color", countries: [] };
const player3 = { color: "color", countries: [] };
const player4 = { color: "color", countries: [] };
const player5 = { color: "color", countries: [] };
const player6 = { color: "color", countries: [] };

const startGame = (playerNumber, colorChoice) => {

    let y = 2;
    let cIndex;
    let assignableTroops = 21;
    let randArmy = 0;
    const allIndexNum = [];
    const colors = ["blue", "green", "purple", "red", "white", "black", "yellow", "orange"];


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
                player1.countries.push({ id: `${cIndex}`, army: `${randArmy}` });
                y++;
                break;
            case 2:
                randomArmy();
                player2.countries.push({ id: `${cIndex}`, army: `${randArmy}` });
                y++;
                break;
            case 3:
                randomArmy();
                player3.countries.push({ id: `${cIndex}`, army: `${randArmy}` });
                y++;
                if (playerNumber === 3) {
                    y = 1;
                }
                break;
            case 4:
                randomArmy();
                player4.countries.push({ id: `${cIndex}`, army: `${randArmy}` });
                y++;
                if (playerNumber === 4) {
                    y = 1;
                }
                break;
            case 5:
                randomArmy();
                player5.countries.push({ id: `${cIndex}`, army: `${randArmy}` });
                y++;
                if (playerNumber === 4) {
                    y = 1;
                }
                break;
            case 6:
                randomArmy();
                player6.countries.push({ id: `${cIndex}`, army: `${randArmy}` })
                y++;
                if (playerNumber === 4) {
                    y = 1;
                }
                break;
        }
    }

    const generateGamestate = () => {
        cIndex = Math.floor(Math.random() * 42) + 1;
        allIndexNum.push(cIndex);

        randomArmy();
        player1.countries.push({ id: `${cIndex}`, army: `${randArmy}` })

        for (let x = 0; x < 41; x++) {
            do {
                cIndex = Math.floor(Math.random() * 42) + 1
            } while (allIndexNum.indexOf(cIndex) > 0);

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

            break;

        case 4:
            // each player gets 30 troops 
            // and two players get 11 countries and two players get 10 countries
            // 30 - 11 = 19 >>> every country gets at least one

            assignableTroops = 19;
            generateGamestate();
            assignColors();
            break;

        case 5:
            // each player gets 25 troops 
            // two players get 9 countries and three get 8 countries
            // 25 - 9 = 16 >>> every country gets at least one

            assignableTroops = 16;
            generateGamestate();
            assignColors();
            break;
        case 6:
            // each player gets 20 troops 
            // players get 7 countries 
            // 20 - 7 = 13 >>> every country gets at least one

            assignableTroops = 13;
            generateGamestate();
            assignColors();
            break;

        default:
            break;
    }

}





//Will return an integer corresponding to the result of the attack 
// 0 : both lose one troop
// 1 : attacker loses one
// 2 : attacker loses two 
// 3 : defender loses one
// 4 : deroll loses two 
function attadiceck(attackdice, defenderdice) {
    let highestattack = 0;
    let highestdefend = 0;
    const attackerrolls = [];
    const defenderrolls = [];
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
        for (i = 0; i < attackdice; i++) {
            let random = Math.floor(Math.random() * 6);
            attackerrolls[i] = random;
        }
        if (defenderdice < 2) {
            highestdefend = Math.floor(Math.random() * 6);
            for (j = 0; j < attackerrolls.length; j++) {
                let random = Math.floor(Math.random() * 6);
                if (random > highestattack) {
                    highestattack = random;
                }
            }
            if (highestattack > highestdefend) {
                return 3;
            }
            else {
                return 2;
            }
        }
        else {
            for (i = 0; i < defenderdice; i++) {
                let random = Math.floor(Math.random() * 6);
                defenderrolls[i] = random;
            }
            attackerrolls.sort();
            defenderrolls.sort();

            if (attackerrolls[attackerrolls.size - 1] > defenderrolls[defenderrolls.size - 1]) {
                if (attackerrolls[attackerrolls.size - 2] > defenderrolls[defenderrolls.size - 2]) {
                    return 4;
                }
                else {
                    return 0;
                }
            }
            else {
                if (attackerrolls[attackerrolls.size - 2] > defenderrolls[defenderrolls.size - 2]) {
                    return 0;
                }
                else {
                    return 2;
                }
            }

        }

    }

}

function rolldicattackingCountry, defendingCountryeat
    ta kck() { }

