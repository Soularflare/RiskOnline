import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import MapSVG from './MapSVG.js';
import {saveGame} from './apiServices/gameApi';
import {useHistory} from "react-router";
function Game() {
    const history = useHistory();
    const [playerTurn, setPlayerTurn] = useState([]);
    const [userId, setUserId] = useState(0);
    const [game, setGame] = useState({});
    const [country, setCountry] = useState({});
    const [player, setPlayer] = useState({});
    const [actionState, setActionState] = useState("");

    useEffect(() => {
        if(playerTurn.length === 0){
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
            document.getElementById("action").removeAttribute("disabled");
            document.getElementById("action").style.opacity = "1.0";
        } else {
            document.getElementById("start").setAttribute("disabled", "disabled");
            document.getElementById("start").style.opacity = "0.4";
            cpuTurn();
        }
    });

    useEffect(() => {
        
            document.getElementById("action").innerHTML = actionState;
        
    }, );

    const cpuTurn = () => {



        //switch to next player
        playerTurn.push(playerTurn.shift());
    };

    const start = evt => {
        if (playerTurn.length === 0) {
            const startbtn = document.getElementById("start");
            startbtn.innerHTML = "End Turn";
            setActionState("reinforce");
            document.getElementById("action").innerHTML = actionState;
            //setup

        } else if (playerTurn === "player"){
            document.getElementById("action").setAttribute("disabled", "disabled");
            document.getElementById("action").style.opacity = "0.0";
            // switch to cpu turns
            playerTurn.push(playerTurn.shift());
        }
    };

    const save = evt => {
        //     saveGame(game, country, player)
        //     .then(() => history.push("/"))
        //     .catch((err) => console.log(err.toString()));
    }; //--temp changed to view localhost

    const action = evt => {
        
        if(actionState === "attack"){
            //attack functionality
            setActionState("confirm attack");
            
        }
        else if(actionState === "confirm attack"){
            //save attack state
            
            // if(attacks > 0){
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
                        <h5 className="offset-2 mt-4">[Action phase]</h5>
                        <h5 className="offset-1 mt-4">Reinforcements/Troops: x</h5>
                        <p className="border border-dark" style={{ marginTop: '100px' }}>InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText</p>
                        <p className="border col-1 offset-5">1</p>
                        <div>
                            <button className="col-1 btn btn-primary me-2 offset-4">+</button>
                            <button className="col-1 btn btn-primary ms-2">-</button>
                        </div>
                    </div>
                </div>
                <div className="col-7 pe-0" style={{ marginTop: '-150px' }}>
                    <MapSVG></MapSVG>

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