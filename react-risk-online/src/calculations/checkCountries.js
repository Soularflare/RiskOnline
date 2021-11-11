
const countriesOwned = (currentPlayer) => {
    const countriesArray = [...currentPlayer.countries];
    var countryOwnedArray = [];
    //console.log("Reinforcement stuff");
    //console.log(countriesArray);


    for(let x = 0; x < countriesArray.length; x++){
        countryOwnedArray.push(parseInt(countriesArray[x].id));
    }
    //console.log(countryOwnedArray);
    return countryOwnedArray;
}

export function checkAustralia(currentPlayer) {
    const playerOwnedCountries = countriesOwned(currentPlayer);    

    if(playerOwnedCountries.indexOf(38)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(39)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(40)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(41)<0){
        return false;
    }
    else
    {
        return true;
    }
}

export function checkSAmerica(currentPlayer) {
    const playerOwnedCountries = countriesOwned(currentPlayer);

    if(playerOwnedCountries.indexOf(9)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(10)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(11)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(12)<0){
        return false;
    }
    else
    {
        return true;
    }
}

export function checkAfrica(currentPlayer) {
    const playerOwnedCountries = countriesOwned(currentPlayer);

    if(playerOwnedCountries.indexOf(13)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(14)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(15)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(16)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(17)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(18)<0){
        return false;
    }
    else
    {
        return true;
    }
}

export function checkEurope(currentPlayer) {
    const playerOwnedCountries = countriesOwned(currentPlayer);

    if(playerOwnedCountries.indexOf(19)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(20)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(21)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(22)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(23)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(24)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(25)<0){
        return false;
    }
    else
    {
        return true;
    }
}

export function checkNAmerica(currentPlayer) {
    const playerOwnedCountries = countriesOwned(currentPlayer);

    if(playerOwnedCountries.indexOf(0)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(1)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(2)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(3)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(4)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(5)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(6)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(7)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(8)<0){
        return false;
    }
    else
    {
        return true;
    }
}

export function checkAsia(currentPlayer) {
    const playerOwnedCountries = countriesOwned(currentPlayer);

    if(playerOwnedCountries.indexOf(26)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(27)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(28)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(29)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(30)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(31)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(32)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(33)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(34)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(35)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(36)<0){
        return false;
    }
    if(playerOwnedCountries.indexOf(37)<0) {
        return false;
    }
    else
    {
        return true;
    }
}