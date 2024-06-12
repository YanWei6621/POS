//匯入需要的各類套件
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import java.sql.*;

//問題領域層類別
//CPD_employee: Class ProblemDomain_employee (員工類別)

class CPD_employee
{
    private int eid;   		//屬性:編號整數                    
    private String ename;   	//屬性:姓名字串
    private String position;   	//屬性:職位字串
    private int password;   	//屬性:密碼整數
    
    //建構子:類別CPD_employee
    public CPD_employee()
    {
	eid=0;
        ename="";
        position="";
        password=0;
    }

    //方法:設定編號
    public void seteid(int eid_num)
    {
	eid = eid_num;
    }

    //方法:設定姓名
    public void setename(String aname)
    {
	ename = aname;
    }

    //方法:設定職位
    public void setposition(String aposition)
    {
	position = aposition;
    }

    //方法:設定密碼
    public void setpassword(int password_num)
    {
	password = password_num;
    }

    //方法:取得編號
    public int geteid()
    {
    	return( eid );
    }

    //方法:取得姓名
    public String getename()
    {
    	return( ename );
    }

    //方法:取得職位
    public String getposition()
    {
    	return( position );
    }

    //方法:取得密碼
    public int getpassword()
    {
    	return( password );
    }

 } //end for: class CPD_employee

