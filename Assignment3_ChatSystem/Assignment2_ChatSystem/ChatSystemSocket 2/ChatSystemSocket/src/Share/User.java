package Share;

import java.io.Serializable;

public class User implements Serializable
{

  private String userName;
  private String password;

  public User(String userName)
  {
    this.userName = userName;
  }

  public String getUserName()
  {
    return userName;
  }

  public String getPassword()
  {
    return password;
  }

  public boolean equals(Object obj){
    if (!(obj instanceof User user)){
      return false;
    }
    return user.getUserName().equals(userName);
  }
}
