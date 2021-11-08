const url =  "http://localhost:8080/api";

export async function findByUserData(username, password){
    const response = await fetch(url + "/" + username + "/" + password);
    if(response.status === 204) {
        return response.json();
    }
    throw new Error("Invalid user");

};

export async function AddUser(username, password){
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
    }
    const response = await fetch(url + "/" + username + "/" + password, init);
    if(response.status === 200) {
        return response.json();
    }
    throw new Error("User already exists");

};

export async function fetchPts(userData){
    const init = {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(userData)
    }
    const response = await fetch(url + "/points", init);
    if(response.status === 200) {
        return response.json();
    }
    throw new Error("User does not exist");
};

export async function savePts(userData, num){
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(userData)
    }
    const response = await fetch(url + "/points/" + num, init);
    if(response.status === 200) {
        return response.json();
    }
    throw new Error("User does not exist");
};

export async function fetchUserInfo(userData){
    const init = {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(userData)
    }
    const response = await fetch(url + "/info", init);
    if(response.status === 200) {
        return response.json();
    }
    throw new Error("User does not exist");
};