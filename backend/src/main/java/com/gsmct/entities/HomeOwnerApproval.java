package com.gsmct.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Entity(name = "homeowner_approval_t")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HomeOwnerApproval {

  @Id
  @Column(name = "howner_id")
  private Integer id;

  @MapsId
  @OneToOne
  @JoinColumn(name = "howner_id")
  private HomeOwner homeOwner;

  @Column(name = "approved_by")
  private String approvedBy;

  @Column(name = "approved_on")
  private LocalDateTime approvedOn;
}
