package ru.stqa.pft.addressbook.models;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactInformation {
    @XStreamOmitField
    private int id;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String nickname;
    @Expose
    private String title;
    @Expose
    private String company;
    @Expose
    private String address;
    @Expose
    private String home;
    @Expose
    private String mobile;
    @Expose
    private String work;
    @Expose
    private String fax;
    @Expose
    private String email;
    @Expose
    private String email2;
    @Expose
    private String email3;
    @XStreamOmitField
    private String group;
    @XStreamOmitField
    private String allPhones;
    @XStreamOmitField
    private String allEmails;
    @XStreamOmitField
    private File photo;


    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public File getPhoto() {
        return photo;
    }

    public ContactInformation withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public ContactInformation withId(int id) {
        this.id = id;
        return this;
    }

    public ContactInformation withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactInformation withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactInformation withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactInformation withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactInformation withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactInformation withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactInformation withHome(String home) {
        this.home = home;
        return this;
    }

    public ContactInformation withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactInformation withWork(String work) {
        this.work = work;
        return this;
    }

    public ContactInformation withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactInformation withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactInformation withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactInformation withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactInformation withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactInformation withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactInformation withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    @Override
    public String toString() {
        return "ContactInformation{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInformation that = (ContactInformation) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}
