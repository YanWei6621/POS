//匯入需要的各類套件
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;


//資料管理層類別
//CDM_dbma: Class DatabaseManagement_database manipulation and acess (資料庫操作與存取類別)

class CDM_dbma
{                    
    Connection connection;
    Statement statement;
    
    
    //建構子:類別Cdbma
    public CDM_dbma()
    {   
    }
     
    //方法:建立資料庫bmsdb
    public void createDB()
    {
	//安裝MySQL驅動程式, 與建立資料庫bmsdb
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
	    JOptionPane.showMessageDialog(null,"MySQL驅動程式安裝失敗!"); 
        }
        //建立 bmsdb資料庫
        try
	{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+
                                                     "?user=root&password=mysql");
            statement = connection.createStatement();
            String createDB = "CREATE DATABASE bmsdb";
            statement.executeUpdate(createDB);
            JOptionPane.showMessageDialog(null,"bmsdb資料庫建立成功!");
            statement.close();   
	} 
	catch(SQLException e)
	{
            if(statement != null) 
            	JOptionPane.showMessageDialog(null,"bmsdb資料庫已存在,可正常使用!");
            else
                JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        }

    } //end for: public void createDB()

    //建立資料庫bmsdb中的資料表:product
    public void createTB_product()
    {
	try
	{
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.createStatement();
	    String createTB = "CREATE TABLE product("+
                              "pid             INT PRIMARY KEY, "+  //產品編號
                              "class           VARCHAR(15), "+      //產品分類  
                              "pname           VARCHAR(15), "+      //產品名稱
                              "price           INT,"+	    	    //產品售價
			      "amount	       INT)";               //產品數量

	    statement.executeUpdate(createTB);
 	    JOptionPane.showMessageDialog(null,"product資料表建立成功!");
	    statement.close();
       
	} 
	catch(SQLException e)
	{
	    if(statement != null) 
	    	JOptionPane.showMessageDialog(null,"product資料表已存在,可正常使用!"); 
            else
                JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
        } 
	catch(Exception e)
	{
	    JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        }  
    }

    //建立資料庫bmsdb中的資料表:employee
    public void createTB_employee()
    {
	try
	{
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.createStatement();
	    String createTB = "CREATE TABLE employee("+
                              "eid             INT PRIMARY KEY, "+  //員工編號  
                              "ename           VARCHAR(15), "+      //員工姓名
                              "position        VARCHAR(15),"+	    //員工職位
			      "password	       INT)";               //員工密碼

	    statement.executeUpdate(createTB);
 	    JOptionPane.showMessageDialog(null,"employee資料表建立成功!");
	    statement.close();
       
	} 
	catch(SQLException e)
	{
	    if(statement != null) 
	    	JOptionPane.showMessageDialog(null,"employee資料表已存在,可正常使用!"); 
            else
                JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
        } 
	catch(Exception e)
	{
	    JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        }  
    }

    //建立資料庫bmsdb中的資料表:clockin
    public void createTB_clockin()
    {
	try
	{
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.createStatement();
	    String createTB = "CREATE TABLE clockin("+
                              "num             INT AUTO_INCREMENT PRIMARY KEY, "+  //自動編號
                              "date            DATE, "+  //日期 
                              "eid             INT, "+  //員工編號                
			      "startwork       TIME NOT NULL,"+  //上班時間
			      "offwork         TIME NOT NULL)";  //下班時間       

	    statement.executeUpdate(createTB);
 	    JOptionPane.showMessageDialog(null,"clockin資料表建立成功!");
	    statement.close();
       
	} 
	catch(SQLException e)
	{
	    if(statement != null) 
	    	JOptionPane.showMessageDialog(null,"clockin資料表已存在,可正常使用!"); 
            else
                JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
        } 
	catch(Exception e)
	{
	    JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        }  
    } 

    //建立資料庫bmsdb中的資料表:sell 銷售表
    public void createTB_sell()
    {
	try
	{
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.createStatement();
	    String createTB = "CREATE TABLE sell("+
			      "sid             INT AUTO_INCREMENT PRIMARY KEY, "+      //銷售編號
                              "sdate           DATE, "+  		//銷售日期                           
			      "stotal	       INT)";               //銷售產品總計

	    statement.executeUpdate(createTB);
 	    JOptionPane.showMessageDialog(null,"sell資料表建立成功!");
	    statement.close();
       
	} 
	catch(SQLException e)
	{
	    if(statement != null) 
	    	JOptionPane.showMessageDialog(null,"sell資料表已存在,可正常使用!"); 
            else
                JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
        } 
	catch(Exception e)
	{
	    JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        }  
    }

    //建立資料庫bmsdb中的資料表:detail 銷售明細
    public void createTB_detail()
    {
	try
	{
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.createStatement();
	    String createTB = "CREATE TABLE detail("+
                              "snum            INT AUTO_INCREMENT PRIMARY KEY, "+  //自動編號
                              "sid             INT, "+  //銷售編號  
                              "pname           VARCHAR(15), "+  //產品名稱
                              "price           INT,"+	    	    //產品價錢
                              "samount         INT,"+	    	    //產品銷售數量
			      "ptotal	       INT)";               //產品銷售小計

	    statement.executeUpdate(createTB);
 	    JOptionPane.showMessageDialog(null,"detail資料表建立成功!");
	    statement.close();
       
	} 
	catch(SQLException e)
	{
	    if(statement != null) 
	    	JOptionPane.showMessageDialog(null,"detail資料表已存在,可正常使用!"); 
            else
                JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
        } 
	catch(Exception e)
	{
	    JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        }  
    }

    //傳入完整的一筆員工物件資料,然後將此資料存入資料庫的employee資料表中
    public void insertRD_into_TB_employee(CPD_employee aemployee)
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

        //在smsdb資料庫中, 新增一筆員工資料到資料表: employee   
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
            JOptionPane.showMessageDialog(null,"在bmsdb資料庫中, 成功寫入一筆[員工記錄]到employee資料表中!");
            statement.close();
        } 
	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"在bmsdb資料庫中, 寫入一筆[員工記錄]到employee資料表中發生錯誤!");
        }
     }

     //傳入一筆[產品編號],查詢出該產品的[品項]與[單價]資料並回傳其查得[品項][單價]字串結果
     public String[] findRD_in_TB_product(int apid)
     {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;

        String pname="";
        int price=0;
        String[] myResult = new String[2];
     
        //資料庫前置作業
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL驅動程式安裝失敗!"); 
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
            JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        } 

        myResult[0] = pname;
        myResult[1] = String.valueOf(price);   //String.valueOf() 是將[整數值]轉成字串
	return( myResult );            
    }


     //傳入[編號密碼],查詢出該員工的[姓名]資料並回傳其查得字串結果
     public String findRD_in_TB_employee(int[] aeid)
     {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;

        String ename="";
        String myResult="";
     
        //資料庫前置作業
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL驅動程式安裝失敗!"); 
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
            JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        } 

        myResult = ename;

	return( myResult );            
    }

    //傳入完整的一筆打卡物件資料,然後將此資料存入資料庫的clockin資料表中
    public void clockin(String[] clock)
    {

	int re=0;
	String date=clock[0];
	int eid=Integer.valueOf(clock[1]);
	re=findRD_in_TB_clockin(date,eid);

        //在smsdb資料庫中, 新增一筆資料到資料表: clockin
	if(re==0)
	    insert_clockin(clock);
	if(re==1)
	    update_clockin(clock);   	
    
     }

    //傳入[日期編號],查詢出該員工的[上班打卡]資料並回傳其查得結果
    public int findRD_in_TB_clockin(String adate,int aeid)
    {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;

        int eid=0;
     
        //資料庫前置作業
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL驅動程式安裝失敗!"); 
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
		JOptionPane.showMessageDialog(null,"無記錄");
		
	    }
	    else
	    {
	    	eid=1;
		JOptionPane.showMessageDialog(null,"有記錄");
            }
            statement.close();            
	}
	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        } 
		
	return( eid );            
    }

    public void update_clockin(String[] offwork)//打卡更新
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

	try
	{
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
	    cmdData = " UPDATE clockin SET offwork=? WHERE date=? AND eid=? ";

	    statement = connection.prepareStatement(cmdData);
	    statement.setString(1,offwork[2]);
	    statement.setString(2,offwork[0]);
	    statement.setInt(3,Integer.valueOf(offwork[1]));

	    statement.executeUpdate();
	    JOptionPane.showMessageDialog(null,"打卡成功");
	    statement.close();
	}

	catch(SQLException e)
	{
	    JOptionPane.showMessageDialog(null,"打卡發生錯誤!");
        }
    }

    //傳入完整的一筆打卡物件資料,然後將此資料存入資料庫的clockin資料表中
    public void insert_clockin(String[] clock)
    {
    	Connection connection;
        PreparedStatement statement;
        String cmdData;
	int re=0;

        //資料庫前置作業
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL驅動程式安裝失敗!"); 
        }
	

        //在smsdb資料庫中, 新增一筆資料到資料表: clockin  
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
            JOptionPane.showMessageDialog(null,"打卡成功");
            statement.close();
	}

	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"打卡發生錯誤!");
        } 
     }

    //傳入銷售資料,然後將此資料存入資料庫的sell資料表中
    public void insert_sell(String[] sell)
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
	
        //在smsdb資料庫中, 新增一筆資料到資料表: sell  
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
            JOptionPane.showMessageDialog(null,"發生錯誤1!");
        } 
     }

    //查詢銷售編號
    public int findsid()
    {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;

        int sid=0;
     
        //資料庫前置作業
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL驅動程式安裝失敗!"); 
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
            JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        } 
		
	return( sid );            
    }

     
    //傳入銷售明細資料,然後將此資料存入資料庫的detail資料表中
    public void insert_detail(String[] detail)
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
	
        //在smsdb資料庫中, 新增一筆資料到資料表: detail  
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
            JOptionPane.showMessageDialog(null,"發生錯誤0!");
        } 
    }

    public void update_product(String sid, String pname )//庫存更新
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
	    JOptionPane.showMessageDialog(null,"發生錯誤!");
        }
    }

    //傳入[產品名稱],計算庫存減銷售後的剩餘並回傳其查得結果
    public int product_count(String sid,String pname)
    {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;

        int total=0;
     
        //資料庫前置作業
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        } 
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL驅動程式安裝失敗!"); 
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
            JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        } 
		
	return( total );            
    }
} //end for: class CDM_dbma
