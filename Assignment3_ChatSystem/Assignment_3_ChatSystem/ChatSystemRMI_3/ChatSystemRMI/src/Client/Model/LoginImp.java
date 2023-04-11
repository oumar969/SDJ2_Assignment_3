package Client.Model;

import Client.networking.Client;
import Share.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.List;

public class LoginImp implements loginModel
{
	private Client client;
	private User user;
	private PropertyChangeSupport support;
	public LoginImp(Client client) throws RemoteException
	{
		this.client=client;
		support= new PropertyChangeSupport(this);
		client.startClient();
	}

	@Override public boolean addUser(String username, String password)
	{
		return false;
	}

	@Override public void addUser(String username)
	{
		System.out.println("add user login model");
        user = new User(username);
	}

	@Override public User getUser()
	{
		return user;
	}

	@Override
	public boolean checkLoginUp(String username) {
		return true;
	}

	@Override public boolean login(User user) throws RemoteException
	{
		boolean isloginPossible= client.login(user);
		if (isloginPossible)this.user=user;
		return isloginPossible;
	}

	@Override public List<String> getAllUsers() throws RemoteException
	{
		return client.getUserList();
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
