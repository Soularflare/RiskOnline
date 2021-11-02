import Home from './Home';
import Login from './Login';
import Profile from './Profile';
import GameForm from './GameForm';
import Games from './Games';
import Game from './Game';
import Avatars from './Avatars';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Switch>
      
      <Route path="/login">
        <Login></Login>
      </Route>
      <Route path="/profile/:userId">
        <Profile></Profile>
      </Route>
      <Route path="/game">
        <Game></Game>
      </Route>
      <Route path="/gameform">
        <GameForm></GameForm>
      </Route>
      <Route path="/load">
        <Games></Games>
      </Route>
      <Route path="/avatars">
        <Avatars></Avatars>
      </Route>
      <Route path="/">
        <Home></Home>
      </Route>
      </Switch>
    </Router>
  );
}

export default App;
