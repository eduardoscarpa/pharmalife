package model.prodotto;

import model.categoria.Categoria;
import model.categoria.CategoriaDAO;
import model.marchio.Marchio;
import model.storage.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

@Generated
public class ProdottoDAO implements ProdottoDAOMethod {

    /**
     * Questo metodo ricerca un determinato prodotto in base al suo codice identificativo
     * @param codiceProdotto identificativo del prodotto
     * @return un oggetto di tipo Prodotto
     */
    public Prodotto cercaProdotto(int codiceProdotto) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("select * from Prodotto where codiceProdotto=?");
            preparedStatement.setInt(1, codiceProdotto);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Prodotto prodotto = new Prodotto();
                prodotto.setCodiceProdotto(resultSet.getInt("codiceProdotto"));

                prodotto.setNome(resultSet.getString("nome"));
                prodotto.setPrezzo(resultSet.getInt("prezzo"));
                Marchio m = new Marchio();
                m.setNomeMarchio(resultSet.getString("nomeMarchio"));
                prodotto.setMarchio(m);

                prodotto.setQuantita(resultSet.getInt("quantita"));
                CategoriaDAO categoriaDAO= new CategoriaDAO();
                Categoria c = categoriaDAO.cercaCategoriaById(resultSet.getInt("idCategoria"));

