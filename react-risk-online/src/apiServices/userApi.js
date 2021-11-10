const url =  "http://localhost:8080/api";

export async function findByUserData(userData){
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(userData)
    }
    const response = await fetch(url + "/appuser", init);
    if(response.status === 200) {
        return response.json();
    }
    throw new Error("Invalid user");

};

export async function AddUser(userData){
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(userData)
    }

    const response = await fetch(url + "/appuser/create", init);
    if(response.status === 201) {
        return response.json();
    }
    throw new Error("User already exists");

};

export async function fetchPts(userData){
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(userData)
    }
    const response = await fetch(url + "/profile/points", init);
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
    const response = await fetch(url + "/profile/update/points/" + num, init);
    if(response.status === 200) {
        return response.json();
    }
    throw new Error("User does not exist");
};

export async function fetchUserInfo(userData){
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(userData)
    }
    const response = await fetch(url + "/profile", init);
    if(response.status === 200) {
        return response.json();
    }
    throw new Error("User does not exist");
};