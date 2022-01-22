
function assistenza(){

    let messaggio = $("#subject");

    if(messaggio.val().length<=0){
        console.log(messaggio.val()+ "   " +messaggio.val().length);
        messaggio.addClass("lampeggioBordo");
        alert("Il testo del messaggio NON deve essere vuoto");
        return event.preventDefault();
    }
    else{
        essaggio.removeClass("lampeggioBordo");
    }
}