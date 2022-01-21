package storage.utente;

import storage.messaggio.Messaggio;
import storage.ordine.Ordine;
import storage.prodotto.Prodotto;

import java.sql.SQLException;
import java.util.ArrayList;

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
