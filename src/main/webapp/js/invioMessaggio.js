var patternMessaggio = new RegExp("^[a-zA-Z0-9]{1,}$");

function assistenza(){

    let messaggio = $("#subject");

    if (!patternMessaggio.test(messaggio.val())) {
        messaggio.addClass("lampeggioBordo");
        alert("Il testo del messaggio NON deve essere vuoto");
        return event.preventDefault();
    }
    else {
        messaggio.removeClass("lampeggioBordo");
    }
}