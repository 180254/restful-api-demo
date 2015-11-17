package org.dreando.restfuldemo.controller;

import org.dreando.restfuldemo.data.DataProvider;
import org.dreando.restfuldemo.resource.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private DataProvider dataProvider;


    @RequestMapping(value = "student/{studentID}", method = RequestMethod.GET)
    public Student studentByID(@PathVariable("studentID") int id) {
        return dataProvider.getStudentByID(id);
    }

    @RequestMapping(value = "student", method = RequestMethod.GET)
    public List<Student> studentByID(@RequestParam(required = false) String name) {
        if (name == null || name.isEmpty()) {
            return dataProvider.getStudents();
        }
        return dataProvider.getStudentByName(name);
    }

    @RequestMapping(value = "student", method = RequestMethod.POST)
    public boolean addStudent(@RequestParam int id,
                              @RequestParam String name) {
        return dataProvider.addStudent(new Student(id, name));
    }

    @RequestMapping(value = "student/{studentID}", method = RequestMethod.PUT)
    public boolean updateStudent(@PathVariable("studentID") int id,
                                 @RequestParam String name) {
        return dataProvider.updateStudent(new Student(id, name));
    }

    @RequestMapping(value = "student/{studentID}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable("studentID") int id) {
        return dataProvider.deleteStudent(id);
    }
}

