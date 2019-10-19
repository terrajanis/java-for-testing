package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Listeners (MyTestListener.class)
public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp(ITestContext context) throws Exception {
        app.init();
        context.setAttribute("app", app);
    }


    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.logout();
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info("Start rest " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod (alwaysRun = true)
    public void logTestStop(Method m, Object[] p){
        logger.info("Stop rest " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    public void verifyGroupListInUi() {
        if(Boolean.getBoolean("verifyUi")) {
            Groups dbGroups = app.getDbHelper().groups();
            Groups uiGroups = app.getGroupHelper().all();
            assertThat(uiGroups, equalTo(dbGroups.stream().map((g) -> new GroupData().
                    withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
        }
    }

}
