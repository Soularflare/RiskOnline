const colors = ["blue", "green", "purple", "red", "white", "black", "yellow", "orange"];

const coloring = (colorChoice) => {
    const colorIndex = colors.indexOf(colorChoice);
    colors.splice(colorIndex, 1);
}

coloring("green");

for(let x = 0; x < colors.length; x++){
    console.log(colors[x]);
}