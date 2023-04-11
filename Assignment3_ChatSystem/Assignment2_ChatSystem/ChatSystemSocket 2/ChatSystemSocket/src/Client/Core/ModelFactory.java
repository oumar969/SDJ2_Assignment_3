package Client.Core;
import Client.Model.ChatModel;
import Client.Model.ChatModelImpl;
import Client.Model.LoginImp;
import Client.Model.loginModel;

public class ModelFactory
{
  private ClientFactory clientFactory;
  private ChatModel chatModel;
  private loginModel loginModel;

  public ModelFactory(ClientFactory clientFactory){
    this.clientFactory=clientFactory;
  }


  public ChatModel getChatModel(){
    if (chatModel== null){
      this.chatModel= new ChatModelImpl(clientFactory.getClient());
    }
    return chatModel;
  }

  public loginModel getLoginModel(){
    if (loginModel== null){
      this.loginModel= new LoginImp(clientFactory.getClient());
    }
    return loginModel;
  }



}
