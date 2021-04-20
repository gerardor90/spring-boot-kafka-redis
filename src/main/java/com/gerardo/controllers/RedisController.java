package com.gerardo.controllers;

import com.gerardo.models.Student;
import com.gerardo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class RedisController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/redis/{id}/{name}")
    public String create(@PathVariable String id, @PathVariable String name) {
        Student student = new Student(id, name, Student.Gender.MALE, 1);
        studentRepository.save(student);
        return "Valor " + student.toString() + " añadido en redis.";
    }

    @GetMapping("/redis/{id}")
    public String findOne(@PathVariable String id) {
        Student retrievedStudent = studentRepository.findById(id);
        return "Estudiante " + retrievedStudent.toString() + " encontrado.";
    }

    @GetMapping("/redis")
    public String findAll() {
        Map<String, Student> students = studentRepository.findAll();
        String response = "Lista de estudiante encontrados: ";
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            response += entry.getKey() + " - " + entry.getValue() + "<br>";
        }
        return response;
    }

    @GetMapping("/redis/delete/{id}")
    public String delete(@PathVariable String id) {
        studentRepository.delete(id);
        return "Estudiante " + id + " eliminado.";
    }

    @GetMapping("/redis/update/{id}/{name}")
    public String update(@PathVariable String id, @PathVariable String name) {
        Student retrievedStudent = studentRepository.findById(id);
        retrievedStudent.setName(name);
        studentRepository.save(retrievedStudent);
        return "Estudiante " + retrievedStudent.toString() + " actualizado.";
    }

}
