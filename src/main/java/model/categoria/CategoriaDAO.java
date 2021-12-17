package model.categoria;

import model.storage.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class CategoriaDAO implements CategoriaDAOMethod {
    @Override
    public Categoria cercaCategoria(String nome) {

        try(Connection connection= ConPool.getConnection()){

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

    @Override
    public Categoria cercaCategoriaById(int id) {
        try(Connection connection=ConPool.getConnection()) {
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

    @Override
    public void deleteCategoria(int idCategoria) {

        try(Connection connection=ConPool.getConnection()){
            PreparedStatement ps;
            ps=connection.prepareStatement("delete from Categoria where idCategoria=?");
            ps.setInt(1,idCategoria);
            ps.execute();
        }catch (SQLException sqlException){
            throw new RuntimeException("delete error");
        }
    }

    @Override
    public void insertCategoria(Categoria c) {

        try(Connection connection=ConPool.getConnection()){

            PreparedStatement ps= connection.prepareStatement("insert into Categoria value (?,?,?)");
            ps.setInt(1,c.getIdCategoria());
            ps.setString(2,c.getNomeCategoria());
            ps.setInt(3,c.getRoot());

            ps.execute();

        }catch (SQLException sqlException){
            throw new RuntimeException("insert error");
        }
    }

    @Override
    public void updateCategoria(Categoria c, int idCategoria) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("update Categoria set nomeCategoria = ?, root = ?" +
                    "where idCategoria = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getNomeCategoria());
            ps.setInt(2, c.getRoot());
            ps.setInt(3, c.getIdCategoria());
            if(ps.executeUpdate() != 1) {
                throw new RuntimeException("update error");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public ArrayList<Categoria> doRetraiveByAllCategorie() {
        try (Connection connection = ConPool.getConnection()) {
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
            connection.close();
            return lista;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public ArrayList<Categoria> doRetraiveByAllCategorieRoot() {
        try(Connection connection=ConPool.getConnection()){
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
            connection.close();
            return categorie;

        }catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
    }

    @Override
    public ArrayList<Categoria> cercaCategorie(int start, int end) {
        ArrayList<Categoria> lista =new ArrayList<>();
        try(Connection connection=ConPool.getConnection()){

            PreparedStatement ps=connection.prepareStatement("select * from Categoria order by idCategoria" +
                    "limit ? offset ?");
            ps.setInt(1,start);
            ps.setInt(2,end);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                Categoria categoria=new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setNomeCategoria(rs.getString(2));
                categoria.setRoot(rs.getInt(3));
                lista.add(categoria);
            }
            connection.close();
            return lista;
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }
}
