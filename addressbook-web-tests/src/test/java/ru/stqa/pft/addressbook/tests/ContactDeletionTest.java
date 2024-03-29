package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactInformation;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.getDbHelper().contacts().size() == 0) {
            Groups groups = app.getDbHelper().groups();
            app.getContactHelper().create(new ContactInformation().
                    withFirstname("Gintoki").
                    withLastname("Sakata").
                    withNickname("Gin-san").
                    withTitle("Chief").
                    withCompany("Yorozuya").
                    withAddress("Japan" + "\n" + "Tokyo" + "\n" + "Kabuki-cho").
                    withHome("44444").
                    withMobile("111").
                    withWork("333232").
                    withFax("1111").
                    withEmail("gin-san@yandex.ru").
                    withEmail2("kagura-chan@yandex.ru").
                    withEmail3("shinpachi@yandex.ru").
                    inGroup(groups.iterator().next()) ,
                    true);
        }
    }

    @Test
    public void testContactDeletion(){
        Contacts before = app.getDbHelper().contacts();
       ContactInformation deletedContact = before.iterator().next();
        app.getContactHelper().delete(deletedContact);
        assertThat(app.getContactHelper().count(), equalTo(before.size() - 1));
        Contacts after = app.getDbHelper().contacts();
       assertThat(before.without(deletedContact), equalTo(after));
    }

}
