package patronesSW.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class finalException {

    public static void exception(PreparedStatement statement) throws DAOException {
        if(statement != null){
            try{
                statement.close();
            } catch (SQLException e){
                throw new DAOException("Error en SQL", e);
            }
        }

    }


}
