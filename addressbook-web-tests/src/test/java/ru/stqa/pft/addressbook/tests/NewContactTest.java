package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.models.ContactInformation;

public class NewContactTest extends TestBase {


  @Test
  public void testNewContact() throws Exception {
    app.getNavigationHelper().goToAddNewContact();
    app.getContactHelper().fillNewContact(new ContactInformation("Gintoki", "Sakata", "Gin-san", "Chief", "Yorozuya", "Kabuki-cho", "Tokyo", "111", "Yorozuya", "1111", "gin-san@yandex.ru", "test"), true);
    app.getContactHelper().sumbitNewContact();
    app.getNavigationHelper().goToHomePage();
  }

}
