package model.marchio;

import model.prodotto.Prodotto;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@interface Generated {
}

@Generated
public class Marchio {

    private String nomeMarchio;
    private ArrayList<Prodotto> prodotti;

    public Marchio() {
    }


    public Marchio(String nomeMarchio, ArrayList<Prodotto> prodotti) {
        this.nomeMarchio = nomeMarchio;
        this.prodotti = prodotti;
    }

    public String getNomeMarchio() {
        return nomeMarchio;
    }

    public void setNomeMarchio(String nomeMarchio) {
        this.nomeMarchio = nomeMarchio;
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marchio marchio = (Marchio) o;
        return Objects.equals(nomeMarchio, marchio.nomeMarchio);
    }


}
