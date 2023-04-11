package Client.Model;



import Share.Message;
import Share.util.Subject;

import java.rmi.RemoteException;
import java.util.List;

public interface ChatModel extends Subject
{
  List<String> getUsersname() throws RemoteException;

  List<Message> getMessages() throws RemoteException;
  void sendMessage(Message message) throws RemoteException;


}
