package model.carrello;

import model.ordine.Ordine;
import model.prodotto.Prodotto;
import model.utente.Utente;

import java.util.ArrayList;
import java.util.Objects;

public class Carrello {

    private int codiceCarrello;
    private Utente utente;
    private ArrayList<Prodotto> prodotti;
    private int totaleProdotti;
    private double totalePrezzo=0;
    private ArrayList<Ordine> ordini;
    private StringBuilder prodottiCarrello;

    public Carrello() {
        prodotti= new ArrayList<>();
    }

    public Carrello(int codiceCarrello, Utente utente, ArrayList<Prodotto> prodotti, int totaleProdotti) {
        this.codiceCarrello = codiceCarrello;
        this.utente = utente;
        this.prodotti = prodotti;
        this.totaleProdotti = totaleProdotti;
    }

    public int getCodiceCarrello() {
        return codiceCarrello;
    }

    public void setCodiceCarrello(int codiceCarrello) {
        this.codiceCarrello = codiceCarrello;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public void addProdotto(Prodotto p ){
        prodotti.add(p);
        totalePrezzo+=p.getPrezzo();
    }

    public int getTotaleProdotti() {
        return totaleProdotti;
    }

    public void setTotaleProdotti(int totaleProdotti) {
        this.totaleProdotti = totaleProdotti;
    }

    public double getTotalePrezzo() {
        return totalePrezzo;
    }

    public void sottraiTotale(double prezzo){
        this.totalePrezzo-=prezzo;
    }
    public void setTotalePrezzo(int totalePrezzo) {
        this.totalePrezzo = totalePrezzo;
    }

    public StringBuilder prodottiToString(){
        prodottiCarrello=new StringBuilder();
        for(Prodotto p : prodotti){
            prodottiCarrello.append(p.getNome() + ",");
        }
        System.out.println("Lista " + prodottiCarrello.toString() );
        return prodottiCarrello;
    }

    public void setListaProdottiString(String lista){

        prodottiCarrello=new StringBuilder(lista);

    }

    public StringBuilder getProdottiCarrello() {
        return prodottiCarrello;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrello carrello = (Carrello) o;
        return codiceCarrello == carrello.codiceCarrello;
    }


}
