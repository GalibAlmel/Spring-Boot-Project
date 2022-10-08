package com.student.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.student.sms.entity.Student;
import com.student.sms.service.StudentService;

@Controller
public class StudentController {
	//constractor basesd dependancy Injction
	private StudentService studentService;
	//Avoid @Autowire annotation becuse Spring bean has only one consractor

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	//handler method to handle List of student and return  model and view
	@GetMapping("/students")
	public String listStudent(Model model) {
		model.addAttribute("students",studentService.getAllStudents());
		return "students";

	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		//create student object to hold data
		
		Student student =new Student();
		model.addAttribute("student",student);
		return "create_student";
	}
	
	@PostMapping("students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		
		studentService.saveStudent(student);
		return "redirect:/students";
		
	}
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id,Model model) {
		model.addAttribute("student",studentService.getStudentById(id));
		return "edit_student";
	}
		
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id ,
			@ModelAttribute("student") Student student,
			Model  model) {
		//get student from databas by id method
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
        existingStudent.setFirst_Name(student.getFirst_Name());
		existingStudent.setLast_Name(student.getLast_Name());
		existingStudent.setEmail(student.getEmail());
		
		//save updated student object
		studentService.updateStudent(existingStudent);
		return "redirect:/students";
		
         } 
	//Handler method to delete sudent request
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return  "redirect:/students";
		
	}
	
}	
	
	
	
	
