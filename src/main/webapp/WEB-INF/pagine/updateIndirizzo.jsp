<%@ page import="model.utente.Utente" %><%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 19/07/2021
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Utente utente=(Utente)session.getAttribute("utente");%>
<%String messaggio=(String) request.getAttribute("updateAddress");    %>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="PHarmaLife - Supermercato Farmaceutico"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleUpdateIndirizzo"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
    <style>
        h2{
            text-align: center;
            color: deepskyblue;
        }
    </style>
    <script src="js/header.js" type="text/javascript" defer></script>
</head>
<body>
<jsp:include page="default/header.jsp"/>
<%if(messaggio!=null){   %>
<h2><%=messaggio%></h2>
<%  }  %>
<div class="container-form">
    <fieldset>
        <legend>Modifica Indirizzo</legend>
        <form action="ServletUpdateIndirizzo" method="post" onclick="">
            <div class="blocco">
                <label for="via">Via</label>
                <input type="text" name="via" id="via">
            </div>
            <div class="blocco">
                <label for="numero">Numero Civico</label>
                <input type="text" name="numero" id="numero">
            </div>
            <div class="blocco">
                <label for="cap">CAP</label>
                <input type="text" name="cap" id="cap">
            </div>
            <input type="hidden" name="codiceFiscale" value="<%=utente.getCodiceFiscale()%>">
            <input type="submit" value="MODIFICA">
        </form>
    </fieldset>

</div>


<jsp:include page="default/footer.jsp"/>
</body>
</html>
