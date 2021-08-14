package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class StudentRepositoryTest {
@Autowired
private StudentRepository underTest;

    @AfterEach
    void tearDown() {
       underTest.deleteAll();
    }

    @Test
    void ItShouldCheckIfEmailExists() {
        //given
        String email = "jamila@gmail.com";
        Student student = new Student("Jamal",email,Gender.FEMALE);

        underTest.save(student);
        //when
        boolean expected = underTest.selectExistsEmail(email);

        //then
        assertThat(expected).isTrue();
    }
    @Test
    void ItShouldCheckIfEmailDoesNotExist() {
        //given
        String email = "jakes12345@gmail.com";
        Student student = new Student("Jamal",email,Gender.FEMALE);

        //when
        boolean expected = underTest.selectExistsEmail(email);

        //then
        assertThat(expected).isFalse();
    }
}