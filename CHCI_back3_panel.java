//�פJ�ݭn���U���M��
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DecimalFormat;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.*;

//�H�����ʼh���O
//CHCI_back3_panel: Class HumanComputerInteraction_back3_panel (�H������-[��s���~]�ާ@�e�����O)

class CHCI_back3_panel extends JPanel
{
    //���
    Vector rowData,columnNames;
    JTable table = null;
    JTableHeader head = null;
    DefaultTableModel DFMO;
    
    Font font1 = new Font("�L�n������" , Font.BOLD , 24);
    Font font2 = new Font("�L�n������" , Font.BOLD , 28);
    Font font3 = new Font("�L�n������" , Font.BOLD , 30);
    Color c1 = new Color(255,218,185);//�������
    Color c2 = new Color(255,160,122);//�G�D����
    Color c3 = new Color(255,128,153);//�L�D����
    Color c4 = new Color(240,128,128);//�G�����
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();

    //�غc�l:���OCHCI_back3_panel
    public CHCI_back3_panel()
    {
	//��泡��
	columnNames=new Vector();   
        columnNames.add("�s��");  
        columnNames.add("�m�W");  
        columnNames.add("¾��");  
  
	DFMO = new DefaultTableModel(rowData, columnNames)
	{
	    public boolean isCellEditable(int row, int column)
	    {
		return false;
	    }
	};
	table = new JTable(DFMO);
	table.setFont(font3);		
	table.setRowHeight(50);
	head=table.getTableHeader();
	head.setFont(font3);
	table.addMouseListener(new MouseAddp());		
	JScrollPane sp = new JScrollPane( table,
					   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	sp.setBounds(13,15,660,650);
	add(sp);

	setBounds(0,30,700,720);
	setBackground(c1);
	setLayout(null);
	setVisible(true);
	datachange();
    }

    public class MouseAddp extends java.awt.event.MouseAdapter//����I��
    {
	public void mouseClicked(java.awt.event.MouseEvent me)
	{
	    if(me.getClickCount()==2) //<--- this is dobule click
	    {
		int row=table.getSelectedRow();
		int cell= table.getModel().getColumnCount();
		int column=0;
		Object selectedValue = table.getModel().getValueAt(row,column);
		int eid=Integer.valueOf(selectedValue.toString());
		delete_employee(eid);
		DFMO.getDataVector().removeAllElements();
		datachange();	
		
	    }
	}
    };

    public void datachange()
    {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;
	rowData = new Vector();    
	
	try
	{
	    cmdData = "SELECT * FROM  employee ";
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.prepareStatement(cmdData);
	    result = statement.executeQuery();
	    while(result.next())
	    {
		Vector<String> newRow = new Vector<>();
		newRow.addElement(String.valueOf(result.getInt("eid")));
		newRow.addElement(result.getString("ename"));
		newRow.addElement(result.getString("position"));
		DFMO.addRow(newRow);
		DFMO.fireTableDataChanged();
	    }
	    statement.close();
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
    }

    public void delete_employee(int eid)//���u��ƧR��
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
	int ans = JOptionPane.showConfirmDialog(null,
			  "�O�_�n�R���ӵ����u���?",
			  "�T�{",JOptionPane.YES_NO_OPTION,
			  JOptionPane.QUESTION_MESSAGE);
	if(ans == JOptionPane.YES_OPTION)
	{
	    try
	    {
	    	connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
	    	cmdData = " DELETE FROM employee WHERE eid=? ";

	    	statement = connection.prepareStatement(cmdData);
	    	statement.setInt(1,eid);
	    	statement.executeUpdate();
	    	statement.close();
	    }
	    catch(SQLException e)
	    {
	    	JOptionPane.showMessageDialog(null,"�o�Ϳ��~!");
            }
	}
    }

} //end for: class CHCI_OP_panel
