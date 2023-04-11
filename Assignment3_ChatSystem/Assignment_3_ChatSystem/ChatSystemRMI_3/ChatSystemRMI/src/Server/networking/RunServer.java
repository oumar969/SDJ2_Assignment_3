package Server.networking;

import Server.model.ChatHandlerImpl;
import Server.model.LoginHandler;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException
  {
     RMIServer ss= new RMIServer(new LoginHandler(),new ChatHandlerImpl());
     ss.startServer();
  }
}
