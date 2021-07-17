package DTO;

import DAO.ClienteDAO;

public class ClienteDTO {

    private final String nombre;
    private final String email;
    private final int telefono;
    private final int rut;
    private final int id;

    public ClienteDTO(String nombre, String email, int telefono, int rut){
        this.id = -1;
        this.nombre = nombre;
        this.email = email;
        this.telefono  = telefono;
        this.rut = rut;

    }
    public ClienteDTO(int id, String nombre, String email, int telefono, int rut){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.rut = rut;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getEmail(){
        return this.email;
    }
    public int getTelefono(){
        return this.telefono;
    }
    public int getRut(){
        return this.rut;
    }
    public int getId(){return this.id;}

    public String[] toArray(){
        String id = Integer.toString(this.id);
        String telefono = Integer.toString(this.telefono);
        String rut = Integer.toString(this.rut);

        return new String[]{id, nombre, email, telefono, rut};
    }

    @Override
    public boolean equals(Object o){
        if( o == this){
            return true;
        }
        if(!(o instanceof ClienteDTO)){
            return false;
        }
        ClienteDTO c = (ClienteDTO) o;

        return this.getNombre().toLowerCase().equals(c.getNombre().toLowerCase())
                && this.getEmail().toLowerCase().equals(c.getEmail().toLowerCase())
                && this.getRut() == c.getRut() && this.getTelefono() == c.getTelefono();

    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((this.nombre == null) ? 0 : this.nombre.hashCode());
        return result;
    }

}
