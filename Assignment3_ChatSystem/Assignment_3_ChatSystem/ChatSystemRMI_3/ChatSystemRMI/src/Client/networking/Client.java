package Client.networking;

import Share.Message;
import Share.User;
import Share.util.Subject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Client extends Subject, Remote
{
  //void listenToServer(User user);

  List<String> getUserList() throws RemoteException;
  boolean addUser(User user1)throws RemoteException;

  boolean login(User user)throws RemoteException;
  //List<Message> getMessages();
  void sendMessage(Message message)throws RemoteException;

  List<Message>  getPreviousMessages()throws RemoteException;
  void startClient()throws RemoteException;
}
