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
        if(app.getDbHelper().contacts().size() == 0) {
            app.getContactHelper().create(new ContactInformation().
                    withFirstname("Gintoki").
                    withLastname("Sakata").
                    withNickname("Gin-san").
                    withTitle("Chief").
                    withCompany("Yorozuya").
                    withAddress("Japan" + "\n" + "Tokyo" + "\n" + "Kabuki-cho").
                    withHome("4444").
                    withMobile("111").
                    withWork("321").
                    withFax("1111").
                    withEmail("gin-san@yandex.ru").
                    withEmail2("kagura-chan@yandex.ru").
                    withEmail3("shinpachi@yandex.ru").
                    withGroup("test"));
        }
    }

    @Test
    public void testContactModification(){

        Contacts before = app.getDbHelper().contacts();
        ContactInformation modifiedContact = before.iterator().next();
        ContactInformation contact = new ContactInformation().
                withId(modifiedContact.getId()).
                withFirstname("Kotaro").
                withLastname("Katsura").
                withNickname("Zura").
                withTitle("Leader").
                withCompany("Joui").
                withAddress("Japan" + "\n" + "Tokyo" + "\n" + "Kabuki-cho").
                withHome("2323").
                withMobile("111").
                withWork("4444").
                withFax("1111").
                withEmail("elizabeth@yandex.ru").
                withEmail2("jouiforever@yandex.ru").
                withEmail3("jouiforever@yandex.ru");
        app.getContactHelper().modify(modifiedContact, contact);
        assertThat(app.getContactHelper().count(), equalTo(before.size()));
        Contacts after = app.getDbHelper().contacts();
        assertThat(before.withAdded(contact).without(modifiedContact), equalTo(after));
    }


}
