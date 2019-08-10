package ru.stqa.pft.addressbook;


import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    initGroupCreation();
    fillGroupForm(new GroupData("test", "test1", "test2"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
