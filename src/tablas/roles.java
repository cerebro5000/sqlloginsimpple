/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

/**
 *
 * @author daniel
 */
public class roles {
    Integer id;
    String nombre;
    Integer privilegio;

    public roles(Integer id, String nombre, Integer privilegio) {
        this.id = id;
        this.nombre = nombre;
        this.privilegio = privilegio;
    }

    public roles(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public roles(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(Integer privilegio) {
        this.privilegio = privilegio;
    }
}
