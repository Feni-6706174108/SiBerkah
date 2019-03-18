package org.d3ifcool.siberkah;

public class UserInformation {
    private int id;
    private String namaUser;
    private String email;
    private String passUser;
    private String noKtp;

    public UserInformation(int id, String namaUser, String email, String passUser, String noKtp) {
        this.id = id;
        this.namaUser = namaUser;
        this.email = email;
        this.passUser = passUser;
        this.noKtp = noKtp;
    }

    public UserInformation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }
}
