package DAO;

import DBConnection.DBConnection;
import DTO.ItemDTO;
import DTO.VentaDTO;
import java.sql.*;
import java.util.ArrayList;

/**
 * Maneja el CRUD de ventas en la base de datos
 */
public class VentaDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ItemDTO item = null;
    private VentaDTO venta = null;

    /**
     * El constructor de la clase, se encarga de generar la conexion a la base de datos con DBConnection
     * y almacenar la informacion de la venta recibida
     *
     * @param venta un objeto VentaDTO
     */
    public VentaDAO(VentaDTO venta){
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
            this.venta = venta;
            this.item = new ItemDAO().getItemById(venta.getIdProducto());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Se encarga de crear la conexion a la base de datos con DBConnection
     */
    public VentaDAO(){
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ejecuta una venta en la base de datos
     * @param saleMaxStock realizar venta con el maximo stock en
     *                     caso de no existir el deseado
     */

    public void executeSale(boolean saleMaxStock){
        if(this.item !=  null){

            if(checkStockSufficiency()){
                    saveSaleDB();
                    new ItemDAO().updateStock(venta.getIdProducto(), -venta.getCantidadVendida());
            }
            else{

                if(saleMaxStock){
                    venta.setCantidadVendida(item.getCantidad());
                    saveSaleDB();
                    new ItemDAO().updateStock(venta.getIdProducto(), -venta.getCantidadVendida());
                }
            }
        }

    }

    /**
     * Se encarga de guardar la venta en la base de datos
     */
    private void saveSaleDB(){
        try{
            String query = "INSERT INTO ventas VALUES (null, ?,?,?,?,?,?)";
            pstmt = (PreparedStatement) con.prepareStatement(query);
            int total = this.item.getPrecio()*this.venta.getCantidadVendida();
            pstmt.setString(1, this.item.getNombre());
            pstmt.setInt(2,this.venta.getIdProducto());
            pstmt.setInt(3, this.venta.getCantidadVendida());
            pstmt.setInt(4, total);
            pstmt.setInt(5, new ClienteDAO().getClienteByRut(this.venta.getRutCliente()).getId());
            java.sql.Date sqlDate = new java.sql.Date(this.venta.getFecha().getTime());
            pstmt.setDate(6, sqlDate);

            pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();

        }
    }

    /**
     * Edita una venta en la base de datos mediante el identificador id
     * @param id identificador de la venta a editar
     * @param infoVenta informacion nueva de la venta
     */
    public void editSaleById(int id, VentaDTO infoVenta){
        this.item = new ItemDAO().getItemById(infoVenta.getIdProducto());

        try{

            String query = "UPDATE ventas SET producto=?, id_producto=?, cantidad=?, total=?, rut_cliente=?, fecha=? " +
                    "WHERE id='"+id+"'";
            pstmt = (PreparedStatement) con.prepareStatement(query);
            int total = this.item.getPrecio()*infoVenta.getCantidadVendida();
            pstmt.setString(1, this.item.getNombre());
            pstmt.setInt(2, infoVenta.getIdProducto());
            pstmt.setInt(3, infoVenta.getCantidadVendida());
            pstmt.setInt(4, total);
            pstmt.setInt(5, new ClienteDAO().getClienteByRut(infoVenta.getRutCliente()).getId());
            java.sql.Date sqlDate = new java.sql.Date(infoVenta.getFecha().getTime());
            pstmt.setDate(6, sqlDate);

            pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Corrobora que exista stock suficiente del item para realizar la venta
     * @return booleano que describe si existe suficiente stock
     */
    private boolean checkStockSufficiency(){
        boolean existsStock = false;
        if(this.venta.getCantidadVendida() <= this.item.getCantidad()){
            existsStock = true;
        }
        return existsStock;
    }

    /**
     * Metodo para obtener todas las ventas almacenadas en la DB
     * @return ArrayList de VentasDTO con cada una de las ventas almacenadas
     */
    public ArrayList<VentaDTO> getVentasDB(){
        ResultSet rs;

        try{

            String query = "SELECT * FROM ventas v INNER JOIN items i ON v.id_producto = i.id"+
                " INNER JOIN clientes c ON v.id_cliente = c.id";
            rs=stmt.executeQuery(query);

        }catch(SQLException e){
            rs = null;
            e.printStackTrace();
        }
        return rsIntoArrayList(rs);
    }

    /**
     * Transforma el ResultSet de todas las ventas a un ArrayList de VentaDTO que contiene la informacion de todas las ventas
     * @param rs
     * @return
     */
    private ArrayList<VentaDTO> rsIntoArrayList(ResultSet rs){
        ArrayList<VentaDTO> array = new ArrayList<>();
        try{
            while(rs.next()){
                array.add(new VentaDTO( rs.getInt("id_producto"),
                        rs.getInt("cantidad"), rs.getInt("c.rut"), rs.getDate("fecha"),
                        rs.getInt("total"), rs.getInt("id"), rs.getString("i.nombre")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return array;
    }

    /**
     * Obtiene la venta de la base de datos mediante su identificador id
     * @param id identificador de la venta
     * @return un objeto VentaDTO con la informacion de la venta
     */

    public VentaDTO getVentaById(int id){
        VentaDTO venta = null;
        try{

            String query = "SELECT * FROM ventas v INNER JOIN items i ON v.id_producto = i.id";
            query += " INNER JOIN clientes c ON v.id_cliente = c.id WHERE v.id='"+id+"'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            rs.next();
            venta = new VentaDTO( rs.getInt("id_producto"), rs.getInt("cantidad"), rs.getInt("c.rut"),
                    rs.getDate("fecha"), rs.getInt("total"), id, rs.getString("i.nombre"));

        }catch(SQLException e){
            e.printStackTrace();
        }

        return venta;
    }

    /**
     * Elimina una venta de la BD mediante su identificador id
     * @param id identificador de la venta
     */

    public void deleteSaleById(int id){
        if(checkSaleExistenceById(id)){

            this.venta = getVentaById(id);

            try{
                String query = "DELETE FROM ventas WHERE id='"+id+"'";
                pstmt = con.prepareStatement(query);
                pstmt.executeUpdate();

                new ItemDAO().updateStock(this.venta.getIdProducto(), this.venta.getCantidadVendida());

            }catch(SQLException e){
                e.printStackTrace();
            }

        }else{
            System.out.println("La venta no existe en los registros");
        }
    }

    /**
     * Corrobora que exista la venta en la base de datos mediante identificador
     * @param id identificador de la venta
     * @return booleano si la venta existe o no
     */

    private boolean checkSaleExistenceById(int id){
        boolean exists = false;
        try{
            String query = "SELECT producto FROM ventas WHERE id='"+id+"'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if(rs.next()){
                exists = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exists;
    }



}
