package Server.model;

import Share.Message;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatHandlerImpl extends UnicastRemoteObject implements ChatHandler
{
  private List<Message> messages;
  private PropertyChangeSupport support;

  public ChatHandlerImpl() throws RemoteException
  {
    messages= new ArrayList<>();
    support= new PropertyChangeSupport(this);
  }

  @Override public List<Message> getMessages()throws RemoteException
  {
    return messages;
  }

  @Override public void addMessages(Message message)throws RemoteException
  {
    messages.add(message);
    //System.out.println(support.hasListeners("addNewMessage") + " :from chat handler");
    support.firePropertyChange("addNewMessage",null,message);
  }

  @Override public List<Message> getPreviousMessage()throws RemoteException
  {
    return messages;
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName,listener);
  }

}
