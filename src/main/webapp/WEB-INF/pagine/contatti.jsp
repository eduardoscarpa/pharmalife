<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="Contatti"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleContatti"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
    <script src="js/header.js" type="text/javascript" defer></script>
    <link rel="stylesheet" href="./css/styleContatti.css">
</head>

<body>
<jsp:include page="default/header.jsp"/>

<div class="contatti-scritta">
    <h1>CONTATTI</h1>
    <hr>
</div>

<div class="contatti-padre">
    <div class="contatti-box">
        <p>  Per domande sulla <b>spedizione</b> del vostro ordine, inviare un messaggio alla seguente e-mail, un nostro collaboratore
            contatterà il corriere espresso SDA Poste Italiane -


            <a href="" class="icone-Footer"><i class="far fa-envelope" style="color: limegreen;font-size: 15px" ></i></a> pharmalife-dev@gmail.com</p>


    </div>

    <div class="parole-contatti">
        PER QUALSIASI ALTRA RICHIESTA
        <p>Siamo a tua completa disposizione</p>
    </div>

    <div class="ass-contatti">
        Hai bisogno di assistenza? Clicca <a href="ServletLink?scelta=assistenza">qui</a>.
    </div>

    <div class="imm-contatti">
        <img class="immContatti" src="./immagini/GIULIA_ESPOSITO.png" alt="immagineContatti">
        <div class="imm-contatti-testo">
            <hr>
            <h1>Giulia Esposito</h1><br>
            <p><b>Informatrice Scientifica del Farmaco</b> garantisce il collegamento ed il passaggio di informazioni
                tra l’industria produttrice di medicinali nel settore farmaceutico e i medici/operatori sanitari.
                <b>Lei è una della nostre tante collabotratici e adesso è a lavoro per voi.</b></p>
        </div>
    </div>
</div>
<jsp:include page="default/footer.jsp"/>
</body>

</html>