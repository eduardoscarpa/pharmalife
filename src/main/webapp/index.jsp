<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="PHarmaLife - Supermercato Farmaceutico"/>
        <jsp:param name="style" value="styleContenuti,styleHeader,styleFooter"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
  <!--  <script src="js/header.js" type="text/javascript" defer></script>-->
    <script src="js/header.js" type="text/javascript" defer></script>
    <script src="js/barraRicerca.js" type="text/javascript" defer></script>
    <style>
        html, body {
            font-family: "Montserrat-Regular";
            font-weight: normal;
            font-style: normal;
        }
    </style>
</head>
<body>
<div class="container-index">
    <jsp:include page="WEB-INF/pagine/default/header.jsp"/>
    <jsp:include page="WEB-INF/pagine/contenuti.jsp"/>
    <jsp:include page="WEB-INF/pagine/default/footer.jsp"/>
</div>
</body>
</html>

