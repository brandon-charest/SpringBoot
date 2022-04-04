package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

  @Bean
  CommandLineRunner commandLineRunner(StudentRepository repository) {
    return args -> {
      Student bob = new Student(
          "Bob",
          "Bob.Test@gmail.com",
          LocalDate.of(2000, Month.APRIL, 22)
      );

      Student alex = new Student(
          "Alex",
          "Alex221@gmail.com",
          LocalDate.of(2007, Month.FEBRUARY, 1)
      );

      repository.saveAll(
          List.of(bob, alex)
      );
    };
  }
}
