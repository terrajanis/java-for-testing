package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.models.ContactInformation;

import java.util.Comparator;
import java.util.List;

public class NewContactTest extends TestBase {


  @Test
  public void testNewContact() throws Exception {
    List<ContactInformation> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToAddNewContact();
    ContactInformation contact = new ContactInformation("Gintoki", "Sakata", "Gin-san", "Chief", "Yorozuya", "Kabuki-cho", "Tokyo", "111", "Yorozuya", "1111", "gin-san@yandex.ru", "test");
    app.getContactHelper().fillNewContact(contact, true);
    app.getContactHelper().sumbitNewContact();
    app.getNavigationHelper().goToHomePage();
    List<ContactInformation> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactInformation> byID = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }

}
