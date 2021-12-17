<%@ page import="java.util.ArrayList" %>
<%@ page import="model.messaggio.Messaggio" %><%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 16/07/2021
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList<Messaggio> messaggi=(ArrayList<Messaggio>) request.getAttribute("messaggi"); %>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="PHarmaLife - Supermercato Farmaceutico"/>
        <jsp:param name="style" value="styleHeader,styleFooter,login,styleAssistenzaUtenti"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
    <script src="js/header.js" type="text/javascript" defer></script>
</head>
<body>
<jsp:include page="../default/header.jsp"/>
<% if(messaggi!=null) {%>
<a onclick="tornaIndietro()" class="myButtonTorna">Torna indietro</a>
<h1>ASSISTENZA</h1>
<table>
    <tr>
        <th>EMAIL</th>
        <th>MESSAGGIO</th>
        <th>DATA</th>
        <th>ORA</th>

    </tr>
    <%for(Messaggio m : messaggi) {   %>
    <tr>
        <td><%=m.getUtente().getEmail()%></td>
        <td  class="messaggio"> <a href=""><%=m.getTesto()%></a></td>
        <td><%=m.getData()%></td>
        <td><%=m.getOra()%></td>

    </tr>
    <%  }  %>

</table>
<%  } else { %>
<h1> Non ci sono messaggi</h1>
<%  } %>

</div>


<jsp:include page="../default/footer.jsp"/>
</body>
<script>
    function tornaIndietro(){
        window.history.back();
    }
</script>
</html>
