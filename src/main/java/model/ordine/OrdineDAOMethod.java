package model.ordine;

import model.prodotto.Prodotto;
import model.utente.Utente;

import java.util.ArrayList;
import java.util.Optional;

public interface OrdineDAOMethod {

    public Ordine cercaOrdine(int idOrdine);
    public void deleteOrdine(int idOrdine);
    public void insertCarrello(Ordine o);
    public void updateOrdine(Ordine o, int idOrdine);
    public ArrayList<Ordine> doRetraiveByAllOrdini();
    public ArrayList<Ordine> cercaOrdini(int start, int end);
    public ArrayList<Ordine> doRetraiveByAllById(Utente utente);



}
