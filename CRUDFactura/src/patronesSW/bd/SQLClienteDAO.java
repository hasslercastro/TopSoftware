package patronesSW.bd;

import com.mysql.cj.xdevapi.Client;
import patronesSW.clases.Cliente;
import patronesSW.dao.ClienteDAO;
import patronesSW.dao.DAO;
import patronesSW.dao.DAOException;
import patronesSW.dao.finalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLClienteDAO implements ClienteDAO {

    final String INSERT = "INSERT INTO clientes(nombres, apellidos, genero, fechaNacimiento, estadoCivil) VALUES(?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE clientes SET nombres = ? , apellidos = ? , genero = ? , fechaNacimiento = ?, estadoCivil = ? WHERE cliente_id = ?";
    final String DELETE = "DELETE FROM clientes WHERE cliente_id = ? ";
    final String GETALL = "SELECT * FROM clientes";
    final String GETONE = "SELECT * FROM clientes WHERE cliente_id = ?";


    private Connection connection;

    public SQLClienteDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertar(Cliente cliente) throws DAOException {

        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(INSERT);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getGenero());
            statement.setDate(4, new Date(cliente.getFechaNacimiento().getTime()));
            statement.setString(5, cliente.getEstadoCivil());
            if(statement.executeUpdate() == 0 ){
                throw new DAOException("No se ha añadido el cliente");
            }
        } catch (SQLException e) {
            throw new DAOException("No se puede agregar el cliente", e);
        } finally {
            finalException.exception(statement);
        }
    }


    @Override
    public void modificar(Cliente cliente) throws DAOException {

        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getGenero());
            statement.setDate(4, new Date(cliente.getFechaNacimiento().getTime()));
            statement.setString(5, cliente.getEstadoCivil());
            statement.setInt(6, cliente.getId());
            if(statement.executeUpdate() == 0 ){
                throw new DAOException("No se ha actualizado el cliente");
            }
        } catch (SQLException e) {
            throw new DAOException("No se puede actualizar el cliente", e);
        } finally {
            finalException.exception(statement);
        }

    }

    @Override
    public void eliminar(Integer id) throws DAOException {

        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);

            if(statement.executeUpdate() == 0 ){
                throw new DAOException("No se ha añadido el cliente");
            }
        } catch (SQLException e) {
            throw new DAOException("No se puede agregar el cliente", e);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            finalException.exception(statement);
        }




    }

    private Cliente convertir(ResultSet rs) throws SQLException {
        String nombre = rs.getString("nombres");
        String apellido = rs.getString("apellidos");
        String genero = rs.getString("genero");
        Date fechaNacimiento = rs.getDate("fechaNacimiento");
        String estadoCivil = rs.getString("estadoCivil");
        int cliente_id = rs.getInt("cliente_id");
        Cliente cliente = new Cliente(nombre, apellido, genero, fechaNacimiento,estadoCivil);
        cliente.setId(cliente_id);
        return cliente;
    }

    @Override
    public List<Cliente> obtenerTodos() throws DAOException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();


        try{
            statement = connection.prepareStatement(GETALL);
            rs = statement.executeQuery();
            while (rs.next()){
                clientes.add(convertir(rs));
            }
        } catch(SQLException e){
            throw  new DAOException("Error en SQL" , e);
        } finally {
            if(rs != null){
                try{
                    rs.close();
                } catch(SQLException e) {
                    new DAOException("Error en SQL", e);
                }
            }
            finalException.exception(statement);

        }

        return clientes;

    }

    @Override
    public Cliente obtener(Integer id) throws DAOException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try{
            statement = connection.prepareStatement(GETONE);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if(rs.next()){
                cliente = convertir((rs));
            }else {
                throw new DAOException("No se encontro ningun registro");
            }
            } catch(SQLException e){
                throw  new DAOException("Error en SQL" , e);
            } finally {
                if(rs != null){
                    try{
                        rs.close();
                    } catch(SQLException e) {
                        new DAOException("Error en SQL", e);
                    }
                }
                finalException.exception(statement);

        }

        return cliente;

        }

        /**
        public static void main(String[] args) throws SQLException, DAOException {
        Connection conn = null;

            conn = DriverManager.getConnection("jdbc:mysql://localhost/swfacturas" , "root" , "1234");

            Cliente _c = new Cliente("Juan", "Perez", "Hombre",  new java.util.Date("16/03/2000")
                    , "Soltero");


                      clientedao.insertar(_c);

            ClienteDAO clientedao = new SQLClienteDAO(conn);

            //clientedao.modificar();

            List<Cliente> clientes = clientedao.obtenerTodos();
            for(Cliente c : clientes){
                System.out.println(c.toString());
                c.setNombre("Pipetas");
                clientedao.modificar(c);
                System.out.println(c.toString());
            }

//            String st = clientedao.obtener(1).toString();
 //           System.out.println(st);

            //clientedao.eliminar(1);




        }**/
    }


