package earthquakeGetter;

import java.sql.*;

public class SQLControl {
	String url = null;
	String id = null;
	String pw = null;
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	public SQLControl(String ip,int port,String id,String pw,String dataBase) throws ClassNotFoundException {
		// TODO Auto-generated constructor stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.url = "jdbc:mysql://" + ip + ":" + port + "/" + dataBase;
		try {
			connection = DriverManager.getConnection(url, id, pw);
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Connect not success");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SQLDataPrint(String tableName,String[] query) {
		try {
			connection.setAutoCommit(false);
			int queryLength = query.length;
			String sql = "select ";
			for(int i=0;i<queryLength-1;i++) {
				sql = sql + query[i] + ",";
			}
			sql = sql + query[queryLength-1] + " from ";
			sql += tableName;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void SQLDataPrint(String tableName,String[] query,String order) {
		try {
			connection.setAutoCommit(false);
			int queryLength = query.length;
			String sql = "select ";
			for(int i=0;i<queryLength-1;i++) {
				sql += "?,";
			}
			sql = sql + "? from ?" + " order by " + order;
			PreparedStatement statement = this.connection.prepareStatement(sql);
			for(int i=1;i<=queryLength;i++) {
				statement.setString(i, query[i-1]);
			}
			statement.setString(queryLength+1, tableName);
			statement.setString(queryLength+2, order);
			statement.addBatch();
			resultSet = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SQLDataInsert(String lv,String time,String wd,String jd,String depth,String location) {
		try {
			String sql = "insert into mainDataBase(震级,时间,纬度,经度,深度,地点) values(?,?,?,?,?,?)";
			PreparedStatement statement = this.connection.prepareStatement(sql);
			statement.setString(1,lv);
			statement.setString(2,time);
			statement.setString(3, wd);
			statement.setString(4,jd);
			statement.setString(5, depth);
			statement.setString(6, location);
			statement.addBatch();
			statement.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void SQLDataInsert(int bookId,int price) {
		try {
			String sql = "insert into bookTest(bookId,price) values(?,?)";
			PreparedStatement statement;
			statement = this.connection.prepareStatement(sql);
			statement.setInt(1, bookId);
			statement.setInt(3, price);
			statement.addBatch();
			statement.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void SQLDataCreatter(String sql) {
		try {
			statement = connection.createStatement();
			if(statement.executeLargeUpdate(sql)==0) {
				System.out.println("Create success!");
			}
			else {
				System.out.println("Create not success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void SQLClose() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
}
