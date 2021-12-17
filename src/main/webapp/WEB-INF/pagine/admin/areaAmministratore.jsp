<%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 08/07/2021
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="Area Admin"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleAreaAmministratore"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
    <script src="js/header.js" type="text/javascript" defer></script>
    <script src="js/ajaxTableAdmin.js" type="text/javascript" defer></script>
</head>
<body>
<jsp:include page="../default/header.jsp"/>


<div class="opzione">
    <div class="scelte">

        <ul>
            <li><a  onclick="mostraTabella('prodotti')" >Lista prodotti </a><i class="fas fa-pills"></i> </li>
            <li><a  onclick="mostraTabella('utenti')">Lista utenti </a> <i class="fas fa-users"></i></li>
            <li><a href="ServletAdmin?value=statistiche">Statistiche</a><i class="fas fa-chart-pie"></i></li>
            <li><a href="ServletAdmin?value=insertProdotto">Inserisci prodotto </a> <i class="fas fa-pills"></i></li>
            <li><a href="ServletAdmin?value=messaggi">Assistenza utenti </a> <i class="fas fa-comment-alt"></i></li>
        </ul>
    </div>
    <div   class="tabella">
        <table id="table">

        </table>
    </div>

</div>
<jsp:include page="../default/footer.jsp"/>
</body>
</html>
