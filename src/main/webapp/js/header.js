
//BARRA DI NAVIGAZIONE
function mostraMenu() {
    var x = document.getElementsByTagName("nav")[0];
    if (x.style.display === "flex") {
        x.style.display = "none";
    } else {
        x.style.display = "flex";
    }
}