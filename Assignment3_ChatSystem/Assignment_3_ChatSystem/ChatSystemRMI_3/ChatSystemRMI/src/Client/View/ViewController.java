package Client.View;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;

public interface ViewController
{
	void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory);
}