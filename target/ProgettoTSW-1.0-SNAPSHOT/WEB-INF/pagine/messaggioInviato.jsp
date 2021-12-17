<%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 15/07/2021
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="PHarmaLife - Supermercato Farmaceutico"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleMessaggioInviato"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
    <script src="js/header.js" type="text/javascript" defer></script>
</head>
<body>
<jsp:include page="default/header.jsp"/>




<div class="imm-mano">
    <img class="immMano" src="./immagini/stretta-di-mano--1280x720.jpg" alt="immMano">
</div>

<p class="scritta-mano">Messaggio inviato con successo, sii paziente e ci sarà un nostro collaboratore pronto ad aiutarti.
    Clicca qui sotto per tornare alla homepage.</p>

<div class="bottone-ass">

    <form method="get" action="ServletLink">
        <input type="button" value="Torna alla homepage" onclick="location.href='/ProgettoTSW-1.0-SNAPSHOT/index.jsp'" style="
    background-color: #096900;
    width: 18em;
    padding: .5em;
    color: #ffffff;
    border: solid thin limegreen;
    background-image: -webkit-gradient(linear, left top, left bottom,
    from(#e9ede8), to(#096900),color-stop(0.4, limegreen));">
    </form>

</div>


<jsp:include page="default/footer.jsp"/>
</body>
</html>
