/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author EnzoV
 */
public class PedidoProd{

    private int id_pedido;
    private int ID_Prod;
    private String Nom_Prod;
    private Double Precio_Prod;
    private int Tiempo_Prod;
    private int ID_Mesa;

    public PedidoProd(int id_pedido, int ID_Prod, String Nom_Prod, Double Precio_Prod, int ID_Mesa,int Tiempo_Prod) {
        this.id_pedido = id_pedido;
        this.ID_Prod = ID_Prod;
        this.Nom_Prod = Nom_Prod;
        this.Precio_Prod = Precio_Prod;
        this.ID_Mesa = ID_Mesa;
        this.Tiempo_Prod = Tiempo_Prod;
    }

    public int getTiempo_Prod() {
        return Tiempo_Prod;
    }

    public void setTiempo_Prod(int Tiempo_Prod) {
        this.Tiempo_Prod = Tiempo_Prod;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getID_Prod() {
        return ID_Prod;
    }

    public void setID_Prod(int ID_Prod) {
        this.ID_Prod = ID_Prod;
    }

    public String getNom_Prod() {
        return Nom_Prod;
    }

    public void setNom_Prod(String Nom_Prod) {
        this.Nom_Prod = Nom_Prod;
    }

    public Double getPrecio_Prod() {
        return Precio_Prod;
    }

    public void setPrecio_Prod(Double Precio_Prod) {
        this.Precio_Prod = Precio_Prod;
    }

    public int getID_Mesa() {
        return ID_Mesa;
    }

    public void setID_Mesa(int ID_Mesa) {
        this.ID_Mesa = ID_Mesa;
    }

    

}
