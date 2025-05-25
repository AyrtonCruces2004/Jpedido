/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Models.Boleta;
import Models.Categoria;
import Models.Conexion;
import Models.Mesa;
import Models.PedidoDespacho;
import Models.PedidoFinal;
import Models.PedidoProd;
import Models.Producto;
import Models.Sucursal;
import Models.Usuario;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author EnzoV
 */
public class CAdmin {

    private String ruta = "src/JSON/BD.JSON";
    String texto = "";

    public List<Usuario> ListarUsuario(int id_res) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            Connection connection = Conexion.getConnection();
            String query = "SELECT u.Id_Usuario,u.user_usuario,u.nombre_usuario,u.pass_usuario,u.Rol,u.Estado,u.ID_Suc FROM usuario u ";
            if (id_res != 0) {
                query += "INNER JOIN sucursal s on s.ID_Suc=u.ID_Suc INNER JOIN restaurante r on r.ID_Res=s.ID_Res=" + id_res;
            }
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("Id_Usuario"), rs.getString("user_usuario"), rs.getString("nombre_usuario"), rs.getString("pass_usuario"), rs.getInt("ID_Suc"), rs.getString("Rol"), rs.getString("Estado")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar los datos. Por favor, inténtelo de nuevo.", "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarios;
    }

    public List<PedidoDespacho> ListarPedidosDespacho(String fecha) {
        List<PedidoDespacho> pedido = new ArrayList<>();
        try {
            Connection connection = Conexion.getConnection();
            String query = "SELECT b.Cod_Bol,b.Fecha,pf.Tiempo_Restante,pf.Estado FROM `boleta` b INNER JOIN pedido_final pf on pf.ID_PedFinal=b.ID_Ped ";
            if (!fecha.isBlank()) {
                query += "where  b.Fecha='" + fecha + "'";
            }
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                pedido.add(new PedidoDespacho(rs.getString("Cod_Bol"), rs.getString("Fecha"), rs.getInt("Tiempo_Restante"), rs.getString("Estado")));
            }
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedido;
    }

    public List<Producto> ListarProductos(int id_suc) {
        List<Producto> productos = new ArrayList<>();
        try {
            Connection connection = Conexion.getConnection();
            String query = "SELECT * FROM productos p INNER JOIN categorias c on c.ID_Cat = p.ID_Cat INNER JOIN sucursal s on s.ID_Suc= c.ID_Suc=" + id_suc;
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                productos.add(new Producto(rs.getInt("ID_Prod"), rs.getString("Nom_Prod"), rs.getDouble("Priece_Prod"), rs.getInt("Tiem_Prod"), rs.getInt("ID_Cat")));
            }
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar los datos. Por favor, inténtelo de nuevo.", "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productos;
    }

    public List<Mesa> ListarMesa(int id_suc) {
        List<Mesa> mesa = new ArrayList<>();
        try {
            Connection connection = Conexion.getConnection();
            String query = "SELECT * FROM mesa where ID_Suc=" + id_suc;
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                mesa.add(new Mesa(rs.getInt("ID_Mesa"), rs.getString("Nombre_Mesa"), rs.getString("Estado"), rs.getString("Disponible")));
            }
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mesa;
    }

    public List<PedidoProd> Listarpedidomesa(int id_mesa) {
        List<PedidoProd> pedidoProds = new ArrayList<>();
        String query = "";
        try {
            Connection connection = Conexion.getConnection();
            query = "SELECT pp.ID_Ped,p.Nom_Prod,p.ID_Prod,p.Tiem_Prod,p.Priece_Prod FROM pedidoprod pp INNER JOIN productos p on p.ID_Prod=pp.ID_Prod "
                    + "INNER JOIN mesa m on m.ID_Mesa=pp.ID_Mesa "
                    + "where m.ID_Mesa=" + id_mesa + " and pp.Estado='Pendiente'";
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                pedidoProds.add(new PedidoProd(rs.getInt("ID_Ped"), rs.getInt("ID_Prod"), rs.getString("Nom_Prod"), rs.getDouble("Priece_Prod"), id_mesa, rs.getInt("Tiem_Prod")));
            }
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedidoProds;
    }

    public List<PedidoProd> Listarpedidoboleta(String boleta) {
        List<PedidoProd> pedidoProds = new ArrayList<>();
        String query = "";
        try {
            Connection connection = Conexion.getConnection();
            query = "SELECT pp.ID_Ped,p.Nom_Prod,p.ID_Prod,p.Tiem_Prod,p.Priece_Prod FROM boleta b INNER JOIN pedido_final pf "
                    + "ON pf.ID_PedFinal=b.ID_Ped INNER JOIN pedidoprod pp ON pp.ID_Ped=pf.ID_Ped INNER JOIN productos p on p.ID_Prod=pp.ID_Prod "
                    + "where b.Cod_Bol='" + boleta + "'";
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                pedidoProds.add(new PedidoProd(rs.getInt("ID_Ped"), rs.getInt("ID_Prod"), rs.getString("Nom_Prod"), rs.getDouble("Priece_Prod"), 0, rs.getInt("Tiem_Prod")));
            }
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedidoProds;
    }

    public int ObtenerIdRes(int id_usuario) {
        int id_res = 0;
        try {
            Connection connection = Conexion.getConnection();
            String query = "SELECT r.ID_Res FROM usuario u INNER JOIN sucursal s on s.ID_Suc=u.ID_Suc INNER JOIN restaurante r on r.ID_Res=s.ID_Res WHERE u.Id_Usuario=" + id_usuario;
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs = prs.executeQuery();
            if (rs.next()) {
                id_res = rs.getInt("ID_Res");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_res;
    }

    public List<Categoria> ListarCategorias(int id_sucursal) {
        List<Categoria> categorias = new ArrayList<>();

        try {
            Connection connection = Conexion.getConnection();
            String query = "SELECT * FROM `categorias` where ID_Suc=" + id_sucursal;
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs2 = prs.executeQuery();
            while (rs2.next()) {
                categorias.add(new Categoria(rs2.getInt("ID_Cat"), rs2.getString("Nom_Cat")));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorias;
    }

    public List<Sucursal> ListarSucursal(int id_usuario) {
        List<Sucursal> sucursales = new ArrayList<>();
        try {
            Connection connection = Conexion.getConnection();
            int id_res = ObtenerIdRes(id_usuario);
            String query = "SELECT s.ID_Suc,s.ID_Res,s.Nombre_Sucursal FROM `sucursal` s  INNER JOIN restaurante r on r.ID_Res=" + id_res;
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs2 = prs.executeQuery();
            while (rs2.next()) {
                sucursales.add(new Sucursal(rs2.getInt("ID_Res"), rs2.getInt("ID_Suc"), rs2.getString("Nombre_Sucursal")));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sucursales;
    }

    public List<PedidoFinal> ListarPedidos(int id_sucursal) {
        List<PedidoFinal> sucursales = new ArrayList<>();
        try {
            Connection connection = Conexion.getConnection();
            String query = "SELECT pf.ID_Ped,p.Nom_Prod,p.Tiem_Prod FROM `pedido_final` pf INNER JOIN pedidoprod pd on pd.ID_Ped=pf.ID_Ped "
                    + "INNER JOIN productos p ON p.ID_Prod = pd.ID_Prod INNER JOIN categorias c on c.ID_Cat=p.ID_Cat  "
                    + "where pf.Estado='Pendiente' and c.ID_Suc=" + id_sucursal;
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs2 = prs.executeQuery();

            while (rs2.next()) {
                sucursales.add(new PedidoFinal(rs2.getInt("ID_Ped"), rs2.getString("Nom_Prod"), null, rs2.getInt("Tiem_Prod")));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sucursales;
    }

    public int UltimoIDPedido(int id_mesa) {
        int id_pedido = 0;
        String query = "";
        try {
            Connection connection = Conexion.getConnection();
            query = "SELECT * FROM `pedidoprod` ORDER BY ID_Ped DESC LIMIT 1";
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs2 = prs.executeQuery();
            if (rs2.next()) {
                id_pedido = rs2.getInt("ID_Ped");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_pedido;
    }

    public int IDPedidoFinal(int id_pedido) {
        String query = "";
        int id_pedidof = 0;
        try {
            Connection connection = Conexion.getConnection();
            query = "SELECT * FROM pedido_final where ID_Ped=" + id_pedido;
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs2 = prs.executeQuery();
            if (rs2.next()) {
                id_pedidof = rs2.getInt("ID_PedFinal");
            }
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_pedidof;
    }

    public int GetIDProducto(String nombre_producto, int id_sucursal) {
        int idproducto = 0;
        try {
            Connection connection = Conexion.getConnection();
            String query = "SELECT p.ID_Prod FROM productos p INNER JOIN categorias c on c.ID_Cat = p.ID_Cat where Nom_Prod='" + nombre_producto + "' and c.ID_Suc=" + id_sucursal;
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs = prs.executeQuery();
            if (rs.next()) {
                idproducto = rs.getInt("ID_Prod");
            }
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar los datos. Por favor, inténtelo de nuevo.", "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idproducto;
    }

    public void AgregarPedido(List<PedidoProd> pedidos, int id_mesa) {
        String sql = "";
        int rowsInserted = 0;
        try {
            Connection connection = Conexion.getConnection();
            for (PedidoProd pedido : pedidos) {
                sql = "INSERT INTO pedidoprod (ID_Ped, ID_Prod, ID_Mesa, Estado, Precio_Prod) VALUES (?, ?, ?, 'Pendiente', ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Setting parameters
                preparedStatement.setInt(1, pedido.getId_pedido());
                preparedStatement.setInt(2, pedido.getID_Prod());
                preparedStatement.setInt(3, pedido.getID_Mesa());
                preparedStatement.setDouble(4, pedido.getPrecio_Prod()); // Insertar el precio del producto

                // Executing the statement
                rowsInserted = preparedStatement.executeUpdate();
            }
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Se agregó pedido!");
            }
            ActualizarMesa(id_mesa, "no");
            LocalTime horaActual = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String horaFormateada = horaActual.format(formatter);

            sql = "INSERT INTO pedido_final (ID_Ped, hora_enviado, Estado) VALUES (?, ?, 'Pendiente')";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Setting parameters
            preparedStatement.setInt(1, pedidos.get(0).getId_pedido());
            preparedStatement.setString(2, horaFormateada);

            // Executing the statement
            rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted < 0) {
                JOptionPane.showMessageDialog(null, "Error");
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EditarUsuario(String usuario, String nombre, String pass, long last_iduser, String rol, int idsucursal, String estado, String opcion) {
        String texto = "";
        int rowsInserted = 0;
        try {
            Connection connection = Conexion.getConnection();
            PreparedStatement prs = null;
            String query = "";
            if (opcion.equals("agregar")) {
                query = "insert into usuario(user_usuario,nombre_usuario,pass_usuario,ID_Suc,Rol,Estado) values(?,?,?,?,?,'Activo')";
                prs = connection.prepareStatement(query);
                prs.setString(1, usuario);
                prs.setString(2, nombre);
                prs.setString(3, pass);
                prs.setInt(4, idsucursal);
                prs.setString(5, rol);
                texto = "Se agrego usuario";

            } else {
                query = "update usuario set user_usuario = ? , nombre_usuario= ?, ID_Suc = ? ,Rol = ?, Estado = ?";
                if (!pass.isBlank()) {
                    query += ",  pass_usuario = ?";
                }
                query += " where Id_Usuario = " + last_iduser;
                prs = connection.prepareStatement(query);

                prs.setString(1, usuario);
                prs.setString(2, nombre);
                prs.setInt(3, idsucursal);
                prs.setString(4, rol);
                prs.setString(5, estado);

                if (!pass.isBlank()) {
                    prs.setString(6, pass);
                }
                texto = "Se edito usuario";
            }
            rowsInserted = prs.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, texto);
            }
            prs.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EditarProducto(int tiempo, String nombre, double precio, int id_cat, long last_iduser, String opcion) {
        String texto = "";
        int rowsInserted = 0;
        try {
            Connection connection = Conexion.getConnection();
            PreparedStatement prs = null;
            String query = "";
            if (opcion.equals("agregar")) {
                query = "insert into productos(Nom_Prod,Tiem_Prod,Priece_Prod,ID_Cat) values(?,?,?,?)";
                prs = connection.prepareStatement(query);
                prs.setString(1, nombre);
                prs.setInt(2, tiempo);
                prs.setDouble(3, precio);
                prs.setInt(4, id_cat);
                texto = "Se agrego usuario";

            } else {
                query = "update productos set Nom_Prod = ? , Tiem_Prod= ?, Priece_Prod = ? ,ID_Cat = ? where ID_Prod = " + last_iduser;
                prs = connection.prepareStatement(query);

                prs.setString(1, nombre);
                prs.setInt(2, tiempo);
                prs.setDouble(3, precio);
                prs.setInt(4, id_cat);

                texto = "Se edito usuario";
            }
            rowsInserted = prs.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, texto);
            }
            prs.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ActualizarMesa(int id_mesa, String estado) {
        Connection connection;
        try {
            connection = Conexion.getConnection();
            int rowsInserted = 0;
            String sql = "update mesa set Disponible = ? where ID_Mesa=" + id_mesa;
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
            preparedStatement2.setString(1, estado);
            // Executing the statement
            rowsInserted = preparedStatement2.executeUpdate();

            if (rowsInserted < 0) {
                JOptionPane.showMessageDialog(null, "Error al actualizar mesa");
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ActualizarPedido(int id_ped, String estado) {
        Connection connection;
        try {
            connection = Conexion.getConnection();
            int rowsInserted = 0;
            String sql = "update pedidoprod set Estado = ? where ID_Ped=" + id_ped;
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
            preparedStatement2.setString(1, estado);
            // Executing the statement
            rowsInserted = preparedStatement2.executeUpdate();

            if (rowsInserted < 0) {
                JOptionPane.showMessageDialog(null, "Error al actualizar mesa");
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String ObtenerPedidoHora(int id_pedido) {
        int idproducto = 0;
        String hora_enviado = "";
        try {
            Connection connection = Conexion.getConnection();
            String query = "SELECT * FROM pedido_final where ID_Ped=" + id_pedido;
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs = prs.executeQuery();
            if (rs.next()) {
                hora_enviado = rs.getString("hora_enviado");
            }
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar los datos. Por favor, inténtelo de nuevo.", "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hora_enviado;
    }

    public String Despacho(List<PedidoFinal> pedidoProd, LocalTime horaActual, int id_pedido) {

        String shora_enviado = ObtenerPedidoHora(id_pedido), query;
        LocalTime hora_enviado = LocalTime.parse(shora_enviado);
        PreparedStatement prs;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String hora_entregado = horaActual.format(formatter);
        ResultSet rs;

        Duration duracion = Duration.between( hora_enviado,horaActual);

        long tiempo_estimado = 0;
        int resultado_tiempo;
        String respuesta = "";
        for (PedidoFinal pedido : pedidoProd) {
            tiempo_estimado += (long) pedido.getTime_Prod() * 60;
        }
        resultado_tiempo = (int) (tiempo_estimado - duracion.getSeconds());
        String respuesta2 = "";
        if (resultado_tiempo >= 0) {
            respuesta = "Se entrego a tiempo";
            respuesta2 = "A tiempo";
        } else if (resultado_tiempo < 0) {
            respuesta = "Retraso de entrega";
            respuesta2 = "Tardio";
        }

        try {
            Connection connection = Conexion.getConnection();
            query = "select pf.Estado from pedido_final pf INNER JOIN pedidoprod pd on pd.ID_Ped=pf.ID_Ped where pd.ID_Ped = " + id_pedido;
            prs = connection.prepareStatement(query);
            rs = prs.executeQuery();
            if (rs.next()) {
                String estado = rs.getString("Estado");
                if (estado.equals("Pendiente")) {
                    query = "update pedido_final set Tiempo_Restante = ?, Estado = ? , hora_atendido=? where ID_Ped = " + id_pedido;
                    prs = connection.prepareStatement(query);;
                    prs.setInt(1, resultado_tiempo);
                    prs.setString(2, respuesta2);
                    prs.setString(3, hora_entregado);
                    int rowsInserted = prs.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Se despacho pedido");
                    }
                }
            }

            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    public void Agregar_Boleta(String metodo, String tipo_doc, LocalDate horaActual, int id_ped, double Total_Cost) {
        String ID_Doc = "";
        String query = "";
        String ceros = "";

        try {
            Connection connection = Conexion.getConnection();

            // Calcular el costo total del pedido
            String totalCostQuery = "SELECT SUM(p.Priece_Prod) as TotalCost FROM pedidoprod pp "
                    + "INNER JOIN productos p ON pp.ID_Prod = p.ID_Prod "
                    + "WHERE pp.ID_Ped = ?";
            PreparedStatement totalCostStmt = connection.prepareStatement(totalCostQuery);
            totalCostStmt.setInt(1, id_ped);

            // Obtener el último código de boleta
            query = "SELECT * FROM boleta WHERE Tipo_Comprobante='" + tipo_doc + "' ORDER BY Cod_Bol DESC LIMIT 1";
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs2 = prs.executeQuery();
            if (rs2.next()) {
                String[] last_cod = rs2.getString("Cod_Bol").split("-");
                int numero = Integer.parseInt(last_cod[1]);
                int suma = numero + 1;

                if (suma / 10 == 0) {
                    ceros = "0000";
                } else if (suma / 100 == 0) {
                    ceros = "000";
                } else if (suma / 1000 == 0) {
                    ceros = "00";
                } else if (suma / 10000 == 0) {
                    ceros = "0";
                }

                ID_Doc = last_cod[0] + "-" + ceros + suma;
            } else {
                if (tipo_doc.equals("Boleta")) {
                    ID_Doc = "BOLETA-00001";
                } else {
                    ID_Doc = "FACTURA-00001";
                }
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fecha = horaActual.format(formatter);

            // Insertar boleta con el costo total
            query = "INSERT INTO boleta(Cod_Bol, Metodo_Pago, Tipo_Comprobante, Fecha, ID_Ped, Total_Cost) VALUES (?, ?, ?, ?, ?, ?)";
            prs = connection.prepareStatement(query);
            prs.setString(1, ID_Doc);
            prs.setString(2, metodo);
            prs.setString(3, tipo_doc);
            prs.setString(4, fecha);
            prs.setInt(5, id_ped);
            prs.setDouble(6, Total_Cost); // Agregar el costo total

            // Ejecutar la inserción
            int rowsInserted = prs.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Se generó boleta: " + ID_Doc + " con costo total: " + Total_Cost);
            }

            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar boleta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DescargarBoleta(String nro_boleta) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(nro_boleta + ".pdf"));
            document.open();

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            Paragraph titulo = new Paragraph("Boleta N° " + nro_boleta);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph("\n"));

            table.addCell(new PdfPCell(new Phrase("PRODUCTOS", new Font(Font.FontFamily.COURIER, 12, Font.BOLD))));
            table.addCell(new PdfPCell(new Phrase("PRECIO", new Font(Font.FontFamily.COURIER, 12, Font.BOLD))));

            double precio_igv = 0;
            double precios_singv = 0;

            // Consulta SQL para obtener los productos y precios de la boleta
            String query = "SELECT p.Nom_Prod, p.Priece_Prod FROM boleta b "
                    + "INNER JOIN pedido_final pf ON pf.ID_PedFinal = b.ID_Ped "
                    + "INNER JOIN pedidoprod pd ON pd.ID_Ped = pf.ID_Ped "
                    + "INNER JOIN productos p ON p.ID_Prod = pd.ID_Prod "
                    + "WHERE b.Cod_Bol = ?";

            try (Connection connection = Conexion.getConnection(); PreparedStatement prs = connection.prepareStatement(query)) {

                prs.setString(1, nro_boleta);
                ResultSet rs2 = prs.executeQuery();

                while (rs2.next()) {
                    String nombre = rs2.getString("Nom_Prod");
                    double precio = rs2.getDouble("Priece_Prod");
                    double cant_precio = 1 * precio;
                    double porcentaje = (cant_precio * 0.1);
                    cant_precio -= porcentaje;
                    precio_igv += porcentaje;
                    precios_singv += cant_precio;

                    PdfPCell cellProducto = new PdfPCell(new Phrase(nombre, new Font(Font.FontFamily.TIMES_ROMAN, 12)));
                    PdfPCell cellPrecio = new PdfPCell(new Phrase(String.valueOf(cant_precio), new Font(Font.FontFamily.TIMES_ROMAN, 12)));

                    cellProducto.setPadding(5f);
                    cellPrecio.setPadding(5f);

                    table.addCell(cellProducto);
                    table.addCell(cellPrecio);
                }
            } catch (SQLException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
            }

            document.add(table);

            // Calcular total y mostrar en el documento
            double total = precio_igv + precios_singv;
            DecimalFormat df = new DecimalFormat("#.00");
            String psingv_decimal = df.format(precio_igv);

            Paragraph pSinIgv = new Paragraph("Subtotal: " + precios_singv, FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK));
            pSinIgv.setAlignment(Element.ALIGN_RIGHT);
            document.add(pSinIgv);

            Paragraph pIgv = new Paragraph("IGV: " + psingv_decimal, FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK));
            pIgv.setAlignment(Element.ALIGN_RIGHT);
            document.add(pIgv);

            Paragraph pTotal = new Paragraph("Total: " + total, FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK));
            pTotal.setAlignment(Element.ALIGN_RIGHT);
            document.add(pTotal);

            document.close();
            JOptionPane.showMessageDialog(null, "Se descargó boleta " + nro_boleta + " como PDF.");

            // Abrir el archivo PDF generado automáticamente
            String filePath = nro_boleta + ".pdf";
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(new java.io.File(filePath));
                } catch (java.io.IOException ex) {
                    JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo PDF.");
                }
            }

        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarresumen(LocalDate fecha, double total) {
        try {
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fecha_actual = fecha.format(formatter);
            
            Connection connection = Conexion.getConnection();
            String query = "SELECT * FROM resumen_diario WHERE Fecha = '"+fecha_actual+"'";
            PreparedStatement prs = connection.prepareStatement(query);
            ResultSet rs = prs.executeQuery();

          

            if (rs.next()) {
                // Si ya existe una entrada para esta fecha, actualizar el costo total
                double totalExistente = rs.getDouble("Total_cost");
                total += totalExistente; // Sumar el total existente al nuevo total
                query = "UPDATE resumen_diario SET Total_cost = ? WHERE Fecha = '"+fecha_actual+"'" ;
                prs = connection.prepareStatement(query);
                prs.setDouble(1, total);
                prs.executeUpdate();
            } else {
                // Si no existe una entrada para esta fecha, insertar una nueva fila
                query = "INSERT INTO resumen_diario (Fecha, Total_cost) VALUES (?, ?)";
                prs = connection.prepareStatement(query);
                prs.setString(1, fecha_actual);
                prs.setDouble(2, total);
                prs.executeUpdate();
            }

            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al actualizar resumen diario", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al conectar con la base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Boleta> mostrarDetalles(String fecha) {
        String sql = "SELECT Cod_Bol, Total_Cost, Metodo_Pago FROM boleta WHERE Fecha = '" + fecha + "'";
        Conexion con = new Conexion();
        List<Boleta> boletas = new ArrayList<>();

        try {
            Connection conexion = con.getConnection();
            PreparedStatement pst = conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                boletas.add(new Boleta(rs.getString("Cod_Bol"), rs.getDouble("Total_Cost"), rs.getString("Metodo_Pago")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return boletas;
    }
}
