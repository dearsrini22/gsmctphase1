package com.gsmct.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "unit_t")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Unit {

  @Id
  @GeneratedValue(strategy = SEQUENCE, generator = "unit_id_seq")
  @SequenceGenerator(name = "unit_id_seq", allocationSize = 1)
  @Column(name = "unit_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "address_id")
  private Address address;

  @ManyToOne
  @JoinColumn(name = "assoc_id")
  private AssociationDetails association;
}
