/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author EnzoV
 */
public class Sucursal {
     int id_res;
     int id_sucursal;
    String nom_sucursal;

    public Sucursal(int id_res, int id_sucursal, String nom_sucursal) {
        this.id_res = id_res;
        this.id_sucursal = id_sucursal;
        this.nom_sucursal = nom_sucursal;
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getNom_sucursal() {
        return nom_sucursal;
    }

    public void setNom_sucursal(String nom_sucursal) {
        this.nom_sucursal = nom_sucursal;
    }

    
}
