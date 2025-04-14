package com.example.pcontact;

public class AllContact{
    String cName,cEmail,cPhone;
    int id;

    public AllContact(String cName, int id, String cPhone, String cEmail) {
        this.cName = cName;
        this.id = id;
        this.cPhone = cPhone;
        this.cEmail = cEmail;
    }

    public String getcName() {
        return cName;
    }

    public int getId() {
        return id;
    }

    public String getcPhone() {
        return cPhone;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }
}
