package beans.abstracts;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Abdel on 2017-03-08.
 * DB interface
 */
public interface DaoDatabaseInterface<T, Id extends Serializable> {

    Session openCurrentSession();

    Session openCurrentSessionWithTransaction();

    void closeCurrentSession();

    void closeCurrentSessionWithTransaction();

    int persist(T entity);

    void update(T entity);

    T findById(Id id);

    void delete(T entity);

    List<T> findAll();

    void deleteAll();

}
