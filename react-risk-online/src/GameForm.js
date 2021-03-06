import { Link } from "react-router-dom";
import { useHistory } from "react-router";
import { useState } from "react";




function GameForm() {
    const [startForm, setStartForm] = useState({});
    const history = useHistory();

    const onChange = (evt) => {
        const stForm = { ...startForm };
        stForm[evt.target.name] = evt.target.value;
        setStartForm(stForm);
    }

    const onSubmit = (evt) => {
        evt.preventDefault(); 
        history.push(`/game/${startForm.players}/${startForm.color}/0`);
    };

    return (
        <div className="container mt-5">
            <h1 className="offset-4" style={{ color: '#f7544d' }}>Create New Game</h1>
            <form onSubmit={onSubmit}>
                <div className="form-group row">
                    <div className="col-4 offset-4">
                        <label htmlFor="color" className="form-label">Color</label>
                        <select className="form-select" name="color" onChange={onChange} required>
                            <option defaultValue>Choose a color</option>
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
                        <label htmlFor="players" className="form-label">Number of Players</label>
                        <select className="form-select" name="players" onChange={onChange} required>
                            <option defaultValue>Select the number of players</option>
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