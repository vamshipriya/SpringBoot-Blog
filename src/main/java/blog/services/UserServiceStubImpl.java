package blog.services;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public abstract class UserServiceStubImpl implements UserService{

    public boolean authenticate(String username,String password) {
        return Objects.equals(username,password);
    }

}
