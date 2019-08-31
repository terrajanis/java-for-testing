package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactInformation;
import ru.stqa.pft.addressbook.models.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

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
    public void testContactModification(){

        Contacts before = app.getContactHelper().set();
        ContactInformation modifiedContact = before.iterator().next();
        app.getContactHelper().initModificationById(modifiedContact.getId());
        ContactInformation contact = new ContactInformation().
                withId(modifiedContact.getId()).
                withFirstname("Kotaro").
                withLastname("Katsura").
                withNickname("Zura").
                withTitle("Leader").
                withCompany("Joui").
                withAddress("Kabuki-cho").
                withHome("Tokyo").
                withMobile("111").
                withWork("Joui").
                withFax("1111").
                withEmail("elizabeth@yandex.ru");
        app.getContactHelper().fillNewContact(contact, false);
        app.getContactHelper().update();
        app.getNavigationHelper().goToHomePage();
        Contacts after = app.getContactHelper().set();
        assertThat(after.size(), equalTo(before.size()));

        assertThat(before.withAdded(contact).without(modifiedContact), equalTo(after));
    }
}
