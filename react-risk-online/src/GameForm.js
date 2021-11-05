import { Link } from "react-router-dom";
import { useHistory } from "react-router";
import { useState } from "react";




function GameForm() {
    const [startForm, setStartForm] = useState({});
    const history = useHistory();
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


    const onChange = (evt) => {
        const stForm = { ...startForm };
        stForm[evt.target.name] = evt.target.value;
        setStartForm(stForm);
    }

    const onSubmit = (evt) => {
        evt.preventDefault();
        console.log(startForm);
        const playerNumber = startForm.players;
        console.log(playerNumber);
        const colorChoice = startForm.color;
        console.log(colorChoice);

        startGame(playerNumber, colorChoice);

        console.log(player1);
        console.log(player2);
        console.log(player3);
        console.log(player4);
        console.log(player5);
        console.log(player6);

        // history.push("/game");
    };

    return (
        <div className="container mt-5">
            <h1 className="offset-4" style={{ color: '#f7544d' }}>Create New Game</h1>
            <form onSubmit={onSubmit}>
                <div className="form-group row">
                    <div className="col-4 offset-4">
                        <label for="color" className="form-label">Color</label>
                        <select className="form-select" name="color" onChange={onChange} required>
                            <option selected>Choose a color</option>
                            <option value="blue">blue</option>
                            <option value="green">green</option>
                            <option value="purple">purple</option>
                            <option value="red">red</option>
                            <option value="white">white</option>
                            <option value="black">black</option>
                            <option value="yellow">yellow</option>
                            <option value="orange">orange</option>
                        </select>
                    </div>
                </div>
                <div className="form-group row">
                    <div className="col-4 offset-4">
                        <label for="players" className="form-label">Number of Players</label>
                        <select className="form-select" name="players" onChange={onChange} required>
                            <option selected>Select the number of players</option>
                            <option value={3}>3</option>
                            <option value={4}>4</option>
                            <option value={5}>5</option>
                            <option value={6}>6</option>
                        </select>
                    </div>
                </div>
                <div className="mt-3 offset-4">
                    <button type="submit" className="btn btn-primary me-1">Start Game</button>
                    {/* <Link to="/game" className="btn btn-primary ms-1">Start Game</Link> */}
                    <Link to="/" className="btn btn-secondary ms-1">Cancel</Link>
                </div>
            </form>

        </div>
    );
};

export default GameForm;