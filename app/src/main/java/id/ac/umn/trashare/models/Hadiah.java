package id.ac.umn.trashare.models;

import java.io.Serializable;

public class Hadiah implements Serializable {
    public String deskripsiHadiah;
    public String fotoHadiah;
    public long idHadiah;
    public String namaHadiah;
    public String periodeTukar;
    public long poin;
    public String sponsor;

    public Hadiah(String deskripsiHadiah, String fotoHadiah, String namaHadiah, String periodeTukar, long poin, String sponsor) {
        this.deskripsiHadiah = deskripsiHadiah;
        this.fotoHadiah = fotoHadiah;
        this.namaHadiah = namaHadiah;
        this.periodeTukar = periodeTukar;
        this.poin = poin;
        this.sponsor = sponsor;
    }
}
