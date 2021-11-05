import { useState, useEffect } from 'react';
import { Link, useHistory } from 'react-router-dom';
import MapSVG from './MapSVG.js';
import { saveGame } from './apiServices/gameApi';


function Game() {
    const history = useHistory();
    const [playerTurn, setPlayerTurn] = useState([]);
    const [userId, setUserId] = useState(0);

    useEffect(() => {
        if (playerTurn.length === 0) {
            // getPlayers()
            // .then(players => setPlayerTurn(players))
            // .catch((err) => console.log(err.toString())); -temp changed to get npm start to work
        }
        else if (playerTurn[0] === userId) {
            document.getElementById("start").removeAttribute("disabled");
            document.getElementById("start").style.opacity = "1.0";
            //setup reinforcement phase
            document.getElementById("action").innerHTML = "reinforce";
        } else {
            document.getElementById("start").setAttribute("disabled", "disabled");
            document.getElementById("start").style.opacity = "0.0";
            cpuTurn();
        }
    });

    const cpuTurn = () => {



        //switch to next player
        playerTurn.push(playerTurn.shift());
    };

    const start = evt => {
        if (playerTurn.length === 0) {
            const startbtn = document.getElementById("start");
            startbtn.innerHTML = "End Turn";
            //setup

        } else if (playerTurn === "player") {
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
        const actionbtn = document.getElementById("action");
        if (actionbtn.innerHTML === "attack") {
            //attack functionality
            actionbtn.innerHTM = "confirm attack"
        }
        else if (actionbtn.innerHTML === "confirm attack") {
            //save attack state
            actionbtn.innerHTM = "move"
        }
        else if (actionbtn.innerHTML === "reinforce") {
            actionbtn.innerHTM = "confirm reinforce"
        }
        else if (actionbtn.innerHTML === "confirm reinforce") {

        }
        else if (actionbtn.innerHTML === "move") {

        }
        else if (actionbtn.innerHTML === "confirm move") {

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
                    <button className="btn btn-primary" onClick={action}>Action</button>
                    <button className="btn btn-secondary">Done</button>
                </div>
                <div className="col-4 offset-1">
                    <button className="btn btn-primary" id="start" onClick={start}>Start</button>
                </div>
            </div>
        </div>
    );
};

export default Game;