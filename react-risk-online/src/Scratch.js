
let countryToAttack;
let attackTroops;
let listToAttack = []; // run through matrix to get
let reinforcements;

const randomTroopsAttack = () => {
    attackTroops = Math.floor(Math.random() * 3) + 1;
    if (attackTroops === 1){
        if(Math.random() >= 0.5){
            attackTroops++;
        }
    }
} // for choosing number of attack dice to roll
    // need to validate troops!

const randomAttackCountry = () => {
    // get list to attack... 
    do {
       countryToAttack = Math.floor(Math.random() * 42);
    } while (listToAttack.indexOf(countryToAttack) < 0);
} // need to store country to attack if reattacking

const firstAttack = () => {
    randomTroopsAttack();
    randomAttackCountry();
}

const attackAgain = () => {
    if(Math.random() <= 0.75){
        // attack again

        // !!! get updated list to attack !!!
        if(listToAttack.indexOf(countryToAttack) >= 0){
            // if they dont own the country after first attack, generate chance to attack country just attacked
            if(Math.random() < 0.8){
                //20% chance to change country to attack 
                randomAttackCountry();
            }
            // !!! ATTACK !!!
            randomTroopsAttack();
        } else {
            // !!! ATTACK !!!
            firstAttack(); //technically attack again but calls same functions as first attack;
        } 

    } else {
        // !!! end turn !!!
    }

}

const countriesToReinforce = () =>{
    for(let country in listToAttack){ // loop potential reinforce countries --- may be called something else
        if(ownedCountries.indexOf(country) >= 0){
            reinforceCountries.push(country);
        }
    }
}

const randomlyReinforce = (c) =>{ 
    if (reinforcements === 0) {
        randAdd = 0;
    } else {
        randAdd = Math.floor(Math.random() * 3); // 0, 1, 2 
        if (randAdd > reinforcements) {
            randAdd = reinforcements;
        }
       reinforcements = reinforcements - randAdd;
       // adjust country accordingly here -- existing function? using c (countryID) will also need player?
    }
}



const computerReinforce = () => {
    const reinforceCountries = [];
    countriesToReinforce();
    for(let c in reinforceCountries){
        randomlyReinforce(c);
    }
    
    // get countries that can be attacked -- existing function?
    // run attackable countries through countries to attack function
    // match countries owned... and randomly reinforce these ---> countriesToReinforce function
    // need number of reinforcements ---> existing function?
    // randomly reinforce countries ---> part of existing function?  
 }

 // Move troops? --- currently not sure how to tackle this 



//Will return an integer corresponding to the result of the attack 
// 0 : both lose one troop
// 1 : attacker loses one
// 2 : attacker loses two 
// 3 : defender loses one
// 4 : defender loses two 
function rolldice(attackdice, defenderdice) {
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
                else
                {
                    return 0;
                }
            }
            else
            {
                if (attackerrolls[attackerrolls.size - 2] > defenderrolls[defenderrolls.size - 2]) {
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
            

 

function changeOwner(defendingCountry,attackerid){

    let defenderid = getplayerid(defendingCountry);


    switch(attackerid){
        case 0:
            cont newcountries = 
            player1.countries[player1.countries.size] = defendingCountry;
            break;
        case 1:
            player1.countries[player1.countries.size] = defendingCountry;
            break;
        case 2:
            player1.countries[player1.countries.size] = defendingCountry;
            break;
        case 3:
            player1.countries[player1.countries.size] = defendingCountry;
            break;
        case 4:
            player1.countries[player1.countries.size] = defendingCountry;
            break;
        case 5:
            player1.countries[player1.countries.size] = defendingCountry;
            break;
    }

}

function updateTroops(Country, troopsLost,defender, attackerid)
{      
    if(defender && Country.army <= troopsLost)
    {
        changeOwner(Country,attackerid);
    }
    else{
        Country.army = Country.army-troopslost;
    }
    if(defender)
    {
        let defenderid = getplayerid(defendingCountry);
    
    }
    
}


function attack(attackingCountry, defendingCountry,attackdice) { 
    const defenderdice;
    const attackerid = getplayerid(attackingCountry);
    if(defendingCountry.army>=2)
    {
        defenderdice = 2;
    } 
    else{
        defenderdice =1;
    }
    const result = rolldice(attackdice, defenderdice);
    if(result == 0)
    {
        updateTroops(attackingCountry,1,false);
        updateTroops(defendingCountry,1,true);
    }
    else if(result == 1)
    {
        updateTroops(attackingCountry,1,false);
    }
    else if(result == 2)
    {
        updateTroops(attackingCountry,2,false);
    }
    else if(result == 3)
    {
        updateTroops(defendingCountry,1,true);
    }
    else if(result == 4)
    {
        updateTroops(defendingCountry,2,true);
    }

}