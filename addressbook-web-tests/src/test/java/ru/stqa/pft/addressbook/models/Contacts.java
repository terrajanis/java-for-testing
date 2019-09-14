package ru.stqa.pft.addressbook.models;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactInformation> {

    private Set<ContactInformation> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactInformation>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactInformation>();
    }

    public Contacts(Collection<ContactInformation> contacts) {
        this.delegate = new HashSet<ContactInformation>(contacts);
    }

    @Override
    protected Set<ContactInformation> delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactInformation contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without(ContactInformation contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }

}
