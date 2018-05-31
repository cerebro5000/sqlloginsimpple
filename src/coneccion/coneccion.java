/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import tablas.datosusuarios;
import tablas.privilegios;
import tablas.roles;
import tablas.titulosprof;
import tablas.usuarios;
/**
 *
 * @author daniel
 */
public class coneccion {
    public static Connection coneccion;
    
    //ponemos que el metod pueda generar un error de SQLException para que le de tratamiento otro metodo
    public static Connection conectar() throws SQLException{
        /*se crean variables de configuracion*/
        String db = "ejem_login";
        String url = "jdbc:mysql://localhost:3306/"+db;
        String user = "root";
        String pass = "1234";
        try {
            //se llama el driver
            Class.forName("com.mysql.jdbc.Driver");
            //se genera coneccion
            coneccion = DriverManager.getConnection(url,user,pass);
            System.out.println("base de datos conectada");
        } catch (ClassNotFoundException ex) {
            //imprimimoes este mensaje si tenermos problema de encontrar el driver correcto
            System.out.println("no pudo encontrar el driver");
        }
        return coneccion;
    }
    
    public static boolean crearusuario(usuarios user, datosusuarios datos, roles rol, titulosprof titulo){
        //contamos con los datos del usuario su user y pass el nombre del rol y numbre del titulo profesional
        //para una query valida
        //ocupamos el id del rol no el nombre
        //ocupamos el id del titulo no el nombre
        //ocupamos el id del usuario para almacenar us datos personales
        try {
            coneccion = conectar();
            //creamos instancia
            Statement instancia = coneccion.createStatement();
            //obtenemos el registro completo del rol que venga en el objeto rol
            ResultSet resultado = instancia.executeQuery("select * from roles where nombre = \""+rol.getNombre()+"\"");
            //obtenemo el primer resultado
            resultado.first();
            //verificamos que el resultado no este vacio
            if (resultado != null) {
                //pasamos el valor de la columna id al objeto rol
                rol.setId(Integer.parseInt(resultado.getString("id")));
            }
            //buscamos el resultado de el titulo profesional en el objeto titulo
            resultado = instancia.executeQuery("select * from titulosprof where titulo=\""+titulo.getTitulo()+"\"");
            //obtenemos le primer resultado
            resultado.first();
            //verificamos que no este vacio
            if (resultado != null) {
                //obtenemos el valor del id y lo guardamos en el titulo
                titulo.setId(Integer.parseInt(resultado.getString("id")));
            }
            //insertamos en la tabla usuarios el user, pass, rol y titulo profesional de los objetos
            instancia.execute("INSERT INTO usuarios(usuario,pass,roles_id,titulosprof_id) "
                    + "values(\""+user.getUsuario()+"\",\""+user.getPass()+"\","+rol.getId()+","+titulo.getId()+")");
            //recuperamos el id del usuario que guardamos
            resultado = instancia.executeQuery("select * from usuarios where usuario=\""+user.getUsuario()+"\"");
            //obtenemos el primer resultado
            resultado.first();
            //verificamos que no este vacio
            if (resultado != null) {
                //guardamso el vlaor del id del usuario
                user.setId(Integer.parseInt(resultado.getString("id")));
            }
            //insertamso en la tabla los datos personales
            instancia.execute("INSERT INTO datosusuario(usuarios_id,nombre,apellido)"
                    + "values(\""+user.getId()+"\",\""+datos.getNombre()+"\","
                            + "\""+datos.getApellido()+"\")");
            coneccion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("no se pudo guardar el usuario");
        }
        return false;
    }
    public static boolean crearrol(roles rol, privilegios priv){
        //para poder almacenar el rol es necesario tener el id del privilegio
        try {
            //conectamos con la bd
            coneccion = conectar();
            //creamos la instancia
            Statement instancia = coneccion.createStatement();
            //mandamos el comando que regresa el privilegio
            ResultSet resultado = instancia.executeQuery("select * from privilegios where modulo =\""+priv.getModulo()+"\"");
            //regresamos el primer resultado
            resultado.first();
            //verificamos que no este vacio
            if (resultado != null) {
                //guardamos el resultado
                priv.setId(Integer.parseInt(resultado.getString("id")));
            }
            //insertamos el rol en la base de datos
            instancia.execute("INSERT INTO roles(nombre,privilegios_id) values(\""+rol.getNombre()+"\",\""+priv.getId()+"\")");
            coneccion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("error de conexion");
        }
        return false;
    }
    public static boolean crearprivilegio(privilegios priv){
        try {
            coneccion = conectar();
            Statement instancia = coneccion.createStatement();
            instancia.execute("INSERT INTO privilegios(modulo) values(\""+priv.getModulo()+"\")");
            coneccion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("error de conexion");
        }
        return false;
    }
    
    public static boolean creartitulo(titulosprof titulo){
        try {
            coneccion = conectar();
            Statement instancia = coneccion.createStatement();
            instancia.execute("INSERT INTO titulosprof(titulo) values(\""+titulo.getTitulo()+"\")");
            coneccion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("error de conexion");
        }
        return false;
    }
    //metodo ejemplo para hacer delete
    public static boolean eliminartitulo (titulosprof titulo){
        try {
            coneccion = conectar();
            Statement instancia = coneccion.createStatement();
            instancia.execute("DELETE from titulosprof where titulo=\""+titulo.getTitulo()+"\"");
            instancia.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("error de coneccion");
        }
        
        return false;
    }
}
