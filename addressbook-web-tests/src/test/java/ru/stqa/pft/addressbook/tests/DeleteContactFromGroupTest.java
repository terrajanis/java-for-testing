package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactInformation;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.util.Iterator;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;

public class DeleteContactFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.getDbHelper().groups().size() == 0) {
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().create(new GroupData().withName("rest").withHeader("test1").withFooter("test2"));
        } //проверяем наличие групп, если нет, создаем новую
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
        } //проверяем наличие контактов, если нет, создаем новый, при этом группу контакту присваиваем
    }

    @Test
    public void deleteContactFromGroup(){
        Groups groups = app.getDbHelper().groups();
        Contacts contacts = app.getDbHelper().contacts();
        ContactInformation deletedContact = contacts.iterator().next();
        Groups groupsOfDeletedContact = deletedContact.getGroups();
        if (deletedContact.getGroups().size() == 0) {
            app.getContactHelper().addContact(deletedContact, groups.iterator().next());
        } // проверяем, что у контакта есть группы, если нет, то привязываем контакт к группе
        GroupData linkedGroup = groupsOfDeletedContact.iterator().next();
        app.getContactHelper().deleteContact(deletedContact, linkedGroup);
        ContactInformation contactsAfter = app.getDbHelper().selectContactFromDbById(deletedContact.getId()).iterator().next();
        Groups groupsOfDeletedContactAfter = contactsAfter.getGroups();
        assertThat(groupsOfDeletedContact.without(linkedGroup), equalTo(groupsOfDeletedContactAfter));
    }

}
