package Client.View.Chat;

import Client.Model.ChatModel;
import Client.Model.loginModel;
import Share.Message;

import Share.User;
import Share.util.Request;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Client.Model.loginModel;

import java.beans.PropertyChangeEvent;

public class ChatViewModel
{
  private ChatModel chatModel;
  private loginModel loginModel;
  private ObservableList<String> users;
  private ObservableList<Message> chatViewsGlobalChat;
  private StringProperty messageTextField;

  public ChatViewModel(ChatModel chatModel, loginModel loginModel)
  {
    this.chatModel = chatModel;
    this.loginModel=loginModel;
    messageTextField = new SimpleStringProperty();
    users = FXCollections.observableArrayList(chatModel.getUsersname());
    //usernameLabel.setValue(loginModel.getUser().getUserName());

    chatViewsGlobalChat = FXCollections.observableArrayList(
        chatModel.getMessages());

    chatModel.addListener(Request.TYPE.ONLOGGEDINADDUSER.toString(),
        this::userAdded);
    chatModel.addListener("addMessage", this::addMessage);
  }


  public StringProperty messageTextFieldProperty()
  {
    return messageTextField;
  }

  private void addMessage(PropertyChangeEvent event)
  {
    System.out.println(event.getNewValue() + ": chat view model");
    Platform.runLater(
        () -> chatViewsGlobalChat.add((Message) event.getNewValue()));
  }

  private void userAdded(PropertyChangeEvent event)
  {
    Platform.runLater(
        () -> users.add(((User) event.getNewValue()).getUserName()));
  }

  public ObservableList<String> getUsers()
  {
    System.out.println(users + "");
    return users;
  }

  public void sendMessage()
  {
    User user = loginModel.getUser();
    if (user != null) {
      chatModel.sendMessage(new Message(user.getUserName(), messageTextField.getValue()));
    }
    messageTextField.setValue("");
  }

  public ObservableList<Message> getMessages()
  {
    return chatViewsGlobalChat;
  }
}
