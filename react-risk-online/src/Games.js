import { Link } from "react-router-dom";
function Games(){
    return(
        <div className="container mt-5">
            <h1 className="offset-4" style={{color: '#f7544d'}}>Load Game</h1>
            <form>
            <div className="form-group row">
                <div className="col-4 offset-4">
            <label for="game" className="form-label">Game</label>
            <select className="form-select" name="game" required>
                <option selected>Select a game</option>

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