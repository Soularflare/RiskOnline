import { Link } from "react-router-dom";

function Login() {
    return(
        <div className="container mt-5">
            <h1 className="offset-5" style={{color: '#f7544d'}}>Login</h1>
            <form>
            <div className="form-group row">
                <div className="col-4 offset-4">
            <label for="username" className="form-label">Username</label>
            <input className="form-control" type="text" name="Username"></input>
            </div>
            </div>
            <div className="form-group row">
            <div className="col-4 offset-4">
            <label for="password" className="form-label">Password</label>
            <input className="form-control" type="password" name="password"></input>
            </div>
            </div>
            </form>
            <div className="mt-3 offset-4">
            <button type="submit" className="btn btn-primary me-1">Submit</button>
            <Link to="/" className="btn btn-secondary ms-1">Cancel</Link>
            </div>
        </div>
    );
};

export default Login;