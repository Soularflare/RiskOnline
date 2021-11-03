import { useContext, useState } from "react";
import { useHistory } from "react-router";
import { Link } from "react-router-dom";
import UserContext from "./UserContext";
import {AddUser} from './Api';

function Login() {
    const [userData, setUserData] = useState({
        username: "",
        password: ""  
      });
      const history = useHistory();
      const user = useContext(UserContext);
  
      const onChange = (evt) => {
          const tmp = {...userData};
          tmp[evt.target.name] = evt.target.value;
          setUserData(tmp);
      };
  
      const onSubmit = (evt) => {
          evt.preventDefault();
          AddUser(userData.username, userData.password)
          .then(() => {
            user.login(userData);
            history.push("/");
          })
          .catch((err) => console.log(err.toString()));
      };

    return(
        <div className="container mt-5">
            <h1 className="offset-5" style={{color: '#f7544d'}}>Sign up</h1>
            <form onSubmit={onSubmit}>
            <div className="form-group row">
                <div className="col-4 offset-4">
            <label for="username" className="form-label">Username</label>
            <input className="form-control" type="text" name="username" value={userData.username} onChange={onChange}></input>
            </div>
            </div>
            <div className="form-group row">
            <div className="col-4 offset-4">
            <label for="password" className="form-label">Password</label>
            <input className="form-control" type="text" name="password" value={userData.password} onChange={onChange}></input>
            </div>
            </div>
            <div className="mt-3 offset-4">
            <button type="submit" className="btn btn-primary me-1">Sign Up</button>
            <Link to="/login" className="btn btn-secondary ms-1">Back</Link>
            </div>
            </form>
            
        </div>
    );
};

export default Login;