package javaee.ole.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javaee.ole.entity.Exam;
import javaee.ole.entity.Exampaper;
import javaee.ole.entity.Teacher;

public class AdminDAO {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	static{
		factory = Persistence.createEntityManagerFactory("OLE");  
	    manager = factory.createEntityManager();
	}
	
	public static Teacher getAdmin(String id){
		return manager.find(Teacher.class, id);
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

	}

}
