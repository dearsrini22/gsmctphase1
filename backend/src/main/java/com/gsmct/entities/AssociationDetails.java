package com.gsmct.entities;

import com.gsmct.payload.request.AssociationRequest;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import static com.gsmct.utils.ConstantsAndUtilities.DEFAULT_COUNTRY;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "assoc_details_t")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AssociationDetails {

  @Id
  @GeneratedValue(strategy = SEQUENCE, generator = "assoc_id_seq")
  @SequenceGenerator(name = "assoc_id_seq", allocationSize = 1)
  @Column(name = "assoc_id")
  private Integer id;

  @Column(name = "assoc_name")
  private String name;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private Address address;

  @ManyToOne
  @JoinColumn(name = "assoc_type_id")
  private AssociationType type;

  public AssociationDetails(AssociationRequest request, AddressType addressType) {
    this.name = request.getName();
    this.address = Address.builder().type(addressType).street(request.getAddress()).state(request.getState())
      .zipCode(address.getZipCode()).country(DEFAULT_COUNTRY).build();
  }
}
