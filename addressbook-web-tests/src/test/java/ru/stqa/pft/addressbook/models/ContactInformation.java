package ru.stqa.pft.addressbook.models;

import java.util.Objects;

public class ContactInformation {

    private int id;
    private String firstname;
    private String lastname;
    private String nickname;
    private String title;
    private String company;
    private String address;
    private String home;
    private String mobile;
    private String work;
    private String fax;
    private String email;
    private String group;

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
