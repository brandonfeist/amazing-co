package com.amazing.amazingcompany.services;

import com.amazing.amazingcompany.models.domain.Department;
import com.amazing.amazingcompany.repositories.DepartmentDescendentRepository;
import com.amazing.amazingcompany.repositories.DepartmentRepository;
import com.amazing.amazingcompany.services.impl.DepartmentServiceImpl;
import com.amazing.amazingcompany.utils.DepartmentTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static com.amazing.amazingcompany.utils.DepartmentTestUtils.BLANK_UUID_1;
import static com.amazing.amazingcompany.utils.DepartmentTestUtils.BLANK_UUID_2;
import static com.amazing.amazingcompany.utils.DepartmentTestUtils.BLANK_UUID_3;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Import(DepartmentTestUtils.class)
public class DepartmentServiceTest {
  @InjectMocks
  private DepartmentServiceImpl departmentService;

  @Mock
  private DepartmentRepository departmentRepository;

  @Mock
  private DepartmentDescendentRepository departmentDescendentRepository;

  @Autowired
  private DepartmentTestUtils departmentTestUtils;

  /* ----- Get Descendants ----- */
  @Test
  public void whenGetDescendantsFromRoot_returnsListOfRootAndAllDepartments() {
    when(departmentRepository.findByClientId(any(UUID.class))).thenReturn(departmentTestUtils.getChild());
    when(departmentDescendentRepository.getDescendants(anyLong(), anyInt()))
        .thenReturn(departmentTestUtils.getDepartmentAndDescendants());

    departmentService.getDescendants(BLANK_UUID_1);

    verify(departmentRepository, times(1)).findByClientId(any(UUID.class));
    verify(departmentDescendentRepository, times(1)).getDescendants(anyLong(), anyInt());
  }

  @Test(expected = ResponseStatusException.class)
  public void whenGetDescendantsFromNonExistingDepartment_throwsNotFound() {
    when(departmentRepository.findByClientId(any(UUID.class))).thenReturn(null);

    departmentService.getDescendants(BLANK_UUID_1);
  }

  /* ----- Change Department Parent ----- */
  @Test
  public void whenChangeParentsValidDepartmentAndValidParent_returnsUpdatedDepartment() {
    final Department newParent = departmentTestUtils.getParent_2();

    when(departmentRepository.findByClientId(BLANK_UUID_1)).thenReturn(departmentTestUtils.getChild());
    when(departmentRepository.findByClientId(BLANK_UUID_3)).thenReturn(newParent);
    when(departmentRepository.findByParent(any(Department.class))).thenReturn(departmentTestUtils.getChildList());

    final Department updatedDepartment = departmentService.changeDepartmentParent(BLANK_UUID_1, BLANK_UUID_3);
    assertEquals(newParent.getId(), updatedDepartment.getParent().getId());
  }

  @Test(expected = ResponseStatusException.class)
  public void whenChangeParentOfInvalidDepartment_throwsNotFound() {
    when(departmentRepository.findByClientId(any(UUID.class))).thenReturn(null);

    departmentService.changeDepartmentParent(BLANK_UUID_1, BLANK_UUID_2);
  }

  @Test(expected = ResponseStatusException.class)
  public void whenChangeParentOfDepartmentWithInvalidParent_throwsNotFound() {
    when(departmentRepository.findByClientId(BLANK_UUID_1)).thenReturn(departmentTestUtils.getChild());
    when(departmentRepository.findByClientId(any(UUID.class))).thenReturn(null);

    departmentService.changeDepartmentParent(BLANK_UUID_1, BLANK_UUID_2);
  }

  @Test(expected = ResponseStatusException.class)
  public void whenChangeDepartmentParentToItself_throwsBadRequest() {
    departmentService.changeDepartmentParent(BLANK_UUID_1, BLANK_UUID_1);
  }
  @Test(expected = ResponseStatusException.class)
  public void whenChangeParentOfRoot_throwsBadRequest() {
    when(departmentRepository.findByClientId(any(UUID.class))).thenReturn(departmentTestUtils.getRoot());

    departmentService.changeDepartmentParent(BLANK_UUID_1, BLANK_UUID_2);
  }
}
