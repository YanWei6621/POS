import java.awt.*;
import javax.swing.*;
import java.sql.*;

class loadDATA
{
    Connection connection;
    Statement statement;

    //�غc�l:���OCdbma
    public loadDATA()
    {
    }

    //�ǤJ���㪺�@�����~��Ʀr��}�C(cmData),�M��N����ƾA���ഫ��,�s�J��Ʈw��product��ƪ�
    public void insertRD_into_TB_product(String[] cmData)
    {
	Connection connection;
        PreparedStatement statement;
        String cmdData;

        //��Ʈw�e�m�@�~
        try
	{
	    Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!");
        }

        //�bposdb��Ʈw��, �s�W�@�����~��ƨ��ƪ�: product 
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
            JOptionPane.showMessageDialog(null,"�b��Ʈw��, �g�J[���~�O��]��product��ƪ��o�Ϳ��~!");
        }
	
    }

    //�ǤJ���㪺�@�����u��Ʀr��}�C(cmData),�M��N����ƾA���ഫ��,�s�J��Ʈw��employee��ƪ�
    public void insertRD_into_TB_employee(String[] cmData)
    {
	Connection connection;
        PreparedStatement statement;
        String cmdData;

        //��Ʈw�e�m�@�~
        try
	{
	    Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!");
        }

        //�bposdb��Ʈw��, �s�W�@�����u��ƨ��ƪ�: employee 
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
            JOptionPane.showMessageDialog(null,"�b��Ʈw��, �g�J[���u���]��employee��ƪ��o�Ϳ��~!");
        }
	
    } 

}