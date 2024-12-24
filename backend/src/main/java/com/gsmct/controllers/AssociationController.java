package com.gsmct.controllers;

import com.gsmct.entities.AssociationDetails;
import com.gsmct.payload.request.AssociationRequest;
import com.gsmct.payload.request.AssociationSearchRequest;
import com.gsmct.payload.response.AssociationSearchResponse;
import com.gsmct.service.AssociationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
public class AssociationController {

  @Autowired
  private AssociationService associationService;

  @PostMapping("/association")
  public ResponseEntity<Void> addOrUpdateAssociation(@Valid @RequestBody AssociationRequest request) {
    associationService.addOrUpdateAssociation(request);
    return new ResponseEntity<>(NO_CONTENT);
  }

  @GetMapping("/association/{associationId}")
  public ResponseEntity<AssociationSearchResponse.Association> getAssociation(@Valid @PathVariable("associationId") Integer associationId) {
    return new ResponseEntity<>(associationService.getAssociation(associationId), OK);
  }

  @DeleteMapping("/association/{associationId}")
  public ResponseEntity<Void> deleteAssociation(@Valid @PathVariable("associationId") Integer associationId) {
    associationService.deleteAssociation(associationId);
    return new ResponseEntity<>(NO_CONTENT);
  }

  @PostMapping("/search/association")
  public ResponseEntity<AssociationSearchResponse<AssociationSearchResponse.Association>> searchAssociation(@Valid @RequestBody AssociationSearchRequest request) {
    return new ResponseEntity<>(associationService.searchAssociation(request), OK);
  }
}
