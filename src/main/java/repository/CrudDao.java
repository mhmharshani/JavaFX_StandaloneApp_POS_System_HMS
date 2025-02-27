package repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao <T,ID> extends SuperDao{

    //Strategy Design Pattern ----> Place Common methods for CRUD here for all Dao types

    boolean save (T entity) throws SQLException;

    boolean update(ID id,T entity) throws SQLException;

    T search(ID id);

    boolean delete(ID id);

    List<T> getAll();

}
