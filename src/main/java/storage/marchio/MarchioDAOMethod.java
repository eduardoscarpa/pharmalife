package storage.marchio;

import java.util.ArrayList;

public interface MarchioDAOMethod {

    public Marchio cercaMarchio(String nomeMarchio);
    public ArrayList<Marchio> doRetraiveByAllMarchi();
}

