package com.gsmct.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity(name = "user_t")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @Column(name = "user_id")
  private Integer id;

  @MapsId
  @OneToOne
  @JoinColumn(name = "user_id")
  private HomeOwner homeOwner;

  @Column(name = "user_name")
  private String username;

  @Column(name = "user_password")
  private String password;
}
