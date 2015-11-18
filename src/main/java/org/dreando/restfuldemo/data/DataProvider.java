package org.dreando.restfuldemo.data;

import org.dreando.restfuldemo.resource.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProvider {

    private final List<Student> students = new ArrayList<>(10);

    public DataProvider() {
        students.add(new Student(1, "Marcin J"));
        students.add(new Student(2, "Andrzej P"));
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudentByID(int number) {
        return students.stream()
                .filter(i -> i.getId() == number)
                .findAny().orElse(null);
    }

    public List<Student> getStudentByName(String name) {
        return students.stream()
                .filter(i -> i.getName().equals(name))
                .collect(Collectors.toList());
    }

    public boolean addStudent(Student student) {
        return getStudentByID(student.getId()) == null
                && students.add(student);
    }

    public boolean updateStudent(Student student) {
        Student student1 = getStudentByID(student.getId());
        if ((student1 == null)) return false;

        student1.setName(student.getName());
        return true;
    }

    public boolean deleteStudent(int id) {
        Student student1 = getStudentByID(id);
        return student1 != null && students.remove(student1);

    }
}
