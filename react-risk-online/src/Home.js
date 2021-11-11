import { Link } from "react-router-dom";


function Home({userData, logout}){

    const onLogout = evt => {
        logout();
    };

    return(
        <div className="container">
        <div className="row pb-5">
            <h1 className="display-1 fw-bolder offset-4" style={{color: '#f7544d'}}>Risk Online</h1>
            
        </div>
        <img src={require('./risk-map.png').default} alt="map not found" height="200px" width="400px" className="offset-4"/>
        <div className="row d-grid gap-4 pt-5 mt-5">
            <h1 className="display-5 fw-bolder offset-4" >Hello{userData? ", " + userData.userName: ""}</h1>
            {!userData && <Link to="/login" className="btn btn-primary btn-lg col-8 offset-2">Login</Link>}
            <Link to="/gameform" className="btn btn-primary btn-lg col-8 offset-2">New Game</Link>
            {userData && <Link to="/load" className="btn btn-primary btn-lg col-8 offset-2">Load Game</Link>}
            {userData && <Link to="/profile/:userId" className="btn btn-primary btn-lg col-8 offset-2">Profile</Link>}
            {userData && <button className="btn btn-primary btn-lg col-8 offset-2" onClick={onLogout}>Logout</button>}
        </div>
        </div>
    );
};

export default Home;