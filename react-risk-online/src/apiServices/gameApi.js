const url =  `${process.env.REACT_APP_API_URL}`;

export async function findGames(){

};

export async function saveGame(gameId, gameObj){
    if(gameId > 0){
        return updateGame(gameId, gameObj);
    } else {
        return addGame(gameObj);
    }
};

async function addGame(gameObj) {
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(gameObj)
    }


    const response = await fetch(url + "/game", init);
    if(response.status === 201) {
        return response.json();
    }
    throw new Error("Unable to add game");
};

async function updateGame(gameId, gameObj) {
    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(gameObj)
    }

    const response = await fetch(url + "/game/" + gameId, init);
    if(response.status === 204) {
        return;
    }
    throw new Error("Unable to update game");
};

export async function loadGame(gameId){
    let result = [];
    
    let response = await fetch(url + "/game/" + gameId);
    if(response.status === 200) {
        result.push(response.json());
    } else {
        throw new Error("Unable load game");
    }

    response = await fetch(url + "/players/" + gameId);
    if(response.status === 200) {
        result.push(response.json());
    } else {
        throw new Error("Unable load game players");
    }
    response = await fetch(url + "/countries/" + gameId);
    if(response.status === 200) {
        result.push(response.json());
    } else {
        throw new Error("Unable load game state");
    }

    return result;
};

export async function deleteGame(gameId){
    const init = {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        
    }

    const response = await fetch(url + "/game/" + gameId, init);
    if(response.status === 204) {
        return;
    }
    throw new Error("Unable to delete game");

};