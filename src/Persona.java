import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectStreamClass;

public class Persona implements Serializable{

	public String nombre;
	public String apellido;
	public int edad;
	public transient String dni;
	
	public Persona (String nombre, String apellido, int edad, String dni) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.edad=edad;
		this.dni=dni;

	}
	
	public static void main (String[] args) {

		Persona p1 = new Persona("Luna", "Navarro", 29, "1234X");
		Persona p2 = new Persona("Pepa", "García", 69, "4321Y");
		
		File fichero = new File("archivoPersona.dat");
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichero));
			//Guardamos las personas
			oos.writeObject(p1);
			oos.writeObject(p2);
			
			//Cerramos el stream
			oos.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		

		try {
			//Leemos las personas
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
			Persona p3 = (Persona) ois.readObject();
			Persona p4 = (Persona) ois.readObject();

			//Sacamos su info por pantalla
			String dni = (p3.dni==null)?"dni nulo":"dni no nulo"; 
			System.out.println(p3.nombre + p3.apellido + p3.edad + dni + ObjectStreamClass.lookup(p3.getClass()).getSerialVersionUID());
			dni = (p4.dni==null)?"dni nulo":"dni no nulo";
			System.out.println(p4.nombre + p4.apellido + p4.edad + dni + ObjectStreamClass.lookup(p4.getClass()).getSerialVersionUID());			
			
			//Cerramos el stream
			ois.close();
		}
		catch(Exception e){System.out.println("Excepción: "+e);}
			
	}
}