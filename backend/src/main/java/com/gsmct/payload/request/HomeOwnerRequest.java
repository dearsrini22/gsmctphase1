package com.gsmct.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HomeOwnerRequest {

  @NonNull
  @NotBlank
  private String state;

  @NonNull
  @NotBlank
  private String associationId;

  @NonNull
  @NotBlank
  private String firstName;

  private String middleName;

  @NonNull
  @NotBlank
  private String lastName;

  @NonNull
  @NotBlank
  @Email
  private String email;

  @NonNull
  @NotBlank
  @Size(max = 12)
  private String phone;

  @Size(max = 12)
  private String mobileNumber;

  @NonNull
  @NotBlank
  private String address1;

  private String address2;

  @NonNull
  @NotBlank
  private String city;

  @NonNull
  @NotBlank
  @Size(max = 12)
  private String zipCode;
}
