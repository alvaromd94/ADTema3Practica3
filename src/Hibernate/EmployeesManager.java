package Hibernate;

import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Pojo.Employees;


public class EmployeesManager {
	
	//Tenemos el SessionFactory para hibernate que adem�s nos llama a la clase emloyees
	private static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Employees.class)
				.addAnnotatedClass(Employees.class).configure().buildSessionFactory();
		return sessionFactory;
	}

	public static void Create() {
		
		//Tenemos que crear un empleado. establecemos las diferentes variables que despu�s insertaremos
		int emp_no;
		int ano;
		int mes;
		int dia;
		String name;
		String last;
		String gender;
		int ano2;
		int mes2;
		int dia2;
		
		//Las vamos pidiendo por teclado
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduzca el id del empleado");
		emp_no=teclado.nextInt();
		System.out.println("Introduzca el d�a de nacimiento del empleado");
		dia=teclado.nextInt();
		System.out.println("Introduzca el mes de nacimiento del empleado");
		mes=teclado.nextInt();
		System.out.println("Introduzca el a�o de nacimiento del empleado");
		ano=teclado.nextInt();
		System.out.println("Introduzca el nombre del empleado");
		name=teclado.next();
		System.out.println("Introduzca el apellido del empleado");
		last=teclado.next();
		System.out.println("Introduzca el g�nero del empleado (F/M)");
		gender=teclado.next();
		System.out.println("Introduzca el d�a de contrataci�n del empleado");
		dia2=teclado.nextInt();
		System.out.println("Introduzca el mes de contrataci�n del empleado");
		mes2=teclado.nextInt();
		System.out.println("Introduzca el a�o de contrataci�n del empleado");
		ano2=teclado.nextInt();
		
		//Insertamos los datos
		Employees e = new Employees(emp_no, new GregorianCalendar(ano, mes -1, dia).getTime(), name, last, gender, new GregorianCalendar(ano2, mes2 -1, dia2).getTime());
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();
		sessionObj.save(e);
		transObj.commit();
		sessionObj.close();
		System.out.println("Employees " + e.getEmp_no() + " insertado correctamente");
	}

	//Para el read tenemos que crear la lista dentro del m�todo y despues llamar al read en el main
	public static Employees Read() {
		int emp_no;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduzca el ID del empleado");
		emp_no=teclado.nextInt();
		
		Session sessionObj = getSessionFactory().openSession();
		String query = "from Employees where emp_no="+ emp_no;
		//Aqu� creamos la lista
		List<Employees> resultado = sessionObj.createQuery(query).list();
		
		sessionObj.close();
		return resultado.get(0);
	}

	public static void Update() {
	
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduzca el ID del Empleado que desee modificar");
	    int emp_no = teclado.nextInt();
		System.out.println("Introduzca el nuevo Apellido de este empleado");
	    String lastName = teclado.next();
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();
		//Con sessionObj.load cargamos el id del empleado
		Employees EmployeesBD = (Employees) sessionObj.load(Employees.class, emp_no);
		//con setLast_name introducimos el nuevo apellido
		EmployeesBD.setLast_name(lastName);
		transObj.commit();
		sessionObj.close();
		System.out.println("Actualizado correctamente");
	}

	public static void Delete() {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduzca el id del usuario que desea eliminar");
	    int emp_no = teclado.nextInt();
		
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();
	    Employees employees = (Employees) sessionObj.load(Employees.class, emp_no);
	    //Con .delete eliminamos el registro
		sessionObj.delete(employees);
		transObj.commit();
		sessionObj.close();
		System.out.println("Eliminado correctamente");
	}

	//Con este m�todo metemos por teclado (scanner) las diferentes opciones de la pr�ctica y a cada una le pertenece un m�todo al cual llamamos
	public static void menu() throws ClassNotFoundException, SQLException {
		int opcion;
		String cadena=null;
		do {
			Scanner teclado = new Scanner(System.in);
			System.out.println("1.- Leer desde el teclado los datos de un empleado, guardarlo en variables e insertarlo en la tabla employees.");
			System.out.println("2.- Mostrar los datos de un empleado obteniendo su id por teclado.");
			System.out.println("3.- Obtener por teclado un idEmpleado y un apellido y modificar el apellido de ese empleado.");
			System.out.println("4.- Obtener por teclado un id de empleado y eliminarlo.");
			//Leemos por teclado la opci�n deseada
			opcion = teclado.nextInt();
			switch (opcion) 
			{
			case 1: 
			{
				Create();
				
				break;
			}
			case 2: 
			{
				//Este m�todo tiene que imprimir por pantalla los datos de un empleado y es por ello que adem�s del m�todo tenemos un System.out.println
				Employees empleado = Read();
				System.out.println(empleado.getEmp_no() + " " + empleado.getBirth_date() + " " + empleado.getFirst_name() + " " + empleado.getLast_name() + " " + empleado.getGender() + " " + empleado.getHire_date());
				break;
			}
			case 3: 
			{
				Update();
				break;
			}
			case 4: 
			{
				Delete();
				break;

			}
			default: 
			{
				System.out.println("No ha seleccionado ninguna opci�n correcta");
			}
			}
		} while (opcion != 4);

	}
	
	//Establezco el main donde se van a ir leyendo todas las opciones y seg�n el n�mero q pulsemos se ejecuta un m�todo u otro
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		menu();
	}
}


