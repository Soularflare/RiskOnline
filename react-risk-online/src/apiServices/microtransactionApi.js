export async function findAvatars(userId){
    const response = await fetch(url + "/appuser/");
    if(response.status === 200) {
        return response.json();
    }
    throw new Error("Unable to fetch avatars");
};



export async function addAvatar(id, userId){
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        
    }
    const response = await fetch(url + "/appuser/" + id + "/" + userId, init);
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
    const response = await fetch(url + "/appuser/" + id + "/" + userId, init);
    if(response.status === 204) {
        return;
    }
    throw new Error("Unable to equip avatar");
};