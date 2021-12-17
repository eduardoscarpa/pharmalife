var patternPassword = new RegExp(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/);

//da 6 a 20 caratteri,almeno un numero,una maiuscola e minuscola
function  validazioneDati() {
    let nomeUtente = $("#nome");
    let cognomeUtente = $("#cognome");
    let emailUtente = $("#email");
    let password = $("#password");
    let newPassword = $("#newPassword");

    if (password.val() == newPassword.val()) {
        newPassword.addClass("lampeggioBordo");
        alert("La nuova Password deve essere diversa da quella precedente");
        return event.preventDefault();
    } else {
        newPassword.removeClass("lampeggioBordo");
    }

    if (!patternPassword.test(newPassword.val())) {
        newPassword.addClass("lampeggioBordo");
        alert("La nuova password non rispetta il formato del pattern: deve contenere almeno una lettera minuscola, una maiuscola e un numero");
        return event.preventDefault();
    } else {
        newPassword.removeClass("lampeggioBordo");
    }
}