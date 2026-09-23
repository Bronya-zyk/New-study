package glimmer.T4;

import java.util.HashMap;

public class MyRepository implements Repository<User> {
    HashMap<Integer, User> storage = new HashMap<>();
    int currentId = 0;
    @Override
    public void save(User entity) {
        // Implementation for saving the entity
        storage.put(++currentId, entity);
    }
    @Override
    public int getById(int id) {
        return id;
    }
    public static void main(String[] args) {
        MyRepository Store = new MyRepository();
        User user1 = new User("Alice", 30);
        User user2 = new User("Bob", 25);
        Store.save(user1);
        Store.save(user2);
        Store.storage.forEach((id, user) -> System.out.println("ID: " + id + ", User: " + user));
    }
}
