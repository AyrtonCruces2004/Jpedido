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
public class Producto{
    int id_Producto;
    String nom_Producto;
    Double precio_Producto;
    int time_Producto;
    int id_cat;

    public Producto(int id_Producto, String nom_Producto, Double precio_Producto, int time_Producto,int id_Cat) {
        this.id_Producto = id_Producto;
        this.nom_Producto = nom_Producto;
        this.precio_Producto = precio_Producto;
        this.time_Producto = time_Producto;
        this.id_cat = id_Cat;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public Double getPrecio_Producto() {
        return precio_Producto;
    }

    public void setPrecio_Producto(Double precio_Producto) {
        this.precio_Producto = precio_Producto;
    }

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public String getNom_Producto() {
        return nom_Producto;
    }

    public void setNom_Producto(String nom_Producto) {
        this.nom_Producto = nom_Producto;
    }

    public int getTime_Producto() {
        return time_Producto;
    }

    public void setTime_Producto(int time_Producto) {
        this.time_Producto = time_Producto;
    }

   /*     List<Producto> productos = new ArrayList<>();

    public void Lista() {
        productos.add(new Producto(1, "Lomo saltado", 15.0, 15, 1, "Carne"));
        productos.add(new Producto(2, "Aji de Gallina", 15.0, 20, 1, "Carne"));
        productos.add(new Producto(3, "Pejerey Frito", 20.0, 30, 2, "Pescado"));
    }*/
}
