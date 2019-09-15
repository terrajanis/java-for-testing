package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactInformation;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if(app.getDbHelper().groups().size() == 0) {
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().create(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
        } //проверяем наличие групп, если нет, создаем новую
        if(app.getDbHelper().contacts().size() == 0) {
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
                            withEmail3("shinpachi@yandex.ru"),
                    true); //проверяем наличие контактов, если нет, создаем новый, при этом группу контакту не присваиваем
        }
    }

    @Test
    public void addContactToGroup(){
        Contacts contacts = app.getDbHelper().contacts();
        ContactInformation addedContact = contacts.iterator().next();
        Groups groups = app.getDbHelper().groups();
        GroupData linkedGroup = groups.iterator().next();
        Groups groupsOfAddedContact = addedContact.getGroups();
        Iterator<ContactInformation> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            if (groupsOfAddedContact.equals(groups)) {
                addedContact = iterator.next();
                groupsOfAddedContact = addedContact.getGroups();
            } else {
                break;
            }
        }
            if (groupsOfAddedContact.equals(groups)) {
                app.getNavigationHelper().goToGroupPage();
                linkedGroup = new GroupData().withName("test11").withHeader("test12").withFooter("test13");
                app.getGroupHelper().create(linkedGroup);
                app.getContactHelper().addContact(addedContact, linkedGroup);
            } //  если у всех контактов есть все присвоенные группы, то linkedGroup будет новая группа и именно ее мы привязываем к контакту
        // иначе (если все условия не соблюдены) выбираем любую рандомную группу
        app.getContactHelper().addContact(addedContact, linkedGroup);
        ContactInformation contactsAfter = app.getDbHelper().selectContactFromDbById(addedContact.getId()).iterator().next();
        Groups groupsOfAddedContactAfter = contactsAfter.getGroups();
        assertThat(groupsOfAddedContact.withAdded(linkedGroup), equalTo(groupsOfAddedContactAfter));
    }

}
