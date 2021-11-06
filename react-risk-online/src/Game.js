import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import MapSVG from './MapSVG.js';
import {saveGame} from './apiServices/gameApi';
import {useHistory} from "react-router";



function Game() {
    const history = useHistory();
    const [playerTurn, setPlayerTurn] = useState([]);
    const [userId, setUserId] = useState(0);
    const [gameId, setGameId] = useState(0);
    const [country, setCountry] = useState({id: {}, army: {}});
    const [actionState, setActionState] = useState("");
    const [countrySelect, setCountrySelect] = useState("");
    const [countryTarget, setCountryTarget] = useState("");
    const [playerList, setPlayerList] = useState([]);
    const {numPlayers, chosenColor} = useParams();
    const [troopCount, setTroopCount] = useState(0);

    useEffect(() => {
        if (playerTurn[0] === 0) {
            // document.getElementById("action").setAttribute("disabled", "disabled");
            // document.getElementById("action").style.opacity = "0.0";
        
            // getPlayers()
            // .then(players => setPlayerTurn(players))
            // .catch((err) => console.log(err.toString())); -temp changed to get npm start to work
        }
        else if (playerTurn[0] === userId) {
            document.getElementById("start").removeAttribute("disabled");
            document.getElementById("start").style.opacity = "1.0";
            //setup reinforcement phase
            setActionState("reinforce");
            document.getElementById("action").style.opacity = "0.4";
        } else {
            document.getElementById("start").setAttribute("disabled", "disabled");
            document.getElementById("start").style.opacity = "0.4";
            cpuTurn();
        }
    });

    useEffect(() => {
        
            document.getElementById("action").innerHTML = actionState;
         
    }, );

    useEffect(() => {
        if(playerList[0].countries.length == 0){
            //loss condition
        }
        else if (playerList[0].countries.length == 42){
            //win condition
        }
     
    },[playerList] );

    function startGame(playerNumber, colorChoice){

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
                    if (playerNumber === 5) {
                        y = 1;
                    }
                    break;
                case 6:
                    randomArmy();
                    player6.countries.push({ id: `${cIndex}`, army: `${randArmy}` })
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
            player1.countries.push({ id: `${cIndex}`, army: `${randArmy}` })

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
        console.log(playList);
    }


    const cpuTurn = () => {



        //switch to next player
        playerTurn.push(playerTurn.shift());
    };

    const addTroops = evt => {
        setTroopCount(troopCount++);
    }

    const subTroops = evt => {
        setTroopCount(troopCount--);
    }

    const start = evt => {
        if (playerTurn.length === 0) {
            const startbtn = document.getElementById("start");
            startbtn.innerHTML = "End Turn";
            setActionState("reinforce");
            document.getElementById("action").innerHTML = actionState;
            //setup
            startGame(numPlayers, chosenColor);
         

        } else if (playerTurn === "player"){
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.0";
            // switch to cpu turns
            playerTurn.push(playerTurn.shift());
        }
    };

    const onCountrySelect = (id, countryName) => {
        setClickable(false);

        if(countrySelect === countryName){
            //unselect countries
            setCountrySelect("");
            setCountryTarget("");
            //unhighlight selected countries

            //disable action
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
        } else if(countrySelect === ""){
             //verify country is valid
                // if(!isValid(player, id)){
             //     break;
            // }
        
            //highlight country
            setCountrySelect(countryName);
        } else if(countryTarget === countryName){
            //unselect country
            setCountryTarget("");
            //unhighlight selected country

            //disable action
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.4";
        } else if(countryTarget === ""){
                //verify country is valid
                // if(!isValid(player, id)){
                //     break;
                // }
            setCountryTarget(countryName);
            
            //highlight target country

            //enable action
            document.getElementById("action").removeAttribute("disabled");
            document.getElementById("action").style.opacity = "1.0";
        }
        
        

    };

    const save = evt => {
        //     saveGame(gameId, country, player, playerTurn)
        //     .then(() => history.push("/"))
        //     .catch((err) => console.log(err.toString()));
    }; //--temp changed to view localhost


         
            
      
    function getplayerid(country) {
        const id = country.id;
        for(let i=0;i<player.length;i++)
        {
            playercountries = player[i].countries;
            for(let j=0;j<playercoutries.length;j++)
            {
                if(playercountries[j].id == id)
                {
                    return i;
                }
            }
        }
        return 0;
    }
    function replacecountry(){

    }
    const action = evt => {
        
        if(actionState === "attack"){
            let attackerid = 0;
            let defenderid = 0;
            //attack functionality

            //select country to attack from

            //api call to get countries for which to attack 

            //player must select number of die to roll as an attacker 
            setActionState("confirm attack");
            
        }
        else if(actionState === "confirm attack"){
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
        } 
        else if(actionState === "reinforce"){
            
            setActionState("confirm reinforce");
        }
        else if(actionState === "confirm reinforce"){
            //set reinforcements
            // if(reinforcements > 0){
            //     setActionState("reinforce");
            // }
            // else {
                setActionState("attack");
            //}
        }
        else if(actionState === "move"){

            setActionState("confirm move");
        }
        else if(actionState === "confirm move"){

            setActionState("move");
        }
    };

    const done = evt => {
        //skip immediately to next phase
       

        if(actionState === "reinforce" || actionState === "confirm reinforce"){
            setActionState("attack");
        }
        else if( actionState === "attack" || actionState === "confirm attack"){
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
                        <h2 className="col-6 ps-0" style={{ color: '#f7544d' }}>Username</h2>
                    </div>
                    <div className="row">
                        <button className="btn btn-primary col-5" onClick={save}>Save Game</button>
                        <Link to="/" className="btn btn-secondary col-5" >Quit Game</Link>
                    </div>
                    <div className="row">
                        <h2 className="offset-2 mt-4">User's Turn</h2>
                        <h5 className="offset-2 mt-">[Action phase]</h5>
                        <h5 className="offset-1 mt-4">Reinforcements/Troops: x</h5>
                        <p className="border border-dark" style={{ marginTop: '100px' }}>InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText</p>
                        <p className="border col-1 offset-5" id="troopNum">{troupCount}</p>
                        <div>
                            <button className="col-1 btn btn-primary me-2 offset-4" id="troopMinus" onClick={subTroops}>-</button>
                            <button className="col-1 btn btn-primary ms-2" id="troopPlus" onClick={addTroops}>+</button>
                        </div>
                    </div>
                </div>
                <div className="col-7 pe-0" style={{ marginTop: '-150px' }}>
                    <MapSVG key="mapSVG" id="mapSVG" playerList={playerList} onCountrySelect={onCountrySelect}></MapSVG>

               </div>
              <div className="col-2 ps-5 ms-2">
<p>player x attacked country y and won</p>
                    <p>player x attacked country y and lost</p>
                    <p>player x attacked country y and lost</p>
                    <p>player x attacked country y and won</p>
                    <p>player x attacked country y and won</p>
                    <table className="table table-striped table-bordered" style={{ marginTop: '400px' }}>
                        <thead>
                            <tr>
                                RISK
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th>North America</th>
                                <td>x</td>
                            </tr>
                            <tr>
                                <th>South America</th>
                                <td>x</td>
                            </tr>
                            <tr>
                                <th>Europe</th>
                                <td>x</td>
                            </tr>
                            <tr>
                                <th>Africa</th>
                                <td>x</td>
                            </tr>
                            <tr>
                                <th>Asia</th>
                                <td>x</td>
                            </tr>
                            <tr>
                                <th>Australia</th>
                                <td>x</td>
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