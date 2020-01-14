package com.amazing.amazingcompany.repositories;

import com.amazing.amazingcompany.models.domain.Department;
import com.amazing.amazingcompany.models.domain.DepartmentDescendant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentDescendentRepository extends JpaRepository<DepartmentDescendant, Long> {
  /**
   * Uses a recursive query to find all descendants of the given department.
   * @param id Long db id of the department
   * @return List {@link Department} of the given department and its descendants
   */
  @Query(value = "WITH RECURSIVE descendantstable AS" +
      "(" +
      "    SELECT id, client_id, name, root_id, parent_id, :startHeight AS height" +
      "    FROM departments " +
      "WHERE id = :id" +
      "    UNION ALL" +
      "    SELECT S.id, S.client_id, S.name, S.root_id, S.parent_id, D2.height + 1 AS height" +
      "    FROM descendantstable AS D2 JOIN departments as S on S.parent_id = D2.id" +
      ")" +
      "SELECT * FROM descendantstable ORDER BY height, parent_id, name", nativeQuery = true)
  List<DepartmentDescendant> getDescendants(@Param("id") Long id, int startHeight);
}
