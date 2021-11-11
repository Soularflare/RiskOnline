const url =  `${process.env.REACT_APP_API_URL}`;

export async function addAvatar(id, userId){
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        
    }
    const response = await fetch(url + "/profile/" + id + "/" + userId, init);
    if(response.status === 204) {
        return;
    }
    throw new Error("Unable to add avatar");
};

export async function equipAvatar(id, userId){
    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        
    }
    const response = await fetch(url + "/profile/equip/" + id + "/" + userId, init);
    if(response.status === 204) {
        return;
    }
    throw new Error("Unable to equip avatar");
};

export async function updatePoints(points, userId){
    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        
    }
    const response = await fetch(url + "/profile/points/" + points + "/" + userId, init);
    if(response.status === 204) {
        return;
    }
    throw new Error("Unable to equip avatar");
};