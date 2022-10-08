package com.student.sms.service;

import java.util.List;

import com.student.sms.entity.Student;

public interface StudentService {
	List<Student> getAllStudents();
	
	Student saveStudent(Student student);
	//no need to write public becuse method within interface are automatically public,absract
	
	Student getStudentById(Long id);
	
	Student updateStudent(Student student);
	void deleteStudentById(Long id);

}
