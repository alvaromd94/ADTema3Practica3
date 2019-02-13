package Pojo;

import java.util.Date;

import javax.persistence.*;
 //Como siempre creamos la clase pojo
@Entity
@Table(name = "employees")
public class Employees {
	@Id
	//Tenemos que quitarle el Generated value porque el id en la BD no es autoincrement
	@Column(name = "emp_no")
	private int emp_no;
	@Column(name = "birth_date")
	private Date birth_date;
	@Column(name = "first_name")
	private String first_name;
	@Column(name = "last_name")
	private String last_name;
	@Column(name = "gender")
	private String gender;
	@Column(name = "hire_date")
	private Date hire_date;
	
	
	
	public Employees() {
		setEmp_no(0);

		setBirth_date(new Date());
		setFirst_name("");
		setLast_name("");
		setGender("");
		setHire_date(new Date());
		
	}
	public Employees(int e,Date bd, String fn, String ln, String g, Date hd) {
		setEmp_no(e);
		setBirth_date(bd);
		setFirst_name(fn);
		setLast_name(ln);
		setGender(g);
		setHire_date(hd);
	}
	
	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}


}