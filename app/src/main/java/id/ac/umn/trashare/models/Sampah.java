package id.ac.umn.trashare.models;

import java.io.Serializable;

public class Sampah implements Serializable {
    public long hargaBeliLapak;
    public long hargaBeliNasabah;
    public long idSampah;
    public String namaSampah;
    public String tipeSampah;

    public Sampah(long hargaBeliLapak, long hargaBeliNasabah, String namaSampah, String tipeSampah) {
        this.hargaBeliLapak = hargaBeliLapak;
        this.hargaBeliNasabah = hargaBeliNasabah;
        this.namaSampah = namaSampah;
        this.tipeSampah = tipeSampah;
    }
}
