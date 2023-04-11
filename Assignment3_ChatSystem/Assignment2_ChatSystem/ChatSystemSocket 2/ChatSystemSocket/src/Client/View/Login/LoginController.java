package Client.View.Login;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Client.View.Chat.ChatSystemController;
import Client.View.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController implements ViewController
{



	private ViewModelFactory viewModelFactory;

	@FXML private TextField userNameTextField;

	private LoginViewModel loginViewModel;
	private ViewHandler viewHandler;

	@Override public void init(ViewHandler viewHandler,
			ViewModelFactory viewModelFactory)
	{
		this.viewModelFactory=viewModelFactory;
		this.viewHandler = viewHandler;
		this.loginViewModel = viewModelFactory.getClientViewModel();
		userNameTextField.textProperty().bindBidirectional(viewModelFactory.getClientViewModel().usernameProperty());
		//label.textProperty().bind(viewModelFactory.getLoginViewModel().labelProperty());
	}

	private boolean authenticate(String userName)
	{
		if (userName.isEmpty())
		{
			return false;
		}
		return true;
	}

	@FXML void LoginButton(ActionEvent event) throws IOException
	{
		if (!authenticate(userNameTextField.getText()))

		{
			System.out.println("Invalid username");
			return;
		}
		System.out.println("LoginButton before addUSer");
		loginViewModel.addUser(userNameTextField.getText());

		if (!(userNameTextField.getText().isEmpty()))
		{
			viewModelFactory.getChatViewModel().getUsers().add(userNameTextField.getText());
			viewHandler.openGlobalChatPage();
		}
		else if(userNameTextField.getText().isEmpty())
		{
			viewHandler.error("error");
		}

	}


	@FXML
	void cancelButton(ActionEvent event)
	{
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}


}