package javaee.ole.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the exam database table.
 * 
 */
@Entity
@NamedQuery(name="Exam.findAll", query="SELECT e FROM Exam e")
public class Exam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String date;

	private int exampaperid;

	private String examtitle;

	public Exam() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getExampaperid() {
		return this.exampaperid;
	}

	public void setExampaperid(int exampaperid) {
		this.exampaperid = exampaperid;
	}

	public String getExamtitle() {
		return this.examtitle;
	}

	public void setExamtitle(String examtitle) {
		this.examtitle = examtitle;
	}

}