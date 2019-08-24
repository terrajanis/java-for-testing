package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactInformation;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {
    @Test
    public void testContactModification(){
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactInformation("Gintoki", "Sakata", "Gin-san", "Chief", "Yorozuya", "Kabuki-cho", "Tokyo", "111", "Yorozuya", "1111", "gin-san@yandex.ru", "test"));
        }
        List<ContactInformation> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification();
        ContactInformation contact = new ContactInformation(before.get(before.size() - 1).getId(),"Kotaro", "Sakata", "Gin-san", "Danna", "Yorozuya", "Kabuki-cho", "Tokyo", "111", "Yorozuya", "1111", "gin-san@yandex.ru",  null);
        app.getContactHelper().fillNewContact(contact, false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
        List<ContactInformation> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactInformation> byID = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before, after);
    }
}
