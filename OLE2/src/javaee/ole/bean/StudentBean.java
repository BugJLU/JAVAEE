package javaee.ole.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javaee.ole.dao.EJBRemoteClient;
import javaee.ole.dao.StudentDAO;
import javaee.ole.entity.Exam;
import javaee.ole.entity.Exampaper;
import javaee.ole.entity.Student;
import javaee.ole.jdbc.ExamJDBC;
import javaee.ole.utils.MD5Enc;
import javaee.ole.utils.MailSender;
import javaee.ole.utils.RandPassword;
import lunch.ejb.stf.UserBeanStfRemote;

@ManagedBean
@SessionScoped
public class StudentBean {
	private String uname;
	private String passwd;
	private String mail;
	private boolean issub;
	private String sublab;
	private List<Exam> examlist;
	private UserBeanStfRemote us;

	public StudentBean(){
		issub = false;
		sublab = "¶©ÔÄ";
		us = null;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String login(){
		Student s = StudentDAO.findUserByName(uname);
		if(s!=null&&MD5Enc.encodeMD5(passwd).equals(s.getPasswd())){
			if(us==null)
				us = (UserBeanStfRemote) EJBRemoteClient.lookUpRemoteStatefulEJB("UserBeanStf", "lunch.ejb.stf.UserBeanStfRemote");
			us.setLog(true);
			us.setUname(uname);
			return "succ";
		}else
			return "falu";
	}
	public String register(){
		StudentDAO.addUser(uname, passwd, mail);
		return "succ";
	}
	public String findpass(){
		String pass = RandPassword.genPass(10);
		Student s = StudentDAO.findUserByMail(mail);
		if(s!=null){
			try{
				MailSender.sendMail(mail, "ÕÒ»ØÃÜÂë", pass);
				s.setPasswd(MD5Enc.encodeMD5(pass));
				StudentDAO.updateUser(s);
				return "succ";
			}catch(Exception e){
				return "falu";
			}
		}
		return "falu";
	}
	
	public String joinExam(Exam exam){
		if(us!=null&&us.isLog()){
			return "logsucc";
		}else
			return "logfalu";
	}
	public String seeMyExam(Exampaper exam){
		if(us!=null&&us.isLog()){
			return "logsucc";
		}else
			return "logfalu";
	}
	public String showExams(){
		examlist = ExamJDBC.searchDueExam();
		if(examlist.size()>0)
			System.out.println(examlist.size());
		else
			System.out.println("no  exam");
		return null;
	}
	public String myExam(){
		if(us!=null&&us.isLog()){
			examlist = ExamJDBC.searchMyExam(us.getUname());
			return "logsucc";
		}
		return "logfalu";
	}
	public String subscribe(){
		return null;
	}
	public String getNotice(){
		return null;
	}
	public List<Exam> getExamlist(){
		return examlist;
	}
	
}
