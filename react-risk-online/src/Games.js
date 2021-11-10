import { Link } from "react-router-dom";
import { useEffect, useState } from "react/cjs/react.development";
import { useHistory } from "react-router";
import { fetchUserInfo } from "./apiServices/userApi";
function Games({userData}){
    const [games, setGames] = useState([]);
    const [startForm, setStartForm] = useState(0);
    const history = useHistory();

    const onChange = (evt) => {
    
        setStartForm(evt.target.value);
    }

    const onSubmit = (evt) => {
        evt.preventDefault(); 
        history.push(`/game/6/blue/${startForm}`);
    };

    // useEffect(() => {
    //     fetchUserInfo(userData)
    //     .then((info) => setGames(info.ongoingGames))
    //     .catch((err) => console.log(err.toString()));
    // }), [];
    return(
        <div className="container mt-5">
            <h1 className="offset-4" style={{color: '#f7544d'}}>Load Game</h1>
            <form onSubmit={onSubmit}>
            <div className="form-group row">
                <div className="col-4 offset-4">
            <label for="game" className="form-label">Game</label>
            <select className="form-select" name="game" required>
                <option selected>Select a game</option>
                {games.map(g => <option key={g.gameId} value={g.gameId}>game {g.gameId}</option>)}
            </select>
            </div>
            </div>
            </form>
            <div className="mt-3 offset-4">
            <button type="submit" className="btn btn-primary me-1">Start Game</button>
            <Link to="/" className="btn btn-secondary ms-1">Cancel</Link>
            </div>
        </div>
    );
};

export default Games;