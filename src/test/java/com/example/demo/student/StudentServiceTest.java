package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    private StudentService underTest;
    @Mock private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        //underTest = MockitoAnnotations.openMocks(this);
        underTest = new StudentService(studentRepository);
    }

    @Test
    @Disabled
    void canGetAllStudents() {
        //when
        underTest.getAllStudents();
        //then
        verify(studentRepository).findAll();

    }

    @Test
    @Disabled
    void canAddStudent() {
        //given
        Student student = new Student("Jamal","jacob@gmail.com",Gender.FEMALE);
        //when
        underTest.addStudent(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }
    @Test
    @Disabled
    void willThrowWhenEmailIsTaken() {
        //given
        Student student = new Student("Jamal","jacob@gmail.com",Gender.FEMALE);
        //when
        given(studentRepository.selectExistsEmail(student.getEmail()))
                .willReturn(true);

        //then
        assertThatThrownBy(()->underTest.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " taken");
        verify(studentRepository, never()).save(any());
    }

    @Test
    @Disabled
    void deleteStudent() {
        //given
        Long studentId = 1L;
        Student student = new Student(1L,"Jamal","jacob@gmail.com",Gender.FEMALE);
        //when
        given(studentRepository.existsById(student.getId()))
          .willReturn(true);
        //then
        underTest.deleteStudent(studentId);
    }
    @Test
    void willThrowWhenIdIsTaken() {
        //given
        Long studentId = 1L;
        Student student = new Student(studentId,"Jamal","jacob@gmail.com",Gender.FEMALE);
        //when
        given(studentRepository.existsById(student.getId()))
                .willReturn(true);
       //

        //then
        assertThatThrownBy(()->underTest.deleteStudent(studentId))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Student with id " + studentId + " does not exists");
        verify(studentRepository, never()).deleteById(studentId);
        //underTest.deleteStudent(studentId);
    }
}