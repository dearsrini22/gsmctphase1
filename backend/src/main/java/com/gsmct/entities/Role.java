package com.gsmct.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity(name = "role_t")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {

  @Id
  @Column(name="role_id")
  private Integer id;

  @Column(name="role_description")
  private String description;
}
