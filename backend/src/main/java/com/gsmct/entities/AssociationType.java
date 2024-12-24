package com.gsmct.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "assoc_type_t")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AssociationType {

  @Id
  @GeneratedValue(strategy = SEQUENCE, generator = "assoc_type_id_seq")
  @SequenceGenerator(name = "assoc_type_id_seq", allocationSize = 1)
  @Column(name = "assoc_type_id")
  private Integer id;

  @Column(name = "assoc_type_desc")
  private String description;
}
