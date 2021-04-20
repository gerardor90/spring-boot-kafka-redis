package com.gerardo.repositories;

import com.gerardo.models.Student;
import java.util.Map;

public interface StudentRepository {

    void save(Student student);
    Map<String, Student> findAll();
    Student findById(String id);
    void update(Student student);
    void delete(String id);

}
