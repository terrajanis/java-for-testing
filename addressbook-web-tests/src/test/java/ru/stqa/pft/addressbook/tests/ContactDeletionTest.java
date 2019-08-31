package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactInformation;
import ru.stqa.pft.addressbook.models.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.getContactHelper().set().size() == 0) {
            app.getContactHelper().create(new ContactInformation().
                    withFirstname("Gintoki").
                    withLastname("Sakata").
                    withNickname("Gin-san").
                    withTitle("Chief").
                    withCompany("Yorozuya").
                    withAddress("Kabuki-cho").
                    withHome("Tokyo").
                    withMobile("111").
                    withWork("Yorozuya").
                    withFax("1111").
                    withEmail("gin-san@yandex.ru").
                    withGroup("test"));
        }
    }

    @Test
    public void testContactDeletion(){
        Contacts before = app.getContactHelper().set();
       ContactInformation deletedContact = before.iterator().next();
        app.getContactHelper().selectById(deletedContact.getId());
        app.getContactHelper().pressDeleteButton();
        app.wd.switchTo().alert().accept();
        Contacts after = app.getContactHelper().set();
        assertThat(after.size(), equalTo(before.size() - 1));
       assertThat(before.without(deletedContact), equalTo(after));
    }
}
