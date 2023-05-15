package mk.ukim.finki.tutormind.tutormind.service;

import mk.ukim.finki.tutormind.tutormind.model.User;

public interface AuthService {
    User login(String username, String password);

}
