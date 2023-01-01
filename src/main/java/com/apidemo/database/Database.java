package com.apidemo.database;

import com.apidemo.models.Student;
import com.apidemo.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Student student1 = new Student("namlcp", new java.util.Date("11/12/2019"), 12);
//                Student student2 = new Student("maihe", new java.util.Date("11/12/2019"), 12);
//                logger.info("insert data: " + studentRepository.save(student1));
//                logger.info("insert data: " + studentRepository.save(student2));
            }
        };
    }
}
