package com.minhan.databasedemo;

import com.minhan.databasedemo.dao.StudentDao;
import com.minhan.databasedemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DatabasedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabasedemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao, EntityManagerFactory entityManagerFactory) {
		return runner -> {
//			var idCreated = createNewStudent(studentDao);
//			printNewStudent(studentDao, 1);
//			delete(studentDao, 1);
//			printNewStudent(studentDao, 1);
//			listAll(studentDao);

//			search(entityManager);

//			update(studentDao);
//			updateAllLastName(entityManagerFactory.createEntityManager());

//			studentDao.deleteAll();

//			createMultipleStudents(studentDao);
//			System.out.println(studentDao.count());
		};
	}

	private void delete(StudentDao studentDao, Integer id) {
		studentDao.deleteById(id);
		System.out.println("Delete successfully!");
	}

//	@Transactional
	private void updateAllLastName(EntityManager entityManager) {
		entityManager.getTransaction().begin();
		var numberRowsUpdated = entityManager.createQuery("UPDATE Student SET lastName='minh'").executeUpdate();
		entityManager.getTransaction().commit();
		System.out.println(numberRowsUpdated);

//		TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
//		System.out.println(query.getResultList());
	}

	private void createMultipleStudents(StudentDao studentDao) {
		studentDao.save(new Student("an 1", "minh", "minhan1@gmail.com"));
		studentDao.save(new Student("an 2", "minh", "minhan2@gmail.com"));
		studentDao.save(new Student("an 3", "minh", "minhan3@gmail.com"));
		studentDao.save(new Student("an 4", "minh", "minhan4@gmail.com"));

		listAll(studentDao);
	}

	private void update(StudentDao studentDao) {
		Student studentToUpdate = studentDao.getById(1);
		System.out.println("Before updating: ");
		System.out.println(studentToUpdate);
		studentToUpdate.setLastName("minhan2");

		Student studentUpdated = studentDao.update(studentToUpdate);
		System.out.println("After updating");
		System.out.println(studentUpdated);
	}

	private void search(EntityManager entityManager) {
		TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE firstName='an 2'", Student.class);
		System.out.println(query.getResultList());

		System.out.println();
	 	query = entityManager.createQuery("FROM Student WHERE email='minhan@gmail.com'", Student.class);
		System.out.println(query.getResultList());

		System.out.println();
		query = entityManager.createQuery("FROM Student WHERE email LIKE '%minhan@gmail.com%'", Student.class);
		System.out.println(query.getResultList());

		System.out.println("Named Parameter");
		query = entityManager.createQuery("FROM Student WHERE firstName=:firstName OR lastName=:lastName", Student.class);
		query.setParameter("firstName", "an");
		query.setParameter("lastName", "minh");
		System.out.println(query.getResultList());

		System.out.println();
		TypedQuery<Student> query1 = entityManager.createQuery("FROM Student WHERE firstName='an' AND email='minhan@gmail.com'", Student.class);
		System.out.println(query1.getResultList());
	}

	private void listAll(StudentDao studentDao) {
		System.out.println();
		System.out.println("--Print all students--");

		List<Student> students = studentDao.findAll();
		students.forEach(System.out::println);
	}

	private int createNewStudent(StudentDao studentDao) {
		System.out.println("Creating new student object...");
		var fName = "an 2";
		var lName = "minh";
		var std = new Student(fName, lName, "minhan@gmail.com");

		System.out.println("Saving the sttudent....");
		studentDao.save(std);

		System.out.println("Saved student. Generated id: " + std.getId());
		System.out.println();
		return std.getId();
	}

	private void printNewStudent(StudentDao studentDao, Integer id) {
		System.out.println("Get student with id: " + id);
		var std = studentDao.getById(id);
		System.out.println(std);
		System.out.println();
	}
}
