<%@ page import="model.utente.Utente" %>
<%@ page import="model.prodotto.Prodotto" %><%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 19/07/2021
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% Integer idProdotto= (Integer) request.getAttribute("idProdotto");
System.out.println(idProdotto);%>--%>
<%Prodotto prodotto=(Prodotto) request.getAttribute("prodotto");%>
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
        .myButtonTorna{
            box-shadow: -1px 6px 28px 3px #3dc21b;
            background:linear-gradient(to bottom, #44c767 5%, #5cbf2a 100%);
            background-color:#44c767;
            display:inline-block;
            cursor:pointer;
            color:#ffffff;
            font-size:16px;
            padding: 16px 31px;
            margin-left: 10px;
            margin-bottom: 10px;
            margin-top: 10px;
            text-decoration:none;
            text-shadow:0px 0px 0px #2f6627;
        }
        .myButtonTorna:hover {
            background:linear-gradient(to bottom, #5cbf2a 5%, #44c767 100%);
            background-color:#5cbf2a;
        }
        .myButtonTorna:active {
            position:relative;
            top:1px;
        }
    </style>
    <script src="js/header.js" type="text/javascript" defer></script>
</head>
<jsp:include page="/WEB-INF/pagine/default/header.jsp"/>

<body>
<a onclick="tornaIndietro()" class="myButtonTorna">Torna indietro</a>
<h1 style="padding: 10px; color: #009fe3;">AGGIORNA PRODOTTO</h1>
<div class="container-form">
    <fieldset>
        <legend>Prodotto 0PR00<%=prodotto.getCodiceProdotto()%></legend>
        <form action="ServletUpdateAdminDUE" method="post" onclick="">
            <div class="blocco">
                <label for="nome">Nome</label>
                <input type="text" name="nome" id="nome">
            </div>
            <div class="blocco">
                <label for="prezzo">Prezzo</label>
                <input type="number" name="prezzo" id="prezzo">
            </div>
            <input type="hidden" name="idProdotto" value="<%=prodotto.getCodiceProdotto()%>">
            <input type="submit" value="MODIFICA">
        </form>
    </fieldset>

</div>


<jsp:include page="/WEB-INF/pagine/default/footer.jsp"/>
</body>
<script>
    function tornaIndietro(){
        window.history.back();
    }
</script>
</html>

