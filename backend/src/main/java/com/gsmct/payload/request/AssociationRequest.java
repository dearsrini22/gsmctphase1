package com.gsmct.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociationRequest {

  private Integer id;

  @NotBlank
  private String name;

  @NotBlank
  private String address;

  private String email;

  @NotBlank
  private String phone;

  @NotBlank
  private String state;

  @NotBlank
  private String zipCode;
}
