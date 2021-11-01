import { Link } from "react-router-dom";

function Profile(){
    return(
        <div>
            <div className="container">
                <img src="?" alt="profile avatar" className="offset-4"/>
                <h1 className="offset-5" style={{color: '#f7544d'}}>Username</h1>
            </div>
            <h4 className="offset-1">Player Statistics</h4>
            <table className="table table-striped table-bordered offset-1" style={{width: '75%'}}>
                
                <tbody>
                    <tr>
                        <th scope="row">Points</th>
                        <td>a</td>
                    </tr>
                    <tr>
                        <th scope="row">Games Played</th>
                        <td>x</td>
                    </tr>
                    <tr>
                        <th scope="row">Games Won</th>
                        <td>y</td>
                    </tr>
                    <tr>
                        <th scope="row">Games Lost</th>
                        <td>z</td>
                    </tr>
                    <tr>
                        <th scope="row">Win/Loss %</th>
                        <td>x%</td>
                    </tr>
                    <tr>
                        <th scope="row">Total Game Time</th>
                        <td>x</td>
                    </tr>
                </tbody>
            </table>
            <div className="mt-5">
            <Link to="/avatars" className="btn btn-primary ms-1 me-1">Avatars</Link>
            <Link to="/" className="btn btn-secondary ">Back</Link>
            </div>
        </div>
    );
};

export default Profile;