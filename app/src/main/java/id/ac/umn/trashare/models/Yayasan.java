package id.ac.umn.trashare.models;

import java.io.Serializable;
import java.util.List;

public class Yayasan implements Serializable {

    public String alamat;
    public String deskripsiBankSampah;
    public String email;
    public String fotoProfil;
    public long idBankSampah;
    public List<Kegiatan> kegiatans = null;
    public List<Member> members = null;
    public String namaBankSampah;
    public String namaKetua;
    public String noTelp;
    public String password;
    public String salt;
    public String sessionToken;
    public String wilayah;
}
