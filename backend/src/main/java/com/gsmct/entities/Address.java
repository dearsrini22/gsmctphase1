package com.gsmct.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "address_t")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

  @Id
  @GeneratedValue(strategy = SEQUENCE, generator = "address_id_seq")
  @SequenceGenerator(name = "address_id_seq", allocationSize = 1)
  @Column(name = "address_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "address_type")
  private AddressType type;

  @Column(name = "street")
  private String street;

  @Column(name = "town")
  private String town;

  @Column(name = "state")
  private String state;

  @Column(name = "zip_code")
  private String zipCode;

  @Column(name = "country")
  private String country;
}
