package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
      if(app.getDbHelper().groups().size() == 0) {
          app.getNavigationHelper().goToGroupPage();
          app.getGroupHelper().create(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
      }
  }
  @Test
  public void testGroupDeletion() throws Exception {
   Groups before = app.getDbHelper().groups();
    app.getNavigationHelper().goToGroupPage();
    GroupData deletedGroup = before.iterator().next();
    app.getGroupHelper().delete(deletedGroup);
      assertThat(app.getGroupHelper().count(), equalTo(before.size() - 1));
    Groups after = app.getDbHelper().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
  }

}
