package com.app.kamelntrial;

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
    int id;

   //  @ManyToOne(fetch = FetchType.LAZY, optional = false)
   // @JoinColumn(name = "user_name")
   // User user;

    @Column(name = "user_name")
    String userName;

    @Column(name = "text")
    String text;
    @Column(name = "vote")
    Integer vote;

}
