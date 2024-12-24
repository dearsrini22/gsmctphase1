package com.gsmct.entities.identities;

import com.gsmct.entities.Role;
import com.gsmct.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class UserRoleIdentity implements Serializable {

  @Serial
  private static final long serialVersionUID = 1231679763483054006L;

  private User user;
  private Role role;
}
