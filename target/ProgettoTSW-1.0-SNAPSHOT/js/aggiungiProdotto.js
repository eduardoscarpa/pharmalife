$(document).ready(function (){
         $('.iconaCarrello').click(function (e) {
             $(e.delegateTarget).next('.btn').toggle();
         });
     });

$.ajaxSetup({
    "type":"post",
    "success": function (data){
        //alert("sss" )
        $("h2").text(data);
        $("h2").css("visibility","visible");
        $("h2").css("color","red");
        setTimeout(function (){
            $("h2").css("visibility","hidden")
        },4000)
    }
})

 function  aggiungiAlCarrello(idProdotto){

     var total=$("#quantita").val();

     $.ajax({
         "url":"ServletAggiungiAlCarrello",
         "data":"prodotto="+idProdotto +"&totale="+total,
     })
 }

 function  aggiungiAiPreferiti(idProdotto){

     $.ajax({
         "url":"ServletPreferiti",
         "data":"value="+idProdotto,
     })
 }
