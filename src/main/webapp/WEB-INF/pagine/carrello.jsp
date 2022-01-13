<%@ page import="model.carrello.Carrello" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.prodotto.Prodotto" %>
<%@ page import="model.utente.Utente" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.io.ObjectOutputStream" %>
<%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 13/07/2021
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Carrello carrello=(Carrello)session.getAttribute("carrello");   %>
<%Utente utente=(Utente)session.getAttribute("utente");  %>
<%ArrayList<Prodotto> prodotti=null;   %>
<%
    if(utente!=null) {
        if(utente.getCarrello()!=null) {
            prodotti=utente.getCarrello().getProdotti();
        }
    }else {
        if(carrello!=null) {
            prodotti = carrello.getProdotti();
        }
    }

%>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="PHarmaLife - Supermercato Farmaceutico"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleCarrello"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" defer></script>
    <script src="js/header.js" type="text/javascript" defer></script>
    <style>
        h2{
            text-align: center;
            color: deepskyblue;
        }
    </style>
</head>
<body>

<jsp:include page="default/header.jsp"/>
<% if(utente!=null){ if(utente.getCarrello()==null) { %>
<h2>  <%=utente.getNome()%> il tuo carrello è vuoto </h2>
<% }else {   %>
<h2> Ecco il tuo Carrello <%=utente.getNome()%></h2>
<%  }  }else { if(carrello!=null) { %>
<h2>Ecco il tuo carrello Visitatore</h2>
<%  } else {  %>
<h2>Il tuo carrelo è vuoto</h2>
<% } %>

<% }  %>


<% if(prodotti!=null){  %>

<div class="include-tutto">
     <div class="lista-prodotti">
        <div class="titolo">
            <h1>CARRELLO DELLA SPESA</h1>
        </div>
        <%for(Prodotto p : prodotti){   %>
        <div class="card-prodotti">
            <div class="immagine-card-prodotti">
                <img src="<%=application.getContextPath()%>/immaginiFarmaci/<%=p.getPathImmagine()%>" alt="filorga-time-filler-crema-50ml" width="150" height="150">
            </div>
            <div class="info-card-prodotti">
                <a class="label" href="">
                  <%=p.getNome()%>
                </a>
                <div class="product-discount">
                    <span class="regular-price" style="text-decoration: line-through;"> <%=p.getPrezzo()%> € </span>
                    <span class="discount-percentage"> -50% </background> </span>
                </div>
                <div class="current-price">
                    <span class="price"> <%=p.getPrezzo()/2%> €</span>
                </div>
            </div>

            <div class="total-delete">

                <a href="ServletRimuoviDalCarrello2?value=<%=p.getCodiceProdotto()%>">
                    <i class="fas fa-trash-alt"></i>
                </a>
            </div>
        </div>
<%  } %>
    </div>


    <div class="cassa">
        <div class="spedizione">
            <span style="margin-top: 10px" class="label"> Spedizione </span>
            <span style="margin-top: 10px" class="value"> Gratis </span>
        </div>
        <div class="totale">
            <span class="label">Totale&nbsp;(Tasse incl.)</span>
            <% if(utente!=null) { %>
            <span class="value"> <%=utente.getCarrello().getTotalePrezzo()/2%>&nbsp;€ </span>
            <% } else{  %>
            <span class="value"> <%=carrello.getTotalePrezzo()/2%>&nbsp;€ </span>
            <% } %>
        </div>
        <div class="codice-sconto">
            <p class="promo-code-button display-promo">
                <a class="collapse-button" href="#promo-code"> Hai un codice sconto? </a>
            </p>
        </div>
        <div class="promo-code">
            <form action="" data-link-action="add-voucher" method="post">
                <input type="hidden" name="token" value="406a80666b6957b3c5c46ea8e5494f3e">
                <input type="hidden" name="addDiscount" value="1">
                <input class="promo-input" type="text" name="discount_name" placeholder="Codice Sconto">
                <button type="submit" class="btn btn-primary">
                    <span>Applica</span>
                </button>
            </form>
            <div class="alert alert-danger js-error" role="alert">
                <i class="material-icons"></i>
                <span class="ml-1 js-error-text"></span>
            </div>
        </div>
        <div class="checkout">

            <div class="text-centered">
                <% if (utente != null) { %>
                <form action="ServletOrdini" method="post">
                    <input type="submit" href="ServletOrdini" class="button-outline" value="Vai alla cassa"
                           ></input>

                </form>
                <% } else if (utente==null) { %>
                <h3>ACCEDI PER ACQUISTARE </h3>
                <% } %>
            </div>
        </div>
    </div>

    </div>
<% }else { %>
<h2>Non ci sono prodotti nel carrello</h2>
<%  }  %>


<jsp:include page="default/footer.jsp"/>
</body>
</html>
