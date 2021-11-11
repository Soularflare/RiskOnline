import { Link } from "react-router-dom";
import { useHistory } from "react-router";
import { useEffect, useState } from "react/cjs/react.development";
import { fetchUserInfo } from './apiServices/userApi';



function Profile({ userData }) {
    const history = useHistory();
    const [userInfo, setUserInfo] = useState({});

    useEffect(() => {
        fetchUserInfo(userData)
            .then((info) => {
                setUserInfo(info)})
            .catch((err) => console.log(err.toString()));
    }, []);

    const toAvatars = evt => {
        history.push(`/avatars/${userInfo.userId}`);
    };

    return (
        <div>
            <div className="container">
                <img src="?" alt="profile avatar" className="offset-4" />
                <h1 className="offset-5" style={{ color: '#f7544d' }}>{userData.userName}</h1>
            </div>
            <h4 className="offset-1">Player Statistics</h4>
            <table className="table table-striped table-bordered offset-1" style={{ width: '75%' }}>

                <tbody>
                    <tr>
                        <th scope="row">Points</th>
                        <td>{userInfo.points}</td>
                    </tr>
                    <tr>
                        <th scope="row">Games Played</th>
                        <td>{userInfo.totalGames}</td>
                    </tr>
                    <tr>
                        <th scope="row">Games Won</th>
                        <td>{userInfo.wins}</td>
                    </tr>
                    <tr>
                        <th scope="row">Games Lost</th>
                        <td>{userInfo.totalGames - userInfo.wins}</td>
                    </tr>
                    <tr>
                        <th scope="row">Win/Loss %</th>
                        <td>{(userInfo.wins / userInfo.totalGames) * 100}%</td>
                    </tr>
                    <tr>
                        <th scope="row">Total Game Time</th>
                        <td>{userInfo.gameTime}</td>
                    </tr>
                </tbody>
            </table>
            <div className="mt-5">
                <button onClick={toAvatars} className="btn btn-primary ms-1 me-1">Avatars</button>
                <Link to="/" className="btn btn-secondary ">Back</Link>
            </div>
        </div>
    );
};

export default Profile;