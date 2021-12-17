var patternNomeUtente = new RegExp("^[a-z A-Z]{3,}$");
var patternCognomeUtente = new RegExp("^[a-z A-Z]{3,}$");
var patternCodiceFiscale = new RegExp("^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$");
var patternEmail = new RegExp(/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/);
var patternPassword = new RegExp(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/);
var patternCivico = new RegExp("^[0-9]{1,3}$");
var patternCap = new RegExp("^[0-9]{5}$");
var patternTelefono = new RegExp("^[0-9]{10}$");

function  validazioneIscrizione(){

    let nome = $("#Idnome");
    let cognomeUtente = $("#Idcognome");
    let codiceFiscale = $("#IdcodiceFiscale");
    let emailUtente = $("#Idemail");
    let password = $("#Idpsw");
    let passwordRepeat = $("#Idpsw-rip");
    let numeroCivico = $("#IdnumeroCivico");
    let cap = $("#Idcap");
    let telefono = $("#Idtelefono");

    if (!patternNomeUtente.test(nome.val())) {
        nome.addClass("lampeggioBordo");
        alert("Il nome deve essere formato solo da lettere e deve contenere almeno tre caratteri");
        return event.preventDefault();
    }
    else {
        nome.removeClass("lampeggioBordo");
    }

    if (!patternCognomeUtente.test(cognomeUtente.val())) {
        cognomeUtente.addClass("lampeggioBordo");
        alert("Il cognome deve essere formato solo da lettere e deve contenere almeno tre caratteri");
        return event.preventDefault();
    }
    else {
        cognomeUtente.removeClass("lampeggioBordo");
    }

    if (!patternCodiceFiscale.test(codiceFiscale.val())) {
        codiceFiscale.addClass("lampeggioBordo");
        alert("Codice fiscale non valido");
        return event.preventDefault();
    }
    else {
        codiceFiscale.removeClass("lampeggioBordo");
    }

    if (!patternEmail.test(emailUtente.val())) {
        emailUtente.addClass("lampeggioBordo");
        alert("Formato email non valido");
        return event.preventDefault();
    }
    else {
        emailUtente.removeClass("lampeggioBordo");
    }

    if (!patternPassword.test(password.val())) {
        password.addClass("lampeggioBordo");
        alert("La password deve contenere almeno una lettera minuscola, una maiuscola e un numero");
        return event.preventDefault();
    }
    else {
        password.removeClass("lampeggioBordo");
    }

    if(password.val() != passwordRepeat.val()){
        passwordRepeat.addClass("lampeggioBordo");
        alert("La password non coincide con quella digitata precedentemente");
        return event.preventDefault();
    }
    else {
        passwordRepeat.removeClass("lampeggioBordo");
    }

    if (!patternCivico.test(numeroCivico.val())) {
        numeroCivico.addClass("lampeggioBordo");
        alert("Il numero civico deve contenere solo numeri (da una a tre cifre)");
        return event.preventDefault();
    }
    else {
        numeroCivico.removeClass("lampeggioBordo");
    }

    if (!patternCap.test(cap.val())) {
        cap.addClass("lampeggioBordo");
        alert("Il cap deve contenere esattamente 5 cifre");
        return event.preventDefault();
    }
    else {
        cap.removeClass("lampeggioBordo");
    }

    if (!patternTelefono.test(telefono.val())) {
        telefono.addClass("lampeggioBordo");
        alert("Il numero di telefono deve contenere esattamente 10 cifre");
        return event.preventDefault();
    }
    else {
        telefono.removeClass("lampeggioBordo");
    }
}