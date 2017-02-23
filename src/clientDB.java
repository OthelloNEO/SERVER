import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class clientDB {
	static Connection con;
	public void connectDB(String dbname) throws ClassNotFoundException, SQLException, InterruptedException{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Boot driver");
		/*Properties prop = new Properties();
		prop.put("user","root" );
		prop.put("password","T0m0yuk!" );
		prop.put("create", "true");*/
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname,"root","T0m0yuk!");
		System.out.println("DB connection successful");
		/*Statement stmt = con.createStatement();
        //stmt.execute("CREATE TABLE client_list(id INT(11) NOT NULL AUTO_INCREMENT,name VARCHAR(36),password VARCHAR(50),PRIMARY KEY (id));");
		stmt.execute("insert into client_list (name, password) values ('Tomoyuku','T0m0yuk!')");
		ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();
        stmt.close();*/
        //con.close();

	}
	public void setquery(String cmd) throws SQLException{
		Statement data = null;
		data = con.createStatement();
		data.execute(cmd);

	}
	public ResultSet responsquery(String cmd) throws SQLException{
		Statement data = null;
		ResultSet res = null;
		data = con.createStatement();
		res = data.executeQuery(cmd);
		return res;
	}

}
