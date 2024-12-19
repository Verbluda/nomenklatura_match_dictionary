package com.example.nomenklatura_match_dictionary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="matchSequenceGenerator")
    @SequenceGenerator(name = "matchSequenceGenerator", sequenceName = "match_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "row_name")
    private String rowName;

    @Column(name = "nomenklatura_name")
    private String nomenklaturaName;
}
