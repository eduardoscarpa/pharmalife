function insertProdotto(){

    let nomeProdotto = $("#nomeId");
    let prezzoProdotto = $("#prezzoId");
    let marchioProdotto = $("#marchioId");
    let categoriaProdotto = $("#categoriaId");
    let descrizioneProdotto = $("#descrizioneId");
    let immagineProdotto = $("#immagine");

    if (nomeProdotto.val().length == 0) {
        nomeProdotto.addClass("lampeggioBordo");
        alert("Il nome del prodotto NON deve essere vuoto");
        return event.preventDefault();
    }
    else {
        nomeProdotto.removeClass("lampeggioBordo");
    }

    if (prezzoProdotto.val().length == 0 || prezzoProdotto.val() == 0) {
        prezzoProdotto.addClass("lampeggioBordo");
        alert("Il campo prezzo del prodotto NON deve essere vuoto");
        return event.preventDefault();
    }
    else {
        nomeProdotto.removeClass("lampeggioBordo");
    }

    if (marchioProdotto.val().length == 0) {
        marchioProdotto.addClass("lampeggioBordo");
        alert("Il campo marchio del prodotto NON deve essere vuoto");
        return event.preventDefault();
    }
    else {
        marchioProdotto.removeClass("lampeggioBordo");
    }

    if (categoriaProdotto.val().length == 0) {
        categoriaProdotto.addClass("lampeggioBordo");
        alert("Il campo categoria del prodotto NON deve essere vuoto");
        return event.preventDefault();
    }
    else {
        categoriaProdotto.removeClass("lampeggioBordo");
    }

    if (descrizioneProdotto.val().length == 0) {
        descrizioneProdotto.addClass("lampeggioBordo");
        alert("La descrizione del prodotto NON deve essere vuota");
        return event.preventDefault();
    }
    else {
        descrizioneProdotto.removeClass("lampeggioBordo");
    }

    if (immagineProdotto.val().length == 0) {
        immagineProdotto.addClass("lampeggioBordo");
        alert("Il campo immagine del prodotto NON deve essere vuoto");
        return event.preventDefault();
    }
    else {
        immagineProdotto.removeClass("lampeggioBordo");
    }
}