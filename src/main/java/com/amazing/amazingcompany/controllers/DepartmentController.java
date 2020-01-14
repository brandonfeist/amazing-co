package com.amazing.amazingcompany.controllers;

import com.amazing.amazingcompany.models.DepartmentParentInputModel;
import com.amazing.amazingcompany.models.DepartmentResource;
import com.amazing.amazingcompany.models.domain.Department;
import com.amazing.amazingcompany.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/departments")
public class DepartmentController {
  private final DepartmentService departmentService;

  @Autowired
  public DepartmentController(final DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  /**
   * Get a list of a Department and its descendants.
   */
  @GetMapping("/{departmentId}")
  public List<DepartmentResource> getDescendants(
      @PathVariable UUID departmentId
  ) {
    return departmentService.getDescendants(departmentId)
        .stream()
        .map(DepartmentResource::new)
        .collect(Collectors.toList());
  }

  /**
   * Changes a the parent Department of the Department tied to the departmentId UUID.
   * The parent department UUID is stored within the {@link DepartmentParentInputModel} request body.
   */
  @PutMapping("/{departmentId}")
  public DepartmentResource changeParent(
      @PathVariable UUID departmentId,
      @Valid @RequestBody DepartmentParentInputModel inputModel
  ) {
    final Department updatedDepartment =
        departmentService.changeDepartmentParent(departmentId, inputModel.getParentId());

    return new DepartmentResource(updatedDepartment);
  }
}
