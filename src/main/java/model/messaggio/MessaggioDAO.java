package model.messaggio;

import model.storage.ConPool;
import model.utente.Utente;
import model.utente.UtenteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

@Generated
public class MessaggioDAO implements MessaggioDAOMethod {


    /**
     * Questo metodo permette ad un logic.utente di inviare i messaggi all'amministratore
     * @param messaggio oggetto da inserire nel dastabase
     */
    public void insertMessaggio(Messaggio messaggio) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement
                    ("insert into Messaggio(testo,dataMessaggio,ora,cf) " +
                            "value (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, messaggio.getTesto());
            ps.setDate(2, (Date) messaggio.getData());
            ps.setTime(3, messaggio.getOra());
            ps.setString(4, messaggio.getUtente().getCodiceFiscale());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            messaggio.setCodiceMessaggio(id);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }



    /**
     * Questo metodo restituisce la litsa di tutti i messaggi inviati da tutti gli utenti
     * @return ArrayList di oggetti di tipo Messaggio
     */
    public ArrayList<Messaggio> doRetrieveByAllMessaggi() {
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

  
}