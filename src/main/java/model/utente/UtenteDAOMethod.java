package model.utente;

import model.messaggio.Messaggio;
import model.ordine.Ordine;
import model.prodotto.Prodotto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface UtenteDAOMethod {

    public Utente cercaUtente (String codiceFiscale);
    public Utente cercaUtentebyEmail (String email,String password);
    public ArrayList<Prodotto> doRetrieveByAllPreferitiOfUtente(String codiceFiscale);
    public ArrayList<String> doRetraiveByAllCodiciFiscali() throws SQLException;
    public ArrayList<String> doRetraiveByAllEmail() throws SQLException;
    public ArrayList<Ordine> ordiniAllUtenti();
    public ArrayList<Messaggio> messaggiAllUtenti();
    public void insertUtente (Utente u);
    public void insertPreferito(Utente utente,Prodotto prodotto);
    public void deletePreferito(Utente utente,Prodotto prodotto);
    public boolean updateUtente(Utente utente);
    public boolean updateIndirizzoUtente(Utente utente);
    public ArrayList<Utente> doRetrieveByAllUtenti();
}
