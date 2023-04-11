package Server.model;

import Share.ListOfUsers;
import Share.User;
import Share.util.Request;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class LoginHandler extends UnicastRemoteObject implements Login

{
  private PropertyChangeSupport support;
  private ListOfUsers listOfUsers;

  public LoginHandler()throws RemoteException
  {
    support = new PropertyChangeSupport(this);
    listOfUsers = new ListOfUsers();
  }

  @Override public boolean addUser(User user)throws RemoteException
  {
    listOfUsers.addUser(user);
    return true;
  }

  @Override public boolean login(User user)throws RemoteException
  {
    boolean status = listOfUsers.haveUsers(user);
    if (status) {

      support.firePropertyChange(Request.TYPE.ONLOGGEDINADDUSER.toString(),null,user);
    }
    return status;
  }

  @Override public List<String> getAllUsers()throws RemoteException
  {
    return listOfUsers.userNames();
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName,listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName,listener);
  }
}
