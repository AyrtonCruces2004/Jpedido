/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EnzoV
 */
public class Usuario {

    private int Id_Usuario;
    private int Id_Sucursal;
    private String user_usuario;
    private String nombre_usuario;
    private String pass_usuario;
    private String rol_usuario;
    private String estado_usuario;

    public Usuario(int Id_Usuario, String user_usuario, String nombre_usuario, String pass_usuario,int Id_Sucursal,String rol_usuario,String estado_usuario) {
        this.Id_Usuario = Id_Usuario;
        this.user_usuario = user_usuario;
        this.nombre_usuario = nombre_usuario;
        this.pass_usuario = pass_usuario;
        this.rol_usuario = rol_usuario;
        this.estado_usuario = estado_usuario;
        this.Id_Sucursal = Id_Sucursal;
    }

    public int getId_Sucursal() {
        return Id_Sucursal;
    }

    public void setId_Sucursal(int Id_Sucursal) {
        this.Id_Sucursal = Id_Sucursal;
    }

    public String getRol_usuario() {
        return rol_usuario;
    }

    public void setRol_usuario(String rol_usuario) {
        this.rol_usuario = rol_usuario;
    }

    public String getEstado_usuario() {
        return estado_usuario;
    }

    public void setEstado_usuario(String estado_usuario) {
        this.estado_usuario = estado_usuario;
    }

    public String getUser_usuario() {
        return user_usuario;
    }

    public void setUser_usuario(String user_usuario) {
        this.user_usuario = user_usuario;
    }

    public int getId_Usuario() {
        return Id_Usuario;
    }

    public void setId_Usuario(int Id_Usuario) {
        this.Id_Usuario = Id_Usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPass_usuario() {
        return pass_usuario;
    }

    public void setPass_usuario(String pass_usuario) {
        this.pass_usuario = pass_usuario;
    }

}
