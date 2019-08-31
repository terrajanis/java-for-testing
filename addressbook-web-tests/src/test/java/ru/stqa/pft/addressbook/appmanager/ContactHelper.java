package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.models.ContactInformation;
import ru.stqa.pft.addressbook.models.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    NavigationHelper navigationHelper = new NavigationHelper(wd);

    public void sumbit() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

   public void fillNewContact(ContactInformation contactInformation, boolean creation) {
        type(By.name("firstname"), contactInformation.getFirstname());
        type(By.name("lastname"), contactInformation.getLastname());
        type(By.name("nickname"), contactInformation.getNickname());
        type(By.name("title"), contactInformation.getTitle());
        type(By.name("company"), contactInformation.getCompany());
        type(By.name("address"), contactInformation.getAddress());
        type(By.name("home"), contactInformation.getHome());
        type(By.name("mobile"), contactInformation.getMobile());
        type(By.name("work"), contactInformation.getWork());
        type(By.name("fax"), contactInformation.getFax());
        type(By.name("email"), contactInformation.getEmail());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactInformation.getGroup());
            } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initModification(int index) {
        wd.findElements(By.cssSelector("img[alt=\"Edit\"]")).get(index).click();
    }

    public void initModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void update() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void selectById(int id) { wd.findElement(By.cssSelector("input[value='" + id + "']")).click(); }

    public void pressDeleteButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void create(ContactInformation contact) {
        navigationHelper.goToAddNewContact();
       fillNewContact(contact, true);
        sumbit();
        navigationHelper.goToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts set() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> elements2 = element.findElements(By.tagName("td"));
            String firstname = elements2.get(2).getText();
            String lastname = elements2.get(1).getText();
            int id = Integer.parseInt(elements2.get(0).findElement(By.tagName("input")).getAttribute("value"));
            ContactInformation contact = new ContactInformation().withId(id).withFirstname(firstname).withLastname(lastname);
            contacts.add(contact);
        }
        return contacts;
    }
}
