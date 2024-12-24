package com.gsmct.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity(name = "state_t")
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class State {

  @Id
  private String name;

  public State(String name) {
    this.name = name;
  }
}
