/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author EnzoV
 */
public class Mesa {
     int id_mesa;
    String nom_mesa;
    String estado;
    String disponible;

    public Mesa(int id_mesa, String nom_mesa, String estado, String disponible) {
        this.id_mesa = id_mesa;
        this.nom_mesa = nom_mesa;
        this.estado = estado;
        this.disponible = disponible;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public String getNom_mesa() {
        return nom_mesa;
    }

    public void setNom_mesa(String nom_mesa) {
        this.nom_mesa = nom_mesa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    
    
    
}
