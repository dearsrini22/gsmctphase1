package com.gsmct.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity(name = "board_member_type_t")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardMemberType {

  @Id
  @Column(name = "member_type")
  private String type;

  @Column(name = "member_description")
  private String description;
}
