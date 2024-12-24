package com.gsmct.payload.response;

import com.gsmct.entities.Address;
import com.gsmct.entities.AssociationDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociationSearchResponse<T> {
  private List<T> content;
  private int pageNo;
  private int pageSize;
  private long totalElements;
  private long totalPages;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Association {
    private Integer id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String zipCode;
    private String state;

    public Association(AssociationDetails details) {
      this.id = details.getId();
      this.name = details.getName();
      Address address = details.getAddress();
      if(address != null) {
        this.address = address.getStreet();
        this.zipCode = address.getZipCode();
        this.state = address.getState();
      }
    }
  }
}
