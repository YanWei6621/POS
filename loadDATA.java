import java.awt.*;
import javax.swing.*;
import java.sql.*;

class loadDATA
{
    Connection connection;
    Statement statement;

    //建構子:類別Cdbma
    public loadDATA()
    {
    }

    //傳入完整的一筆產品資料字串陣列(cmData),然後將此資料適當轉換後,存入資料庫的product資料表中
    public void insertRD_into_TB_product(String[] cmData)
    {
	Connection connection;
        PreparedStatement statement;
        String cmdData;

        //資料庫前置作業
        try
	{
	    Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL驅動程式安裝失敗!");
        }

        //在posdb資料庫中, 新增一筆產品資料到資料表: product 
        try
	{  
            cmdData = "INSERT INTO product(pid,class,pname,price,amount)"+
                      " VALUES(?,?,?,?,?)";
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+
                                                     "?user=root&password=mysql");
            statement = connection.prepareStatement(cmdData);
            statement.setInt(1, Integer.parseInt(cmData[0]) );
            statement.setString(2,cmData[1]);
            statement.setString(3,cmData[2]);
            statement.setInt(4, Integer.parseInt(cmData[3]) );
            statement.setInt(5, Integer.parseInt(cmData[4]) );
            statement.executeUpdate();
            statement.close();

	}
	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"在資料庫中, 寫入[產品記錄]到product資料表中發生錯誤!");
        }
	
    }

    //傳入完整的一筆員工資料字串陣列(cmData),然後將此資料適當轉換後,存入資料庫的employee資料表中
    public void insertRD_into_TB_employee(String[] cmData)
    {
	Connection connection;
        PreparedStatement statement;
        String cmdData;

        //資料庫前置作業
        try
	{
	    Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL驅動程式安裝失敗!");
        }

        //在posdb資料庫中, 新增一筆員工資料到資料表: employee 
        try
	{  
            cmdData = "INSERT INTO employee(eid,ename,position,password)"+
                      " VALUES(?,?,?,?)";
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+
                                                     "?user=root&password=mysql");
            statement = connection.prepareStatement(cmdData);
            statement.setInt(1, Integer.parseInt(cmData[0]) );
            statement.setString(2,cmData[1]);
            statement.setString(3,cmData[2]);
            statement.setInt(4, Integer.parseInt(cmData[3]) );
            statement.executeUpdate();
            statement.close();
	}
	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"在資料庫中, 寫入[員工資料]到employee資料表中發生錯誤!");
        }
	
    } 

}