package ru.stqa.pft.addressbook.models;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactInformation {
    @XStreamOmitField
    @Id
    @Column(name="id")
    private int id;
    @Expose
    @Column(name="firstname")
    private String firstname;
    @Expose
    @Column(name="lastname")
    private String lastname;
    @Expose
    @Column(name="nickname")
    private String nickname;
    @Expose
    @Column(name="title")
    private String title;
    @Expose
    @Column(name="company")
    private String company;
    @Expose
    @Column(name="address")
    @Type(type = "text")
    private String address;
    @Expose
    @Column(name="home")
    @Type(type = "text")
    private String home;
    @Expose
    @Column(name="mobile")
    @Type(type = "text")
    private String mobile;
    @Expose
    @Column(name="work")
    @Type(type = "text")
    private String work;
    @Expose
    @Column(name="fax")
    @Type(type = "text")
    private String fax;
    @Expose
    @Column(name="email")
    @Type(type = "text")
    private String email;
    @Expose
    @Column(name="email2")
    @Type(type = "text")
    private String email2;
    @Expose
    @Column(name="email3")
    @Type(type = "text")
    private String email3;
    @XStreamOmitField
    @Transient
    private String group;
    @XStreamOmitField
    @Transient
    private String allPhones;
    @XStreamOmitField
    @Transient
    private String allEmails;
    @XStreamOmitField
    @Column(name="photo")
    @Type(type = "text")
    private String photo;


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
        return new File(photo);
    }

    public ContactInformation withPhoto(File photo) {
        this.photo = photo.getPath();
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
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(title, that.title) &&
                Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, nickname, title, company);
    }
}
