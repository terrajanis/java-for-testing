package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactInformation;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.getDbHelper().groups().size() == 0) {
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().create(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
        }
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
    public void deleteContactFromGroup(){
        Groups groups = app.getDbHelper().groups();
        GroupData linkedGroup = groups.iterator().next();
        Contacts contacts = app.getDbHelper().contacts();
        ContactInformation deletedContact = contacts.iterator().next();
       if (!deletedContact.getGroups().equals(linkedGroup)) {
           app.getContactHelper().addContact(deletedContact, linkedGroup);
        }
        app.getContactHelper().deleteContact(deletedContact, linkedGroup);
        assertThat(deletedContact.getGroups(), not(hasItem(linkedGroup)));

    }

}
