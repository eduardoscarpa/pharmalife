package model.marchio;

import java.util.ArrayList;

public interface MarchioDAOMethod {

    public Marchio cercaMarchio(String nomeMarchio);
   // public void deleteMarchio(String nomeMarchio);
   // public void insertMarchio(Marchio m);
   // public void updateMarchio(Marchio m, String nomeMarchio);
    public ArrayList<Marchio> doRetraiveByAllMarchi();
    //public ArrayList<Marchio> cercaMarchi(int start, int end);
}

