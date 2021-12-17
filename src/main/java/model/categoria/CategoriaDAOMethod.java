package model.categoria;

import java.util.ArrayList;
import java.util.Optional;

public interface CategoriaDAOMethod {

    public Categoria cercaCategoria(String nome);
    public Categoria cercaCategoriaById(int id);
    public void deleteCategoria(int idCategoria);
    public void insertCategoria(Categoria c);
    public void updateCategoria(Categoria c, int idCategoria);
    public ArrayList<Categoria> doRetraiveByAllCategorie();
    public ArrayList<Categoria> doRetraiveByAllCategorieRoot();
    public ArrayList<Categoria> cercaCategorie(int start,int end);

}
