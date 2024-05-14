package com.example.CRUDapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User
{
    @Id
    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String password;



}
