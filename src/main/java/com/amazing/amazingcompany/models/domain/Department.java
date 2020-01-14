package com.amazing.amazingcompany.models.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "departments")
public class Department extends BaseDepartment {
  @Transient
  private int height;

  public Department() {
    super();
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Worst case the height of the tree.
   */
  public void initHeight() {
    this.height = 0;

    Department tmpParent = this.getParent();
    while (tmpParent != null) {
      height++;
      tmpParent = tmpParent.getParent();
    }
  }
}
