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

    public ArrayList<Prodotto> filtroNome(ArrayList<Prodotto> prodotti, String nome);
    public ArrayList<Prodotto> filtroCategoria(ArrayList<Prodotto> prodotti, String categoria);
    public ArrayList<Prodotto> filtroMarchio(ArrayList<Prodotto> prodotti, String marchio);
    public ArrayList<Prodotto> filtroMax(ArrayList<Prodotto> prodotti, double max);
    public ArrayList<Prodotto> filtroMin(ArrayList<Prodotto> prodotti, double min);

    public ArrayList<Prodotto> doRetraiveByAllProdotti();
}