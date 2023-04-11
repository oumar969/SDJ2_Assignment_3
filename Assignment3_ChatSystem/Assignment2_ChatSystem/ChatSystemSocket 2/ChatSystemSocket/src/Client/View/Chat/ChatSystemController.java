package Client.View.Chat;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Client.View.ViewController;

import Share.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatSystemController implements ViewController
{
	@FXML private ListView<String> usersList;
	private ViewHandler viewHandler;
	private ViewModelFactory viewModelFactory;

	@FXML private ListView<Message> chatViewsGlobalChat; //

	@FXML private TextField messageTextField;


	public void init(ViewHandler viewHandler,
			ViewModelFactory viewModelFactory)
	{
		this.viewHandler=viewHandler;
		this.viewModelFactory=viewModelFactory;


		usersList.setItems(viewModelFactory.getChatViewModel().getUsers());

		chatViewsGlobalChat.setItems(viewModelFactory.getChatViewModel().getMessages());

		messageTextField.textProperty().bindBidirectional(viewModelFactory.getChatViewModel().messageTextFieldProperty());

	}


	public void onSendButton(ActionEvent actionEvent)
	{
		viewModelFactory.getChatViewModel().sendMessage();
		messageTextField.clear();
	}

	public void onSelectUserButtonChat(ActionEvent actionEvent)
	{
	}
}
