package model.prodotto;

import java.util.Comparator;

public class ComparatorProdottoPrezzo implements Comparator<Prodotto> {
    @Override
    public int compare(Prodotto o1, Prodotto o2) {
        if(o1.getPrezzo()>o2.getPrezzo())
            return 1;
        else if(o1.getPrezzo()<o2.getPrezzo())
            return -1;
        else
            return 0;
    }
}
