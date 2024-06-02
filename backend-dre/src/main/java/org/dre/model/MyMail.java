package org.dre.model;

public class MyMail {
    private String username;
    private String email;

    private String roleAuteur;

    private String reference;



    public String getRoleAuteur() {
        return roleAuteur;
    }

    public void setRoleAuteur(String roleAuteur) {
        this.roleAuteur = roleAuteur;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
