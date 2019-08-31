package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
   Groups before = app.getGroupHelper().all();
    GroupData group = new GroupData().withName("test").withHeader("test1").withFooter("test2");
    app.getGroupHelper().create(group);
      assertThat(app.getGroupHelper().count(), equalTo(before.size() + 1));
    Groups after = app.getGroupHelper().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

    @Test
    public void testGroupBadCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        Groups before = app.getGroupHelper().all();
        GroupData group = new GroupData().withName("test'").withHeader("test1").withFooter("test2");
        app.getGroupHelper().create(group);
        assertThat(app.getGroupHelper().count(), equalTo(before.size()));
        Groups after = app.getGroupHelper().all();
        assertThat(after, equalTo(before));
    }

}
