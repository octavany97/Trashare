package id.ac.umn.trashare.models;

import java.io.Serializable;

public class Member implements Serializable {

    public String alamat;
    public String email;
    public String fotoIdentitas;
    public String fotoProfil;
    public long idMember;
    public String namaLengkap;
    public String noTelp;
    public String password;
    public long poin;
    public long saldo;
    public String salt;
    public String sessionToken;

    public Member(String alamat, String email, String fotoIdentitas, String fotoProfil, String namaLengkap, String noTelp, String password, String salt) {
        this.alamat = alamat;
        this.email = email;
        this.fotoIdentitas = fotoIdentitas;
        this.fotoProfil = fotoProfil;
        this.namaLengkap = namaLengkap;
        this.noTelp = noTelp;
        this.password = password;
        this.poin = 0;
        this.saldo = 0;
        this.salt = salt;
    }

    public Member(String email, String namaLengkap, String password) {
        this.email = email;
        this.namaLengkap = namaLengkap;
        this.password = password;
        this.poin = 0;
        this.saldo = 0;
    }
}
