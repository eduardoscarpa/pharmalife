package model.messaggio;

import model.storage.ConPool;
import model.utente.Utente;
import model.utente.UtenteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
public class MessaggioDAO implements MessaggioDAOMethod {
    @Override
    public Optional<Messaggio> cercaMessaggio(int codiceMessaggio) {
        try(Connection connection= ConPool.getConnection()){
            PreparedStatement ps;
            ps=connection.prepareStatement("select * from Messaggio where codiceMessaggio=? ");
            ps.setInt(1,codiceMessaggio);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                Messaggio messaggio = new Messaggio();
                messaggio.setCodiceMessaggio(rs.getInt(1));
                messaggio.setTesto(rs.getString(2));
                messaggio.setData(rs.getDate(3));
                messaggio.setOra(rs.getTime (4));
                messaggio.getUtente().setCodiceFiscale(rs.getString(5));
                return Optional.of(messaggio);
            }
        }catch (SQLException sqlException){
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<Messaggio> cercaMessaggioById(Utente u) {
        ArrayList<Messaggio> messaggi= new ArrayList<>();
        try(Connection connection=ConPool.getConnection()){

            PreparedStatement ps=connection.prepareStatement("select * from Messaggio where cf=? ");
            ps.setString(1,u.getCodiceFiscale());
            ResultSet rs=ps.executeQuery();
            while (rs.next()){

                Messaggio m= new Messaggio();
                m.setCodiceMessaggio(rs.getInt(1));
                m.setTesto(rs.getString(2));
                m.setData(rs.getDate(3));
                m.setOra(rs.getTime(4));
                m.getUtente().setCodiceFiscale(rs.getString(5));
                messaggi.add(m);
            }
        }catch (SQLException sqlException){

            throw new RuntimeException(sqlException);
        }
        return messaggi;
    }


    public void deleteMessaggio(int codiceMessaggio) {
        try(Connection connection=ConPool.getConnection()){
            PreparedStatement ps;
            ps=connection.prepareStatement("delete from Messaggio where codiceMessaggio=?");
            ps.setInt(1,codiceMessaggio);
            ps.execute();
        }catch (SQLException sqlException){
            throw new RuntimeException("delete error");
        }
    }
    public void insertMessaggio(Messaggio m) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement
                    ("insert into Messaggio(testo,dataMessaggio,ora,cf) " +
                            "value (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getTesto());
            ps.setDate(2, (Date) m.getData());
            ps.setTime(3, m.getOra());
            ps.setString(4, m.getUtente().getCodiceFiscale());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            m.setCodiceMessaggio(id);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }


    /*
    @Override

    public void insertMessaggio(Messaggio m) {
        try(Connection connection=ConPool.getConnection()){
            //PreparedStatement ps= connection.prepareStatement("insert into Messaggio value (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement ps= connection.prepareStatement
                    ("insert into Messaggio(codiceMessaggio,testo,dataMessaggio,ora,cf) " +
                            "value (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,m.getTesto());
            ps.setDate(2, (Date) m.getData());
            ps.setTime(3,m.getOra());
            //Utente utente = new Utente();
            //utente.setCodiceFiscale(m.getUtente().getCodiceFiscale());
            ps.setString(4, m.getUtente().getCodiceFiscale());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            m.setCodiceMessaggio(id);
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }

     */
    //@Override
    public void updateMessaggio(Messaggio m, int codiceMessaggio){
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("update Messaggio set testo = ?, dataMessaggio = ?, ora = ?, cf = ?" +
                    "where codiceMessaggio = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getTesto());
            ps.setDate(2, (Date) m.getData());
            ps.setTime(3, m.getOra());
            ps.setString(4, m.getUtente().getCodiceFiscale());
            ps.setInt(5, m.getCodiceMessaggio());
            if(ps.executeUpdate() != 1) {
                throw new RuntimeException("update error");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public ArrayList<Messaggio> doRetraiveByAllMessaggi() {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("select * from Messaggio");
            ResultSet rs = ps.executeQuery();
            ArrayList<Messaggio> lista = new ArrayList<>();
            while (rs.next()) {
                Messaggio messaggio=new Messaggio();
                messaggio.setCodiceMessaggio(rs.getInt("codiceMessaggio"));
                messaggio.setTesto(rs.getString("testo"));
                messaggio.setData(rs.getDate("dataMessaggio"));
                messaggio.setOra(rs.getTime("ora"));
                UtenteDAO utenteDAO= new UtenteDAO();
                Utente utente= utenteDAO.cercaUtente(rs.getString("cf"));
                messaggio.setUtente(utente);
              //  messaggio.getUtente().setCodiceFiscale(rs.getString(5));
                lista.add(messaggio);
            }
            connection.close();
            return lista;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public ArrayList<Messaggio> cercaMessaggi(int start, int end) {
        ArrayList<Messaggio> lista =new ArrayList<>();
        try(Connection connection=ConPool.getConnection()){

            PreparedStatement ps=connection.prepareStatement("select * from Messaggio order by codiceMessaggio" +
                    "limit ? offset ?");
            ps.setInt(1,start);
            ps.setInt(2,end);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                Messaggio messaggio=new Messaggio();
                messaggio.setCodiceMessaggio(rs.getInt(1));
                messaggio.setTesto(rs.getString(2));
                messaggio.setData(rs.getDate(3));
                messaggio.setOra(rs.getTime(4));
                messaggio.getUtente().setCodiceFiscale(rs.getString(5));
                lista.add(messaggio);
            }
            connection.close();
            return lista;
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }
}