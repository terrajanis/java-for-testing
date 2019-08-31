package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        if(app.getGroupHelper().all().size() == 0) {
            app.getGroupHelper().create(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
        }
    }

    @Test
    public void testGroupModification(){
        Set<GroupData> before = app.getGroupHelper().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test0").withHeader("test01").withFooter("test02");
        app.getGroupHelper().modify(group);
       Set<GroupData> after = app.getGroupHelper().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }


}
