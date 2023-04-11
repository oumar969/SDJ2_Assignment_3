package Server.model;

import Share.User;
import Share.util.Subject;

import java.util.List;

public interface Login extends Subject
{
  boolean addUser(User user);
  boolean login(User user);
  List<String> getAllUsers();
}
