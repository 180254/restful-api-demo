package org.dreando.restfuldemo.controller;

import org.dreando.restfuldemo.data.DataProvider;
import org.dreando.restfuldemo.resource.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private DataProvider dataProvider;


    @RequestMapping(value = "student", method = RequestMethod.GET)
    public List<Student> studentByID(@RequestParam(required = false) String name) {

        if (name == null || name.isEmpty()) {
            return dataProvider.getStudents();
        } else {
            return dataProvider.getStudentByName(name);
        }
    }

    @RequestMapping(value = "student/{studentID}", method = RequestMethod.GET)
    public Student studentByID(@PathVariable("studentID") int id,
                               HttpServletResponse response) {

        Student studentByID = dataProvider.getStudentByID(id);

        if (studentByID == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        } else {
            return studentByID;
        }
    }

    @RequestMapping(value = "student", method = RequestMethod.POST)
    public void addStudent(@RequestParam int id,
                           @RequestParam String name,
                           HttpServletResponse response) {

        if (dataProvider.addStudent(new Student(id, name))) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    @RequestMapping(value = "student/{studentID}", method = RequestMethod.PUT)
    public void updateStudent(@PathVariable("studentID") int id,
                              @RequestParam String name,
                              HttpServletResponse response) {

        if (dataProvider.updateStudent(new Student(id, name))) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "student/{studentID}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("studentID") int id,
                       HttpServletResponse response) {

        if (dataProvider.deleteStudent(id)) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

