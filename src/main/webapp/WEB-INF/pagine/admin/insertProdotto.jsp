<%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 08/07/2021
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="Inserisci prodotto"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleInsertProdotto"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
    <script src="js/header.js" type="text/javascript" defer></script>
</head>
<body>
<jsp:include page="../default/header.jsp"/>
<a onclick="tornaIndietro()" class="myButtonTorna">Torna indietro</a>
<h1 class="insert">AGGIUNGI UN NUOVO PRODOTTO</h1>

<form  class="insert" action="ServletInsertProdotto" method="POST" name="insert">
    <fieldset>
        <legend>Nuovo Prodotto</legend>
        <div class="campo">
            <label for="nomeId">Nome Prodotto</label><br>
            <input type="text" id="nomeId" name="nome" placeholder="Nome Prodotto..." required>
        </div>
        <div class="campo">
            <label for="prezzoId">Prezzo</label><br>
            <input type="text" id="prezzoId" name="prezzo" placeholder="Prezzo" required>
        </div>
        <div class="campo">
            <label for="marchioId">Marchio</label><br>
            <input type="text" id="marchioId"  name="marchio" placeholder="Marchio" required>
        </div>
        <div class="campo">
            <label for="caegoriaId">Categoria</label><br>
            <input type="text" id="categoriaId"  name="categoria" placeholder="Categoria" required>
        </div>
        <div class="campo">
            <label for="descrizioneId">Descrizione</label>
            <textarea name="descrizione"></textarea>
        </div>
        <div class="campo">
            <label for="immagine">Scegli Immagine</label>
            <input type="file" id="immagine" name="pathImmagine">
        </div>
        <input type="hidden" name="quantita" value="100">
    </fieldset>
    <input type="submit" value="INSERISCI">
</form>
<jsp:include page="../default/footer.jsp"/>
<script>
    function tornaIndietro(){
        window.history.back();
    }

</script>
</body>
</html>
