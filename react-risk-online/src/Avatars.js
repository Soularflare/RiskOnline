import { Link } from "react-router-dom";

function Avatars(){
    return(
        <div>
            <div className="container">
                
                <h1 className="offset-5" style={{color: '#f7544d'}}>Avatars</h1>
            </div>
            <h4 className="offset-1">Current Avatar</h4>
            <img src={require('./risk-map.png').default} height="100px" alt="current_avatar"/>
            <div className="mt-5">
            <Link to="/profile/:userId" className="btn btn-secondary ">Back</Link>
            </div>
        </div>
    );
};

export default Avatars;