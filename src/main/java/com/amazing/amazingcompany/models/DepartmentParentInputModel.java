package com.amazing.amazingcompany.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class DepartmentParentInputModel {
  @NotNull(message = "Must have a replacement parent UUID.")
  private UUID parentId;

  public DepartmentParentInputModel() {
  }

  public UUID getParentId() {
    return parentId;
  }
}
