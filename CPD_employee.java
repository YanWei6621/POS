//�פJ�ݭn���U���M��
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import java.sql.*;

//���D���h���O
//CPD_employee: Class ProblemDomain_employee (���u���O)

class CPD_employee
{
    private int eid;   		//�ݩ�:�s�����                    
    private String ename;   	//�ݩ�:�m�W�r��
    private String position;   	//�ݩ�:¾��r��
    private int password;   	//�ݩ�:�K�X���
    
    //�غc�l:���OCPD_employee
    public CPD_employee()
    {
	eid=0;
        ename="";
        position="";
        password=0;
    }

    //��k:�]�w�s��
    public void seteid(int eid_num)
    {
	eid = eid_num;
    }

    //��k:�]�w�m�W
    public void setename(String aname)
    {
	ename = aname;
    }

    //��k:�]�w¾��
    public void setposition(String aposition)
    {
	position = aposition;
    }

    //��k:�]�w�K�X
    public void setpassword(int password_num)
    {
	password = password_num;
    }

    //��k:���o�s��
    public int geteid()
    {
    	return( eid );
    }

    //��k:���o�m�W
    public String getename()
    {
    	return( ename );
    }

    //��k:���o¾��
    public String getposition()
    {
    	return( position );
    }

    //��k:���o�K�X
    public int getpassword()
    {
    	return( password );
    }

 } //end for: class CPD_employee

