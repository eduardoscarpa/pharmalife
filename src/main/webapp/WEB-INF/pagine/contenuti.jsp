<%@ page import="java.util.ArrayList" %>
<%@ page import="storage.categoria.Categoria" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList<Categoria> categorie=(ArrayList<Categoria>) application.getAttribute("categorie"); %>


<div id="slider">
    <figure>
        <img src="./immaginiSlider/sustenium_plus_desk-min.jpeg" alt="immagine">
        <img src="./immaginiSlider/swisse_desk-min.jpeg" alt="immagine">
        <img src="./immaginiSlider/swisse_integratore-min.jpeg" alt="immagine">
        <img src="./immaginiSlider/systane_desk-min.jpeg" alt="immagine">
        <img src="./immaginiSlider/desk-2964x700-min.jpeg" alt="immagine">

    </figure>
</div>

<div class="cat">
    <h3 >LE NOSTRE CATEGORIE</h3>
    <h5 class="sub-cat">Scopri i migliori prodotti di pHarmaLife in base alle tue esigenze...</h5>
    <hr>
    <div class="listaCategorie">
        <% for(Categoria c : categorie){  if(c.getRoot()==0){ %>
        <div class="categoria" >
            <%=c.getNomeCategoria()%>
        </div>
        <% } } %>
    </div>

</div>

<div class="info-contenuti">

<fieldset>
    <legend>Spediamo Felicità</legend>
    <p class="sub-sped"  > PHarmaLife è la risposta alla tua personale ricerca di benessere.

        La <b>farmacia on-line</b> che ti offre il più vasto assortimento di marche e prodotti <b>al miglior prezzo con consegna in 24/48h
            e reso gratuito.</b>

        Su  pHarmaLife  potrai trovare i migliori integratori alimentari, i tuoi prodotti di bellezza preferiti
        (<b>Bionike, Lierac, Somatoline, Avene, La Roche-Posay, Klorane, Vichy</b>, ecc) e tutto il necessario per la cura del bambino.
        Scopri la linea completa di prodotti per l'igiene orale.

        Inoltre  pHarmaLife  ti offre un servizio costante di farmacisti pronti a supportarti in ogni esigenza.</p>
</fieldset>

</div>
