package com.amazing.amazingcompany.models;

import com.amazing.amazingcompany.models.domain.Department;
import com.amazing.amazingcompany.models.domain.DepartmentDescendant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class DepartmentResource {
  @JsonProperty(required = true)
  private UUID clientId;

  @JsonProperty(required = true)
  private String name;

  @JsonProperty(required = true)
  private int height;

  @JsonProperty(required = true)
  @JsonIgnoreProperties({"id", "height", "root", "parent"})
  private Department root;

  @JsonProperty
  @JsonIgnoreProperties({"id", "height", "root", "parent"})
  private Department parent;

  @SuppressWarnings("unused")
  public DepartmentResource() {
  }

  public DepartmentResource(Department department) {
    this.clientId = department.getClientId();
    this.name = department.getName();
    this.height = department.getHeight();
    this.root = department.getRoot();
    this.parent = department.getParent();
  }

  public DepartmentResource(DepartmentDescendant department) {
    this.clientId = department.getClientId();
    this.name = department.getName();
    this.height = department.getHeight();
    this.root = department.getRoot();
    this.parent = department.getParent();
  }
}
