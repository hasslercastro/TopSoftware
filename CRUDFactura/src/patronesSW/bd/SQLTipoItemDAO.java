package patronesSW.bd;

import patronesSW.clases.tipoItem;
import patronesSW.dao.DAOException;
import patronesSW.dao.finalException;
import patronesSW.dao.tipoItemDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLTipoItemDAO implements tipoItemDAO {

    final String INSERT = "INSERT INTO tipoItem(descripcion) VALUES( ? )";
    final String UPDATE = "UPDATE tipoItem SET descripcion = ? WHERE idTipo = ? ";
    final String DELETE = "DELETE FROM tipoItem WHERE idTipo = ? ";
    final String GETALL = "SELECT * FROM tipoItem";
    final String GETONE = "SELECT * FROM tipoItem WHERE idTipo = ?";

    private Connection connection;

    public SQLTipoItemDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertar(tipoItem tipo) throws DAOException {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(INSERT);
            statement.setString(1, tipo.getDescripcion());
            if(statement.executeUpdate() == 0 ){
                throw new DAOException("No se ha añadido el tipo de item");
            }
        } catch (SQLException e) {
            throw new DAOException("No se puede agregar el tipo de item", e);
        } finally {
            finalException.exception(statement);
        }

    }

    @Override
    public void modificar(tipoItem tipo) throws DAOException {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, tipo.getDescripcion());
            statement.setInt(2, tipo.getId());

            if(statement.executeUpdate() == 0 ){
                throw new DAOException("No se ha actualizado el tipo de item");
            }
        } catch (SQLException e) {
            throw new DAOException("No se puede actualizar el tipo de item", e);
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
                throw new DAOException("No se ha añadido el tipo de item");
            }
        } catch (SQLException e) {
            throw new DAOException("No se puede agregar el tipo de item", e);
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            finalException.exception(statement);
        }

    }


    private tipoItem convertir(ResultSet rs) throws SQLException{
        int id = rs.getInt("idTipo");
        String descripcion = rs.getString("descripcion");
        tipoItem item = new tipoItem(descripcion);
        item.setId(id);
        return item;
    }


    @Override
    public List<tipoItem> obtenerTodos() throws DAOException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<tipoItem> tipo_items = new ArrayList<>();

        try{
            statement = connection.prepareStatement(GETALL);
            rs = statement.executeQuery();
            while (rs.next()){
                tipo_items.add(convertir(rs));
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

        return tipo_items;
    }

    @Override
    public tipoItem obtener(Integer id) throws DAOException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        tipoItem itemTipo = null;
        try{
            statement = connection.prepareStatement(GETONE);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if(rs.next()){
                itemTipo = convertir((rs));
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

        return itemTipo;
    }
}
