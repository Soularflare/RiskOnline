const url =  `${process.env.REACT_APP_API_URL}`;


export async function saveGame(gameId, userId, playerList, playerTurn){
    if(gameId > 0){
        return updateGame(gameId, userId, playerList, playerTurn);
    } else {
        return addGame(userId, playerList, playerTurn);
    }
};

async function addGame(userId, playerList, playerTurn) {
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify()
    }

    const response = await fetch(url, init);
    if(response.status === 201) {
        return response.json();
    }
    throw new Error("Unable to add game");
};

async function updateGame(game, country, player) {
    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(game)
    }

    const response = await fetch(url + "/" + game.gameId, init);
    if(response.status === 204) {
        return;
    }
    throw new Error("Unable to update game");
};

export async function loadGame(gameId){

};