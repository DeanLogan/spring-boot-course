package com.example.cruddemo;

import java.util.List;

// import com.example.cruddemo.dao.StudentDAO;
// import com.example.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.cruddemo.dao.StudentDao;
import com.example.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
			createStudent(studentDao);
			createMultipleStudents(studentDao);
			readStudent(studentDao);
			queryForStudents(studentDao);
			queryForStudentsByLastName(studentDao);
			updateStudent(studentDao);
			deleteStudent(studentDao);
			deleteAll(studentDao);
		};
	}

	private void deleteAll(StudentDao studentDao) {
		System.err.println("Deleting all students");
		System.out.println("Deleted row count: "+studentDao.deleteAll());
	}

	private void deleteStudent(StudentDao studentDao) {
		int studentId = 3;
		System.out.println("Deleting student id: "+studentId);
		studentDao.delete(studentId);
	}

	private void updateStudent(StudentDao studentDao) {
		int studentId = 1;
		System.out.println("Getting student with id: "+studentId);
		Student myStudent = studentDao.findById(studentId);

		System.out.println("Update student");
		myStudent.setFirstName("Scooby");

		studentDao.update(myStudent);

		System.out.println("Updated student: "+myStudent);
	}

	private void queryForStudentsByLastName(StudentDao studentDao) {
		List<Student> theStudents = studentDao.findByLastName("Duck");

		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDao studentDao) {
		List<Student> theStudents = studentDao.findAll();

		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDao studentDao) {
		System.out.println("Creating new student object");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@email.com");

		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: "+theId);
		Student myStudent = studentDao.findById(theId);

		System.out.println("Found the student: "+myStudent);
	}

	private void createStudent(StudentDao studentDao) {
		System.out.println("Creating new student object");
		Student tempStudent = new Student("Paul", "Doe", "random@emali.com");

		System.out.println("Saving the student");
		studentDao.save(tempStudent);

		System.out.println("Saved student. Generated id: "+tempStudent.getId());
	}

	private void createMultipleStudents(StudentDao studentDao) {
		System.out.println("Creating new student object");
		Student tempStudent1 = new Student("Paul", "Doe", "random@emali.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@emali.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@emali.com");

		System.out.println("Saving the student");
		studentDao.save(tempStudent1);
		studentDao.save(tempStudent2);
		studentDao.save(tempStudent3);

		System.out.println("Saved student. Generated id: "+tempStudent1.getId());
		System.out.println("Saved student. Generated id: "+tempStudent2.getId());
		System.out.println("Saved student. Generated id: "+tempStudent3.getId());
	}

}
