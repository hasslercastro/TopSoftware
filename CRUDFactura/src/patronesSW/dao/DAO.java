package patronesSW.dao;

import java.util.List;

public interface DAO<T,K> {

    void insertar(T x) throws DAOException;

    void modificar(T x) throws DAOException;

    void eliminar(K x) throws DAOException;

    List<T> obtenerTodos() throws DAOException;

    T obtener(K id) throws DAOException;

}
