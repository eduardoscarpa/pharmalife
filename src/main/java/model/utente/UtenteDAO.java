package model.utente;

import model.categoria.Categoria;
import model.marchio.Marchio;
import model.messaggio.Messaggio;
import model.ordine.Ordine;
import model.prodotto.Prodotto;
import model.storage.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class UtenteDAO implements UtenteDAOMethod {

    /**
     * Questo metodo serve a ricercare un utente nel datbase in base al suo codice fiscale
     * @param codiceFiscale
     * @return un oggetto di tipo Utente
     */
    @Override
    public Utente cercaUtente(String codiceFiscale) {
        try(Connection connection= ConPool.getConnection()){

            PreparedStatement ps;
            ps=connection.prepareStatement("select * from Utente where codiceFiscale=?");
            ps.setString(1, codiceFiscale);

            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                Utente utente= new Utente();
                utente.setCodiceFiscale(rs.getString(1));
                utente.setNome(rs.getString(2));
                utente.setCognome(rs.getString(3));
                utente.setEmail(rs.getString(4));
                utente.setPassword(rs.getString(5));
                utente.setVia(rs.getString(6));
                utente.setNumeroCivico(rs.getInt(7));
                utente.setCap(rs.getString(8));
                utente.setTelefono(rs.getString(9));
                utente.setAdmin(rs.getBoolean(10));
                return utente;
            }
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return null;
    }


    /**
     * Questo utente cerca un utente nel database in base all'email e la password,
     * paramentri necessari per effettuare l'accesso alla piattaforma
     * @param email di un utente
     * @param password d un utente
     * @return un oggetto di tipo Utente
     */
    @Override
    public Utente cercaUtentebyEmail(String email,String password) {
        try(Connection connection= ConPool.getConnection()){

            PreparedStatement ps;
            ps=connection.prepareStatement("select * from Utente where email=? and pass=SHA1(?)");
            ps.setString(1, email);
            ps.setString(2,password);

            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                Utente utente= new Utente();
                utente.setCodiceFiscale(rs.getString(1));
                utente.setNome(rs.getString(2));
                utente.setCognome(rs.getString(3));
                utente.setEmail(rs.getString(4));
                utente.criptPassword(rs.getString(5));
                utente.setVia(rs.getString(6));
                utente.setNumeroCivico(rs.getInt(7));
                utente.setCap(rs.getString(8));
                utente.setTelefono(rs.getString(9));
                utente.setAdmin(rs.getBoolean(10));
                return utente;
            }
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return null;
    }

    /**
     *
     * @param codiceFiscale
     * @return
     */
    @Override
    public ArrayList<Prodotto> preferiti(String codiceFiscale) {
        ArrayList<Prodotto> prodottiPreferiti= new ArrayList<>();
        try(Connection connection=ConPool.getConnection()){

            PreparedStatement ps=connection.prepareStatement("select p.codiceProdotto, ut.codiceFiscale ,p.nome ,p.prezzo,p.nomeMarchio,p.quantita,p.idCategoria,p.pathImmagine,p.descrizione " +
                    "from Prodotto p,Preferito pr,Utente ut where pr.codiceFiscale=ut.codiceFiscale and pr.codiceProdotto=p.codiceProdotto and ut.codiceFiscale=?");
            ps.setString(1,codiceFiscale);


            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Prodotto p= new Prodotto();

                p.setCodiceProdotto(rs.getInt("codiceProdotto"));
                p.setNome(rs.getString("nome"));
                p.setPrezzo(rs.getDouble("prezzo"));
                Marchio marchio=new Marchio();
                marchio.setNomeMarchio(rs.getString("nomeMarchio"));
                p.setMarchio(marchio);
                p.setQuantita(rs.getInt("quantita"));
                Categoria categoria=new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                p.setCategoria(categoria);
                p.setPathImmagine(rs.getString("pathImmagine"));
                prodottiPreferiti.add(p);
            }
        }catch (SQLException sqlException){

            throw new RuntimeException(sqlException);
        }

        return prodottiPreferiti;
    }

    /**
     * Questo metodo cerca tutti i codici fiscali di tutti gli utenti iscritti alla piattaforma
     * @return un ArrayList di String con tutti i codici fiscali
     * @throws SQLException
     */
    @Override
    public ArrayList<String> doRetraiveByAllCodiciFiscali() throws SQLException {
        ArrayList<String> codiciFiscali = new ArrayList<>();
        try (Connection connection = ConPool.getConnection()) {

            PreparedStatement ps=connection.prepareStatement("select codiceFiscale  from Utente");
            ResultSet resultSet= ps.executeQuery();
            while (resultSet.next()){
                codiciFiscali.add(resultSet.getString("codiceFiscale"));
            }



        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return codiciFiscali;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Ordine> ordiniAllUtenti() {
        ArrayList<Ordine> ordini= new ArrayList<>();
        try(Connection connection=ConPool.getConnection()){

            PreparedStatement ps=connection.prepareStatement("select * from Ordine o , Utente u  " +
                    "where o.cfUtente=u.codiceFiscale;");
           ResultSet rs=ps.executeQuery();
           while (rs.next()){
               Ordine o = new Ordine();
               o.setIdOrdine(rs.getInt(1));
               o.setDataOrdine(rs.getDate(2));
               o.setOra(rs.getTime(3));
               o.getUtente().setCodiceFiscale(rs.getString(4));
               o.getUtente().setNome(rs.getString(6));
               o.getUtente().setCognome(rs.getString(7));
               o.getUtente().setEmail(rs.getString(8));
               o.getUtente().setVia(rs.getString(10));
               o.getUtente().setNumeroCivico(rs.getInt(11));
               o.getUtente().setCap(rs.getString(12));
               o.getUtente().setTelefono(rs.getString(13));
               o.getUtente().setAdmin(rs.getBoolean(14));

               ordini.add(o);
           }

        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return ordini;
    }

    /**
     *Questo metodo serve  a cercare tutti i messaggi inviati da tutti gli utenti ad un amministratore
     * @return ArrayList di Messaggi
     */
    @Override
    public ArrayList<Messaggio> messaggiAllUtenti() {
        ArrayList<Messaggio> messaggi= new ArrayList<>();
        try(Connection connection=ConPool.getConnection()){

            PreparedStatement ps=connection.prepareStatement("select * from Messaggio m , Utente u  " +
                    "where m.cf=u.codiceFiscale;");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Messaggio m = new Messaggio();
                m.setCodiceMessaggio(rs.getInt(1));
                m.setTesto(rs.getString(2));
                m.setData(rs.getDate(3));
                m.setOra(rs.getTime(4));
                m.getUtente().setCodiceFiscale(rs.getString(5));
                m.getUtente().setNome(rs.getString(7));
                m.getUtente().setCognome(rs.getString(8));
                m.getUtente().setEmail(rs.getString(9));
                m.getUtente().setAdmin(rs.getBoolean(10));

                messaggi.add(m);
            }

        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return messaggi;
    }


    /*@Override
    public void deleteUtente(String codiceFiscale) {
        try(Connection connection=ConPool.getConnection()){
            PreparedStatement ps;
            ps=connection.prepareStatement("delete from Utente where codiceFiscale=?");
            ps.setString(1, codiceFiscale);
            ps.execute();
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }*/

    /**
     * Questo metodo inserisce un nuovo utente nel database ,
     * @param utente da iscrivire alla piattaforma
     */
    @Override
    public void insertUtente(Utente utente) {
        try(Connection connection=ConPool.getConnection()){

            PreparedStatement ps= connection.prepareStatement("insert into Utente value (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, utente.getCodiceFiscale());
            ps.setString(2, utente.getNome());
            ps.setString(3, utente.getCognome());
            ps.setString(4, utente.getEmail());
            ps.setString(5, utente.getPassword());
            ps.setString(6, utente.getVia());
            ps.setInt(7, utente.getNumeroCivico());
            ps.setString(8, utente.getCap());
            ps.setString(9, utente.getTelefono());
            ps.setBoolean(10, utente.isAdmin());
            ps.execute();

        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }


    /**
     *
     * @param utente
     * @param prodotto
     */
    @Override
    public void insertPreferito(Utente utente, Prodotto prodotto) {
        try(Connection connection=ConPool.getConnection()){
            PreparedStatement ps= connection.prepareStatement("insert into Preferito value (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,utente.getCodiceFiscale());
            ps.setInt(2, prodotto.getCodiceProdotto());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        }catch (SQLException sqlException){
            throw new RuntimeException("insert error");
        }
    }

    @Override
    public void deletePreferito(Utente utente, Prodotto prodotto) {
        try(Connection connection=ConPool.getConnection()){
            PreparedStatement ps;
            ps=connection.prepareStatement("delete from Preferito where codiceFiscale=? and codiceProdotto=?");
            ps.setString(1, utente.getCodiceFiscale());
            ps.setInt(2,prodotto.getCodiceProdotto());
            ps.execute();
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }


    /**
     * Questo metodo aggiorna le credenziali di un utente
     * @param utente è l'utente che richiede di aggiornare le proprie credenziali
     * @return true se l'aggiornamento delle credenziali va a buon fine , altrimenti false
     */
    @Override
    public boolean updateUtente(Utente utente){
        try(Connection connection=ConPool.getConnection()){
            PreparedStatement ps=connection.prepareStatement("update Utente set nome = ? ,cognome = ? ," +
                    "email = ? ,pass = ? where codiceFiscale=?");
            ps.setString(5,utente.getCodiceFiscale());
            ps.setString(1,utente.getNome());
            ps.setString(2,utente.getCognome());
            ps.setString(3,utente.getEmail());
            ps.setString(4,utente.getPassword());
            System.out.println("Password " + utente.getPassword());
            if(ps.executeUpdate()!=1){
                //throw  new RuntimeException("Errore Update");
                return  false;
            }
            return  true;
        }catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
    }

    /**
     * Questo metodo aggiorna l'indirizzo di spedizione di un utente
     * @param utente che richiede di aggiornare il proprio indirizzo di pedizione
     * @return true se l'aggiornamento dell'indirizzo di spedizione  va a buon fine , altrimenti false
     */
    @Override
    public boolean updateIndirizzoUtente(Utente utente) {

        try(Connection connection=ConPool.getConnection()){
            PreparedStatement ps;
            ps=connection.prepareStatement("update Utente set via = ? , numeroCivico = ? , cap = ? where codiceFiscale = ? ");
            ps.setString(4, utente.getCodiceFiscale());
            ps.setString(1, utente.getVia());
            ps.setInt(2,utente.getNumeroCivico());
            ps.setString(3, utente.getCap());
            if(ps.executeUpdate()!=1){
                return false;
               // throw new RuntimeException("Errore Update");
            }

        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return  true;
    }


    /**
     * Questo metodo cerca tutti gli utenti registrati alla piattaforma
     * @return ArrayList di Utenti
     */
    @Override
    public ArrayList<Utente> doRetraiveByAllUtenti() {
        ArrayList<Utente> lista = new ArrayList<>();
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("select * from Utente");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               Utente utente=new Utente();
               utente.setCodiceFiscale(rs.getString(1));
               utente.setNome(rs.getString(2));
               utente.setCognome(rs.getString(3));
               utente.setEmail(rs.getString(4));
               utente.setPassword(rs.getString(5));
               utente.setVia(rs.getString(6));
               utente.setNumeroCivico(rs.getInt(7));
               utente.setCap(rs.getString(8));
               utente.setTelefono(rs.getString(9));
               utente.setAdmin(rs.getBoolean(10));
               lista.add(utente);
            }
            connection.close();
            return lista;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    /** non serve
    @Override
    public ArrayList<Utente> cercaUtenti(int start, int end) {
        ArrayList<Utente> lista =new ArrayList<>();
        try(Connection connection=ConPool.getConnection()){
            PreparedStatement ps=connection.prepareStatement("select * from Utente order by codiceProdotto" +
                    "limit ? offset ?");
            ps.setInt(1,start);
            ps.setInt(2,end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Utente utente=new Utente();
                utente.setCodiceFiscale(rs.getString(1));
                utente.setNome(rs.getString(2));
                utente.setCognome(rs.getString(3));
                utente.setEmail(rs.getString(4));
                utente.setPassword(rs.getString(5));
                utente.setVia(rs.getString(6));
                utente.setNumeroCivico(rs.getInt(7));
                utente.setCap(rs.getString(8));
                utente.setTelefono(rs.getString(9));
                utente.setAdmin(rs.getBoolean(10));
                lista.add(utente);
            }
            connection.close();
            return lista;
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }**/
}
