var patternNomeProdotto = new RegExp("^[a-z A-Z 0-9]{1,}$");


function updateProdotto(){

    let nomeProdotto = $("#nome");
    let prezzoProdotto = $("#prezzo");

    if (!patternNomeProdotto.test(nomeProdotto.val())) {
        nomeProdotto.addClass("lampeggioBordo");
        alert("Il nome del prodotto NON deve essere vuoto");
        return event.preventDefault();
    }
    else {
        nomeProdotto.removeClass("lampeggioBordo");
    }

    if (prezzoProdotto.val().length == 0) {
        prezzoProdotto.addClass("lampeggioBordo");
        alert("Il prezzo del prodotto NON deve essere vuoto");
        return event.preventDefault();
    }
    else {
        nomeProdotto.removeClass("lampeggioBordo");
    }
}