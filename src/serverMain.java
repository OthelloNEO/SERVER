import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverMain {

	public static void main(String[] args){
		// TODO 自動生成されたメソッド・スタブ
		serverMain apprication = serverMain.getInstance();
		apprication.start();

	}

	private static serverMain instance;
	public static serverMain getInstance(){
		if(instance == null){
			instance = new serverMain();
		}
		return instance;
	}
	private ServerSocket server = null;
	public void start(){

		try {

			server = new ServerSocket(8080);
			if(!server.isClosed()){
				Socket client = server.accept();
				clientActivity user = new clientActivity(client);
			}

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


	}

}
