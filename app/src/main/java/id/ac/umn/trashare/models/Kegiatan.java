package id.ac.umn.trashare.models;

import java.io.Serializable;

public class Kegiatan implements Serializable {
    public String deskripsiKegiatan;
    public long idKegiatan;
    public String namaKegiatan;
    public String tanggalKegiatan;
    public String fotoKegiatan;

    public Kegiatan(String namaKegiatan,String deskripsiKegiatan,String tempatEvent,String tanggalKegiatan){
        this.namaKegiatan = namaKegiatan;
        this.deskripsiKegiatan = deskripsiKegiatan;
        this.tanggalKegiatan = tanggalKegiatan;
    }
}