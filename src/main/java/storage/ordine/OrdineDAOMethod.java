package storage.ordine;

import storage.utente.Utente;

import java.util.ArrayList;

public interface OrdineDAOMethod {

    public Ordine cercaOrdine(int idOrdine);
   public void deleteOrdine(int idOrdine);
    public void insertCarrello(Ordine o);
    public void updateOrdine(Ordine o, int idOrdine);
    public ArrayList<Ordine> doRetraiveByAllOrdini();
    public ArrayList<Ordine> cercaOrdini(int start, int end);
    public ArrayList<Ordine> doRetraiveByAllById(Utente utente);



}
