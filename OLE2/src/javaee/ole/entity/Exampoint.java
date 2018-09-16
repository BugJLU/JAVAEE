package javaee.ole.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the exampoint database table.
 * 
 */
@Entity
@NamedQuery(name="Exampoint.findAll", query="SELECT e FROM Exampoint e")
public class Exampoint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int category;

	@Lob
	private String content;

	private String date;

	@Lob
	private String solution;

	private int subject;

	public Exampoint() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory() {
		return this.category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSolution() {
		return this.solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public int getSubject() {
		return this.subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

}