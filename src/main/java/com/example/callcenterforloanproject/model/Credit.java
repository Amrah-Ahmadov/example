package com.example.callcenterforloanproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Credit {
    @Id
    @SequenceGenerator(name = "CREDIT_ID_GENERATOR", allocationSize = 1, sequenceName = "CREDIT_ID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDIT_ID_GENERATOR")
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "credit")
    private List<Loan> loans;

    public Credit(Long id) {
        this.id = id;
    }
}
