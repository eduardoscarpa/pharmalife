<%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 24/07/2021
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="PHarmaLife - Supermercato Farmaceutico"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleStatistiche"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
</head>
<body>
<jsp:include page="../default/header.jsp"/>


<h1 style="text-align: center;color: deepskyblue;margin-top: 10px">STATISTICHE</h1>
<div class="container-statistiche">

    <div class="box">
        <div class="num">
            <%=request.getAttribute("prodotti")%>
        </div>
        <div class="tipo">
            Prodotti
        </div>

    </div>
    <div class="box">
        <div class="num">
            <%=request.getAttribute("messaggi")%>
        </div>
        <div class="tipo">
            Messaggi
        </div>
    </div>
    <div class="box">
        <div class="num">
            <%=request.getAttribute("ordini")%>
        </div>
        <div class="tipo">
            Ordini
        </div>
    </div>
    <div class="box">
        <div class="num">
          <%=request.getAttribute("utenti")%>
        </div>
        <div class="tipo">
            Utenti Registrati
        </div>
    </div>
</div>

<jsp:include page="../default/footer.jsp"/>
</body>
</html>
