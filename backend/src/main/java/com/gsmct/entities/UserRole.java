package com.gsmct.entities;

import com.gsmct.entities.identities.UserRoleIdentity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity(name = "user_role_t")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserRoleIdentity.class)
public class UserRole {

  @Id
  @ManyToOne
  private User user;

  @Id
  @OneToOne
  private Role role;

  private String description;
}
