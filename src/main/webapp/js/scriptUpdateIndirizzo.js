var patternCivico = new RegExp("^[0-9]{1,3}$");
var patternCap = new RegExp("^[0-9]{5}$");
let numeroCivico = $("#IdnumeroCivico");
let cap = $("#Idcap");
function validateAddress() {
    if (!patternCivico.test(numeroCivico.val())) {
        numeroCivico.addClass("lampeggioBordo");
        alert("Il numero civico deve contenere solo numeri (da una a tre cifre)");
        return event.preventDefault();
    } else {
        numeroCivico.removeClass("lampeggioBordo");
    }

    if (!patternCap.test(cap.val())) {
        cap.addClass("lampeggioBordo");
        alert("Il cap deve contenere esattamente 5 cifre");
        return event.preventDefault();
    } else {
        cap.removeClass("lampeggioBordo");
    }
}