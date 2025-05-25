/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author EnzoV
 */
public class PedidoDespacho {
    private String cod_boleta;
    private String fecha;
    private int tiempo;
    private String estado;

    public PedidoDespacho(String cod_boleta,String fecha, int tiempo, String estado) {
        this.cod_boleta = cod_boleta;
        this.fecha = fecha;
        this.tiempo = tiempo;
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCod_boleta() {
        return cod_boleta;
    }

    public void setCod_boleta(String cod_boleta) {
        this.cod_boleta = cod_boleta;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
