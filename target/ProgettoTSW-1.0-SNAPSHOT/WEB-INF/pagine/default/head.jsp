<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta charset="uft-8"> <!-- per la codifica dei caratteri-->
<meta name="viewport" content="width=device-width,initial-scale=1,viewport-fit=cover">
<!-- serve per il responsive
viewport-fit:cover serve per evitare lo spazio bianco sui dispositivi apple-->
<title>${param.title}</title>
<meta name="description" content="Ecommerce PharmaLife">
<meta name="theme-color" content="red">
<link rel="icon" type="image/png" href=""> <!-- mettere il path del logo dentro href in formato png-->
<meta name="apple-mobile-web-app-capable" content="yes"> <!-- per installare oil sito come se fosse una web app-->
<meta name="format-detection" content="telephone-no"> <!-- evitare che i browser intrpetrino stringh edi numeri come numeri di telefono-->
<meta name="apple-mobile-web-app-title" content="PharmaLife"><!-- nome da usare quando aggiungi il sito  alla schermata home-->
<meta name="apple-mobile-web-app-status-bar-style" content="default"> <!-- barra dello stato apple-->
<link rel="apple-touch-icon" href=""> <!-- icona da usare per aggiungere ai preferiti-->
<link rel="apple-touch-startup-image" href=""> <!--quando carica il sito esce un'immagine di caricamento con l'icona del sito che lampeggia-->
<!-- metatag per android-->
<meta name="theme-color" content=""> <!--mettere dentro content la il colore principale del sito-->
<!--<link href="../../css" rel="stylesheet">-->
<link rel="stylesheet" href="css/reset.css">
<link rel="shortcut icon" type="image/png" href="immagini/favicon11.png">
 <!--controlla se param non è vuoto e lo importa prendedo il parametro da header-->
 <c:forTokens items="${param.style}" delims="," var="stile">
     <link rel="stylesheet" href="css/${stile}.css">
 </c:forTokens>
<script src="https://kit.fontawesome.com/7159358a04.js" crossorigin="anonymous"></script>
<script src="./js/barraRicerca.js" type="text/javascript" defer></script>
<c:if test="${not empty param.script}">

</c:if>
<%--<c:forTokens items="${param.script}" delims="," var="scriptjs">
 <script src="js/${param.scriptjs}.js" type="text/javascript" defer></script>
</c:forTokens>--%>

