import { Link } from 'react-router-dom';
import MapSVG from './MapSVG.js';
function Game() {


    return(
        <div className="container mx-0">
            <div className="row">
                <div className="col-3">
                <div className="row border border-dark border-2">
                    <img className="col-6 ps-0" src={require('./risk-map.png').default} height="100px" alt="user_avatar" />
                    <h2 className="col-6 ps-0" style={{color: '#f7544d'}}>Username</h2>
                    </div>
                    <div className="row">
                        <button className="btn btn-primary col-5" >Save Game</button>
                        <Link to="/" className="btn btn-secondary col-5" >Quit Game</Link>
                    </div>
                    <div className="row">
                        <h3 className="offset-2 mt-4">User's Turn</h3>
                        <p className="border border-dark" style={{marginTop: '150px'}}>InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText InfoText</p>
                    </div>
                </div>
                <div className="col-9 pe-0" style={{marginTop: '-150px'}}>
                <MapSVG></MapSVG>
                
                </div>
                
            </div>
            <div className="row">
                <div className="col-4">
                    <button className="btn btn-primary">Attack</button>
                    <button className="btn btn-primary">Reinforce</button>
                </div>
                <div className="col-4 offset-3">
                    <button className="btn btn-primary">Start</button>
                    <button className="btn btn-primary">End Turn</button>
                </div>
            </div>
        </div>
    );
};

export default Game;