package model.marchio;

import model.prodotto.Prodotto;

import java.util.ArrayList;
import java.util.Optional;

public interface MarchioDAOMethod {

    public Marchio cercaMarchio(String nomeMarchio);
    public void deleteMarchio(String nomeMarchio);
    public void insertMarchio(Marchio m);
    public void updateMarchio(Marchio m, String nomeMarchio);
    public ArrayList<Marchio> doRetraiveByAllMarchi();
    public ArrayList<Marchio> cercaMarchi(int start, int end);
}

