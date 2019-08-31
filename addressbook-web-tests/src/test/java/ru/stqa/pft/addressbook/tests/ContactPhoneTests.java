package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.ContactInformation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones() {
        ContactInformation contact = app.getContactHelper().all().iterator().next();
        ContactInformation contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

        assertThat(contact.getHome(), equalTo(clean(contactInfoFromEditForm.getHome())));
        assertThat(contact.getMobile(), equalTo(clean(contactInfoFromEditForm.getMobile())));
        assertThat(contact.getWork(), equalTo(clean(contactInfoFromEditForm.getWork())));
    }

    public String clean(String phone) {
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
