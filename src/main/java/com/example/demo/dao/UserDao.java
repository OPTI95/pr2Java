package com.example.demo.dao;
import com.example.demo.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    private static Long USER_COUNT = 0L;
    private List<User> users;

    {
        users = new ArrayList<>();
        users.add(new User(++USER_COUNT, "John Doe", "john@example.com", "password123"));
        users.add(new User(++USER_COUNT, "Jane Doe", "jane@example.com", "password456"));
    }

    public List<User> index() {
        return users;
    }

    public User show(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(++USER_COUNT);
        users.add(user);
    }

    public void update(Long id, User updatedUser) {
        User existingUser = show(id);
        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
        }
    }

    public void delete(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}