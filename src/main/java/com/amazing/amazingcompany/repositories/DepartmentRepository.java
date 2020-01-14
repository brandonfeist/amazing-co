package com.amazing.amazingcompany.repositories;

import com.amazing.amazingcompany.models.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
  Department findByClientId(@Param("clientId") UUID clientId);

  List<Department> findByParent(@Param("parent") Department parent);
}
