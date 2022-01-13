<%--
  Created by IntelliJ IDEA.
  User: Amministratore
  Date: 19/06/2021
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<footer>
<div class="padre-footer">
    <div class="logoFooter">
        <div class="immagineLogo">
            <a href="ServletLink?scelta=home"><img src="./immagini/LogoPharmaLife.svg" alt="Logo Farmacia" width="130" height="130" align="left"></a>
        </div>

        <div class="primaScrittaFooter">
            <h1>PHARMALIFE.IT</h1>
            <p style="color: grey">In qualunque posto, da qualsiasi device,in pochi <br>
                click direttamente a casa tua!
            </p>
        </div>
        <div class="secondaScrittaFooter">
           <h1>pHarmaLife Srl</h1>
            <p>
                P.IVA 0923324243<br>
                BEA SA - 329342<br>
                Via Trento, 48/A<br>
                84084 - Salerno
            </p>
        </div>
    </div>



    <div class="menuScorrimento">
        <a href="javascript:void(0)"  onclick="mostraMenuINFORMAZIONI0()" style="text-align: center;color: #009fe3; margin-left: 2px; float: left">INFORMAZIONI<i class="fas fa-chevron-down" style="float: left"></i> </a>
        <div class="dropdown-content-footer">
            <a href="ServletLink?scelta=infoAzienda">Chi Siamo</a>
            <a href="ServletAssistenza">Contatti</a>
            <a href="ServletLink?scelta=termini">Termini e Condizioni</a>
            <a href="">Privacy policy</a>
            <a href="ServletLink?scelta=assistenza">Ti Aiutiamo Noi</a>
            <a href="ServletLink?scelta=faq">FaQ</a>
        </div>
    </div>
    <div class="menuScorrimento">
        <a href="javascript:void(0)"  onclick="mostraMenuINFORMAZIONI1()" style="text-align: center;color: #009fe3; margin-left: 2px; float: left">ACCOUNT<i class="fas fa-chevron-down" style="float: left"></i> </a>
        <div class="dropdown-content-footer">
            <a href="ServletLink?scelta=login">Accedi</a>
            <a href="ServletLink?scelta=mioAccount">Il Mio Account</a>
            <% if(session.getAttribute("logic.utente")!=null){  %>
            <a href="ServletLink?scelta=ordini">I Miei Ordini</a>
            <%  }  else { %>
            <a style="cursor: pointer" onclick="alert('Registrati per accedere')">I Miei Ordini</a>
            <%  }  %>
            <a href="">I Miei Indirizzi</a>
            <a href="">Controlla Ordine Ospite</a>
            <a href="ServletLink?scelta=iscriviti">Registrati</a>
        </div>
    </div>



    <div class="pr-footer">
        <div class="NostriContatti" style="text-align: center; margin-left: 2px";>
            I NOSTRI CONTATTI
        </div>
        <div class="Footer-icone">
            <a href="" class="icone-Footer"><i class="far fa-envelope" style="color: limegreen;font-size: 30px" ></i></a>
            <p>assistenza@pHarmaLife.it</p>

        </div>

        <div class="Footer-icone">
            <a href="" class="icone-Footer"><i class="fas fa-map-marker-alt" style="color: limegreen;font-size: 30px" ></i></a>
            <p>Via Trento, 48/B - 80129 - Salerno</p>
        </div>

        <div class="Footer-icone">
            <a href="" class="icone-Footer"><i class="fas fa-mobile-alt" style="color: limegreen;font-size: 30px"; ></i></a>
            <p>+39 392 059 16 62</p>
        </div>
    </div>
</div>
    <div class="copyright-card">
        <div class="lista-pagamenti">
            <img src="./immagini/pagamenti.webp">
        </div>
    </div>
    <div class="copyright-footer">
        Copyrights © 2021 ADPSoftware. Tutti I Diritti Riservati.<br>
        Sede legale e operativa:   Via Trento, 48/B - 80129 (SA) Italy  pharmalife-dev@gmail.com
    </div>
</footer>

<script>
    function mostraMenuINFORMAZIONI1() {
        var x = document.getElementsByClassName("dropdown-content-footer")[1];
        if (x.style.display === "block") {
            x.style.display = "none";
        } else {
            x.style.display = "block";
        }
    }
    function mostraMenuINFORMAZIONI0() {
        var x = document.getElementsByClassName("dropdown-content-footer")[0];
        if (x.style.display === "block") {
            x.style.display = "none";
        } else {
            x.style.display = "block";
        }
    }



</script>
</html>