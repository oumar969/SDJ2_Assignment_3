package Server.networking;

import Server.model.ChatHandler;
import Server.model.Login;
import Share.Message;
import Share.User;
import Share.util.Request;


import java.beans.PropertyChangeEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class  ServerHandler implements Runnable
{
  private Socket socket;
  private ChatHandler chatHandler;
  private ConnectionPool pool;
  private Login login;
  private User user;
  private ObjectOutputStream outputStream;
  private ObjectInputStream inputStream;

  public ServerHandler(Socket socket, Login login, ConnectionPool pool,
      ChatHandler chatHandler)
  {
    this.socket = socket;
    this.login = login;
    this.chatHandler = chatHandler;
    this.pool = pool;

    try
    {
      inputStream = new ObjectInputStream(socket.getInputStream());
      outputStream = new ObjectOutputStream(socket.getOutputStream());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    while (true)
    {
      try
      {
        // Læs en request fra input streamen og behandle den
        Request response = (Request) inputStream.readObject();

        //if (RequestType.ADDUSER.equals(response.getType())) {
        //  // handle ADDUSER request
        //}
        //else if (RequestType.LOGINPOSSIBLE.equals(response.getType())) {
        //  // handle LOGINPOSSIBLE request
        //}


        // Hvis request typen er "Listener", tilføjes en lytter til login og chatHandler objekterne
        // og brugerobjektet sættes til det, der er modtaget som argument i requesten
        switch (response.getType())
        {
          case "Listener" -> {
            this.user = (User) response.getArg();
            login.addListener(Request.TYPE.ONLOGGEDINADDUSER.toString(),
                this::onUserLoggedIn);
            chatHandler.addListener("addNewMessage", this::messageAdded);
          }
        }
        // Hvis request typen er "ADDUSER", bliver et brugerobjekt tilføjet til login objektet
        // og svaret skrives til output streamen
        if (Request.TYPE.ADDUSER.toString().equals(response.getType()))
        {
          boolean temp = login.addUser((User) response.getArg());
          outputStream.writeObject(
              new Request(Request.TYPE.ADDUSER.toString(), temp));

          break;
        }
        else if (Request.TYPE.LOGINPOSSIBLE.toString()
            .equals(response.getType()))
        {
          boolean temp = login.login((User) response.getArg());
          if (temp)
          {
            this.user = user;
            outputStream.writeObject(
                new Request(Request.TYPE.LOGINPOSSIBLE.toString(), temp));
            pool.addConnection(this);
          }
          break;
        }
        else if (Request.TYPE.USERLIST.toString().equals(response.getType()))
        {
          List<String> loginAllUsers = login.getAllUsers();
          outputStream.writeObject(
              new Request(Request.TYPE.USERLIST.toString(), loginAllUsers));
          break;
        }
        else if ("sendMessage".equals(response.getType()))
        {
          chatHandler.addMessages((Message) response.getArg());
          outputStream.writeObject(new Request("addNewMessage", null));
          break;
        }
        // Hvis request typen er "getPreviousMessages", sender vi en liste over tidligere beskeder
        // fra chatHandler objektet tilbage til output streamen
        else if ("getPreviousMessages".equals(response.getType()))
        {
          List<Message> messages = chatHandler.getPreviousMessage();
          outputStream.writeObject(
              new Request("getPreviousMessages", messages));
        }
      }
      catch (EOFException e)
      {
        // Connection closed, terminate the loop
        break;
      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
      }
    }
  }

  private void messageAdded(PropertyChangeEvent event)
  {
    try
    {
      outputStream.writeObject(
          new Request(event.getPropertyName(), event.getNewValue()));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }

  private void onUserLoggedIn(PropertyChangeEvent event)
  {
    try
    {
      outputStream.writeObject(
          new Request(event.getPropertyName(), event.getNewValue()));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void sendMessageToClient(Message msg)
  {
    try
    {
      outputStream.writeObject(new Request("getMessage", msg));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public User getUser()
  {
    return user;
  }

  public void sendUsers(User users)
  {
    try
    {
      outputStream.writeObject(
          new Request(Request.TYPE.ONLOGGEDINADDUSER.toString(), users));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
