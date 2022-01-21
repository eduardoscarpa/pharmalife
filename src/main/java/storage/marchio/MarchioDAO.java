package storage.marchio;

import storage.utils.ConPool;

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

}


