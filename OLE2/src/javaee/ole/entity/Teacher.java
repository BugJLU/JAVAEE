package javaee.ole.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the teacher database table.
 * 
 */
@Entity
@NamedQuery(name="Teacher.findAll", query="SELECT t FROM Teacher t")
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String passwd;

	private int privl;

	public Teacher() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getPrivl() {
		return this.privl;
	}

	public void setPrivl(int privl) {
		this.privl = privl;
	}

}