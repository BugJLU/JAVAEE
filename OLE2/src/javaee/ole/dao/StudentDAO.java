package javaee.ole.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import javaee.ole.entity.Student;
import javaee.ole.utils.MD5Enc;


public class StudentDAO {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	
	static{
		factory = Persistence.createEntityManagerFactory("OLE");  
	    manager = factory.createEntityManager();
	}
	public static Student findUserByName(String name){
		Student s = manager.find(Student.class, name);
		return s;
	}
	
	public static Student findUserByMail(String mail){
		Query q = manager.createQuery("Select s from Student s where s.mail=:mail");
		q.setParameter("mail", mail);
		if(q.getResultList().size()>0)
			return (Student) q.getResultList().get(0);
		else
			return null;
	}
	
//	public static String getUserPasswd(String name){
//		Student s = findUserByName(name);
//		if(s!=null)
//			return s.getPasswd();
//		else
//			return "";
//	}
	
	public static void addUser(String name,String passwd,String mail){
		Student s = new Student();
		s.setName(name);
		s.setPasswd(MD5Enc.encodeMD5(passwd));
		s.setMail(mail);
		manager.getTransaction().begin();
		manager.persist(s);
		manager.getTransaction().commit();
		
	}
	
	public static boolean updateUser(Student s){
		manager.getTransaction().begin();
		manager.merge(s);
		manager.getTransaction().commit();
		return true;
	}
	
	public static void close(){
		try{
			manager.close();
			factory.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Student s = findUserByName("KKK");
		if(s!=null)
			System.out.println(s.getMail());
		else{
			System.out.println("not exist");
		}
		close();

	}

}
