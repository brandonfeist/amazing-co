package com.amazing.amazingcompany.utils;

import com.amazing.amazingcompany.models.domain.Department;
import com.amazing.amazingcompany.models.domain.DepartmentDescendant;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class DepartmentTestUtils {
  public static final UUID BLANK_UUID_1 = UUID.fromString("00000000-0000-0000-0000-000000000001");
  public static final UUID BLANK_UUID_2 = UUID.fromString("00000000-0000-0000-0000-000000000002");
  public static final UUID BLANK_UUID_3 = UUID.fromString("00000000-0000-0000-0000-000000000003");

  public Department getRoot() {
    Department root = new Department();
    root.setId(1L);
    root.setClientId(BLANK_UUID_1);
    root.setName("root");
    root.setRoot(root);
    root.setParent(null);
    root.setHeight(0);

    return root;
  }

  public Department getChild() {
    Department child = new Department();
    child.setId(3L);
    child.setClientId(BLANK_UUID_2);
    child.setName("Child");
    child.setRoot(getRoot());
    child.setParent(getParent());
    child.setHeight(2);

    return child;
  }

  public Department getParent() {
    Department parent = new Department();
    parent.setId(2L);
    parent.setClientId(BLANK_UUID_2);
    parent.setName("Parent");
    parent.setRoot(getRoot());
    parent.setParent(getRoot());
    parent.setHeight(1);

    return parent;
  }

  public Department getParent_2() {
    Department parent = new Department();
    parent.setId(4L);
    parent.setClientId(BLANK_UUID_3);
    parent.setName("Parent2");
    parent.setRoot(getRoot());
    parent.setParent(getRoot());
    parent.setHeight(1);

    return parent;
  }

  public List<Department> getChildList() {
    return Collections.emptyList();
  }

  public List<DepartmentDescendant> getDepartmentAndDescendants() {
    return null;
  }
}
