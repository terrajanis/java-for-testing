package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactInformation;

import static org.testng.Assert.assertTrue;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion(){
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactInformation("Gintoki", "Sakata", "Gin-san", "Chief", "Yorozuya", "Kabuki-cho", "Tokyo", "111", "Yorozuya", "1111", "gin-san@yandex.ru", "test"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().pressDeleteButton();
        app.wd.switchTo().alert().accept();
    }
}
