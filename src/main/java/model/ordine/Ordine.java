package model.ordine;

import model.carrello.Carrello;
import model.utente.Utente;

import java.sql.Time;
import java.sql.Date;
import java.util.Objects;

public class Ordine {
    private int idOrdine;
    private Date dataOrdine;
    private Time ora;
    private Utente utente;
    private Carrello carrello;



    public Ordine() {
    }

    public Ordine(int idOrdine, Date dataOrdine, Time ora, Utente utente, Carrello carrello) {
        this.idOrdine = idOrdine;
        this.dataOrdine = dataOrdine;
        this.ora = ora;
        this.utente = utente;
        this.carrello = carrello;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Date getDataOrdine() {
        return  dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Time getOra() {
        return ora;
    }

    public void setOra(Time ora) {
        this.ora = ora;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

 //   public ArrayList<Carrello> getCarrelli() {
  //      return carrelli;
   // }

    //public void setCarrelli(ArrayList<Carrello> carrelli) {
      //  this.carrelli = carrelli;
    //}


    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordine ordine = (Ordine) o;
        return Objects.equals(idOrdine, ordine.idOrdine);
    }


}
