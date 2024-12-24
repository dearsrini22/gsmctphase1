package com.gsmct.controllers;

import com.gsmct.entities.AssociationDetails;
import com.gsmct.entities.State;
import com.gsmct.payload.request.AssociationSearchLikeRequest;
import com.gsmct.payload.request.HomeOwnerRequest;
import com.gsmct.payload.request.LoginRequest;
import com.gsmct.service.AssociationService;
import com.gsmct.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @Autowired
  private AssociationService associationService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(authService.authenticateUser(loginRequest));
  }

  @GetMapping("/states")
  public List<State> getAllStates() {
    return associationService.getAllStates();
  }

  @PreAuthorize(value="hasRole('ROLE_ANONYMOUS')")
  @PostMapping("/associations")
  public ResponseEntity<List<AssociationDetails>> getAllAssociationsLike(@Valid @RequestBody AssociationSearchLikeRequest request) {
    return ResponseEntity.ok(associationService.getAllAssociationsLike(request));
  }

  @PostMapping("/home-owner/register")
  public ResponseEntity<Void> registerHomeOwner(@Valid @RequestBody HomeOwnerRequest request) {
    associationService.registerHomeOwner(request);
    return new ResponseEntity<>(NO_CONTENT);
  }
}
