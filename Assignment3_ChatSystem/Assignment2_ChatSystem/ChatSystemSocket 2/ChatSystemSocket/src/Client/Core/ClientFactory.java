package Client.Core;

import Client.networking.Client;
import Client.networking.ClientRMI;


public class ClientFactory
{
  private Client client;

  public Client getClient()
  {
    if (client== null){
      client= new ClientRMI();
    }
    return client;
  }
}
