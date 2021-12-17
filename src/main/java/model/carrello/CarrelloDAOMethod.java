package model.carrello;

import model.utente.Utente;

import java.util.ArrayList;

public interface CarrelloDAOMethod {

    public Carrello cercaCarrello(int codiceCarrello);
    public void deleteCarrello(int codiceCarrello);
    public void insertCarrello(Carrello c);
    public void updateCarrello(Carrello c, int codiceCarrello);
    public Carrello cercaCarrelloByUtente(Utente utente);
    public ArrayList<Carrello> doRetraiveByAllCarrelli();
    public ArrayList<Carrello> cercaCarrelli(int start,int end);
}
