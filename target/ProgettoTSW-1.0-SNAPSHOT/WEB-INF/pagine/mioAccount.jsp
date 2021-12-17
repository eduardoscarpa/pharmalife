<%@ page import="model.utente.Utente" %><%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 03/07/2021
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Utente utente=(Utente) session.getAttribute("utente"); %>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="Il mio account"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleMioAccount"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
    <script src="js/header.js" type="text/javascript" defer></script>
</head>
<body>
<jsp:include page="default/header.jsp"/>
<%if(utente!=null){  %>
<h1 class="myAccount">Benvenuto <%=utente.getNome()%></h1>
<div class="flex-container">
    <div class="flex-item">
        <a href="ServletLink?scelta=info"><i class="fas fa-user-circle" style="background-color: white" ></i></a><br>
        <p>INFORMAZIONE</p>
    </div>

    <div class="flex-item">
        <a href="ServletLink?scelta=indirizzo"><i class="fas fa-map-marker-alt" ></i></a><br>
        <p>MODIFICA INDIRIZZO</p>
    </div>

    <div class="flex-item">
        <a href="ServletMostraOrdini"><i class="far fa-calendar-alt" ></i></a><br>
        <p>CRONOLOGIA ORDINI E DETTAGLI</p>
    </div>

    <div class="flex-item">
        <a href=""><i class="fas fa-receipt" ></i></a><br>
        <p>NOTA DI CREDITO</p>
    </div>

    <div class="flex-item">
        <a href=""><i class="fas fa-tag" ></i></a><br>
        <p>BUONI</p>
    </div>

    <div class="flex-item">
        <a href="" onclick="print()"> <i class="fas fa-portrait" ></i></a><br>
        <p>I MIEI DATI PERSONALI</p>
    </div>

    <div class="flex-item">
        <a href=""> <i class="fas fa-heart" ></i></a>
        <p>LISTA DEI DESIDERI</p>
    </div>

    <div class="flex-item">
        <a href=""> <i class="far fa-credit-card"></i></a><br>
        <p>LE MIE CARTE</p>
    </div>

    <% if(utente!=null){ if(utente.isAdmin()){ %>
    <div class="flex-item">
        <a href="ServletLink?scelta=admin"> <i class="fas fa-user-lock"></i></a><br>
        <p>AREA ADMIN</p>
    </div>
    <% } } %>
</div>
<%  }  else {%>
<h1 class="myAccount">Registrati per accedere all'area Utente</h1>
<% } %>

<jsp:include page="default/footer.jsp"/>
</body>
</html>
