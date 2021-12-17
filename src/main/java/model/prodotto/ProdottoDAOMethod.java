package model.prodotto;

import model.marchio.Marchio;
import model.utente.Utente;

import java.util.ArrayList;

public interface ProdottoDAOMethod {
    public Prodotto cercaProdotto(int codiceProdotto);
    public Prodotto cercaProdottoByNome(String nomeprodotto);
    public ArrayList<Prodotto> cercaProdottiMarchio(String nomeMarchio,int start,int end);
    public ArrayList<Prodotto> prodotttoSearch(String start);
    public void deleteProdotto(int codiceProdotto);
    public void insertProdotto(Prodotto p);
    public void updateProdotto(Prodotto p);

    public ArrayList<Prodotto> doRetraiveByAllProdotti();
    public ArrayList<Prodotto> doRetraiveByAllProdottiByMarchio(String nomeMarchio);
    public ArrayList<Prodotto> cercaProdottiRoot(int root);
    public ArrayList<Prodotto> cercaProdotti(int root,int start, int end);
}