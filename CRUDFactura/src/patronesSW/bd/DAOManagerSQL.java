package patronesSW.bd;

import patronesSW.clases.Factura;
import patronesSW.clases.Items;
import patronesSW.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOManagerSQL implements DAOManager {

    private Connection conn;


    private ClienteDAO clientedao = null;
    private FacturaDAO  facturadao = null;
    private ItemsDAO itemsdao = null;
    private tipoItemDAO tipoitemdao = null;

    public DAOManagerSQL(String host, String username, String password, String database) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database, username, password);
    }

    @Override
    public ClienteDAO getClienteDAO() {
        if(clientedao == null){
            clientedao = new SQLClienteDAO(conn);
        }
        return clientedao;
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        if(facturadao == null){
            facturadao = new SQLFacturaDAO(conn);
        }
        return facturadao;

    }

    @Override
    public ItemsDAO getItemsDAO() {
        if(itemsdao == null){
            itemsdao = new SQLItemsDAO(conn);
        }
        return itemsdao;
    }

    @Override
    public tipoItemDAO getTipoItemDAO() {
        if(tipoitemdao == null){
            tipoitemdao = new SQLTipoItemDAO(conn);
        }
        return  tipoitemdao;
    }
}
