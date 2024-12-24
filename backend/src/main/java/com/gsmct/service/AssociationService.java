package com.gsmct.service;

import com.gsmct.exception.BadRequestException;
import com.gsmct.entities.*;
import com.gsmct.payload.request.AssociationRequest;
import com.gsmct.payload.request.AssociationSearchLikeRequest;
import com.gsmct.payload.request.AssociationSearchRequest;
import com.gsmct.payload.request.HomeOwnerRequest;
import com.gsmct.payload.response.AssociationSearchResponse;
import com.gsmct.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gsmct.enums.AddressTypeEnum.ASSOCIATION;
import static com.gsmct.enums.AddressTypeEnum.HOME_OWNER_PERMANENT;
import static com.gsmct.enums.BoardMemberTypeEnum.HOME_OWNER;
import static com.gsmct.utils.ConstantsAndUtilities.DEFAULT_COUNTRY;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
@Slf4j
@Transactional
public class AssociationService {

  @Autowired
  private AssociationDetailsRepository associationDetailsRepository;

  @Autowired
  private StateRepository stateRepository;

  @Autowired
  private AddressTypeRepository addressTypeRepository;

  @Autowired
  private BoardMemberTypeRepository boardMemberTypeRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private HomeOwnerRepository homeOwnerRepository;

  private AssociationTypeRepository associationTypeRepository;

  public void addOrUpdateAssociation(AssociationRequest request) {
    AddressType type = addressTypeRepository.findById(ASSOCIATION.name()).orElseThrow();
    AssociationDetails association;
    if (request.getId() == null) {
      association = new AssociationDetails(request, type);
    } else {
      association = associationDetailsRepository.findById(request.getId())
        .orElseThrow(() -> new BadRequestException(request.getId() + " - Association doesn't exist"));
      association.setName(request.getName());
      association.getAddress().setStreet(request.getAddress());
      association.getAddress().setState(request.getState());
      association.getAddress().setZipCode(request.getZipCode());
    }
    associationDetailsRepository.save(association);
  }

  public AssociationSearchResponse.Association getAssociation(Integer associationId) {
    AssociationDetails associationDetails = associationDetailsRepository.findById(associationId)
      .orElseThrow(() -> new BadRequestException(associationId + " - Association doesn't exist"));
    return new AssociationSearchResponse.Association(associationDetails);
  }

  public List<HomeOwner> getAllHomeOwners(Long associationId) {
    return List.of();
  }

  public void deleteAssociation(Integer associationId) {
    AssociationDetails association = associationDetailsRepository.findById(associationId)
      .orElseThrow(() -> new BadRequestException(associationId + " - Association doesn't exist"));
    associationDetailsRepository.delete(association);
  }

  @Transactional(readOnly = true)
  public List<State> getAllStates() {
    return (List<State>) stateRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<AssociationDetails> getAllAssociationsLike(AssociationSearchLikeRequest request) {
    return associationDetailsRepository.findTop10ByNameContainsIgnoreCaseAndAddress_State(request.getText(), request.getState());
  }

  @Transactional(readOnly = true)
  public AssociationSearchResponse<AssociationSearchResponse.Association> searchAssociation(AssociationSearchRequest request) {
    Sort sort = isEmpty(request.getSortBy()) ? null : isEmpty(request.getSortDir()) ? Sort.by(request.getSortBy()).ascending() :
      request.getSortBy().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(request.getSortBy()).ascending()
        : Sort.by(request.getSortBy()).descending();
    int pageIndex = request.getPageNo() - 1;
    int pageSize = request.getPageSize();
    Pageable pageable = sort == null ? PageRequest.of(pageIndex, pageSize) :
      PageRequest.of(pageIndex, pageSize, sort);
    Page<AssociationDetails> associations = associationDetailsRepository.findAll(pageable);
    List<AssociationSearchResponse.Association> associationList = associations.getContent().stream()
      .map(AssociationSearchResponse.Association::new).toList();
    return new AssociationSearchResponse<>(associationList, pageIndex, pageSize, associations.getTotalElements(),
      associations.getTotalPages());
  }

  @Transactional
  public void registerHomeOwner(HomeOwnerRequest request) {
    AddressType addressType = addressTypeRepository.findById(HOME_OWNER_PERMANENT.name()).orElse(null);
    BoardMemberType memberType = boardMemberTypeRepository.findById(HOME_OWNER.name()).orElse(null);
    Address address = Address.builder().type(addressType).street(request.getAddress1() + "\n" + request.getAddress2())
      .town(request.getCity()).state(request.getState()).zipCode(request.getZipCode()).country(DEFAULT_COUNTRY).build();
    addressRepository.save(address);
    HomeOwner homeOwner = HomeOwner.builder().address(address).firstName(request.getFirstName()).middleName(request.getMiddleName())
      .lastName(request.getLastName()).phoneNumber(request.getPhone()).mobileNumber(request.getMobileNumber())
      .memberType(memberType).build();
    homeOwnerRepository.save(homeOwner);
  }
}
