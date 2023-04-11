package Client.Core;

import Client.View.Chat.ChatSystemController;
import Client.View.Login.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
	private Stage stage;
	private Scene loginScene, chatScene;
	private ViewModelFactory viewModelFactory;

	public ViewHandler(ViewModelFactory viewModelFactory){
		this.viewModelFactory=viewModelFactory;
	}

	public void start() throws IOException
	{
		stage= new Stage();
		openLoginPage();
	}

	public void openLoginPage(){
		FXMLLoader loader= new FXMLLoader();

		Parent root= loadFXMLFiles("/Client/View/Login/Login.fxml",loader);
		LoginController loginController= loader.getController();
		loginController.init( this, viewModelFactory);

		loginScene= new Scene(root);
		stage.setTitle("Login");
		stage.setResizable(false);
		stage.setScene(loginScene);
		stage.show();
	}

	public void openGlobalChatPage(){
		FXMLLoader loader= new FXMLLoader();
		Parent root= loadFXMLFiles("/Client/View/Chat/chatSystem.fxml",loader);

		ChatSystemController chatSystemController = loader.getController();
		chatSystemController.init(this,viewModelFactory);

		chatScene= new Scene(root);
		stage.setTitle("Chat");
		stage.setResizable(false);
		stage.setScene(chatScene);
		stage.show();
	}
	public void error(String error){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText(" Error");
		alert.setContentText("Ooops, there was an error!");

		alert.showAndWait();
	}

	private Parent loadFXMLFiles(String path, FXMLLoader loader)
	{
		loader.setLocation(getClass().getResource(path));
		Parent root = null;
		try
		{
			// loading fxml files in parent instance, containing all GUI elements
			root = loader.load();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return root;
	}
}
