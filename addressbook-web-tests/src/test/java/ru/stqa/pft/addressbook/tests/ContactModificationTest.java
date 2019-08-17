package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactInformation;

public class ContactModificationTest extends TestBase {
    @Test
    public void testContactModification(){
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactInformation("Gintoki", "Sakata", "Gin-san", "Chief", "Yorozuya", "Kabuki-cho", "Tokyo", "111", "Yorozuya", "1111", "gin-san@yandex.ru", "test"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillNewContact(new ContactInformation("Gintoki", "Sakata", "Gin-san", "Danna", "Yorozuya", "Kabuki-cho", "Tokyo", "111", "Yorozuya", "1111", "gin-san@yandex.ru",  null), false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
    }
}
