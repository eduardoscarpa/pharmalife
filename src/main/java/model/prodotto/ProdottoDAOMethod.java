package model.prodotto;

import java.util.ArrayList;

public interface ProdottoDAOMethod {
    public Prodotto cercaProdotto(int codiceProdotto);
    public Prodotto cercaProdottoByNome(String nomeprodotto);
    public ArrayList<Prodotto> cercaProdottiMarchio(String nomeMarchio,int start,int end);
    public ArrayList<Prodotto> prodottoSearch(String start);
    public void deleteProdotto(int codiceProdotto);
    public void insertProdotto(Prodotto p);
    public void updateProdotto(Prodotto p);

    public ArrayList<Prodotto> doRetrieveByAllProdotti();
    public ArrayList<Prodotto> doRetraiveByAllProdottiByMarchio(String nomeMarchio);
    public ArrayList<Prodotto> cercaProdottiRoot(int root);
    public ArrayList<Prodotto> cercaProdotti(int root,int start, int end);

    ArrayList<Prodotto> FiltroNome(ArrayList<Prodotto> prodotti, String nome);
    ArrayList<Prodotto> FiltroCategoria(ArrayList<Prodotto> prodotti, String categoria);
    ArrayList<Prodotto> FiltroMarchio(ArrayList<Prodotto> prodotti, String marchio);
    ArrayList<Prodotto> FiltroMax(ArrayList<Prodotto> prodotti, double max);
    ArrayList<Prodotto> FiltroMin(ArrayList<Prodotto> prodotti, double min);

    ArrayList<Prodotto> doRetraiveByAllProdotti();
}