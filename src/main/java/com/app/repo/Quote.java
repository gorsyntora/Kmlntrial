package com.app.repo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "quotes")

public class Quote {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

   //  @ManyToOne(fetch = FetchType.LAZY, optional = false)
   // @JoinColumn(name = "user_name")
   // User user;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "text")
    private String text;
    @Column(name = "vote")
    private Integer vote;

}
