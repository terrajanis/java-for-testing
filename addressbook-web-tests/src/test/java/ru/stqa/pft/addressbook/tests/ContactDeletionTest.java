package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getContactHelper().selectContact();
        app.getContactHelper().pressDeleteButton();
        app.wd.switchTo().alert().accept();
    }
}
