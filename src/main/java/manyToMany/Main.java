package manyToMany;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("connector");
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();
	
	private static Course fetchOrCreateCourse(String courseName,int courseId) {
	    Course course = em.createQuery("SELECT c FROM Course c WHERE c.courseName = :name", Course.class)
	                      .setParameter("name", courseName)
	                      .getResultStream()
	                      .findFirst()
	                      .orElse(null);

	    if (course == null) {
	        course = new Course();
	        course.setCourseName(courseName);
	        course.setCourseId(courseId);
	        et.begin();
	        em.persist(course);
	        et.commit();
	    }
	    return course;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What operation would you like to performed. ");
			System.out.println("  1. Insert Data");
			System.out.println("  2. Retrieve Data");
			System.out.println("  3. Update Data ");
			System.out.println("  4. Delete Data");
			
		switch(sc.nextInt()) {
		
			case 1 :{ // insert
				
				System.out.println("Enter Student Id : ");
				int id = sc.nextInt();
				System.out.println("Enter Student Name : ");
				String name = sc.next();
				
				Students student = new Students();
				student.setId(id);
				student.setName(name);
				
				
				System.out.println("Well we have four courses");
					
					System.out.println("You can choose any 2");
					System.out.println(" 1. Java");
					System.out.println(" 2. SQL");
					System.out.println(" 3. Python");
					System.out.println(" 4. JavaScript");
				
					int courseId1 = 0;
					int courseId2 = 0;
					
					String courseName1 = null;
					String courseName2 = null;
					
				    System.out.println("Enter 1st Course Name :");
				    String first = sc.next().toLowerCase();
				    switch(first) {
					    case "java" :{
					    	courseId1 = 101;
					    	courseName1 = "Java";
					    }
					    break;
					    case "sql" :{
					    	courseId1 = 102;
					    	courseName1 = "SQL";
					    }
					    break;
					    case "python" :{
					    	courseId1 = 103;
					    	courseName1 = "Python";
					    }
					    break;
					    case "javascript" :{
					    	courseId1 = 104;
					    	courseName1 = "Javascript";
					    }
					    break;
				    }
				    
				    System.out.println("Enter 2nd Course Name :");
				    String second = sc.next().toLowerCase();
				    switch(second) {
					    case "java" :{
					    	courseId2 = 101;
					    	courseName2 = "Java";
					    }
					    break;
					    case "sql" :{
					    	courseId2 = 102;
					    	courseName2 = "SQL";
					    }
					    break;
					    case "python" :{
					    	courseId2 = 103;
					    	courseName2 = "Python";
					    }
					    break;
					    case "javascript" :{
					    	courseId2 = 104;
					    	courseName2 = "Javascript";
					    }
					    break;
				    }
				
			    
			    Course course1 = fetchOrCreateCourse(courseName1, courseId1);
				Course course2 = fetchOrCreateCourse(courseName2, courseId2);
			    
			    // Assign courses to student
		        Set<Course> courses = new HashSet<>();
		        courses.add(course1);
		        courses.add(course2);
		        student.setCourses(courses);

		        // Assign students to courses
		        course1.setStudents(new HashSet<>());
		        course1.getStudents().add(student);

		        course2.setStudents(new HashSet<>());
		        course2.getStudents().add(student);

		        // Persist data
		        et.begin();
		        em.persist(student);
		        em.persist(course1);
		        em.persist(course2);
		        et.commit();	
		        
		        System.out.println();
		        System.out.println("Registration Done now Time for a fee....");
		        System.out.println();
				
			}
			break;
			
			case 2 :{ // read
				

			    
			 
			}
			break;
			
			case 3 :{ // update
		
			}
			break;
			
			case 4 :{ // delete
		
			}
			break;
		
		}
		
		
		
	}

}
