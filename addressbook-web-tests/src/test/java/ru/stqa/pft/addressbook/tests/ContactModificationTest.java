package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactInformation;

public class ContactModificationTest extends TestBase {
    @Test
    public void testContactModification(){
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillNewContact(new ContactInformation("Gintoki", "Sakata", "Gin-san", "Danna", "Yorozuya", "Kabuki-cho", "Tokyo", "111", "Yorozuya", "1111", "gin-san@yandex.ru", "kagura-chan@yandex.ru", "shimpachi@yandex.ru", "Tokyo. Kabuki-cho", "This"));
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
    }
}
