package datos;
import domain.Persona;

import java.net.ConnectException;
import java.sql.*;
import java.util.*;

public class PersonasJDBC {
    private final String SQL_INSERT = "INSERT INTO persona(nombre, apellido) VALUES(?,?)";

    private final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=? WHERE id_persona=?";

    private final String SQL_DELETE = "DELETE FROM persona WHERE id_persona=?";

    private final String SQL_SELECT = "SELECT id_persona, nombre, apellido FROM persona ORDER BY id_persona";


    public int insert (String nombre, String apellido){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null; //wont use this in thi sexercice

        int rows = 0;

        try{
            conn = Conection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1; //cunter columns
            stmt.setString(index++, nombre);
            stmt.setString(index++, apellido);
            System.out.println("Execute query: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            Conection.close(stmt);
            Conection.close(conn);
        }
        return rows;
    }


    public int update(int id_persona, String nombre, String apellido){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try{
            conn = Conection.getConnection();
            System.out.println("Executing query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, nombre);
            stmt.setString(index++, apellido);
            stmt.setInt(index, id_persona);
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados: " + rows);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Conection.close(stmt);
            Conection.close(conn);
        }
        return rows;
    }

    public int delete(int id_persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try{
            conn = Conection.getConnection();
            System.out.println("Executing query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id_persona);
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados: " + rows);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Conection.close(stmt);
            Conection.close(conn);
        }
        return rows;
    }

    public List<Persona> select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<Persona>();
        try{
            conn = Conection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()){
                int id_persona = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                persona = new Persona();
                persona.setApellido(apellido);
                persona.setNombre(nombre);
                persona.setId_persona(id_persona);
                personas.add(persona);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Conection.close(conn);
            Conection.close(stmt);
            Conection.close(rs);
        }
        return personas;
    }
}
