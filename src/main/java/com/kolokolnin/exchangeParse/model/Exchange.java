package com.kolokolnin.exchangeParse.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchange_table")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "_char_name")
    private String charName;

    @Column(name = "_nominal")
    private int nominal;

    @Column(name = "_name")
    private String name;

    @Column(name = "_value")
    private double value;

    @Column(name = "_changed")
    private String changed;
}
