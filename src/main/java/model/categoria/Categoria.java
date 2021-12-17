package model.categoria;

import model.prodotto.Prodotto;

import java.util.ArrayList;
import java.util.Objects;

public class Categoria {

    private int idCategoria;
    private String nomeCategoria;
    private int root;
    private ArrayList<Prodotto> prodotti;

    public Categoria() {
    }

    public Categoria(int idCategoria, String nomeCategoria, int root, ArrayList<Prodotto> prodotti) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.root = root;
        this.prodotti = prodotti;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return idCategoria == categoria.idCategoria;
    }


}
