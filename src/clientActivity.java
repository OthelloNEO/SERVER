import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;

class MessageEvent extends EventObject{
	private clientActivity source;
	private String name;
	private String value;

	public MessageEvent(clientActivity source,String name,String value) {
		super(source);
		// TODO 自動生成されたコンストラクター・スタブ
		this.name =name;
		this.value = value;
	}

}

interface MessageListener extends EventListener{
	void messageThrow(MessageEvent e);
}
class clientActivity implements Runnable{
	private Socket socket;
	private String name;
	private ArrayList<MessageListener> messageListeners;
	public clientActivity(Socket socket){
		this.socket = socket;
		messageListeners = new ArrayList<MessageListener>();
		Thread thread = new Thread(this);
		thread.start();
	}

	public void run(){
		try {
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			Boolean loginok = false;
			Boolean useridok = false;
			if(!socket.isClosed()){
				String line = reader.readLine();
				System.out.println("line:"+line);
				if(line.equals("msg stopserver")){
					System.out.println("System is stopping...");
					System.exit(0);
				}
				if(line.matches("login-"+".*")){

					String [] idpass = line.split("-");
					System.out.println("get login id from device:id:"+idpass[1]);
					/*clientDB login = new clientDB();
					login.connectDB("client_list");
					ResultSet result = login.responsquery("select password from client_list where name='"+idpass[1]+"'");
					ArrayList<String> strs = new ArrayList<String>();
					while(result.next()){
						strs.add(result.getString("password"));
						System.out.println("get password from client_list DB:password:"+strs.get(0));
						useridok = true;

					}
					if(strs.size() == 1){
						if(strs.get(0).trim().equals(idpass[2])){
							loginok = true;
						}
					}
					if(loginok){
						sendMessage("success");
						System.out.println(idpass[1]+"is login");
					}else if(useridok){
						sendMessage("failed-password");
					}else{
						sendMessage("failed-userid");
					}*/
					sendMessage("success");

				}
			}

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}/* catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}*/
	}
	public void messageThrow(MessageEvent e){

	}
	public void sendMessage(String message){
		try {
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output);
			writer.println(message);
			writer.flush();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	public void addMessageListener(MessageListener l){
		messageListeners.add(l);
	}
}
