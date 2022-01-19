package model.marchio;

import model.utils.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Generated
public class MarchioDAO implements MarchioDAOMethod {
    private ConPool conpool=ConPool.getInstance();
    private Connection connection= conpool.getConnection();

    public MarchioDAO() throws SQLException {
    }

    /**
     * Questo metodo retituisce un oggetto di tipo Marchio dato il parametro nomeMarchio
     * @param nomeMarchio è un oggetto di tipo stringa
     * @return un oggetto di tipo Marchio
     */
    @Override
    public Marchio cercaMarchio(String nomeMarchio) {
        try{

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

    /*
    @Override
    public void deleteMarchio(String nomeMarchio) {
        try{
            PreparedStatement ps;
            ps=connection.prepareStatement("delete from Marchio where nomeMarchio=?");
            ps.setString(1, nomeMarchio);
            ps.execute();
        }catch (SQLException sqlException){

        }
    }

     */
/*
    @Override
    public void insertMarchio(Marchio m) {

        try{

            PreparedStatement ps= connection.prepareStatement("insert into Marchio value (?)");
            ps.setString(1, m.getNomeMarchio());

            ps.execute();

        }catch (SQLException sqlException){
            throw new RuntimeException("insert error");
        }
    }
*/
    /*
    @Override
    public void updateMarchio(Marchio m, String nomeMarchio) {
        // Metodo inutile per 'marchio', siccome sono tutti attributi non modificabili
    }
*/

    /**
     * Questo metodo retituisce una lista di tutti i Marchi
     * @return una lista di tutti i marchi
     */
    @Override
    public ArrayList<Marchio> doRetraiveByAllMarchi() {
        try {
            PreparedStatement ps;
            ps = connection.prepareStatement("select * from Marchio");
            ResultSet rs = ps.executeQuery();
            ArrayList<Marchio> lista = new ArrayList<>();
            while (rs.next()) {
                Marchio marchio=new Marchio();
                marchio.setNomeMarchio(rs.getString(1));
                lista.add(marchio);
            }

            return lista;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    /*
    @Override
    public ArrayList<Marchio> cercaMarchi(int start, int end) {
        ArrayList<Marchio> lista =new ArrayList<>();
        try{

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

            return lista;
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }*/
}


