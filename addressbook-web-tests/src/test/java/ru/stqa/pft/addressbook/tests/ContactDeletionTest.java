package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactInformation;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion(){
        if(! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactInformation("Gintoki", "Sakata", "Gin-san", "Chief", "Yorozuya", "Kabuki-cho", "Tokyo", "111", "Yorozuya", "1111", "gin-san@yandex.ru", "test"));
        }
        List<ContactInformation> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().pressDeleteButton();
        app.wd.switchTo().alert().accept();
        List<ContactInformation> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
