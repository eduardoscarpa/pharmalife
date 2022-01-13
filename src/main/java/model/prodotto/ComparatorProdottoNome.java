package model.prodotto;

import java.util.Comparator;

@Generated
public class ComparatorProdottoNome implements Comparator<Prodotto> {
    @Override
    public int compare(Prodotto o1, Prodotto o2) {
        return o1.getNome().compareToIgnoreCase(o2.getNome());
    }


}