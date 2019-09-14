package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.getDbHelper().groups().size() == 0) {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().create(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
        }
    }

    @Test
    public void testGroupModification(){
        Groups before = app.getDbHelper().groups();
        app.getNavigationHelper().goToGroupPage();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test0").withHeader("test01").withFooter("test02");
        app.getGroupHelper().modify(group);
        assertThat(app.getGroupHelper().count(), equalTo(before.size()));
      Groups after = app.getDbHelper().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }


}
