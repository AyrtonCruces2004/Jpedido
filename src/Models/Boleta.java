/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author EnzoV
 */
public class Boleta{
    private String Cod_Boleta;
    private Double Total;
    private String Metodo_Pago;

    public Boleta(String Cod_Boleta, Double Total, String Metodo_Pago) {
        this.Cod_Boleta = Cod_Boleta;
        this.Total = Total;
        this.Metodo_Pago = Metodo_Pago;
    }

    public String getCod_Boleta() {
        return Cod_Boleta;
    }

    public void setCod_Boleta(String Cod_Boleta) {
        this.Cod_Boleta = Cod_Boleta;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }

    public String getMetodo_Pago() {
        return Metodo_Pago;
    }

    public void setMetodo_Pago(String Metodo_Pago) {
        this.Metodo_Pago = Metodo_Pago;
    }

    
   
}
