package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import java.io.File;

public class RegistrationTests extends TestBase {

    @Test
    public void testRegistration() {
        app.registration().start("user", "user@yandex.ru");
    }

}