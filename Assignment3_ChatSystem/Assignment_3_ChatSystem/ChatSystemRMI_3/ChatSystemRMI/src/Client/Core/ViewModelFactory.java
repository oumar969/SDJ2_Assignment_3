package Client.Core;

import Client.View.Chat.ChatViewModel;
import Client.View.Login.LoginViewModel;

public class ViewModelFactory
{
	private ModelFactory modelFactory;
	private LoginViewModel clientViewModel;
	private ChatViewModel chatViewModel;
	private LoginViewModel loginViewModel;

	public ViewModelFactory(ModelFactory modelFactory)
	{
		this.modelFactory = modelFactory;
	}

	public LoginViewModel getClientViewModel()
	{
		if (clientViewModel == null)
		{
			clientViewModel = new LoginViewModel(modelFactory.getLoginModel());
		}
		return clientViewModel;
	}

	public ChatViewModel getChatViewModel(){
		if (chatViewModel== null){
			chatViewModel= new ChatViewModel(modelFactory.getChatModel(),
					modelFactory.getLoginModel());
		}
		return chatViewModel;
	}
}
