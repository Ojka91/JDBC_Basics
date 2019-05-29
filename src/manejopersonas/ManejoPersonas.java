package manejopersonas;
import datos.PersonasJDBC;
import domain.Persona;
import java.util.List;

public class ManejoPersonas {
    public static void main(String[] args){
        PersonasJDBC personasJDBC = new PersonasJDBC();

        //test insert method
        personasJDBC.insert("fbh", "ELPRO");

        //test update method
        //personasJDBC.update(5, "comino", "pelotas");

        //test delete method
        //personasJDBC.delete(1);

        //test select method
       // List<Persona> personas = personasJDBC.select();
       // for (Persona persona:personas) {
        //    System.out.println(persona);
        //}
    }
}
