package crud_operation;

import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class Main {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("connector");
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();

	public static void main(String[] args) {

		Scanner sc =new Scanner(System.in);
		
		System.out.println(" What operation would you like to performed");
		for(;;) {
			System.out.println("  1. Insert Data");
			System.out.println("  2. Update Data");
			System.out.println("  3. Retrieve Data");
			System.out.println("  4. Delete Data");
			System.out.println("  5. Exit");
			
			int input = sc.nextInt();
			
			switch(input) {
				
			case 1:{  // Insert
				
					System.out.println("Enter Name : ");
					String name = sc.next();
					System.out.println("Enter Surame : ");
					String surname = sc.next();
					System.out.println("Enter Pan Number : ");
					String pan = sc.next();
				
					//max id
					int id = 1 ; 
					Query query = em.createQuery("SELECT MAX(s.id) FROM Student s");
					Integer maxId = (Integer) query.getSingleResult();
					if(maxId != null) {
						id = maxId+1 ;
					}
					
					Student student = new Student();
					student.setId(id);
					student.setName(name);
					student.setSurname(surname);
					student.setPan_number(pan);
					
					et.begin();
					em.persist(student);
					et.commit();
					System.out.println();
					System.out.println("Data Entered Successfully");
					System.out.println();
			//		em.close(); .... don't close here if u close here then multiple insert is not goinna performed
					//Exception in thread "main" java.lang.IllegalStateException: Session/EntityManager is closed
			    }
				break;
				
			case 2 :{  // update
				
					System.out.println("Enter Id : ");
					int id = sc.nextInt();
					
					Student student = em.find(Student.class, id);
					if(student != null) {
						
						System.out.println("What u want to update");
						System.out.println(" 1. Name.");
						System.out.println(" 2. Surname.");
						System.out.println(" 3. Pan Number");
						
						switch(sc.nextInt()) {
						
						case 1: { // name
							
							System.out.println(" Enter new Name");
							String name = sc.next();
							student.setName(name);
							System.out.println();
							System.out.println("Name Updated successfully");
							System.out.println();
							
							}
						 break;
						 
						case 2: { // surname
							
							System.out.println(" Enter new Surame");
							String surname = sc.next();
							student.setSurname(surname);
							System.out.println();
							System.out.println("Surname Updated successfully");
							System.out.println();
							
							}
					 	 break;
					 	 
						case 3: { //Pan
							
							System.out.println(" Enter new Pan Number");
							String pan = sc.next();
							student.setPan_number(pan);
							System.out.println();
							System.out.println("Pan Updated successfully");
							System.out.println();
							
							}
					 	 break;
					 	 
						}
					}
				
			    }
				break;
				
			case 3:{ // read
				
					System.out.println(" Enter Id : ");
					int id = sc.nextInt();
					Student student = em.find(Student.class, id);
					if(student != null) {
						System.out.println();
						System.out.println(" Id : " + student.getId());
						System.out.println(" Name : " + student.getName());
						System.out.println(" Surname : " + student.getSurname());
						System.out.println(" Pan : " + student.getPan_number());
						System.out.println();
					}else {
						System.out.println("Student not found!");
					}
					
					//retrieve all.....
					
//					List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
//					for (Student s : students) {
//					    System.out.println("ID: " + s.getId() + ", Name: " + s.getName());
//					}
				}
				break;
				
			case 4 :{ // delete
					
					System.out.println(" Enter Id :");
					int id = sc.nextInt();
					
					Student student = em.find(Student.class, id);
					
					et.begin();
					em.remove(student);
					et.commit();
					
					System.out.println();
					System.out.println("Data removed");
					System.out.println();
				
			     }
				break;
				
			case 5 : // Exit....
				System.out.println("Thank you for visiting");
				em.close();
				System.exit(0);
				break;
			
			}
		}
		
		
	}

}
