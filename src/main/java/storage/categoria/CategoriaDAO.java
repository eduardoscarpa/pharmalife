package storage.categoria;

import storage.utils.ConPool;

import java.sql.*;
import java.util.ArrayList;


@Generated
public class CategoriaDAO implements CategoriaDAOMethod {

    private ConPool conpool=ConPool.getInstance();
    private Connection connection= conpool.getConnection();

    public CategoriaDAO() throws SQLException {
    }

    /**
     * Questo metodo retituisce una Categoria in base ad un nome
     * @param nome è un oggetto di tipo stringa
     * @return un oggetto di tipo Categoria
     */
    @Override
    public Categoria cercaCategoria(String nome) {

        try{

            PreparedStatement ps;
            ps=connection.prepareStatement("select * from Categoria where nomeCategoria=?");
            ps.setString(1,nome);

            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                Categoria categoria= new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setNomeCategoria(rs.getString(2));
                categoria.setRoot(rs.getInt(3));
                return categoria;
            }
        }catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
        return null;
    }


    /**
     * Questo metodo retituisce una Categoria in base ad un id
     * @param id è un oggetto che serve per identificare la categoria
     * @return un oggetto di tipo Categoria
     */
    @Override
    public Categoria cercaCategoriaById(int id) {
        try{
            PreparedStatement ps=connection.prepareStatement("select * from Categoria where idCategoria=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Categoria categoria= new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNomeCategoria(rs.getString("nomeCategoria"));
                categoria.setRoot(rs.getInt("root"));
                return  categoria;
            }

        }catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
        return  null;
    }

    /**
     * Questo metodo permette di visualizzare tutte le categorie di prodotti presenti sulla piattaforma
     * @return ArrayList di Oggetti di tipo Categoria
     */
    @Override
    public ArrayList<Categoria> doRetraiveByAllCategorie() {
        try {
            PreparedStatement ps;
            ps = connection.prepareStatement("select * from Categoria");
            ResultSet rs = ps.executeQuery();
            ArrayList<Categoria> lista = new ArrayList<>();
            while (rs.next()) {
                Categoria categoria=new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setNomeCategoria(rs.getString(2));
                categoria.setRoot(rs.getInt(3));
                lista.add(categoria);
            }

            return lista;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }


    /**
     * Questo metodo retituisce tutte le Categorie
     * @throws 'sqlException'
     * @return un ArrayList di Oggetti di tipo Categoria
     */
    @Override
    public ArrayList<Categoria> doRetraiveByAllCategorieRoot() {
        try{
            PreparedStatement ps=connection.prepareStatement("select  * from Categoria where root=0");
            ResultSet rs=ps.executeQuery();
            ArrayList<Categoria> categorie= new ArrayList<>();
            while (rs.next()){
                Categoria categoria= new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setNomeCategoria(rs.getString(2));
                categoria.setRoot(rs.getInt(3));
                categorie.add(categoria);
            }

            return categorie;

        }catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
    }

}
