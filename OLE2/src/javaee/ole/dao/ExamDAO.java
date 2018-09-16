package javaee.ole.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javaee.ole.entity.Exam;
import javaee.ole.entity.Exampaper;

public class ExamDAO {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	static{
		factory = Persistence.createEntityManagerFactory("OLE");  
	    manager = factory.createEntityManager();
	}
	
	public static Exam getExam(int examid){
		return manager.find(Exam.class, examid);
	}
	
	public static void addExam(Exam e){
		manager.getTransaction().begin();
		manager.persist(e);
		manager.getTransaction().commit();
	}
	
	private static void addExam(String date,String title){
		Exampaper p = ExampaperDAO.getPaperByTitle(title);
		if(p!=null){
			Exam e = new Exam();
			e.setDate(date);
			e.setExamtitle(p.getTitle());
			e.setExampaperid(p.getId());
			addExam(e);
			System.out.println("add  a exam");
		}
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
		addExam("2018-12-16 15:30:00", "2018-12-16 CET6");
		close();
	}

}
