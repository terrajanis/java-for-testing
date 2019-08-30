package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        if(! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().create(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
        }
    }

    @Test
    public void testGroupModification(){
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size() - 1;
        GroupData group = new GroupData().withId(before.get(index).getId()).withName("test0").withHeader("test01").withFooter("test02");
        app.getGroupHelper().modify(index, group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byID = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before, after);
    }


}
