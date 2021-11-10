import Home from './Home';
import Login from './Login';
import Profile from './Profile';
import GameForm from './GameForm';
import Games from './Games';
import Game from './Game';
import Avatars from './Avatars';
import Signup from './Signup';
import UserContext from './UserContext';
import { useState } from "react";
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';

function App() {
  const [userData, setUserData] = useState("");



  const logout = () => {
    setUserData("");
  }

  const login = (data) => {
    if(data.username === "admin" && data.password ==="admin"){
      setUserData({
        username: data.username,
        role: "ADMIN"
      });
    } else {
      setUserData({
        username: data.username,
        role: "USER"
      });
    }

  }

  const user = {
    userData,
    login: login
  };

  return (
    <UserContext.Provider value={user}>
    <Router>
      <Switch>
      
      <Route path="/login">
        <Login></Login>
      </Route>
      <Route path="/signup">
        <Signup></Signup>
      </Route>
      <Route path="/profile">
        <Profile userData={userData}></Profile>
      </Route>
      <Route path="/game/:numPlayers/:chosenColor/:gameId">
        <Game userData={userData}></Game>
      </Route>
      <Route path="/gameform">
        <GameForm></GameForm>
      </Route>
      <Route path="/load">
        <Games></Games>
      </Route>
      <Route path="/avatars/:userId">
        <Avatars userData={userData}></Avatars>
      </Route>
      <Route path="/">
        <Home userData={userData} logout={logout} ></Home>
      </Route>
      </Switch>
    </Router>
    </UserContext.Provider>
  );
}

export default App;
