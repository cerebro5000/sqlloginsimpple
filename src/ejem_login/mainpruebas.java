/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejem_login;

import coneccion.coneccion;
import tablas.datosusuarios;
import tablas.privilegios;
import tablas.roles;
import tablas.titulosprof;
import tablas.usuarios;

/**
 *
 * @author daniel
 */
public class mainpruebas {
    public static void main(String[] args) {
        //este main es de pruebas
        
        privilegios privilegio = new privilegios("ventas");
        roles rol = new roles("vendedor");
        usuarios maria = new usuarios("maria","mariasgamesa");
        datosusuarios datos = new datosusuarios("maria", "pancha");
        titulosprof titulo = new titulosprof("licenciatura");
        
        
        if(coneccion.crearprivilegio(privilegio))
            System.out.println("se agrego el privilegio de " + privilegio.getModulo());
        else
            System.out.println("no se pudo agregar");
        
        if(coneccion.crearrol(rol, privilegio))
            System.out.println("se agrego el rol de "+ rol.getNombre());
        else
            System.out.println("no se agrego");
        
        if(coneccion.creartitulo(titulo))
            System.out.println("sea grego correctamente");
        else
            System.out.println("");
        
        if(coneccion.crearusuario(maria, datos, rol, titulo))
            System.out.println("se agrego el usuario " + datos.getNombre());
        
        
    }
}
