package com.gl.controller;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.model.Employee;

@org.springframework.stereotype.Controller
public class Controller {

	@RequestMapping("/")
	public String serviceLayer() {
		return"homepage";
	}

	@RequestMapping("/employeepage")
	public String showEmployee(Model data ) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		try {

			Transaction tx=session.beginTransaction();
			Query q1=session.createQuery("from Employee");
			List<Employee>employees=q1.getResultList();
			data.addAttribute("employees",employees);


			tx.commit();
		}
		catch(Exception ex) {
			System.out.println("Hibernate Error :"+ex.getMessage());

		}

		return "showEmployee";

	}

	@RequestMapping("/addEmployee")
	public String addCarForm() {

		return "addEmployee";

	}

	@PostMapping("/employeeInsert")
	public String insertEmployee(@RequestParam String employeeName,@RequestParam String employeeAddress,@RequestParam String employeePhone,@RequestParam float employeeSalary,Model data) {

		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		try {
			Transaction tx=session.beginTransaction();
			Employee c1= new Employee( employeeName, employeeAddress, employeePhone, employeeSalary);
			session.save(c1);
			Query q1=session.createQuery("from Employee");
			List<Employee>employees=q1.getResultList();
			data.addAttribute("employees",employees);
			tx.commit();
		}
		catch(Exception ex) {
			System.out.println("Hibernate Error :"+ex.getMessage());

		}
		return "showEmployee";

	}

	@GetMapping("/update-emp-form")
	public String updateEmpForm(@RequestParam int id,Model data) {

		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		try {

			Employee updateEmp=session.get(Employee.class, id);
			data.addAttribute("employee",updateEmp);


		}
		catch(Exception ex) {
			System.out.println("Hibernate Error :"+ex.getMessage());

		}
		return "updateEmp";



	}

	@PostMapping("/updatedEmployee")
	public String updateEmployee(@RequestParam int id,@RequestParam String employeeName,@RequestParam String employeeAddress,@RequestParam String employeePhone,@RequestParam float employeeSalary,Model data) {

		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		try {
			Transaction tx=session.beginTransaction();
			Employee e1= new Employee(id,employeeName,employeeAddress,employeePhone,employeeSalary);
			session.update(e1);
			Query q1=session.createQuery("from Employee");
			List<Employee>employees=q1.getResultList();
			data.addAttribute("employees",employees);
			tx.commit();
		}
		catch(Exception ex) {
			System.out.println("Hibernate Error :"+ex.getMessage());

		}
		return "showEmployee";

	}
	@PostMapping("/delete-employee")
	public String deleteEmplpoyee(@RequestParam int id,Model data) {

		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		try {

			Transaction tx=session.beginTransaction();
			Employee deleteEmployee=new Employee(id, "", "", "", 0);
			session.delete(deleteEmployee);
			Query q1=session.createQuery("from Employee");
			List<Employee>employees=q1.getResultList();
			data.addAttribute("employees",employees);


			tx.commit();
		}
		catch(Exception ex) {
			System.out.println("Hibernate Error :"+ex.getMessage());

		}
		return "showEmployee";



	}

}
