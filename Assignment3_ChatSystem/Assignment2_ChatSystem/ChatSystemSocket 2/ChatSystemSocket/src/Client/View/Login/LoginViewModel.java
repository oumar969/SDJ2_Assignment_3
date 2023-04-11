package Client.View.Login;

import Client.Core.ModelFactory;
import Client.Model.loginModel;
import Share.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel
{
	private StringProperty username;
	private loginModel loginModel;
	private ModelFactory modelFactory;


	public LoginViewModel(loginModel loginModel)
	{
		this.loginModel=loginModel;
		username= new SimpleStringProperty();

	}
	public void addUser(String username) {

		if (username != null && loginModel.checkLoginUp(username)) {

			loginModel.addUser(username);
			System.out.println("Created");
		}
	}
	public StringProperty usernameProperty()
	{
		return username;
	}


	public void sendUsername(String username)
	{
//			clientChatModel.sendUsername(username);
//			System.out.println(username + " from loginViewModel");
	}
	public LoginViewModel getLoginViewModel(){
return null;

	}

	public boolean login(){
		User user= new User(username.get());

		if (!loginModel.login(user)){
			username.set("");
			return false;
		}
		return true;
	}


}
