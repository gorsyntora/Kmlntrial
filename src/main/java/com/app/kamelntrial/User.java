package com.app.kamelntrial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")

public class User {
    @Id
    @Column(name = "name", unique = true)
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Date date;

    @OneToMany(mappedBy = "userName", cascade = CascadeType.ALL)
    private final Set<Quote> quotes = new HashSet<>();
}
