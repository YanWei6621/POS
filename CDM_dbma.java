//�פJ�ݭn���U���M��
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;


//��ƺ޲z�h���O
//CDM_dbma: Class DatabaseManagement_database manipulation and acess (��Ʈw�ާ@�P�s�����O)

class CDM_dbma
{                    
    Connection connection;
    Statement statement;
    
    
    //�غc�l:���OCdbma
    public CDM_dbma()
    {   
    }
     
    //��k:�إ߸�Ʈwbmsdb
    public void createDB()
    {
	//�w��MySQL�X�ʵ{��, �P�إ߸�Ʈwbmsdb
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
	    JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!"); 
        }
        //�إ� bmsdb��Ʈw
        try
	{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+
                                                     "?user=root&password=mysql");
            statement = connection.createStatement();
            String createDB = "CREATE DATABASE bmsdb";
            statement.executeUpdate(createDB);
            JOptionPane.showMessageDialog(null,"bmsdb��Ʈw�إߦ��\!");
            statement.close();   
	} 
	catch(SQLException e)
	{
            if(statement != null) 
            	JOptionPane.showMessageDialog(null,"bmsdb��Ʈw�w�s�b,�i���`�ϥ�!");
            else
                JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        }

    } //end for: public void createDB()

    //�إ߸�Ʈwbmsdb������ƪ�:product
    public void createTB_product()
    {
	try
	{
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.createStatement();
	    String createTB = "CREATE TABLE product("+
                              "pid             INT PRIMARY KEY, "+  //���~�s��
                              "class           VARCHAR(15), "+      //���~����  
                              "pname           VARCHAR(15), "+      //���~�W��
                              "price           INT,"+	    	    //���~���
			      "amount	       INT)";               //���~�ƶq

	    statement.executeUpdate(createTB);
 	    JOptionPane.showMessageDialog(null,"product��ƪ�إߦ��\!");
	    statement.close();
       
	} 
	catch(SQLException e)
	{
	    if(statement != null) 
	    	JOptionPane.showMessageDialog(null,"product��ƪ�w�s�b,�i���`�ϥ�!"); 
            else
                JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
        } 
	catch(Exception e)
	{
	    JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        }  
    }

    //�إ߸�Ʈwbmsdb������ƪ�:employee
    public void createTB_employee()
    {
	try
	{
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.createStatement();
	    String createTB = "CREATE TABLE employee("+
                              "eid             INT PRIMARY KEY, "+  //���u�s��  
                              "ename           VARCHAR(15), "+      //���u�m�W
                              "position        VARCHAR(15),"+	    //���u¾��
			      "password	       INT)";               //���u�K�X

	    statement.executeUpdate(createTB);
 	    JOptionPane.showMessageDialog(null,"employee��ƪ�إߦ��\!");
	    statement.close();
       
	} 
	catch(SQLException e)
	{
	    if(statement != null) 
	    	JOptionPane.showMessageDialog(null,"employee��ƪ�w�s�b,�i���`�ϥ�!"); 
            else
                JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
        } 
	catch(Exception e)
	{
	    JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        }  
    }

    //�إ߸�Ʈwbmsdb������ƪ�:clockin
    public void createTB_clockin()
    {
	try
	{
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.createStatement();
	    String createTB = "CREATE TABLE clockin("+
                              "num             INT AUTO_INCREMENT PRIMARY KEY, "+  //�۰ʽs��
                              "date            DATE, "+  //��� 
                              "eid             INT, "+  //���u�s��                
			      "startwork       TIME NOT NULL,"+  //�W�Z�ɶ�
			      "offwork         TIME NOT NULL)";  //�U�Z�ɶ�       

	    statement.executeUpdate(createTB);
 	    JOptionPane.showMessageDialog(null,"clockin��ƪ�إߦ��\!");
	    statement.close();
       
	} 
	catch(SQLException e)
	{
	    if(statement != null) 
	    	JOptionPane.showMessageDialog(null,"clockin��ƪ�w�s�b,�i���`�ϥ�!"); 
            else
                JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
        } 
	catch(Exception e)
	{
	    JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        }  
    } 

    //�إ߸�Ʈwbmsdb������ƪ�:sell �P���
    public void createTB_sell()
    {
	try
	{
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.createStatement();
	    String createTB = "CREATE TABLE sell("+
			      "sid             INT AUTO_INCREMENT PRIMARY KEY, "+      //�P��s��
                              "sdate           DATE, "+  		//�P����                           
			      "stotal	       INT)";               //�P�ⲣ�~�`�p

	    statement.executeUpdate(createTB);
 	    JOptionPane.showMessageDialog(null,"sell��ƪ�إߦ��\!");
	    statement.close();
       
	} 
	catch(SQLException e)
	{
	    if(statement != null) 
	    	JOptionPane.showMessageDialog(null,"sell��ƪ�w�s�b,�i���`�ϥ�!"); 
            else
                JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
        } 
	catch(Exception e)
	{
	    JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        }  
    }

    //�إ߸�Ʈwbmsdb������ƪ�:detail �P�����
    public void createTB_detail()
    {
	try
	{
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.createStatement();
	    String createTB = "CREATE TABLE detail("+
                              "snum            INT AUTO_INCREMENT PRIMARY KEY, "+  //�۰ʽs��
                              "sid             INT, "+  //�P��s��  
                              "pname           VARCHAR(15), "+  //���~�W��
                              "price           INT,"+	    	    //���~����
                              "samount         INT,"+	    	    //���~�P��ƶq
			      "ptotal	       INT)";               //���~�P��p�p

	    statement.executeUpdate(createTB);
 	    JOptionPane.showMessageDialog(null,"detail��ƪ�إߦ��\!");
	    statement.close();
       
	} 
	catch(SQLException e)
	{
	    if(statement != null) 
	    	JOptionPane.showMessageDialog(null,"detail��ƪ�w�s�b,�i���`�ϥ�!"); 
            else
                JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
        } 
	catch(Exception e)
	{
	    JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        }  
    }

    //�ǤJ���㪺�@�����u������,�M��N����Ʀs�J��Ʈw��employee��ƪ�
    public void insertRD_into_TB_employee(CPD_employee aemployee)
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

        //�bsmsdb��Ʈw��, �s�W�@�����u��ƨ��ƪ�: employee   
        try
	{  
            cmdData = "INSERT INTO employee(eid,ename,position,password)"+
                      "VALUES(?,?,?,?)";

            connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
            statement = connection.prepareStatement(cmdData);
            statement.setInt(1, aemployee.geteid() );
            statement.setString(2,aemployee.getename() );
            statement.setString(3,aemployee.getposition() );
            statement.setInt(4, aemployee.getpassword() );
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null,"�bbmsdb��Ʈw��, ���\�g�J�@��[���u�O��]��employee��ƪ�!");
            statement.close();
        } 
	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"�bbmsdb��Ʈw��, �g�J�@��[���u�O��]��employee��ƪ��o�Ϳ��~!");
        }
     }

     //�ǤJ�@��[���~�s��],�d�ߥX�Ӳ��~��[�~��]�P[���]��ƨæ^�Ǩ�d�o[�~��][���]�r�굲�G
     public String[] findRD_in_TB_product(int apid)
     {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;

        String pname="";
        int price=0;
        String[] myResult = new String[2];
     
        //��Ʈw�e�m�@�~
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!"); 
        }

        try
	{
            cmdData = "SELECT * FROM product WHERE pid = ?";
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
            statement = connection.prepareStatement(cmdData);
            statement.setInt(1,apid);
            result = statement.executeQuery();
          
            while( result.next() )
	    {
            	pname = result.getString("pname");
                price = result.getInt("price");
	    }
            statement.close();            
	}
	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        } 

        myResult[0] = pname;
        myResult[1] = String.valueOf(price);   //String.valueOf() �O�N[��ƭ�]�ন�r��
	return( myResult );            
    }


     //�ǤJ[�s���K�X],�d�ߥX�ӭ��u��[�m�W]��ƨæ^�Ǩ�d�o�r�굲�G
     public String findRD_in_TB_employee(int[] aeid)
     {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;

        String ename="";
        String myResult="";
     
        //��Ʈw�e�m�@�~
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!"); 
        }

        try
	{
            cmdData = "SELECT * FROM employee WHERE eid = ? AND password = ?";
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
            statement = connection.prepareStatement(cmdData);
            statement.setInt(1,aeid[0]);
            statement.setInt(2,aeid[1]);
            result = statement.executeQuery();
          
            while( result.next() )
	    {
            	ename = result.getString("ename");
            }
            statement.close();            
	}
	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        } 

        myResult = ename;

	return( myResult );            
    }

    //�ǤJ���㪺�@�����d������,�M��N����Ʀs�J��Ʈw��clockin��ƪ�
    public void clockin(String[] clock)
    {

	int re=0;
	String date=clock[0];
	int eid=Integer.valueOf(clock[1]);
	re=findRD_in_TB_clockin(date,eid);

        //�bsmsdb��Ʈw��, �s�W�@����ƨ��ƪ�: clockin
	if(re==0)
	    insert_clockin(clock);
	if(re==1)
	    update_clockin(clock);   	
    
     }

    //�ǤJ[����s��],�d�ߥX�ӭ��u��[�W�Z���d]��ƨæ^�Ǩ�d�o���G
    public int findRD_in_TB_clockin(String adate,int aeid)
    {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;

        int eid=0;
     
        //��Ʈw�e�m�@�~
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!"); 
        }

        try
	{
            cmdData = "SELECT * FROM clockin WHERE date = ? AND eid = ? ";
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
            statement = connection.prepareStatement(cmdData);
            statement.setString(1,adate);
            statement.setInt(2,aeid);
            result = statement.executeQuery();
	    
            if(result.next()==false)
	    {
		eid=0;
		JOptionPane.showMessageDialog(null,"�L�O��");
		
	    }
	    else
	    {
	    	eid=1;
		JOptionPane.showMessageDialog(null,"���O��");
            }
            statement.close();            
	}
	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        } 
		
	return( eid );            
    }

    public void update_clockin(String[] offwork)//���d��s
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

	try
	{
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
	    cmdData = " UPDATE clockin SET offwork=? WHERE date=? AND eid=? ";

	    statement = connection.prepareStatement(cmdData);
	    statement.setString(1,offwork[2]);
	    statement.setString(2,offwork[0]);
	    statement.setInt(3,Integer.valueOf(offwork[1]));

	    statement.executeUpdate();
	    JOptionPane.showMessageDialog(null,"���d���\");
	    statement.close();
	}

	catch(SQLException e)
	{
	    JOptionPane.showMessageDialog(null,"���d�o�Ϳ��~!");
        }
    }

    //�ǤJ���㪺�@�����d������,�M��N����Ʀs�J��Ʈw��clockin��ƪ�
    public void insert_clockin(String[] clock)
    {
    	Connection connection;
        PreparedStatement statement;
        String cmdData;
	int re=0;

        //��Ʈw�e�m�@�~
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!"); 
        }
	

        //�bsmsdb��Ʈw��, �s�W�@����ƨ��ƪ�: clockin  
        try
	{  
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
            cmdData = "INSERT INTO clockin(date,eid,startwork,offwork)"+
                      "VALUES(?,?,?,?)";

            statement = connection.prepareStatement(cmdData);
            statement.setString(1,clock[0]);
            statement.setInt(2,Integer.valueOf(clock[1]));
            statement.setString(3,clock[2]);
	    statement.setString(4,"00:00:00");  	
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null,"���d���\");
            statement.close();
	}

	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"���d�o�Ϳ��~!");
        } 
     }

    //�ǤJ�P����,�M��N����Ʀs�J��Ʈw��sell��ƪ�
    public void insert_sell(String[] sell)
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
	
        //�bsmsdb��Ʈw��, �s�W�@����ƨ��ƪ�: sell  
        try
	{  
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
            cmdData = "INSERT INTO sell(sdate,stotal)"+
                      "VALUES(?,?)";

            statement = connection.prepareStatement(cmdData);
            statement.setString(1,sell[0]);
            statement.setInt(2,Integer.valueOf(sell[1]));  	
            statement.executeUpdate();
            statement.close();
	}

	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"�o�Ϳ��~1!");
        } 
     }

    //�d�߾P��s��
    public int findsid()
    {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;

        int sid=0;
     
        //��Ʈw�e�m�@�~
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!"); 
        }

        try
	{
            cmdData = "SELECT * FROM sell ORDER BY sid DESC LIMIT 1";
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
            statement = connection.prepareStatement(cmdData);
            result = statement.executeQuery();
	    while(result.next())
	    {
                sid = result.getInt("sid");
	    }
            statement.close();            
	}
	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        } 
		
	return( sid );            
    }

     
    //�ǤJ�P����Ӹ��,�M��N����Ʀs�J��Ʈw��detail��ƪ�
    public void insert_detail(String[] detail)
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
	
        //�bsmsdb��Ʈw��, �s�W�@����ƨ��ƪ�: detail  
        try
	{  
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
            cmdData = "INSERT INTO detail(sid,pname,price,samount,ptotal)"+
                      "VALUES(?,?,?,?,?)";

            statement = connection.prepareStatement(cmdData);
	    statement.setInt(1,Integer.valueOf(detail[0]));
            statement.setString(2,detail[1]);
            statement.setInt(3,Integer.valueOf(detail[2]));
            statement.setInt(4,Integer.valueOf(detail[3]));
            statement.setInt(5,Integer.valueOf(detail[4]));  	
            statement.executeUpdate();
            statement.close();
	}

	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"�o�Ϳ��~0!");
        } 
    }

    public void update_product(String sid, String pname )//�w�s��s
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
	int total=0;
	total=product_count(sid,pname);

	try
	{
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
	    cmdData = " UPDATE product SET product.amount=? WHERE pname=? ";

	    statement = connection.prepareStatement(cmdData);
	    statement.setInt(1,total);
	    statement.setString(2,pname);
	    statement.executeUpdate();
	    statement.close();
	}
	catch(SQLException e)
	{
	    JOptionPane.showMessageDialog(null,"�o�Ϳ��~!");
        }
    }

    //�ǤJ[���~�W��],�p��w�s��P��᪺�Ѿl�æ^�Ǩ�d�o���G
    public int product_count(String sid,String pname)
    {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;

        int total=0;
     
        //��Ʈw�e�m�@�~
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!"); 
        }

        try
	{
            cmdData = "SELECT SUM( product.amount - detail.samount ) AS total FROM product INNER JOIN detail ON product.pname = detail.pname WHERE detail.sid=? AND detail.pname=?";
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
            statement = connection.prepareStatement(cmdData);
	    statement.setInt(1,Integer.parseInt(sid));
            statement.setString(2,pname);
            result = statement.executeQuery();
	    while(result.next())
	    {
                total = result.getInt("total");
	    }
            statement.close();            
	}
	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        } 
		
	return( total );            
    }
} //end for: class CDM_dbma
