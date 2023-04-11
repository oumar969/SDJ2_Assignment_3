package Server.model;

import Share.Message;
import Share.util.Subject;


import java.util.List;

public interface ChatHandler extends Subject
{
  List<Message> getMessages();

  void addMessages(Message message);

  List<Message> getPreviousMessage();

}
