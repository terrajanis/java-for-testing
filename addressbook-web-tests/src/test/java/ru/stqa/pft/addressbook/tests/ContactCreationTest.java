package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.models.ContactInformation;
import ru.stqa.pft.addressbook.models.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


  @Test
  public void testNewContact() throws Exception {
    Contacts before = app.getContactHelper().all();
    ContactInformation contact = new ContactInformation().
            withFirstname("Gintoki").
            withLastname("Sakata").
            withNickname("Gin-san").
            withTitle("Chief").
            withCompany("Yorozuya").
            withAddress("Kabuki-cho").
            withHome("123").
            withMobile("111").
            withWork("321").
            withFax("1111").
            withEmail("gin-san@yandex.ru").
            withGroup("test");
    app.getContactHelper().create(contact);
    Contacts after = app.getContactHelper().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    assertThat(before, equalTo(after));
  }


}
