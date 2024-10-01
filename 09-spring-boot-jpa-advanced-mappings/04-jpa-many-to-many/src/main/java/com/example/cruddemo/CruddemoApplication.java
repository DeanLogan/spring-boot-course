package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import com.example.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: "+theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("Pacman - how to score one million points");

		// add some reviews
		tempCourse.addReview(new Review("Good course"));
		tempCourse.addReview(new Review("Great course"));
		tempCourse.addReview(new Review("Loved it"));

		// save the course leveraging cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
		appDAO.save(tempCourse);
		System.out.println("Done");
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: "+theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Finding course id: "+theId);
		Course tempCourse = appDAO.findCourseById(theId);

		System.out.println("Updating course id:"+theId);
		tempCourse.setTitle("Enjoy the simple things");
		appDAO.update(tempCourse);

		System.out.println("Done");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Find instructor by id"+theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		// update the instructor
		System.out.println("Update instructor id: "+theId);
		tempInstructor.setLastName("TESTER");

		appDAO.update(tempInstructor);

		System.out.println("Done");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Find instructor by id"+theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associated courses"+tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor by id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor"+tempInstructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: "+theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// associate the objects
		tempInstructor.setCourses(courses);

		System.out.println("The associated courses: "+tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor by id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor"+tempInstructor);
		System.out.println("the associated courses: "+tempInstructor.getCourses());
		System.out.println("done");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor("Susan", "public", "random1@email.com");
		InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/@AWSEventsChannel", "cloud");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse1 = new Course("Air Guitar");
		Course tempCourse2 = new Course("pinball masterclass");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		System.out.println("Saving instructor: "+tempInstructor);
		System.out.println("The courses: "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Deleting instructor detail id: "+theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		// get the instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
		// print instructor detail
		System.out.println("tempInstructorDetail: "+tempInstructorDetail);
		// print the associated instructor
		System.out.println("associated instructor: "+tempInstructorDetail.getInstructor());
		System.out.println("Done");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting instructor id: "+ theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor"+tempInstructor);
		System.out.println("the associated instructorDetail only: "+tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// create instructor
		Instructor tempInstructor = new Instructor("Chad", "Darby", "random@email.com");

		// create instructor details
		InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/user/linustechtips", "hardware");

		// associate objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		System.out.println("Saving instructor: "+tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done");
	}
}
