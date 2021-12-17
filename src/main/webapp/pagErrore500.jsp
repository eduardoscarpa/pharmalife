<%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 24/07/2021
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="PHarmaLife - Supermercato Farmaceutico"/>
        <jsp:param name="style" value="styleContenuti,styleHeader,styleFooter"/>
    </jsp:include>
    <style>
        .error {
            display: flex;
            flex-direction: row;
            justify-content: center;
            height: 100%;
            width: 90%;


            margin: 20px auto 20px;
        }
    </style>
</head>
<body>
<jsp:include page="WEB-INF/pagine/default/header.jsp"/>

<div class="error">

   <img src="imgErrore/500-master.jpg" alt="errore 500" class="img-centrata">
</div>

<jsp:include page="WEB-INF/pagine/default/footer.jsp"/>

</body>
</html>
