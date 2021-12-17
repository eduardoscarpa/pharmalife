package model.utente;

import model.messaggio.Messaggio;
import model.ordine.Ordine;
import model.prodotto.Prodotto;

import java.util.ArrayList;
import java.util.Optional;

public interface UtenteDAOMethod {

    public Utente cercaUtente (String codiceFiscale);
    public Utente cercaUtentebyEmail (String email,String password);
    public ArrayList<Prodotto> preferiti(String codiceFiscale);
    public ArrayList<Ordine> ordiniAllUtenti();
    public ArrayList<Messaggio> messaggiAllUtenti();
    public void deleteUtente (String codiceFiscale);
    public void insertUtente (Utente u);
    public void insertPreferito(Utente utente,Prodotto prodotto);
    public void deletePreferito(Utente utente,Prodotto prodotto);
   // public void updateUtente (Utente u, String codiceFiscale);
    public boolean updateUtente(Utente utente);
    public boolean updateIndirizzoUtente(Utente utente);
    public ArrayList<Utente> doRetraiveByAllUtenti();
    public ArrayList<Utente> cercaUtenti(int start, int end);
}
