<%@ page import="model.utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Utente utente= (Utente) session.getAttribute("utente");
    String errore= (String) request.getAttribute("errore");
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="Accesso"/>
        <jsp:param name="style" value="styleContenuti,styleHeader,styleFooter,login"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
  <!-- <script src="js/header.js" type="text/javascript" defer></script>--->
    <script src="js/header.js" type="text/javascript" defer></script>

</head>
<body>

<jsp:include page="default/header.jsp"/>

<div class="containerLogin">
    <div class="sub-container">
        <div class="banner-img">
            <picture>
                <source media="(min-width:501px)" srcset="./immagini/farmaciaLogin.jpg">
                <source media="(max-width:500px)" srcset="./immagini/LogoPharmaLife.svg">
                <img src="./immagini/farmaciaLogin.jpg" alt="Logo Farmacia">
            </picture>
        </div>
        <div class="login-form" >
            <form name="loginUser"  action="ServletAccessoUtente?value=login" method="post"  >
                <% if(errore!=null){ %>
                <div class="title" style="color: red">
                    <%=errore%>
                </div>
                <% } else{ %>
                <div class="title">
                    ACCEDI
                    <p id="avviso"></p>
                </div>
                <% } %>
                <div class="input-fields">
                    <label id="nomeUtente"  class="label">E-mail</label><br>
                    <input type="email"    name="emailUser" class="input" id="nomeUtente" placeholder="E-mail..." required>
                </div>
                <div class="input-fields">
                    <label id="password"  class="label">Password</label><br>
                    <input type="password"  name="password" class="input" id="passw" placeholder="password" required>

                </div>
                <div class="forgot">
                    <a href="#">Hai dimenticato la password?</a><br>
                  <a href="ServletLink?scelta=iscriviti">Non sei ancora registrato? Iscriviti!</a>
                </div>
                <input class="submit" type="submit" value="Accedi" >
            </form>

        </div>
    </div>
</div>

<jsp:include page="default/footer.jsp"/>



</body>

</html>