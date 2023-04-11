package Client.Model;



import Share.Message;
import Share.util.Subject;

import java.util.List;

public interface ChatModel extends Subject
{
  List<String> getUsersname();

  List<Message> getMessages();
  void sendMessage(Message message);


}
