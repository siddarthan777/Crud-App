package com.example.CRUDapp;

import com.example.CRUDapp.model.User;
import com.example.CRUDapp.repo.userRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepoTest
{
    @Autowired
    private userRepo dor;
    private User currentObject;

    @BeforeEach
    public void init()
    {

        currentObject = User.builder()
                .username("abc")
                .email("abc@gmail.com")
                .password("abc123")
                .build();
    }

    @Test
    public void save_ReturnsDataObject() {
        //Act
        User savedObject = dor.save(currentObject);

        //Assert
        Assertions.assertThat(savedObject).isNotNull();
        Assertions.assertThat(savedObject.getUsername()).isEqualTo(currentObject.getUsername());
    }

    @Test
    public void findByID_ReturnsDataObject() {
        //Act
        User savedObject = dor.save(currentObject);
        User returnedObject = dor.findById(savedObject.getUsername()).get();
        //Assert
        Assertions.assertThat(returnedObject).isNotNull();
        Assertions.assertThat(returnedObject.getUsername()).isEqualTo(savedObject.getUsername());
    }

}