                prodotto.setCategoria(c);
                prodotto.setPathImmagine(resultSet.getString("pathImmagine"));
                prodotto.setDescrrizione(resultSet.getString("descrizione"));
                return prodotto;
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
        return null;
    }

    /**
     * Questo metodo un prodotto in base al nome
     * @param nomeprodotto
     * @return un oggetto di tipo Prodotto
     */
    @Override
    public Prodotto cercaProdottoByNome(String nomeprodotto) {
        try(Connection connection=ConPool.getConnection()){
            PreparedStatement ps=connection.prepareStatement("select * from prodotto where nome = ?");
            ps.setString(1,nomeprodotto);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()){
                Prodotto prodotto= new Prodotto();
                prodotto.setCodiceProdotto(resultSet.getInt("codiceProdotto"));
                prodotto.setNome(resultSet.getString("nome"));
                prodotto.setPrezzo(resultSet.getInt("prezzo"));
                Marchio m = new Marchio();
                m.setNomeMarchio(resultSet.getString("nomeMarchio"));
                prodotto.setMarchio(m);

                prodotto.setQuantita(resultSet.getInt("quantita"));
                CategoriaDAO categoriaDAO= new CategoriaDAO();
                Categoria c = categoriaDAO.cercaCategoriaById(resultSet.getInt("idCategoria"));

                prodotto.setCategoria(c);
                prodotto.setPathImmagine(resultSet.getString("pathImmagine"));
                prodotto.setDescrrizione(resultSet.getString("descrizione"));
                return  prodotto;
            }

        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return null;
    }

    /**
     * Questo metodo elimina un prodotto dal logic.catalogo
     * @param codiceProdotto del prodotto da eliminare
     */
    @Override
    public void deleteProdotto(int codiceProdotto) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("delete from Prodotto where codiceProdotto=?");
            ps.setInt(1, codiceProdotto);
            ps.execute();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    /**
     * Questo metodo aggiunge un prodotto al logic.catalogo
     * @param prodotto oggetto di tipo prodotto da aggiungere al logic.catalogo
     */
    @Override
    public void insertProdotto(Prodotto prodotto) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement
                    ("insert into Prodotto(nome,prezzo,nomeMarchio,quantita,idCategoria,pathImmagine,descrizione) " +
                            "value (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, prodotto.getNome());
            ps.setDouble(2, prodotto.getPrezzo());
            ps.setString(3, prodotto.getMarchio().getNomeMarchio());
            ps.setInt(4, prodotto.getQuantita());
            ps.setInt(5, prodotto.getCategoria().getIdCategoria());
            ps.setString(6, prodotto.getPathImmagine());
            ps.setString(7, prodotto.getDescrrizione());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            prodotto.setCodiceProdotto(id);
            //ps.execute();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

    }


    @Override
    /*public void updateProdotto(Prodotto p, int codiceProdotto) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("update Messaggio set codiceCarrello = ?, nome = ?, prezzo = ?," +
                    "nomeMarchio = ?, quantita = ?, idCategoria = ?" +
                    "where codiceProdotto = ?", Statement.RETURN_GENERATED_KEYS);

            // ps.setString(2, p.getUtente().getCodiceFiscale()); NON PUOI E' PRIMARY KEY
            ps.setString(2, p.getNome());
            ps.setDouble(3, p.getPrezzo());
            ps.setString(4, p.getMarchio().getNomeMarchio());
            ps.setInt(5, p.getQuantita());
            ps.setInt(6, p.getCategoria().getIdCategoria());
            ps.setInt(7, p.getCodiceProdotto());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("update error");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }*/

    /**
     * Questo metodo aggiorna  il nome  e il prezzo di un prodotto
     */
    public void updateProdotto(Prodotto p) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("update Prodotto set nome = ?, prezzo = ?"+
                    "where codiceProdotto = ?");
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPrezzo());
            ps.setInt(3, p.getCodiceProdotto());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("update error");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }



   /* @Override
    public void aggiungiUtente(int codiceProdotto,Utente logic.utente) {
        try(Connection connection=ConPool.getConnection()){
            ProdottoDAO prodottoDAO= new ProdottoDAO();
            Optional<Prodotto> prodotto=prodottoDAO.cercaProdotto(codiceProdotto);
            if(prodotto.isPresent()){

            }



        }catch (SQLException sqlException){

            throw new RuntimeException(sqlException);
        }
    }*/

    /**
     * Questo metodo restituisce la lista di tutti i prodotti presenti nel logic.catalogo
     * @return ArrayList di oggetti di tipo Prodotto
     */
    @Override
    public ArrayList<Prodotto> doRetrieveByAllProdotti() {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("select * from Prodotto");
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> lista = new ArrayList<>();
            while (rs.next()) {
                Prodotto prodotto = new Prodotto();
                prodotto.setCodiceProdotto(rs.getInt("codiceProdotto"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getDouble("prezzo"));
                Marchio m= new Marchio();
                m.setNomeMarchio(rs.getString("nomeMarchio"));
                prodotto.setMarchio(m);
                prodotto.setQuantita(rs.getInt("quantita"));
                CategoriaDAO categoriaDAO= new CategoriaDAO();
                Categoria categoria=categoriaDAO.cercaCategoriaById(rs.getInt("idCategoria"));
              //  categoria.setIdCategoria(rs.getInt("idCategoria"));
                prodotto.setCategoria(categoria);
                prodotto.setPathImmagine(rs.getString("pathImmagine"));
                prodotto.setDescrrizione(rs.getString("descrizione"));
                lista.add(prodotto);

            }
            connection.close();
            return lista;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

    }

    /**
     * Questo metodo retituisce la lista di tutti i prodotti con un determinato marchio
     * @param nomeMarchio del prodotto
     * @return ArrayList di oggetti di tipo Prodotto
     */
    @Override
    public ArrayList<Prodotto> doRetraiveByAllProdottiByMarchio(String nomeMarchio) {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("select distinct * from Prodotto where nomeMarchio = ?");
            ps.setString(1, nomeMarchio);
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> lista = new ArrayList<>();
            while (rs.next()) {
                Prodotto prodotto = new Prodotto();
                prodotto.setCodiceProdotto(rs.getInt(1));

                // prodotto.getUtente().setCodiceFiscale(rs.getString(3)); NULL
                prodotto.setNome(rs.getString(4));
                prodotto.setPrezzo(rs.getInt(5));
                prodotto.getMarchio().setNomeMarchio(rs.getString(6));
                prodotto.setQuantita(rs.getInt(7));
                prodotto.getCategoria().setIdCategoria(rs.getInt(8));
                lista.add(prodotto);
            }
            connection.close();
            return lista;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    /**
     *
     * @param root è la macrocategoria
     * @return ArrayList di oggtti di tipo Prodottto
     */
    @Override
    public ArrayList<Prodotto> cercaProdottiRoot(int root) {
        try (Connection connection = ConPool.getConnection()) {
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            PreparedStatement ps;
            if (root == 2 || root == 3) {
                ps = connection.prepareStatement("select p.codiceProdotto,p.nome,p.prezzo,p.nomeMarchio,p.quantita,p.idCategoria,p.pathImmagine,p.descrizione" +
                        " from Prodotto p where p.idCategoria=?;");
                ps.setInt(1, root);
            } else {
                ps = connection.prepareStatement("select p.codiceProdotto,p.nome,p.prezzo,p.nomeMarchio,p.quantita," +
                        "p.idCategoria,p.pathImmagine,p.descrizione " +
                        "from Prodotto p,Categoria c where p.idCategoria=c.idCategoria and ?= c.root;");
                ps.setInt(1, root);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setCodiceProdotto(rs.getInt("codiceProdotto"));
                p.setNome(rs.getString("nome"));
                p.setPrezzo(rs.getDouble("prezzo"));
                Marchio m = new Marchio();
                m.setNomeMarchio(rs.getString("nomeMarchio"));
                p.setMarchio(m);
                p.setQuantita(rs.getInt("quantita"));
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("idCategoria"));
                p.setCategoria(c);
                p.setPathImmagine(rs.getString("pathImmagine"));
                p.setDescrrizione(rs.getString("descrizione"));
                prodotti.add(p);
            }
            return prodotti;

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

    }
    /**
     * Questo metodo retituisce la lista di prodotti di una determinata categoria
     * @param root è la macrocategoria,
     * @param start è il numero di partenza della lista dei prodotti
     * @param end il numero di arrivo della lista dei prodotti
     * @return ArrayList di oggetti di tipo Prodotto
     */
   @Override
    public ArrayList<Prodotto> cercaProdotti(int root, int start, int end) {
        try (Connection connection = ConPool.getConnection()) {
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            PreparedStatement ps;
            if (root == 2 || root == 3) {
                ps = connection.prepareStatement("select p.codiceProdotto,p.nome,p.prezzo,p.nomeMarchio,p.quantita,p.idCategoria,p.pathImmagine,p.descrizione" +
                        " from Prodotto p where p.idCategoria=? limit ?,?;");
                ps.setInt(1, root);
                ps.setInt(2, start);
                ps.setInt(3, end);
            } else {
                ps = connection.prepareStatement("select p.codiceProdotto,p.nome,p.prezzo,p.nomeMarchio,p.quantita," +
                        "p.idCategoria,p.pathImmagine,p.descrizione " +
                        "from Prodotto p,Categoria c where p.idCategoria=c.idCategoria and ?= c.root limit ?,?;");
                ps.setInt(1, root);
                ps.setInt(2, start);
                ps.setInt(3, end);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setCodiceProdotto(rs.getInt("codiceProdotto"));
                p.setNome(rs.getString("nome"));
                p.setPrezzo(rs.getDouble("prezzo"));
                Marchio m = new Marchio();
                m.setNomeMarchio(rs.getString("nomeMarchio"));
                p.setMarchio(m);
                p.setQuantita(rs.getInt("quantita"));
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("idCategoria"));
                p.setCategoria(c);
                p.setPathImmagine(rs.getString("pathImmagine"));
                p.setDescrrizione(rs.getString("descrizione"));
                prodotti.add(p);
            }
            return prodotti;

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    /**
     * Questo metodo retituisce la lista di prodotti di una determinato marchio
     * @param nomeMarchio è il nome del marchio
     * @param start è il numero di partenza della lista dei prodotti
     * @param end il numero di arrivo della lista dei prodotti
     * @return ArrayList di oggetti di tipo Prodotto
     */

    public ArrayList<Prodotto> cercaProdottiMarchio(String nomeMarchio, int start, int end) {
        try (Connection connection = ConPool.getConnection()) {
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("select p.codiceProdotto,p.nome,p.prezzo,p.nomeMarchio,p.quantita," +
                    "p.idCategoria,p.pathImmagine,p.descrizione " +
                    "from Prodotto p,Marchio m where p.nomeMarchio=m.nomeMarchio and ?= m.nomeMarchio limit ?,?;");
            ps.setString(1, nomeMarchio);
            ps.setInt(2, start);
            ps.setInt(3, end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setCodiceProdotto(rs.getInt("codiceProdotto"));
                p.setNome(rs.getString("nome"));
                p.setPrezzo(rs.getDouble("prezzo"));
                Marchio m = new Marchio();
                m.setNomeMarchio(rs.getString("nomeMarchio"));
                p.setMarchio(m);
                p.setQuantita(rs.getInt("quantita"));
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("idCategoria"));
                p.setCategoria(c);
                p.setPathImmagine(rs.getString("pathImmagine"));
                p.setDescrrizione(rs.getString("descrizione"));
                prodotti.add(p);
            }
            return prodotti;

        } catch(SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }

    /**
     * Questo metodo retituisce la lista di prodotti
     * @param start è il numero di partenza del prodotto da ricercare
     * @return ArrayList di oggetti di tipo Prodotto
     */

    @Override
    public ArrayList<Prodotto> prodottoSearch(String start) {
        try(Connection connection=ConPool.getConnection()){
            PreparedStatement ps=connection.prepareStatement("select  * from Prodotto where nome like ?");
            ps.setString(1, start + "%");
            ArrayList<Prodotto> prodotti= new ArrayList<>();
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Prodotto p = new Prodotto();
                p.setCodiceProdotto(rs.getInt("codiceProdotto"));
                p.setNome(rs.getString("nome"));
                p.setPrezzo(rs.getDouble("prezzo"));
                Marchio m = new Marchio();
                m.setNomeMarchio(rs.getString("nomeMarchio"));
                p.setMarchio(m);
                p.setQuantita(rs.getInt("quantita"));
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("idCategoria"));
                p.setCategoria(c);
                p.setPathImmagine(rs.getString("pathImmagine"));
                p.setDescrrizione(rs.getString("descrizione"));

                prodotti.add(p);
            }
            return prodotti;
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }

    }



    /**
     * Questo metodo retituisce la lista di prodotti filtrata per nome
     * @param prodotti è la lista dei prodotti da filtrare,
     * @param nome è il parametro secondo il quale filtriamo la lista
     * @return ArrayList di oggetti di tipo Prodotto con lo stesso nome del paramentro "nome"
     */
    public ArrayList<Prodotto> FiltroNome(ArrayList<Prodotto> prodotti,String nome){
        ArrayList<Prodotto> prodotti2=new ArrayList<>();
        for (Prodotto p :prodotti){
            if(p.getNome().contains(nome)) {
                prodotti2.add(p);
            }
        }
        return prodotti2;
    }

    /**
     * Questo metodo retituisce la lista di prodotti filtrata per categoria
     * @param prodotti è la lista dei prodotti da filtrare
     * @param nomeCategoria è il parametro secondo il quale filtriamo la lista
     * @return ArrayList di oggetti di tipo Prodotto con la stessa categoria del paramentro "categoria"
     */
    public ArrayList<Prodotto> FiltroCategoria(ArrayList<Prodotto> prodotti,String nomeCategoria){
        ArrayList<Prodotto> prodotti2=new ArrayList<>();
        for (Prodotto p :prodotti){
            if(p.getCategoria().getNomeCategoria().contains(nomeCategoria)) {
                prodotti2.add(p);
            }
        }
        return prodotti2;
    }

    /**
     * Questo metodo retituisce la lista di prodotti filtrata per il costo minimo
     * @param prodotti è la lista dei prodotti da filtrare,
     * @param min è il valore minimo di prezzo che la lista di prodotti deve rispettare
     * @return ArrayList di oggetti di tipo Prodotto con il prezzo minimo pari a min
     */
    public ArrayList<Prodotto> FiltroMin(ArrayList<Prodotto> prodotti,double min){
        ArrayList<Prodotto> prodotti2=new ArrayList<>();
        for (Prodotto p :prodotti){
            if(p.getPrezzo()>=min) {
                prodotti2.add(p);
            }
        }
        return prodotti2;
    }

    @Override
    public ArrayList<Prodotto> doRetraiveByAllProdotti() {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps;
            ps = connection.prepareStatement("select * from Prodotto");
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> lista = new ArrayList<>();
            while (rs.next()) {
                Prodotto prodotto = new Prodotto();
                prodotto.setCodiceProdotto(rs.getInt("codiceProdotto"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getDouble("prezzo"));
                Marchio m= new Marchio();
                m.setNomeMarchio(rs.getString("nomeMarchio"));
                prodotto.setMarchio(m);
                prodotto.setQuantita(rs.getInt("quantita"));
                CategoriaDAO categoriaDAO= new CategoriaDAO();
                Categoria categoria=categoriaDAO.cercaCategoriaById(rs.getInt("idCategoria"));
                //  categoria.setIdCategoria(rs.getInt("idCategoria"));
                prodotto.setCategoria(categoria);
                prodotto.setPathImmagine(rs.getString("pathImmagine"));
                prodotto.setDescrrizione(rs.getString("descrizione"));
                lista.add(prodotto);

            }
            connection.close();
            return lista;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    /**
     * Questo metodo retituisce la lista di prodotti filtrata per il costo massimo
     * @param prodotti è la lista dei prodotti da filtrare
     * @param max è il valore massimo di prezzo che la lista di prodotti deve rispettare
     * @return ArrayList di oggetti di tipo Prodotto con il prezzo massimo pari a max
     */
    public ArrayList<Prodotto> FiltroMax(ArrayList<Prodotto> prodotti,double max){
        ArrayList<Prodotto> prodotti2=new ArrayList<>();
        for (Prodotto p :prodotti){
            if(p.getPrezzo()<=max) {
                prodotti2.add(p);
            }
        }
        return prodotti2;
    }

    /**
     * Questo metodo retituisce la lista di prodotti filtrata per il nome marchio
     * @param prodotti è la lista dei prodotti da filtrare,
     * @param nomeMarchio è il parametro da rispettare per filtrare la lista
     * @return ArrayList di oggetti di tipo Prodotto con il nome Marchio pari al valore di "nomeMarchio"
     */
    public ArrayList<Prodotto> FiltroMarchio(ArrayList<Prodotto> prodotti,String nomeMarchio){
        ArrayList<Prodotto> prodotti2=new ArrayList<>();
        for (Prodotto p :prodotti){
            if(p.getMarchio().getNomeMarchio().contains(nomeMarchio)) {
                prodotti2.add(p);
            }
        }
        return prodotti2;
    }

 /*  public ArrayList<Prodotto> OrdinaDallaAallaZ(ArrayList<Prodotto> prodotti){
        Collections.sort(prodotti,new ComparatorProdottoNome());
        return prodotti;
    }

    public ArrayList<Prodotto> OrdinaDalMenoCaroAlPiuCaro(ArrayList<Prodotto> prodotti){
        Collections.sort(prodotti,new ComparatorProdottoPrezzo());
        return prodotti;
    }*/
}
