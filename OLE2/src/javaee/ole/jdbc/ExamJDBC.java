package javaee.ole.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javaee.ole.dao.ExamDAO;
import javaee.ole.dao.ExampaperDAO;
import javaee.ole.entity.Exam;
import javaee.ole.entity.Exampaper;

public class ExamJDBC {
	private static String url = "jdbc:mysql://localhost:3306/school?useSSL=false";
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e){
			//System.out.println("\n Error Loading Mysql Driver ...\n");
			System.out.println(e);
		}
	}
	
	public static List<Exam> searchDueExam(){
		String sql = "select * from exam where date > ?";
		List<Exam> exams = new LinkedList<Exam>();
		try {
			Connection conn = DriverManager.getConnection(url,"root","sh11131719");
			PreparedStatement pres = conn.prepareStatement(sql);
			String curTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			//System.out.println(curTime);
			pres.setString(1, curTime);
			ResultSet rs = pres.executeQuery();
			while(rs.next()){
				Exam exam = new Exam();
				exam.setId(rs.getInt("id"));
				exam.setExamtitle(rs.getString("examtitle"));
				exam.setDate(rs.getString("date"));
				exam.setExampaperid(rs.getInt("exampaperid"));
				exams.add(exam);
			}
			rs.close();
			pres.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exams;
	}
	public static List<Exam> searchMyExam(String name){
		String sql = "select examid from stuexam where stuname = ?";
		List<Exam> exams = new LinkedList<Exam>();
		try {
			Connection conn = DriverManager.getConnection(url,"root","sh11131719");
			PreparedStatement pres = conn.prepareStatement(sql);
			pres.setString(1, name);
			ResultSet rs = pres.executeQuery();
			while(rs.next()){
				int id = rs.getInt("examid");
				Exam exam = ExamDAO.getExam(id);
				if(exam!=null)
					exams.add(exam);
			}
			rs.close();
			pres.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exams;
	}
	public static void main(String[] args) {
		searchDueExam();

	}

}
