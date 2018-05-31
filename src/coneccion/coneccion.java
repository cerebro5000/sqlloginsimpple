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
    public static Connection conectar() throws SQLException{
        
        String db = "ejem_login";
        String url = "jdbc:mysql://localhost:3306/"+db;
        String user = "root";
        String pass = "1234";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            coneccion = DriverManager.getConnection(url,user,pass);
            System.out.println("base de datos conectada");
        } catch (ClassNotFoundException ex) {
            System.out.println("no pudo encontrar el driver");
        }
        return coneccion;
    }
    
    public static boolean crearusuario(usuarios user, datosusuarios datos, roles rol, titulosprof titulo){
        try {
            coneccion = conectar();
            Statement instancia = coneccion.createStatement();
            ResultSet resultado = instancia.executeQuery("select * from roles where nombre = \""+rol.getNombre()+"\"");
            resultado.first();
            if (resultado != null) {
                rol.setId(Integer.parseInt(resultado.getString("id")));
            }
            instancia.execute("INSERT INTO usuarios(usuario,pass,roles_id,) values(\""+user.getUsuario()+"\",\""+user.getPass()+"\","+rol.getId()+")");
            resultado = instancia.executeQuery("select * from usuarios where usuario=\""+user.getUsuario()+"\"");
            resultado.first();
            if (resultado != null) {
                user.setId(Integer.parseInt(resultado.getString("id")));
            }
            instancia.execute("INSERT INTO datosusuario(usuarios_id,nombre,apellido,titulosprof)"
                    + "values(\""+user.getId()+"\",\""+datos.getNombre()+"\","
                            + "\""+datos.getApellido()+"\",\""+titulo.getId()+"\")");
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static boolean crearrol(roles rol, privilegios priv){
        
        try {
            coneccion = conectar();
            Statement instancia = coneccion.createStatement();
            ResultSet resultado = instancia.executeQuery("select * from privilegios where modulo =\""+priv.getModulo()+"\"");
            resultado.first();
            if (resultado != null) {
                priv.setId(Integer.parseInt(resultado.getString("id")));
            }
            instancia.execute("INSERT INTO roles(nombre,privilegios_id) values(\""+rol.getNombre()+"\",\""+priv.getId()+"\")");
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
            instancia.execute("INSERT INTO titulosprof(nombre) values(\""+titulo.getTitulo()+"\")");
            coneccion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("error de conexion");
        }
        return false;
    }
}
