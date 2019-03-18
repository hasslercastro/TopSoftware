package patronesSW.bd;

import patronesSW.clases.Factura;
import patronesSW.dao.DAO;
import patronesSW.dao.DAOException;
import patronesSW.dao.FacturaDAO;
import patronesSW.dao.finalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLFacturaDAO implements FacturaDAO {

    final String INSERT = "INSERT INTO Factura( fechaFactura, totalFactura, estado , ClienteID) VALUES( ?, ?, ?, ?)";
    final String UPDATE = "UPDATE Factura SET fechaFactura = ? , totalFactura = ? , estado = ? , ClienteID =? WHERE idFactura = ?";
    final String DELETE = "DELETE FROM Factura WHERE idFactura = ? ";
    final String GETALL = "SELECT * FROM Factura";
    final String GETONE = "SELECT * FROM Factura WHERE idFactura = ?";

    final String GET_FOR_ARRAY = "SELECT ItemID FROM detalleFactura WHERE FacturaID = ?";
    final String INSERT_ARRAY = "INSERT INTO detalleFactura (FacturaID, ItemID) VALUES (?, ?)";
    final String LAST_REG = "SELECT * FROM Factura  ORDER BY idFactura DESC LIMIT 1";


    private Connection connection;

    public SQLFacturaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertar(Factura factura) throws DAOException {
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        try{
            statement = connection.prepareStatement(INSERT);
            statement.setDate(1, new Date(factura.getFechaFactura().getTime()));
            statement.setInt(2,  factura.getTotalFactura());
            statement.setString(3, factura.getEstado());
            statement.setInt(4, factura.getIdCliente());
            if(statement.executeUpdate() == 0 ){
                throw new DAOException("No se ha añadido la factura");
            }
        } catch (SQLException e) {
            throw new DAOException("No se puede agregar el cliente", e);
        } finally {
            finalException.exception(statement);
        }

        try{
            statement2 = connection.prepareStatement(LAST_REG);
            ResultSet rs = statement2.executeQuery();
            if(rs.next()) {
                int valueFactura = rs.getInt("idFactura");
                for( int it : factura.getItems()){
                    insertarTablaDetalles(it, valueFactura);
                }
            }
        }catch (SQLException e){
            throw new DAOException("Error al cargar reg" , e);
        } finally {
            finalException.exception(statement2);
        }


    }

    private void insertarTablaDetalles(int itemid, int facturaid) throws DAOException {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(INSERT_ARRAY);
            statement.setInt(1,facturaid);
            statement.setInt(2, itemid);

            if(statement.executeUpdate() == 0 ){
                throw new DAOException("No se ha añadido la factura");
            }
        } catch (SQLException e) {
            throw new DAOException("No se puede agregar a la factura", e);
        } finally {
            finalException.exception(statement);
        }


    }

    @Override
    public void modificar(Factura factura) throws DAOException {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(INSERT);
            statement.setDate(1, new Date(factura.getFechaFactura().getTime()));
            statement.setInt(2,  factura.getTotalFactura());
            statement.setString(3, factura.getEstado());
            statement.setInt(4, factura.getIdCliente());
            statement.setInt(5, factura.getNroFactura());
            if(statement.executeUpdate() == 0 ){
                throw new DAOException("No se ha actualizado la factura");
            }
        } catch (SQLException e) {
            throw new DAOException("No se puede actualizar la factura", e);
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
                throw new DAOException("No se ha eliminado la factura");
            }
        } catch (SQLException e) {
            throw new DAOException("No se puede eliminar la factura", e);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            finalException.exception(statement);
        }


    }

    private List<Integer> items(int id) throws DAOException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Integer> items = new ArrayList<>();

        try{
            statement = connection.prepareStatement(GET_FOR_ARRAY);
            rs = statement.executeQuery();
            while (rs.next()){
                items.add(rs.getInt("ItemID"));
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

        return items;

    }


    private  Factura convertir(ResultSet rs ) throws SQLException, DAOException {
        int id  = rs.getInt("idFactura");
        java.util.Date fecha = new java.util.Date(rs.getDate("fechaFactura").getTime());
        int totalFactura = rs.getInt("totalFactura");
        String estado = rs.getString("estado");
        int clienteID = rs.getInt("ClienteID");
        List<Integer> items = items(id);
        Factura factura = new Factura(fecha, clienteID, totalFactura, estado, items );
        return factura;

    }

    @Override
    public List<Factura> obtenerTodos() throws DAOException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Factura> facturas = new ArrayList<>();


        try{
            statement = connection.prepareStatement(GETALL);
            rs = statement.executeQuery();
            while (rs.next()){
                facturas.add(convertir(rs));
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

        return facturas;
    }

    @Override
    public Factura obtener(Integer id) throws DAOException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Factura factura = null;
        try{
            statement = connection.prepareStatement(GETONE);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if(rs.next()){
                factura = convertir((rs));
            }else {
                throw new DAOException("No se encontro ningun registro de factura");
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

        return factura;

    }
}
