/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejem_login;

import java.util.Scanner;
import java.lang.Exception;
import tablas.usuarios;
/**
 *
 * @author daniel
 */
public class Ejem_login {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        System.out.println("sistema de visualizacion de info de un login simple");
        entrada();
    }
    
    public static void usuarios(){
        Scanner teclado = new Scanner(System.in);
        int val;
        System.out.println("------menu---------");
        System.out.println("1 usuarios");
        System.out.println("2 roles");
        System.out.println("3 privilegios");
        System.out.println("9 salida");
        try{
        switch(val = teclado.nextInt()){
            case 1:
                usuarios();
                break;
            case 2:
                roles();
                break;
            case 3:
                privilegios();
                break;
            case 9:
                System.exit(10);
                break;
            default:
                throw  new Exception();
        }
        }catch(Exception e){
            usuarios();
        }
    }
    public static void entrada(){
        Scanner teclado = new Scanner(System.in);
        int val;
        System.out.println("------menu---------");
        System.out.println("1 ingresa");
        System.out.println("9 salida");
        try{
        switch(val = teclado.nextInt()){
            case 1:
                login();
                break;
            case 9:
                System.exit(10);
                break;
            default:
                throw  new Exception();
        }
        }catch(Exception e){
            entrada();
        }
    }
    public static void login(){
        Scanner teclado = new Scanner(System.in);
        int val;
        System.out.println("inserta tu usuario");
        String user = teclado.next();
        System.out.println("ingresa tu contraseña");
        String pass = teclado.next();
        usuarios u = new usuarios(user, pass);
        if (acciones(u)) {
        }
        else
            System.out.println("datos erroneos");
        entrada();
    }
    public static boolean acciones(usuarios u){
        return false;
    }
    public static void roles(){}
    public static void privilegios(){}
}
