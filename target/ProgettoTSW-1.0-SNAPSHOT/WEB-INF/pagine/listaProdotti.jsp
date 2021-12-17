<%@ page import="java.util.ArrayList" %>
<%@ page import="model.prodotto.Prodotto" %>
<%@ page import="model.prodotto.ProdottoDAO" %>
<%@ page import="java.lang.reflect.Array" %><%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 07/07/2021
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList<Prodotto> prodotti=(ArrayList<Prodotto>) request.getAttribute("prodotti"); %>
<%String opzione=(String) request.getAttribute("opzione");%>
<html>
<head>
    <jsp:include page="/WEB-INF/pagine/default/head.jsp">
        <jsp:param name="title" value="Lista prodotti"/>
        <jsp:param name="style" value="styleHeader,styleFooter,styleListaProdotti"/>
        <jsp:param name="script" value="header,footer"/>
    </jsp:include>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" defer></script>
    <script src="js/aggiungiProdotto.js" type="text/javascript" defer></script>

    <script src="./js/header.js" type="text/javascript" defer></script>
</head>
<body>
<jsp:include page="default/header.jsp"/>

<div id="container-prodotti">
    <aside>
        <form class="products" action="ServletFilter" method="get">
            <div class="filtraggio">
                <label for="nome">Nome</label>
                <input id="nome" name="nome" type="text"  onkeyup="this.value = this.value.toUpperCase()">
            </div>
            <div class="filtraggio">
                <label for="categoria">Categoria</label>
                <input id="categoria" name="categoria" type="text" >
            </div>
            <div class="filtraggio">
                <label for="marchio">Marchio</label>
                <input id="marchio" name="marchio" type="text " >
            </div>
            <div class="filtraggioPrezzo" id="filatraggioPrezzo">
                <label for="prezzoMinimo">Min </label>
                <input id="prezzoMinimo" name="min" class="prezzo-filter" value="0" type="text" placeholder="Da ">
                <label for="prezzoMassimo">Max </label>
                <input id="prezzoMassimo" name="max" class="prezzo-filter" value="50" type="text" placeholder="A ">
            </div>
            <input type="submit" value="CERCA">
        </form>
    </aside>
    <main>
        <div class="filter">
            <h2 style="text-align: center"></h2>
            <form action="ServletOrdinaProdotti" method="get">
                <select id="ordinamento" name="ordine">
                    <option value="menoCaro" >Dal meno caro al più caro</option>
                    <option value="piuCaro">Dal più caro al meno caro</option>
                    <option value="crescente">Dalla A alla Z</option>
                    <option value="decrescente">Dalla Z  alla A</option>
                </select>
                <input type="submit" value="ORDINA"/>
            </form>
        </div>
        <div class="prodotti">
            <%for(Prodotto p : prodotti) {  %>
            <article>
                <figure>
                    <a href="ServletSchedaProdotto?value=<%=p.getCodiceProdotto()%>">
                        <img src="<%=application.getContextPath()%>/immaginiFarmaci/<%=p.getPathImmagine()%>" alt="Oki" height="200" width="200">
                    </a>
                    <figcaption>
                        <a href="ServletSchedaProdotto?value=<%=p.getCodiceProdotto()%>"><%=p.getNome()%> </a>
                    </figcaption>
                </figure>
                <div class="aggiungi">
                    <input type="hidden" id="quantita" name="tot" value="1">
                    <input type="hidden" id="idProdotto" name="prodotto" value="<%=p.getCodiceProdotto()%>">
                    <span class="iconaCarrello" > <i class="fas fa-cart-plus"></i> </span>
                    <span class="btn"><button onclick="aggiungiAlCarrello(<%=p.getCodiceProdotto()%>)" > Aggiungi Al Carrello</button></span>
                    <span style="display: inline;float: right;color:gray;padding: 2px;margin-top: 4px;border-radius: 4px;"><%=p.getPrezzo()%>€</span>
                </div>
            </article>
            <%  }  %>
        </div>
        <div class="center-pagination">
            <div class="pagination">
                <%if(opzione.contains("Categoria")){%>
                <a onclick="javascript:history.back()"href="#">&laquo;</a>
                <a href="ServletListaProdotti?value=<%=request.getAttribute("idCategoria")%>&nomejsp=list" class="active">1</a>
                <a href="ServletListaProdotti?value=<%=request.getAttribute("idCategoria")%>&nomejsp=list" >2</a>
                <a href="ServletListaProdotti?value=<%=request.getAttribute("idCategoria")%>&nomejsp=list">3</a>
                <a href="ServletListaProdotti?value=<%=request.getAttribute("idCategoria")%>&nomejsp=list">4</a>
                <a href="ServletListaProdotti?value=<%=request.getAttribute("idCategoria")%>&nomejsp=list">5</a>
                <a href="ServletListaProdotti?value=<%=request.getAttribute("idCategoria")%>&nomejsp=list">6</a>
                <a href="ServletListaProdotti?value=<%=request.getAttribute("idCategoria")%>&nomejsp=list">&raquo;</a>
                <%}else if(opzione.contains("Marchio")){%>
                <a  onclick="javascript:history.back()" href="">&laquo;</a>
                <a href="ServletListaMarchi?value=<%=request.getAttribute("nomeMarchio")%>&nomejsp=list" class="active">1</a>
                <a href="ServletListaMarchi?value=<%=request.getAttribute("nomeMarchio")%>&nomejsp=list" >2</a>
                <a href="ServletListaMarchi?value=<%=request.getAttribute("nomeMarchio")%>&nomejsp=list">3</a>
                <a href="ServletListaMarchi?value=<%=request.getAttribute("nomeMarchio")%>&nomejsp=list">4</a>
                <a href="ServletListaMarchi?value=<%=request.getAttribute("nomeMarchio")%>&nomejsp=list">5</a>
                <a href="ServletListaMarchi?value=<%=request.getAttribute("nomeMarchio")%>&nomejsp=list">6</a>
                <a href="ServletListaMarchi?value=<%=request.getAttribute("nomeMarchio")%>&nomejsp=list">&raquo;</a>
                <%}%>
            </div>
        </div>
    </main>
</div>
<jsp:include page="default/footer.jsp"/>
<script>

</script>
</body>
</html>
