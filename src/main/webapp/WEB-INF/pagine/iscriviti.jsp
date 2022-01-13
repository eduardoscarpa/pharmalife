<%@ page import="model.logic.utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%Utente logic.utente=(Utente)session.getAttribute("logic.utente");%>--%>
<%String iscrizione=(String) request.getAttribute("iscriviti");%>

<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="Registrazione"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleIscrizione"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
    <!--  <link rel="stylesheet" href="./css/styleIscrizione.css">-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" defer></script>
    <script src="./js/iscrizione.js" type="text/javascript" defer></script>
    <script src="js/header.js" type="text/javascript" defer></script>
    <style>
        h3{
            text-align: center;
            color: red;
        }
    </style>
</head>
<body>

<jsp:include page="default/header.jsp"/>

<% if(iscrizione!=null){ %>
<h3><%=iscrizione%></h3> <!-- Controllo lato server -->
<% }  %>

<form action="ServletIscrizione" method="post" name="registrazione" onsubmit="validazioneIscrizione()"> <!--Controllo lato javascript -->
    <div class="container-iscriviti">
        <h1>Crea un account</h1>
        <p>Si prega di compilare questo modulo per creare un account.</p>
        <hr>


        <div class="register">
            <label for="Idnome"><b>Nome</b></label>
            <input type="text" placeholder="Inserisci il tuo nome" name="nome" id="Idnome" required>
        </div>


        <div class="register">
            <label for="Idcognome"><b>Cognome</b></label>
            <input type="text" placeholder="Inserisci il tuo cognome" name="cognome" id="Idcognome" required>
        </div>

        <div class="register">
            <label for="IdcodiceFiscale"><b>Codice Fiscale</b></label>
            <input type="text" placeholder="Inserisci il tuo codice fiscale" name="CodiceFiscale" id="IdcodiceFiscale" required style="text-transform: uppercase">
        </div>


        <div class="register">
            <label for="Idemail"><b>Email</b></label>
            <input type="email" placeholder="Inserisci Email" name="email" id="Idemail" required>
        </div>

        <div class="register">
            <label for="Idpsw"><b>Password</b></label>
            <input type="password" placeholder="Inserisci Password" name="psw" id="Idpsw"  title="Ripeti la password" required>
        </div>

        <div class="register">
            <label for="Idpsw-rip"><b>Ripeti Password</b></label>
            <input type="password" placeholder="Inserisci di nuovo la Password" name="psw-rip" id="Idpsw-rip" required>
        </div>

        <div class="register">
            <label for="Idvia"><b>Via</b></label>
            <input type="text" placeholder="Inserisci la via" name="via" id="Idvia" required>
        </div>

        <div class="register">
            <label for="IdnumeroCivico"><b>Numero Civico</b></label>
            <input type="text" placeholder="Numero Civico" name="numeroCivico" id="IdnumeroCivico" required>
        </div>

        <div class="register">
            <label for="Idcap"><b>CAP</b></label>
            <input type="text" placeholder="Inserisci CAP" name="cap" id="Idcap" required>
        </div>

        <div class="register">
            <label for="Idtelefono"><b>Telefono</b></label>
            <input type="tel" placeholder="Inserisci il numero di telefono" name="telefono" id="Idtelefono" required>
        </div>

        <hr>
        <p>Acconsento alla <a href="ServletLink?scelta=termini">Terms & Privacy</a> di pHarmaLife.</p>

        <input type="submit" class="registerbtn" value="Registrati">

        <div class="container signin">
            <p>Hai gia un account? <a href="#">Accedi</a>.</p>
        </div>
        <!--  </form>-->
    </div>
</form>


<jsp:include page="default/footer.jsp"/>
</body>
</html>
