package patronesSW;

import patronesSW.bd.DAOManagerSQL;
import patronesSW.clases.Factura;
import patronesSW.dao.DAOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {


    public static void main(String[] args) throws SQLException, DAOException {

        DAOManagerSQL manager = new DAOManagerSQL("localhost", "root", "1234", "swfacturas");
        List<Integer> it = new ArrayList<>();
        it.add(1);
        it.add(2);
        Factura f = new Factura(new Date("11/2/1998") , 2, 2000, "Pagada", it);
        f.setNroFactura(1);
        manager.getFacturaDAO().insertar(f);

    }

}
