package com.gsmct;

import com.gsmct.entities.*;
import com.gsmct.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Stream;

import static com.gsmct.enums.AddressTypeEnum.HOME_OWNER_PERMANENT;
import static com.gsmct.enums.BoardMemberTypeEnum.HOME_OWNER;
import static com.gsmct.utils.ConstantsAndUtilities.DEFAULT_COUNTRY;

@SpringBootApplication
@Slf4j
public class GsmctBackendApplication {

  @Autowired
  private PasswordEncoder passwordEncoder;

  public static void main(String[] args) {
    SpringApplication.run(GsmctBackendApplication.class, args);
  }

  /*@Bean
  CommandLineRunner init(AdminRepository userRepository, StateRepository stateRepository, AssociationDetailsRepository associationRepository,
                         AddressTypeRepository addressTypeRepository, BoardMemberTypeRepository boardMemberTypeRepository,
                         AssociationTypeRepository associationTypeRepository) {
    return args -> {
      associationRepository.deleteAll();
      associationTypeRepository.deleteAll();
      boardMemberTypeRepository.deleteAll();
      userRepository.deleteAll();
      addressTypeRepository.deleteAll();
      stateRepository.deleteAll();

      List<Admin> users = List.of(new Admin("srini@gcmct.com", passwordEncoder.encode("srini"), "Srini"),
        new Admin("niranjan@gcmct.com", passwordEncoder.encode("niranjan"), "Niranjan"));
      userRepository.saveAll(users);

      Stream.of("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming")
              .forEach(name -> stateRepository.save(new State(name)));

      Stream.of(new AddressType(HOME_OWNER_PERMANENT.name(), "Home Owner Permanent Address")).forEach(addressTypeRepository::save);
      Stream.of(new BoardMemberType(HOME_OWNER.name(), "Home Owner")).forEach(boardMemberTypeRepository::save);
      Stream.of(new AssociationType(1, "Housing Cooperative")).forEach(associationTypeRepository::save);

       AssociationType associationType = associationTypeRepository.findById(1).orElse(null);
      AddressType addressType = addressTypeRepository.findById(HOME_OWNER_PERMANENT.name()).orElse(null);

      AssociationDetails association1 = new AssociationDetails(1, "Gated Colorado", new Address(1, addressType, "No.209, 5th Cross, Colorado",
        "Colorado", "Colorado", "3893227382", DEFAULT_COUNTRY), associationType);
      AssociationDetails association2 = new AssociationDetails(2, "Association California", new Address(2, addressType,
        "No.14, 3rd Main, California", "California", "California", "123-532", DEFAULT_COUNTRY), associationType);
      AssociationDetails association3 = new AssociationDetails(3, "Ashwa Surya", new Address(3, addressType, "No.354, 9th Cross, Connecticut",
        "Connecticut", "Connecticut", "732-643", DEFAULT_COUNTRY), associationType);
      AssociationDetails association4 = new AssociationDetails(4, "Beautiful Florida", new Address(4, addressType,
        "No.10, 10th Main, Florida", "Florida", "Florida", "108-901", DEFAULT_COUNTRY), associationType);
      associationRepository.saveAllAndFlush(List.of(association1, association2, association3, association4));
    };
  }*/
}
