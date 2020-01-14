package com.amazing.amazingcompany.services.impl;

import com.amazing.amazingcompany.models.domain.Department;
import com.amazing.amazingcompany.models.domain.DepartmentDescendant;
import com.amazing.amazingcompany.repositories.DepartmentDescendentRepository;
import com.amazing.amazingcompany.repositories.DepartmentRepository;
import com.amazing.amazingcompany.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {
  private final DepartmentRepository departmentRepository;
  private final DepartmentDescendentRepository departmentDescendentRepository;

  @Autowired
  public DepartmentServiceImpl(final DepartmentRepository departmentRepository,
                               final DepartmentDescendentRepository departmentDescendentRepository) {
    this.departmentRepository = departmentRepository;
    this.departmentDescendentRepository = departmentDescendentRepository;
  }

  @Override
  public List<DepartmentDescendant> getDescendants(UUID departmentId) {
    final Department department = departmentRepository.findByClientId(departmentId);
    if (department == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
          "Department with UUID [" + departmentId + "] was not found.");
    }

    department.initHeight();

    return departmentDescendentRepository.getDescendants(department.getId(), department.getHeight());
  }

  @Override
  @Transactional
  public Department changeDepartmentParent(UUID departmentId, UUID departmentParentId) {
    if (departmentId.equals(departmentParentId)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Cannot set department parent to itself.");
    }

    final Department department = departmentRepository.findByClientId(departmentId);
    if (department == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
          "Department with UUID [" + departmentId + "] was not found.");
    } else if (department.isRoot()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Cannot give root a parent.");
    }
    final Department newParent = departmentRepository.findByClientId(departmentParentId);
    if (newParent == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
          "Parent department with UUID [" + departmentParentId + "] was not found.");
    }

    final Department oldParent = department.getParent();
    final List<Department> children = departmentRepository.findByParent(department);
    for (Department child : children) {
      child.setParent(oldParent);
    }
    department.setParent(newParent);
    department.initHeight();

    return department;
  }
}
