package DAO;

import DBConnection.DBConnection;
import DTO.ClienteDTO;
import DTO.ProveedorDTO;

import java.sql.*;
import java.util.ArrayList;

/**
 * Clase encargada de manejar el CRUD de clientes en la BD
 */
public class ClienteDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    /**
     * Constructor el cual se encarga de realizar la conexion a la base de datos con DBConnection
     *
     */
    public ClienteDAO(){
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para añadir un cliente a la BD
     * @param cliente objeto ClienteDTO que contiene la informacion del cliente
     */
    public boolean addCliente(ClienteDTO cliente){
        if(!checkClientExistence(cliente)){
            try{
                String query = "INSERT INTO clientes VALUES (null, ?,?,?,?) ";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, cliente.getNombre());
                pstmt.setString(2, cliente.getRut());
                pstmt.setString(3, cliente.getEmail());
                pstmt.setInt(4, cliente.getTelefono());

                pstmt.executeUpdate();

                return true;
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Metodo para editar un cliente en la BD mediante su identificador
     * @param cliente objeto ClienteDTO conteniendo la nueva informacion del cliente
     * @param id identificador del cliente
     */
    public boolean editClienteById(ClienteDTO cliente, int id){
        if(checkClientExistenceById(id)){
            try{
                String query = "UPDATE clientes SET nombre=?, email=?, telefono=?, rut=? WHERE id='"+id+"'";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, cliente.getNombre());
                pstmt.setString(2, cliente.getEmail());
                pstmt.setInt(3, cliente.getTelefono());
                pstmt.setString(4, cliente.getRut());
                pstmt.executeUpdate();
                return true;
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Metodo para eliminar un cliente de la BD mediante su identificador
     * @param id identificador del cliente
     */
    public boolean deleteClienteById(int id){
        if(checkClientExistenceById(id)){
            try{
                String query = "DELETE FROM clientes WHERE id='"+id+"'";
                pstmt = con.prepareStatement(query);
                pstmt.executeUpdate();
                return true;
            }catch(SQLException e){
                System.out.println("El usuario no existe");
                e.printStackTrace();
            }
        }
        return false;

    }

    /**
     * Obtiene todos los clientes almacenados en la base de datos
     * @return ArrayList de ClienteDTOs almacenando la informacion de cada cliente
     */
    public ArrayList<ClienteDTO> getClientesDB(){
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        try{
            String query = "SELECT * FROM clientes";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            clientes = rsIntoArrayList(rs);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }

    /**
     * Transforma el ResultSet de clientes de la BD en un ArrayList de ClienteDTO conteniendo toda la informacion
     * de los clientes en la base de datos
     * @param rs ResultSet que contiene todos los usuarios de la BD
     * @return ArrayList de ClienteDTO con toda la informacion de los clientes
     */
    private ArrayList<ClienteDTO> rsIntoArrayList(ResultSet rs){
        ArrayList<ClienteDTO> array = new ArrayList<>();
        try{
            while(rs.next()){
                array.add(new ClienteDTO(rs.getInt("id"),rs.getString("nombre"), rs.getString("email"),
                        rs.getInt("telefono"), rs.getString("rut")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return array;
    }

    /**
     * Obtiene un cliente de la BD mediante su identificador
     * @param id identificador del cliente
     * @return Objeto ClienteDTO con la informacion del cliente
     */
    public ClienteDTO getClienteById(int id ){
        ClienteDTO cliente = null;
        if(checkClientExistenceById(id)){
            try{
                String query = "SELECT * FROM clientes WHERE id='"+id+"'";
                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();
                rs.next();
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String rut = rs.getString("rut");
                int telefono = rs.getInt("telefono");
                cliente =  new ClienteDTO(id, nombre, email, telefono, rut);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return cliente;

    }

    /**
     * Revisa la existencia de un cliente mediante su identificador
     * @param id identificador del cliente
     * @return booleano si existe o no
     */
    private boolean checkClientExistenceById(int id){
        boolean existe =  false;
        try{
            String query = "SELECT nombre FROM clientes WHERE id='"+id+"'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if(rs.next()){
                existe = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return existe;
    }

    /**
     * Revisa la existencia de un cliente chekando sus datos
     * @param cliente Objeto ClienteDTO con la informacion del cliente
     * @return booleano si existe o no
     */
    private boolean checkClientExistence(ClienteDTO cliente){
        boolean existe = false;
        try{
            String query = "SELECT id FROM clientes WHERE nombre=? AND email=? AND telefono=? AND rut=?";
            pstmt =  con.prepareStatement(query);
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setInt(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getRut());

            rs = pstmt.executeQuery();
            if(rs.next()){
                existe = true;
            }
        }catch(SQLException e ){
            e.printStackTrace();
        }
        return existe;
    }

    /**
     * Obtiene un cliente mediante su rut como identificador
     * @param rut rut del cliente guardado en la base de datos
     * @return Objeto ClienteDTO con la informacion del cliente
     */
    public ClienteDTO getClienteByRut(String rut){
        ClienteDTO cliente = null;

        try{
            String query = "SELECT * FROM clientes WHERE rut='"+rut+"'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            rs.next();
            String nombre = rs.getString("nombre");
            String email = rs.getString("email");
            int id = rs.getInt("id");
            int telefono = rs.getInt("telefono");
            cliente =  new ClienteDTO(id, nombre, email, telefono, rut);
        }catch(SQLException e){
            e.printStackTrace();
        }

        return cliente;
    }


  
}
