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
        click(By.xpath("(//input[@name='submit'])[2]"));
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
    }

    public void initContactModification() {
        click(By.cssSelector("img[alt=\"Edit\"]"));
    }

    public void updateContact() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void pressDeleteButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

}
