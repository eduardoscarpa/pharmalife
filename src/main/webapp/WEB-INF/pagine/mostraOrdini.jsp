<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="storage.prodotto.Prodotto" %>
<%@ page import="storage.ordine.Ordine" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="Storico Ordini"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleListaProdottiAdmin,styleMostraOrdini"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
    <script src="js/header.js" type="text/javascript" defer></script>
</head>
<body>
<jsp:include page="default/header.jsp"/>
<h1 class="scrittaOrdini">STORICO ORDINI</h1>

<table class="tabellaOrd">

   <c:forEach items="${ordini}" var="ordine">
        <tr>
            <th>ID Ordine</th>
            <th>Data</th>
            <th>Ora</th>
            <th>Utente</th>
        </tr>
        <tr>
            <td>${ordine.idOrdine}</td>
            <td>${ordine.dataOrdine}</td>
            <td>${ordine.ora}</td>
            <td>${ordine.utente.codiceFiscale}</td>
        </tr>
            <tr>
                <th  colspan="4"> Prodotti </th>
            </tr>
        <tr>
        <c:forTokens items="${ordine.carrello.prodottiCarrello}" delims="," var="element">
                <td>${element}</td>
        </c:forTokens>
        </tr>

    </c:forEach>

</table>

<jsp:include page="default/footer.jsp"/>
<script>
    function tornaIndietro(){
        window.history.back();
    }
</script>
</body>
</html>