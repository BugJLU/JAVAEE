package javaee.ole.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

import javaee.ole.dao.AdminDAO;
import javaee.ole.dao.EJBRemoteClient;
import javaee.ole.dao.ExamDAO;
import javaee.ole.dao.ExampaperDAO;
import javaee.ole.entity.Exam;
import javaee.ole.entity.Exampaper;
import javaee.ole.entity.Exampoint;
import javaee.ole.entity.Teacher;
import javaee.ole.utils.MD5Enc;
import lunch.ejb.stl.AdminBeanRemote;

@ManagedBean
@javax.faces.bean.SessionScoped
public class AdminBean {
	String papertitle;
	String papersubj;
	String username;
	String userpasswd;
	String usermail;
	String pointcnt;
	String pointsubj;
	String pointcate;
	String pointsolu;
	String id;
	String passwd;
	//String examdate_y,examdate_mon,examdate_d,examdate_h,examdate_m;
	String examdate;
	LinkedHashSet<Exampoint> exampoints;
	boolean islog;
	AdminBeanRemote adb;
	public AdminBean(){
		islog = false;
		adb = null;
	}
	public String getPapertitle() {
		return papertitle;
	}


	public void setPapertitle(String papertitle) {
		this.papertitle = papertitle;
	}


	public String getPapersubj() {
		return papersubj;
	}
	
	public String getExamdate() {
		return examdate;
	}
	
	public void setExamdate(String examdate) {
		this.examdate = examdate;
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUserpasswd() {
		return userpasswd;
	}


	public void setUserpasswd(String userpasswd) {
		this.userpasswd = userpasswd;
	}


	public String getUsermail() {
		return usermail;
	}


	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}


	public String getPointcnt() {
		return pointcnt;
	}


	public void setPointcnt(String pointcnt) {
		this.pointcnt = pointcnt;
	}


	public String getPointsubj() {
		return pointsubj;
	}


	public void setPointsubj(String pointsubj) {
		this.pointsubj = pointsubj;
	}


	public String getPointcate() {
		return pointcate;
	}


	public void setPointcate(String pointcate) {
		this.pointcate = pointcate;
	}


	public String getPointsolu() {
		return pointsolu;
	}


	public void setPointsolu(String pointsolu) {
		this.pointsolu = pointsolu;
	}
	public String login(){
		Teacher t = AdminDAO.getAdmin(id);
		if(t!=null&&MD5Enc.encodeMD5(passwd).equals(t.getPasswd())){
			islog=true;
			if(adb==null)
				adb = (AdminBeanRemote) EJBRemoteClient.lookUpRemoteEJB("AdminBean", "lunch.ejb.stl.AdminBeanRemote");
			return "succ";
		}else{
			return "falu";
		}
	}
	public String addPaper(){
		Exampaper paper = new Exampaper();
		paper.setTitle(papertitle);
		paper.setSubject(papersubj);
		paper.setDate(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()));
		adb.addPaper(paper);
		return null;
	}
	public void addExam(){
		Exampaper p = ExampaperDAO.getPaperByTitle(papertitle);
		if(p!=null){
			Exam e = new Exam();
			e.setDate(examdate);
			e.setExamtitle(p.getTitle());
			e.setExampaperid(p.getId());
			adb.addExam(e);
		}
	}
	public void addPoint()
	{}
	
	public void deletePoint(Exampoint point)
	{
		adb.deleteExampoint(point.getId());
	}
}
