package model.utente;

import model.carrello.Carrello;
import model.messaggio.Messaggio;
import model.ordine.Ordine;
import model.prodotto.Prodotto;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

public class Utente {

    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String via;
    private int numeroCivico;
    private String cap;
    private String telefono;
    private boolean isAdmin;
    private Carrello carrello;
    private ArrayList<Prodotto> prodotti;
    private ArrayList<Messaggio> messaggi;
    private ArrayList<Ordine> ordini;

    public Utente() {
    }

    public Utente(String codiceFiscale, String nome, String cognome, String email, String password,
                  String via, int numeroCivico, String cap, String telefono, boolean isAdmin, ArrayList<Prodotto> prodotti) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.via = via;
        this.numeroCivico = numeroCivico;
        this.cap = cap;
        this.telefono = telefono;
        this.isAdmin = isAdmin;
        this.prodotti = prodotti;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }
    public void criptPassword(String password) {

      try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.reset();
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            password = String.format("%020x", new
                    BigInteger(1, messageDigest.digest()));
            setPassword(password);
        } catch (NoSuchAlgorithmException s) {
            throw new RuntimeException(s);
        }
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(int numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public ArrayList<Messaggio> getMessaggi() {
        return messaggi;
    }

    public void setMessaggi(ArrayList<Messaggio> messaggi) {
        this.messaggi = messaggi;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public ArrayList<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(ArrayList<Ordine> ordini) {
        this.ordini = ordini;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return Objects.equals(codiceFiscale, utente.codiceFiscale);
    }


}
