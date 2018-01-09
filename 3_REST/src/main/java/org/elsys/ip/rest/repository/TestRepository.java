package org.elsys.ip.rest.repository;

import org.elsys.ip.rest.model.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestRepository {
  private static List<Test> testList = new ArrayList<>(
    Arrays.asList(
      new Test(1, "name1"),
      new Test(2, "name2"),
      new Test(3, "name3"),
      new Test(4, "name4")
    ));

  public List<Test> getTestList() {
    return testList;
  }

  public Optional<Test> getTestById(Integer id) {
    return testList.stream().filter(test -> test.getId() == id).findFirst();
  }

  public Test saveTest(Test test) {
    testList.add(test);
    return test;
  }

  public Test updateTest(Integer id, Test test) {
    int realId = id - 1;
    testList.set(realId, test);
    return test;
  }

  public void deleteTest(Integer id) {
    testList.removeIf(it -> it.getId() == id);
  }
}
