package model.marchio;

import model.storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class MarchioDAO implements MarchioDAOMethod {
    @Override
    public Marchio cercaMarchio(String nomeMarchio) {
        try(Connection connection= ConPool.getConnection()){

            PreparedStatement ps;
            ps=connection.prepareStatement("select * from Marchio where nomeMarchio=? ");
            ps.setString(1, nomeMarchio);

            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                Marchio marchio = new Marchio();
                marchio.setNomeMarchio(rs.getString(1));
                return marchio;
            }
        }catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
        return null;
    }

    @Override
    public void deleteMarchio(String nomeMarchio) {
        try(Connection connection=ConPool.getConnection()){
            PreparedStatement ps;
            ps=connection.prepareStatement("delete from Marchio where nomeMarchio=?");
            ps.setString(1, nomeMarchio);
            ps.execute();
        }catch (SQLException sqlException){

        }
    }

    @Override
    public void insertMarchio(Marchio m) {

        try(Connection connection=ConPool.getConnection()){

            PreparedStatement ps= connection.prepareStatement("insert into Marchio value (?)");
            ps.setString(1, m.getNomeMarchio());

            ps.execute();

        }catch (SQLException sqlException){
            throw new RuntimeException("insert error");
        }
    }

    @Override
    public void updateMarchio(Marchio m, String nomeMarchio) {
        // Metodo inutile per 'marchio', siccome sono tutti attributi non modificabili
    }

    @Override
    public ArrayList<Marchio> doRetraiveByAllMarchi() {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("select * from Marchio");
            ResultSet rs = ps.executeQuery();
            ArrayList<Marchio> lista = new ArrayList<>();
            while (rs.next()) {
                Marchio marchio=new Marchio();
                marchio.setNomeMarchio(rs.getString(1));
                lista.add(marchio);
            }
            connection.close();
            return lista;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public ArrayList<Marchio> cercaMarchi(int start, int end) {
        ArrayList<Marchio> lista =new ArrayList<>();
        try(Connection connection=ConPool.getConnection()){

            PreparedStatement ps=connection.prepareStatement("select * from Marchio order by nomeMarchio" +
                    "limit ? offset ?");
            ps.setInt(1,start);
            ps.setInt(2,end);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                Marchio marchio=new Marchio();
                marchio.setNomeMarchio(rs.getString(1));
                lista.add(marchio);
            }
            connection.close();
            return lista;
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }
}


