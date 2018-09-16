package lunch.ejb.stl;

import javax.ejb.Remote;

import javaee.ole.entity.Exam;
import javaee.ole.entity.Exampaper;
import javaee.ole.entity.Exampoint;
import javaee.ole.entity.Student;


@Remote
public interface AdminBeanRemote {
	void addPaper(Exampaper paper);
	void addExam(Exam exam);
	void addUser(Student s);
	void addExampoint(Exampoint ep);
	void deletePaper(int paperid);
	void deleteExam(int examid);
	void deleteUser(String name);
	void deleteExampoint(int epid);
	
}	
