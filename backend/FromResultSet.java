package backend;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface FromResultSet<T> {
    T fromResultSet(ResultSet resultSet) throws SQLException;
}