package glimmer.T4;

public interface Repository<T> {
    void save(T entity);
    int getById(int id);
}
