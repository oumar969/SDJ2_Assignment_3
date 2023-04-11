package Server.model;

import Share.Message;
import Share.util.Subject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatHandler extends Remote, Subject
{
  List<Message> getMessages()throws RemoteException;

  void addMessages(Message message)throws RemoteException;

  List<Message> getPreviousMessage()throws RemoteException;

}
