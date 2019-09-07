package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.models.ContactInformation;
import ru.stqa.pft.addressbook.models.Contacts;

import java.io.File;
import java.sql.SQLOutput;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


  @Test
  public void testNewContact() throws Exception {
    Contacts before = app.getContactHelper().all();
    File photo = new File("src/test/resources/stru.jpg");
    ContactInformation contact = new ContactInformation().
            withFirstname("Gintoki").
            withLastname("Sakata").
            withNickname("Gin-san").
            withTitle("Chief").
            withCompany("Yorozuya").
            withAddress("Japan" + "\n" + "Tokyo" + "\n" + "Kabuki-cho").
            withHome("123").
            withMobile("111").
            withWork("321").
            withFax("1111").
            withEmail("gin-san@yandex.ru").
            withEmail2("kagura-chan@yandex.ru").
            withEmail3("shinpachi@yandex.ru").
            withPhoto(photo);
    app.getContactHelper().create(contact);
    assertThat(app.getContactHelper().count(), equalTo(before.size() + 1));
    Contacts after = app.getContactHelper().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    assertThat(before, equalTo(after));
  }

@Test
  public void testCurrentDir() {
    File currentDir = new File(".");
  System.out.println(currentDir.getAbsolutePath());
  File photo = new File("src/test/resources/stru.jpg");
  System.out.println(photo.getAbsolutePath());
  System.out.println(photo.exists());
}
}
