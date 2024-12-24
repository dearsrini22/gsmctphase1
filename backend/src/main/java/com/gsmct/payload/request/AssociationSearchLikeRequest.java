package com.gsmct.payload.request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociationSearchLikeRequest {
  @Size(min = 3)
  private String text;

  @NotBlank
  @Nonnull
  private String state;
}
