package com.delectable.userauth.controllers;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import com.delectable.shared.MessageResponse;
import com.delectable.shared.crud.CRUHardDeleteRepository;
import com.delectable.shared.crud.CRUHardDeleteService;
import com.delectable.userauth.models.User;
import com.delectable.userauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CRUHardDeleteService<User> {

    public UserService(CRUHardDeleteRepository<User> repository) {
        super(repository);
    }

    @Autowired
    private UserRepository repository;

    public User getUser(long id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("Error: User not found!");
        } else {
            return user.get();
        }
    }

    public Page<User> getPageUser(Pageable pageable) {
        return repository.findByIdNot(pageable, 1l);
    }

    public MessageResponse updateUser(long id, User updatedUser) {
        User user = getUser(id);
        boolean usernameInUse = repository.existsByUsername(updatedUser.getUsername());
        boolean usernameIsTheSame = updatedUser.getUsername().compareTo(user.getUsername()) == 0;
        boolean emailInUse = repository.existsByEmail(updatedUser.getEmail());
        boolean emailIsNull =
                updatedUser.getEmail().compareTo("") == 0 || updatedUser.getEmail() == null;
        boolean emailIsTheSame = updatedUser.getEmail().compareTo(user.getEmail()) == 0;

        if (!emailInUse || emailInUse && emailIsTheSame || emailIsNull) {
            if (!usernameInUse || usernameInUse && usernameIsTheSame) {
                updatedUser.setId(user.getId());
                updatedUser.setPassword(user.getPassword());
                repository.save(updatedUser);
                return new MessageResponse("User updated successfully!");
            } else {
                throw new IllegalArgumentException("Error: Username is already taken!");
            }
        } else {
            throw new IllegalArgumentException("Error: Email is already in use!");
        }

    }

}
