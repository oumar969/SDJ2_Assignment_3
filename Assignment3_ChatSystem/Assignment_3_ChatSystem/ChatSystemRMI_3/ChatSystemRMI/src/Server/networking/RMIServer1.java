package Server.networking;

import Share.Message;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIServer1 extends Remote
{
	void startServer() throws RemoteException, AlreadyBoundException;
	void addListener() throws RemoteException, AlreadyBoundException;
	boolean addUser() throws RemoteException, AlreadyBoundException;
	void login() throws RemoteException, AlreadyBoundException;
	List<String> getAllUsers() throws RemoteException, AlreadyBoundException;
	void addMessages(Message message) throws RemoteException, AlreadyBoundException;
	List<Message> getPreviousMessages() throws RemoteException, AlreadyBoundException;


}
