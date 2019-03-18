package patronesSW.dao;

public interface DAOManager {

    ClienteDAO getClienteDAO();
    FacturaDAO getFacturaDAO();
    ItemsDAO getItemsDAO();
    tipoItemDAO getTipoItemDAO();
}
