<%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 06/07/2021
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="Chi siamo"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleInfoAzienda"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
    <script src="js/header.js" type="text/javascript" defer></script>
</head>
<body>
<jsp:include page="default/header.jsp"/>
<div class="imm-padre">
    <img class="immPrin" src="./immagini/54279240_346166276241069_8652447767978835968_n.jpg" alt="immaginePrin">
</div>

<div class="imm-secP">
    <img class="immSec" src="./immagini/INTERNI.webp" alt="immagineSec">
    <div class="imm-secP-testo">
        <hr>
        <h1>La nostra azienda</h1><br>
        <p><b>Leader</b> nella produzione e distribuzione di integratori alimentari, dermocosmetici e dispositivi medici,
            da oltre vent’anni è presente nei canali farmacia, parafarmacia ed erboristeria.</p>
    </div>
</div>

<div class="imm-secP">
    <img class="immSec" src="./immagini/sito-produttivo-pharmalife-research.webp" alt="immagineSec">
    <div class="imm-secP-testo">
        <hr>
        <h1>Sito produttivo</h1><br>
        <p>Attrezzato con equipment ad alta tecnologia,
            dotato di personale selezionato, altamente qualificato, che si sottopone a formazione continua.
        </p>
    </div>
</div>

<div class="padre-mondo">
    <p>Una porta aperta sul mondo</p>
    <img class="immMondo" src="./immagini/Mappa-del-mondo-3-scaled.webp" alt="immagineSec">
</div>

<div class="padre-curriculum">
    <hr>
    <p class="lavorare" style="text-align: center; padding: 10px;">VUOI LAVORARE CON NOI?</p>
    <p class="curr" style="text-align: center; font-size: 30px; margin-bottom: 10px;">INVIACI IL TUO CURRICULUM ADESSO</p>
    <P class="email" style="text-align: center;">Invia tramite e-mail (pharmalife-dev@gmail.com)<br>
        Riceverai presto nella tua email una risposta da parte di un nostro collaboratore.</P>
</div>
<jsp:include page="default/footer.jsp"/>
</body>
</html>
