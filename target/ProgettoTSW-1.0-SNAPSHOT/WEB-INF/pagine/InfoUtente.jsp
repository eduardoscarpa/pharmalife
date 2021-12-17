<%@ page import="model.utente.Utente" %><%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 12/07/2021
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Utente utente=(Utente)session.getAttribute("utente");%>
<%String update=(String) request.getAttribute("update");%>
<% System.out.println(update); %>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="PHarmaLife - Supermercato Farmaceutico"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleInfoUtente"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" defer></script>
    <script src="js/header.js" type="text/javascript" defer></script>
    <script src="js/scriptUpdateUtente.js" type="text/javascript" defer></script>
    <style>
        h3{
            text-align: center;
           color: deepskyblue;
        }
    </style>
</head>
<body>
<jsp:include page="default/header.jsp"/>
<h1 style="padding: 10px; color: #009fe3;">INFORMAZIONI</h1>
<% if(update!=null){ %>
<h3><%=update%></h3>
<% }  %>

<form action="ServletUpdateUtente" name="updateForm" method="POST" onsubmit=" return validazioneDati()">
    <div class="container-info">


        <div class="container-input">

            <div class="input-info">
                <label for="nome">Nome</label>
                <input type="text" id="nome"  name="nome" value="<%=utente.getNome()%>" required>
            </div>
            <div class="input-info">
                <label for="cognome">Cognome</label>
                <input type="text" id="cognome"  name="cognome" value="<%=utente.getCognome()%>" required >
            </div>
            <div class="input-info">
                <label for="email">Email</label>
                <input type="text" id="email"  name="email" value="<%=utente.getEmail()%>" required >
            </div>
            <div class="input-info">
                <label for="password">Password</label>
                <input   type="password" id="password" name="password" value="" required>
                <i  id="psw" class="fas fa-eye-slash" onClick="showPwd('password', this)"></i>
            </div>
            <div class="input-info">
                <label for="newPassword">Nuova Password</label>
                <input type="password" id="newPassword"  name="newPassword" value="" required>
                <i id="pswRepeat" class="fas fa-eye-slash" onClick="showPwd('newPassword', this)" ></i>
            </div>
        </div>
        <div class="info">
            <div class="consenso">
                <input type="checkbox" id="check" value="Acconsento">
                <label  for="check">Acconsento alla <a href="ServletLink?scelta=termini">Terms & Privacy</a> di pHarmaLife.</label>
            </div>
            <input type="hidden"  name="codiceFiscale" value="<%=utente.getCodiceFiscale()%>">
            <input type="submit" value="Salva">

        </div>

    </div>
</form>

<jsp:include page="default/footer.jsp"/>
<script>
    function showPwd(id, el) {
        let x = document.getElementById(id);
        if (x.type === "password") {
            x.type = "text";
            el.className = 'fa fa-eye showpwd';
        } else {
            x.type = "password";
            el.className = 'fa fa-eye-slash showpwd';
        }
    }

</script>
</body>
</html>
