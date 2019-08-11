package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.models.ContactInformation;

public class ContactHelper extends HelperBase{
    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void sumbitNewContact() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
    }

   public void fillNewContact(ContactInformation contactInformation) {
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
        type(By.name("address2"), contactInformation.getAddress2());
        type(By.name("phone2"), contactInformation.getPhone2());
    }

    public void initContactModification() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tokyo111YorozuyaThis'])[1]/following::img[2]"));
    }

    public void updateContact() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void pressDeleteButton() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]"));
    }

}
