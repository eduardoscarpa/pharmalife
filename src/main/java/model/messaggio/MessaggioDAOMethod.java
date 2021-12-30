package model.messaggio;

import model.utente.Utente;

import java.util.ArrayList;
import java.util.Optional;

public interface MessaggioDAOMethod {

   // public Optional<Messaggio> cercaMessaggio(int codiceMessaggio);
   // public ArrayList<Messaggio> cercaMessaggioById(Utente u);
   // public void deleteMessaggio(int codiceMessaggio);
    public void insertMessaggio(Messaggio  m);
   // public void updateMessaggio(Messaggio m, int codiceMessaggio);
    public ArrayList<Messaggio> doRetrieveByAllMessaggi();
    //public ArrayList<Messaggio> cercaMessaggi(int start, int end);
}
