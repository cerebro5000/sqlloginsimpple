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
public class usuarios {
    Integer id;
    String usuario;

    public usuarios(Integer id, String usuario, String pass, Integer rol) {
        this.id = id;
        this.usuario = usuario;
        this.pass = pass;
        this.rol = rol;
    }

    public usuarios(Integer id, String usuario, String pass) {
        this.id = id;
        this.usuario = usuario;
        this.pass = pass;
    }

    public usuarios(String usuario, String pass) {
        this.usuario = usuario;
        this.pass = pass;
    }
    String pass;
    Integer rol;

    public usuarios() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }
}
