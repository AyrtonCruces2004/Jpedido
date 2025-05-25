/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author EnzoV
 */
public class PedidoFinal {
    private int ID_Ped;
    private String Nom_Prod;
    private int Time_Prod;
    private String hora_enviado;

    public PedidoFinal(int ID_Ped, String Nom_Prod,String hora_enviado,int Time_Prod) {
        this.ID_Ped = ID_Ped;
        this.Nom_Prod = Nom_Prod;
        this.Time_Prod = Time_Prod;
    }

    public int getTime_Prod() {
        return Time_Prod;
    }

    public void setTime_Prod(int Time_Prod) {
        this.Time_Prod = Time_Prod;
    }

    public int getID_Ped() {
        return ID_Ped;
    }

    public void setID_Ped(int ID_Ped) {
        this.ID_Ped = ID_Ped;
    }

    public String getNom_Prod() {
        return Nom_Prod;
    }

    public void setNom_Prod(String Nom_Prod) {
        this.Nom_Prod = Nom_Prod;
    }
    
    
}
