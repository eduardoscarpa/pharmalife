<%@ page import="storage.utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%Utente utente=(Utente)session.getAttribute("utente");%>--%>
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
            <input type="text" placeholder="Inserisci il tuo nome..." name="nome" id="Idnome">
        </div>


        <div class="register">
            <label for="Idcognome"><b>Cognome</b></label>
            <input type="text" placeholder="Inserisci il tuo cognome..." name="cognome" id="Idcognome">
        </div>

        <div class="register">
            <label for="IdcodiceFiscale"><b>Codice fiscale</b></label>
            <input type="text" placeholder="Inserisci il tuo codice fiscale..." name="CodiceFiscale" id="IdcodiceFiscale" style="text-transform: uppercase">
        </div>


        <div class="register">
            <label for="Idemail"><b>E-mail</b></label>
            <input type="email" placeholder="Inserisci e-mail..." name="email" id="Idemail">
        </div>

        <div class="register">
            <label for="Idpsw"><b>Password</b></label>
            <input type="password" placeholder="Inserisci password..." name="psw" id="Idpsw"  title="Ripeti la password" required>
        </div>

        <div class="register">
            <label for="Idpsw-rip"><b>Ripeti la password</b></label>
            <input type="password" placeholder="Inserisci di nuovo la password..." name="psw-rip" id="Idpsw-rip" required>
        </div>

        <div class="register">
            <label for="Idvia"><b>VIA</b></label>
            <input type="text" placeholder="Inserisci la via..." name="via" id="Idvia" required>
        </div>

        <div class="register">
            <label for="IdnumeroCivico"><b>NUMERO CIVICO</b></label>
            <input type="text" placeholder="Numero civico..." name="numeroCivico" id="IdnumeroCivico">
        </div>

        <div class="register">
            <label for="Idcap"><b>CAP</b></label>
            <input type="text" placeholder="CAP..." name="cap" id="Idcap">
        </div>

        <div class="register">
            <label for="Idtelefono"><b>Telefono</b></label>
            <input type="tel" placeholder="Inserisci il numero di telefono..." name="telefono" id="Idtelefono" required>
        </div>

        <hr>
        <p>Acconsento alla <a href="ServletLink?scelta=termini">Terms & Privacy</a> di pHarmaLife.</p>

        <input type="submit" class="registerbtn" value="Registrati">

        <div class="container signin">
            <p>Hai gia un account? <a href="#">Accedi</a></p>
        </div>
        <!--  </form>-->
    </div>
</form>


<jsp:include page="default/footer.jsp"/>
</body>
</html>
