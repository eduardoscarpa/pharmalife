

function  verifica(){
    var valore=document.getElementById("ricercaId").value;

    if(!valore){
        alert("Inserire un prodotto");
    return  false;
    }else
    {
        return true;
    }
}
function  ricerca(){

    var valore=document.getElementById("ricercaId").value;

    var xmlhttp= new XMLHttpRequest();
    xmlhttp.onreadystatechange=function (){
        if(this.readyState==4 && this.status==200){
            creaOption(this);
        }
    }
    xmlhttp.open("get","ServletBarraRicerca?value="+valore,true);
    xmlhttp.send();
}


 function  creaOption(xmlhttp){

    var prodotti=JSON.parse(xmlhttp.responseText); // (prodottiJson)
    var option="<option>"+prodotti[0].nome+"</option>";
    for(var i=1;i<prodotti.length;i++){
      option+="<option>"+prodotti[i].nome+"</option>";

    }
    document.getElementById("prod").innerHTML=option;
}