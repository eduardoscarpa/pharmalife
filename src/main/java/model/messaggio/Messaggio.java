package model.messaggio;

import model.utente.Utente;

import java.sql.Time;
import java.util.Date;

public class Messaggio {
    private int codiceMessaggio;
    private String testo;
    private Date data;
    private Time ora;
    private Utente utente;

    public Messaggio() {
    }

    public Messaggio(int codiceMessaggio, String testo, Date data, Time ora, Utente utente) {
        this.codiceMessaggio = codiceMessaggio;
        this.testo = testo;
        this.data = data;
        this.ora = ora;
        this.utente=utente;
    }

    public int getCodiceMessaggio() {
        return codiceMessaggio;
    }

    public void setCodiceMessaggio(int codiceMessaggio) {
        this.codiceMessaggio = codiceMessaggio;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
}

