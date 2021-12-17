<%@ page import="model.utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Utente utente=(Utente)session.getAttribute("utente"); %>
<%String invioMessaggio=(String) request.getAttribute("assistenza");%>
<% System.out.println(invioMessaggio); %>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="Assistenza"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleAssistenza"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
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

<% if(invioMessaggio!=null){ %>
<h3><%=invioMessaggio%></h3>
<% }  %>

<%if(utente!=null){   %>
<h1 class="assistenza">TI AIUTIAMO NOI</h1>
<h1 class="assistenza">CI PENSA PHARMALIFE</h1><br>


<div class="padre">

    <div class="image">
        <img src="./immagini/ti-aiutiamo-noi.jpg">
    </div>

    <div class="flex-container">
        <form action="ServletInvioMessaggio" method="post">
            <div>
                <label for="fname"></label>
                <input type="text" id="fname" name="firstname" placeholder="Nome"><br>
            </div>

            <div>
                <label for="cognome"></label>
                <input type="text" id="cognome" name="lastname" placeholder="Cognome">
            </div>

            <div>
                <label for="telefono"></label>
                <input type="text" id="telefono" name="telefono" placeholder="Telefono">
            </div>
            <div>
                <label for="email"></label>
                <input type="text" id="email" name="email" placeholder="Email">
            </div>
            <div>
                <label for="subject"></label>
                <textarea id="subject" name="messaggio" placeholder="Il Tuo Messaggio" style="height:200px"></textarea>
            </div>
            <input type="submit" value="INVIA">
        </form>
    </div>
</div>
<% } else { %>
<h1 class="assistenza">Registrati per accedere all'area Assistenza</h1>
<% } %>
<jsp:include page="default/footer.jsp"/>
</body>
</html>
