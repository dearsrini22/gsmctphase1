package com.gsmct.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociationSearchRequest extends PaginatedSearchRequest {
  private String name;
  private String state;
  private String zipCode;
}
