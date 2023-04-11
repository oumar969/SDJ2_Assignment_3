package Server.networking;

import Server.model.ChatHandler;
import Server.model.ChatHandlerImpl;
import Server.model.Login;
import Server.model.LoginHandler;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class RMIServer
{
    private Login login;
    private ChatHandler chatHandler;

    public RMIServer(LoginHandler login, ChatHandlerImpl chatHandler)  throws
        RemoteException
    {
        this.login = login;
        this.chatHandler = chatHandler;
    }

    public void startServer() throws RemoteException, AlreadyBoundException
    {
        try {
            ServerSocket socket = new ServerSocket(2001);
            System.out.println("Server starting...");
            ConnectionPool pool = new ConnectionPool();
            while (true) {
                Socket connectionSocket = socket.accept();
                System.out.println("Client connected");
                ServerHandler serverHandler = new ServerHandler(connectionSocket, login, pool, chatHandler);
                Thread th = new Thread(serverHandler);
                th.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
