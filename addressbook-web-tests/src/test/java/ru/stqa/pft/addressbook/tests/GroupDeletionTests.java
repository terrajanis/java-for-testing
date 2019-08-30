package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToGroupPage();
    if(app.getGroupHelper().getGroupList().size() == 0) {
      app.getGroupHelper().create(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
    }
  }
  @Test
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size() - 1;
    app.getGroupHelper().delete(index);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
      Assert.assertEquals(before, after);

  }

}
