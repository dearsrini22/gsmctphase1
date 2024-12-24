package com.gsmct.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "homeowner_t")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeOwner {

  @Id
  @GeneratedValue(strategy = SEQUENCE, generator = "howner_id_seq")
  @SequenceGenerator(name = "howner_id_seq", allocationSize = 1)
  @Column(name = "howner_id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "address_id")
  private Address address;

  @ManyToOne
  @JoinColumn(name = "unit_id")
  private Unit unit;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "middle_name")
  private String middleName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "mobile_number")
  private String mobileNumber;

  @ManyToOne
  @JoinColumn(name = "member_type")
  private BoardMemberType memberType;

  @Column(name = "mem_term_start")
  private LocalDate memTermStart;

  @Column(name = "mem_term_end")
  private LocalDate memTermEnd;
}
