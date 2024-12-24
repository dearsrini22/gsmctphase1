package com.gsmct.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedSearchRequest {
  private int pageNo = 1;
  private int pageSize = 20;
  private String sortBy;
  private String sortDir;
}
