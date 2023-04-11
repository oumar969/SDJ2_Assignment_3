package Client.Model;


import Share.User;
import Share.util.Subject;

import java.rmi.RemoteException;
import java.util.List;

public interface loginModel extends Subject
{
	boolean addUser(String username, String password);

	void addUser(String username);
	User getUser();
	boolean checkLoginUp(String username);


	boolean login(User user) throws RemoteException;
	List<String> getAllUsers() throws RemoteException;
}
