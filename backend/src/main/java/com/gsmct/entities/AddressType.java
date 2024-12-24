package com.gsmct.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity(name = "address_type_t")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressType {

  @Id
  @Column(name = "address_type")
  private String type;

  @Column(name = "address_description")
  private String description;
}
