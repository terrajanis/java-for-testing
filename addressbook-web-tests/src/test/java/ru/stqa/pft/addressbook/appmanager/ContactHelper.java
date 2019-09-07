package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        type(By.name("email2"), contactInformation.getEmail2());
        type(By.name("email3"), contactInformation.getEmail3());
        attach(By.name("photo"), contactInformation.getPhoto());
        /*if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactInformation.getGroup());
            } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        */
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
        contactCache = null;
        navigationHelper.goToHomePage();
    }

    public void delete(ContactInformation contact) {
        selectById(contact.getId());
        pressDeleteButton();
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
        contactCache = null;
    }

    public void modify(ContactInformation modifiedContact, ContactInformation contact) {
        initModificationById(modifiedContact.getId());
        fillNewContact(contact, false);
        update();
        contactCache = null;
        navigationHelper.goToHomePage();
    }

    public ContactInformation infoFromEditForm(ContactInformation contact) {
        initModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactInformation().
                withFirstname(firstname).
                withLastname(lastname).
                withAddress(address).
                withHome(home).
                withMobile(mobile).
                withWork(work).
                withEmail(email).
                withEmail2(email2).
                withEmail3(email3);
    }
    public int count() {
        return wd.findElements(By.name("entry")).size();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> elements2 = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(elements2.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstname = elements2.get(2).getText();
            String lastname = elements2.get(1).getText();
            String allPhones = elements2.get(5).getText();
            String allEmails = elements2.get(4).getText();
            String address = elements2.get(3).getText();
            ContactInformation contact = new ContactInformation().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }
}
