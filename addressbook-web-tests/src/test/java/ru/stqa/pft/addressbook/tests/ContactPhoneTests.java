package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactInformation;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

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
                    withHome("44444").
                    withMobile("111").
                    withWork("333232").
                    withFax("1111").
                    withEmail("gin-san@yandex.ru").
                    withEmail2("kagura-chan@yandex.ru").
                    withEmail3("shinpachi@yandex.ru").
                    withGroup("test"));
        }
    }

    @Test
    public void testContactPhones() {
        ContactInformation contact = app.getContactHelper().all().iterator().next();
        ContactInformation contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

    }

    private String mergePhones(ContactInformation contact) {
        return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
                .stream().filter((s)-> ! s.equals(""))
                .map(ContactPhoneTests::clean)
                .collect(Collectors.joining("\n"));
    }

    public static String clean(String phone) {
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

}
