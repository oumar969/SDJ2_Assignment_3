package Server.model;

import Share.User;
import Share.util.Subject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Login extends Remote, Subject
{
  boolean addUser(User user)throws RemoteException;
  boolean login(User user)throws RemoteException;
  List<String> getAllUsers()throws RemoteException;
}
