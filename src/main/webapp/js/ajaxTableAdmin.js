

function mostraTabella(valore){

    var tipoLista=valore;
    var xmlhttp= new XMLHttpRequest();
    xmlhttp.onreadystatechange=function (){
        if(this.readyState==4 & this.status==200){

            if(valore=="utenti"){
                tabellaUtenti(this.responseText);
            }
            if(valore=="prodotti"){
                tabellaProdotti(this.responseText);
            }

        }
    }

    xmlhttp.open("POST","ServletTabellaAdmin",true);
    xmlhttp.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("lista="+tipoLista);

}

function  tabellaProdotti(xmlhttp){


  var dati=JSON.parse(xmlhttp);
 //   alert("ss" +dati.length +"  " + dati[4].codiceProdotto);
    var tabella="<tr>" +
        " <th>Codice</th>" +
        "<th>Nome</th>" +
        "<th>Categoria</th>" +
        "<th>Prezzo</th>" +
        "<th>Marchio</th>" +
        "<th>Elimina</th>" +
        "<th>Aggiorna</th>" +
        "</tr>";
    for(var i=0;i<dati.length;i++){
        tabella+="<tr><td>" + dati[i].codiceProdotto + "</td>" +
            "<td>" + dati[i].nome+"</td>" +
            "<td>"+ dati[i].categoria.nomeCategoria+"</td>"+
            "<td>"+ dati[i].prezzo+"</td>" +
            "<td>"+ dati[i].marchio.nomeMarchio+"</td>"+
            "<td><a href='ServletDeleteProdottoAdmin?id=" +dati[i].codiceProdotto+ " '>Delete</a></td> "+
            "<td><a href='ServletUpdateProdottoAdmin?id=" +dati[i].codiceProdotto+ " '>Update</a></td> "+
            "</tr>";

    }
    document.getElementById("table").innerHTML=tabella;

}


function tabellaUtenti(xmlhttp){
    var dati=JSON.parse(xmlhttp); // Cast JSON in js
    var tabella="<tr>" +
        " <th>CodiceFiscale</th>" +
        "<th>Nome</th>" +
        "<th>Cognome</th>" +
        "<th>email</th>" +
        "</tr>";
    for(var i=0;i<dati.length;i++){
        tabella+="<tr><td>" + dati[i].codiceFiscale + "</td>" +
            "<td>" + dati[i].nome+"</td>" +
            "<td>"+ dati[i].cognome+"</td>"+
            "<td>"+ dati[i].email+"</td>" +

            "</tr>";

    }
    document.getElementById("table").innerHTML=tabella;
}