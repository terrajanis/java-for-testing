package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToGroupPage();
    if(app.getGroupHelper().all().size() == 0) {
      app.getGroupHelper().create(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
    }
  }
  @Test
  public void testGroupDeletion() throws Exception {
    Set<GroupData> before = app.getGroupHelper().all();
    GroupData deletedGroup = before.iterator().next();
    app.getGroupHelper().delete(deletedGroup);
    Set<GroupData> after = app.getGroupHelper().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedGroup);
      Assert.assertEquals(before, after);

  }

}
