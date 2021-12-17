

<%@ page contentType="text/html; charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList,model.marchio.Marchio" %>
<%@ page import="model.utente.Utente" %>
<%@ page import="model.categoria.Categoria" %>
<%@ page import="model.marchio.MarchioDAO" %>
<% Utente utente=(Utente) session.getAttribute("utente"); %>
<% ArrayList<Categoria> categorie=(ArrayList<Categoria>) application.getAttribute("categorie");%> <!-- L'oggetto application fa parte della ServletContext in ServletStart-->
<%ArrayList<Marchio> marchi=(ArrayList<Marchio>) application.getAttribute("marchi"); %>

<header class="top-header">
    <p class="info">
        Info e Assistenza Clienti: (+39) 3920591662 | Spedizioni gratuite per ordini superiori a 69,99€
    </p>
        <div class="logo">
            <form action="ServletSchedaProdottoSearch" id="formId" method="get" name="formInputSearch"  >
                <label class="field" for="ricercaId" >
                    <input  list="prod" type="text" id="ricercaId" name="search" placeholder="Cerca..."  onkeyup="ricerca()" autocomplete="off">
                    <datalist id="prod">

                    </datalist>
                    <button type="submit" name="sumbit" value="sumbit" onclick="return verifica()"> <i class="fas fa-search" style="font-size: 17px"></i></button>
                </label>
            </form>

            <a href="ServletLink?scelta=home"><img  src="./immagini/LogoPharmaLife.svg" alt="Logo Farmacia" width="150" height="150"></a>

            <div class="contenitore-icone " style="order: 3;">
               <a href="javascript:void(0);"  class="linkMenu"   onclick="mostraMenu()">
                    <i class="fas fa-bars" style="font-size: 20px"></i>
                </a>
                <a   href="ServletLink?scelta=login"  class="icone"> <i class="fa fa-user-circle"  onclick="mostraForm()" style="color: limegreen;font-size: 30px" ></i> </a>
                <a   href="https://www.facebook.com/pharmalife.ivane.5" class="icone"> <i class="fab fa-facebook-f" style="color: limegreen;font-size: 30px" ></i></a>
                <a   href="ServletAssistenza" class="icone"> <i class="fas fa-phone-square" style="color: limegreen;font-size: 30px"></i> </a>
                <a   href="ServletLink?scelta=carrello" class="icone"><i class="fas fa-shopping-cart" style="color: limegreen;font-size: 30px"></i></a>
                <a  title="Preferiti" href="ServletMostraPref" class="icone"><i class="far fa-heart" style="color: limegreen;font-size: 30px"></i> </a>
                <% if(utente!=null){%>
                <a  title="Logout" href="ServletAccessoUtente?value=logout" class="icone"><i class="fas fa-sign-out-alt" style="color: limegreen;font-size: 30px"></i></a>
                <% }%>
            </div>
        </div>
</header>
<nav>
    <a href="ServletLink?scelta=home" class="home">Home</a>
    <div class="dropdown">
        <button class="dropbtn">Prodotti</button>
        <div class="dropdown-content">
            <div class="row">
                <div class="colum uno">
                    <% for(int i=0;i<(categorie.size()/2)+1;i++){    %>
                    <a href="ServletListaProdotti?value=<%=categorie.get(i).getIdCategoria()%>&nomejsp=header">
                        <%=categorie.get(i).getNomeCategoria()%>
                    </a>
                    <% } %>

                </div>
              <div class="colum due">
                  <% for(int i=(categorie.size()/2)+1;i<categorie.size();i++){    %>
                  <a href="ServletListaProdotti?value=<%=categorie.get(i).getIdCategoria()%>&nomejsp=header">
                      <%=categorie.get(i).getNomeCategoria()%></a>
                  <% } %>

                </div>
            </div>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">Marchi</button>
        <div class="dropdown-content">
            <div class="row">
                <div class="colum">
                    <%for(int i=0;i<(marchi.size()/2)+1;i++){   %>
                    <a href="ServletListaMarchi?value=<%=marchi.get(i).getNomeMarchio()%>&nomejsp=header">
                        <%=marchi.get(i).getNomeMarchio()%></a>
                    <% } %>

                </div>
                <div class="colum">
                    <% for(int i= (marchi.size()/2)+1;i<marchi.size();i++){  %>
                    <a href="ServletListaMarchi?value=<%=marchi.get(i).getNomeMarchio()%>&nomejsp=header">
                        <%=marchi.get(i).getNomeMarchio()%></a>
                    <%  } %>

                </div>
            </div>
        </div>
    </div>
    <a href="ServletLink?scelta=infoAzienda">Chi siamo</a>
    <a href="ServletAssistenza">Contatti</a>
    <%if(utente!=null) {  %>
    <a href="ServletLink?scelta=assistenza">Ti Aiutiamo Noi</a>
    <%   } %>
</nav>

