package com.mongo.project1;

import com.mongo.project1.model.User;
import com.mongo.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
@EnableMongoRepositories
public class Project1Application implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner in = new Scanner(System.in);
		int choice;
		String name, email, contact, designation;

		while(true) {
			System.out.println("1. Create User\n2. Update User\n3. Delete User\n4. Get Users\n5. Get User\nEnter your choice: ");
			choice = in.nextInt();
			switch (choice) {
				case 1:
					try {
						Random random = new Random();
						in.nextLine();
						System.out.println("Name: ");
						name = in.nextLine();
						System.out.println("Designation: ");
						designation = in.nextLine();
						System.out.println("Email: ");
						email = in.nextLine();
						System.out.println("Contact: ");
						contact = in.nextLine();
						userRepository.save(new User(Integer.toString(random.nextInt(1000000)), name, designation, email, contact));
						System.out.println("User added");
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						in.nextLine();
						System.out.println("Enter name to be updated");
						name = in.nextLine();
						User user = userRepository.findUserByName(name);
						System.out.println("Designation: ");
						designation = in.nextLine();
						System.out.println("Email: ");
						email = in.nextLine();
						System.out.println("Contact: ");
						contact = in.nextLine();

						if(email.isEmpty()) {
							user.setEmail(user.getEmail());
						}
						if(contact.isEmpty()) {
							user.setContact(user.getContact());
						}
						if(designation.isEmpty()) {
							user.setDesignation(user.getDesignation());
						} else {
							user.setContact(contact);
							user.setEmail(email);
							user.setDesignation(designation);
						}

						userRepository.save(user);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						System.out.println("Enter name to be deleted: ");
						name = in.nextLine();
						User userToDelete = userRepository.findUserByName(name);
						userRepository.delete(userToDelete);
						System.out.println("User deleted");
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 4:
					List<User> users = userRepository.findAll();
					if(users.isEmpty()) {
						System.out.println("No Users");
					} else {
						for(User u : users) {
							System.out.println(u.getName() +"\n"+u.getDesignation());
						}
					}
					break;
				case 5:
					System.out.println("Enter name to get from database");
					in.nextLine();
					name = in.nextLine();
					User us_er = userRepository.findUserByName(name);
					System.out.println(us_er.getName() + "\n" + us_er.getDesignation() + "\n" + us_er.getContact() + "\n" + us_er.getEmail());
					break;
				default:
					System.out.println("Invalid Choice");
			}
		}
	}

}
