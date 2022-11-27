package com.samplebank.accountservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty(message = "is empty")
    private String name;

    @NotEmpty(message = "is empty")
    private String surname;

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;
}
