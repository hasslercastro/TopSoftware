package patronesSW.bd;

import patronesSW.clases.Items;
import patronesSW.dao.DAOException;
import patronesSW.dao.ItemsDAO;
import patronesSW.dao.finalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLItemsDAO implements ItemsDAO {

    final String INSERT = "INSERT INTO items(idTipoItem, descripcion, valorUnidad) VALUES( ?, ?, ?)";
    final String UPDATE = "UPDATE items SET idTipoItem = ? , description = ? , valorUnidad = ? WHERE idItem = ? ";
    final String DELETE = "DELETE FROM items WHERE idItem = ? ";
    final String GETALL = "SELECT * FROM items";
    final String GETONE = "SELECT * FROM items WHERE idItem = ?";

    private Connection connection;

    public SQLItemsDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insertar(Items item) throws DAOException {

        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(INSERT);
            statement.setInt(1, item.getIdTipoItem());
            statement.setString(2, item.getDescripcion());
            statement.setInt(3, item.getValorUnidad());

            if(statement.executeUpdate() == 0 ){
                throw new DAOException("No se ha añadido el item");
            }
        } catch (SQLException e) {
            throw new DAOException("No se puede agregar el item", e);
        } finally {
            finalException.exception(statement);
        }

    }

    @Override
    public void modificar(Items item) throws DAOException {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(UPDATE);
            statement.setInt(1, item.getIdTipoItem());
            statement.setString(2, item.getDescripcion());
            statement.setInt(3, item.getValorUnidad());
            statement.setInt(4, item.getIdItem());
            if(statement.executeUpdate() == 0 ){
                throw new DAOException("No se ha actualizado el item");
            }
        } catch (SQLException e) {
            throw new DAOException("No se puede actualizar el item", e);
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
                throw new DAOException("No se ha añadido el item");
            }
        } catch (SQLException e) {
            throw new DAOException("No se puede agregar el item", e);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            finalException.exception(statement);
        }
    }

    private Items convertir(ResultSet rs) throws SQLException{
        int id = rs.getInt("idItem");
        int id_tipoItem = rs.getInt("idTipoItem");
        String descripcion = rs.getString("descripcion");
        int valorUnidad = rs.getInt("valorUnidad");
        Items item = new Items(id_tipoItem, descripcion, valorUnidad);
        item.setIdItem(id);
        return item;
    }

    @Override
    public List<Items> obtenerTodos() throws DAOException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Items> items = new ArrayList<>();

        try{
            statement = connection.prepareStatement(GETALL);
            rs = statement.executeQuery();
            while (rs.next()){
                items.add(convertir(rs));
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

    @Override
    public Items obtener(Integer id) throws DAOException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Items item = null;
        try{
            statement = connection.prepareStatement(GETONE);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if(rs.next()){
                item = convertir((rs));
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

        return item;
    }

}
