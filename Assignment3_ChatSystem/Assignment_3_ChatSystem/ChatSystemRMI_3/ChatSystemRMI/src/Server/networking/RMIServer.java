package Server.networking;

import Server.model.ChatHandler;
import Server.model.ChatHandlerImpl;
import Server.model.Login;
import Server.model.LoginHandler;

import javax.management.remote.rmi.RMIServerImpl;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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

    public void startServer() {
        try {
            Login loginStub = (Login) UnicastRemoteObject.exportObject(login, 0);
            ChatHandler chatStub = (ChatHandler) UnicastRemoteObject.exportObject(chatHandler, 0);

            // Opret en RMI-registreringsdatabase på port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind login- og chathandlere til RMI-navneserveren
            registry.bind("login", loginStub);
            registry.bind("chat", chatStub);

            // Opret forbindelse til clienter
            System.out.println("Server starting...");
            ConnectionPool pool = new ConnectionPool();

            while (true) {
                ServerHandler serverHandler = new ServerHandler(login, pool, chatHandler);
                pool.addConnection(serverHandler);
            }
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
