package com.amazing.amazingcompany.services;

import com.amazing.amazingcompany.models.domain.Department;
import com.amazing.amazingcompany.models.domain.DepartmentDescendant;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * All service methods related to the department hierarchy tree.
 */
public interface DepartmentService {
  /**
   * Will get a department and all of its descendants.
   * @param departmentId UUID of department to find descendants
   * @return List of {@link Department} related to given department
   */
  List<DepartmentDescendant> getDescendants(UUID departmentId);

  /**
   * Moves the given department to be a child of the given parent department.
   * The root is unable to be moved to a parent and department's cannot parent themselves.
   * @param departmentId UUID of department to be moved
   * @param departmentParentId UUID of new department parent
   * @return The edited {@link Department}
   */
  @Transactional
  Department changeDepartmentParent(UUID departmentId, UUID departmentParentId);
}
