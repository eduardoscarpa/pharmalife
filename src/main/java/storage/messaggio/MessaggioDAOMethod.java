package storage.messaggio;

import java.util.ArrayList;

public interface MessaggioDAOMethod {


    public void insertMessaggio(Messaggio  m);
    public ArrayList<Messaggio> doRetrieveByAllMessaggi();

}
