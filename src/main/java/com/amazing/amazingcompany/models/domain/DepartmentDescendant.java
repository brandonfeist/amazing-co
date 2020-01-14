package com.amazing.amazingcompany.models.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class DepartmentDescendant extends BaseDepartment {
  @Column
  private int height;

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public DepartmentDescendant() {
    super();
  }
}
