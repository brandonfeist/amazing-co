package com.amazing.amazingcompany.models.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseDepartment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private UUID clientId;

  @Column(nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "root_id", nullable = false)
  private Department root;

  @ManyToOne
  @JoinColumn(name = "parent_id")
  private Department parent;

  public boolean isRoot() {
    return id.equals(root.getId());
  }
}
