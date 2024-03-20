package crudOperations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {

		EntityManagerFactory emf=Persistence.createEntityManagerFactory("ragna");
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Student s=new Student();
		s.setId(1);
		s.setName("Ritik");
		s.setBranch("ECE");
		s.setSalary(120000);
		s.setAge(25);
		
		et.begin();
		em.persist(s);
		et.commit();
		
		System.out.println("Data Entered Successfully");

	}

}
