//�פJ�ݭn���U���M��
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


//�H�����ʼh���O
//CHCI_SR_panel: Class HumanComputerInteraction_SearchResult_panel (�H������-[�P��]�ާ@�e�����O)

class CHCI_SR_panel extends JPanel
{
    int index = 0;

    //�W��d����
    JPanel p1 = new JPanel();
    JLabel lab1 = new JLabel("�d�ߤ��");
    JTextField tf1 = new JTextField("");
    JButton btn1 = new JButton("���");
    JButton btn2 = new JButton("�d��");
    JButton btn3 = new JButton("�M��");
    
    //����P�����
    Vector sellrowData,sellcolumnNames;
    JTable selltable = null;
    JTableHeader sellhead = null;
    DefaultTableModel sellDFMO;

    //�k�U��������
    JPanel p2 = new JPanel();
    JLabel lab2 = new JLabel("�P�����");
    String[] title2 = { "�~��" , "���" , "�ƶq" , "�p�p" };
    String[][] data2 = new String[100][4];
    JTable table2 = new JTable(data2,title2);
    JTableHeader head2 = table2.getTableHeader();

    Vector detailrowData,detailcolumnNames;
    JTable detailtable = null;
    JTableHeader detailhead = null;
    DefaultTableModel detailDFMO;
  
    Font font1 = new Font("�L�n������" , Font.BOLD , 40);
    Font font2 = new Font("�L�n������" , Font.BOLD , 24);
    Font font3 = new Font("�L�n������" , Font.BOLD , 30);
    Color c1 = new Color(255,218,185);//�������
    Color c2 = new Color(135,206,250);//�L���Ŧ�
    Color c3 = new Color(240,128,128);//�G�����
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();                  
    
    //�غc�l:���OCHCI_SR_panel
    public CHCI_SR_panel()
    {
	//�]�w�e��p1�k�W�ɶ��d����
	p1.setLayout(null);
	p1.setBounds(600,15,650,150);
	p1.setBackground(Color.white);
	p1.setBorder(BorderFactory.createLineBorder(Color.black,2));
	lab1.setFont(font3);//���
	lab1.setBounds(120,10,120,60);
	p1.add(lab1);
	tf1.setBounds(250,15,280,55);//�����
	tf1.setFont(font3);
	tf1.setBackground(Color.white);
	tf1.setBorder(BorderFactory.createLineBorder(Color.black,2));
	p1.add(tf1);
	btn1.setBounds(90,80,150,50);//���
	btn1.setBackground(c2);
	btn1.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btn1.setFont(font3);
	btn1.addActionListener(SelectDate);
	p1.add(btn1);
	btn2.setBounds(250,80,150,50);//�d��
	btn2.setBackground(c3);
	btn2.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btn2.setFont(font3);
	btn2.addActionListener(ClickBtn);
	p1.add(btn2);
	btn3.setBounds(410,80,150,50);//�M��
	btn3.setBackground(c1);
	btn3.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btn3.setFont(font3);
	btn3.addActionListener(ClickBtn);
	p1.add(btn3);
	add(p1);

	//����P�����
	sellcolumnNames=new Vector();   
        sellcolumnNames.add("���");  
        sellcolumnNames.add("�s��");  
        sellcolumnNames.add("�`��");      
	sellDFMO = new DefaultTableModel(sellrowData, sellcolumnNames)
	{
	    public boolean isCellEditable(int row, int column)
	    {
		return false;
	    }
	};
	selltable = new JTable(sellDFMO);
	selltable.setFont(font2);		
	selltable.setRowHeight(40);
	sellhead=selltable.getTableHeader();
	sellhead.setFont(font2);
	selltable.addMouseListener(new MouseAddp());		
	JScrollPane sp1 = new JScrollPane( selltable,
					   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	sp1.setBounds(35,15,530,635);
	add(sp1);
	
	//�]�w�e��p2�k��������
	p2.setLayout(null);
	p2.setBounds(600,175,650,480);
	p2.setBackground(Color.white);
	p2.setBorder(BorderFactory.createLineBorder(Color.black,2));

	lab2.setFont(font3);//�P�����
	//lab2.setBorder(BorderFactory.createLineBorder(Color.black,2));
	lab2.setBounds(265,0,120,60);
	p2.add(lab2);

	detailcolumnNames=new Vector();   
        detailcolumnNames.add("�~��");  
        detailcolumnNames.add("���");  
        detailcolumnNames.add("�ƶq");  
        detailcolumnNames.add("�p�p");  
	detailDFMO = new DefaultTableModel(detailrowData, detailcolumnNames)
	{
	    public boolean isCellEditable(int row, int column)
	    {
		return false;
	    }
	};
	detailtable = new JTable(detailDFMO);
	detailtable.setFont(font2);		
	detailtable.setRowHeight(40);
	detailhead=detailtable.getTableHeader();
	detailhead.setFont(font2);		
	JScrollPane sp2 = new JScrollPane( detailtable,
					   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	sp2.setBounds(15,60,620,405);
	p2.add(sp2);
	add(p2);

	setBounds(0,30,1300,720);
	setBackground(c1);
        setLayout(null);
        setVisible(false);
	selldatachange();
    }

    //��k:�M�Ůe�������
    public void clearPane()
    {
	tf1.setText("");
    }

    public ActionListener SelectDate = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    if(e.getSource() == btn1)
	    {
		DatePopup dp = new DatePopup(tf1);
		dp.showDialog();
	    }	  
	}
    };

    public ActionListener ClickBtn = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    if(e.getSource() == btn2)//�d��
	    {
		sellDFMO.getDataVector().removeAllElements();
		detailDFMO.setRowCount(0);
		String date=new String();
		date=tf1.getText();//���
		sellsearch(date);
	    }
	    if(e.getSource() == btn3)//�M��
	    {
		sellDFMO.getDataVector().removeAllElements();
		detailDFMO.setRowCount(0);
		selldatachange();
		tf1.setText("");	
	    }

	}
    };

    public void sellsearch(String date)
    {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;
	sellrowData = new Vector();    
	
	try
	{
	    cmdData = "SELECT * FROM  sell WHERE sdate=?";
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.prepareStatement(cmdData);
	    statement.setString(1,date);
	    result = statement.executeQuery();
	    while(result.next())
	    {
		Vector<String> newRow = new Vector<>();
		newRow.addElement(result.getString("sdate"));
		newRow.addElement(String.valueOf(result.getInt("sid")));
		newRow.addElement(String.valueOf(result.getInt("stotal")));
		sellDFMO.addRow(newRow);
		sellDFMO.fireTableDataChanged();
	    }
	    statement.close();
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
    }


    public void selldatachange()
    {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;
	sellrowData = new Vector();    
	
	try
	{
	    cmdData = "SELECT * FROM  sell ";
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.prepareStatement(cmdData);
	    result = statement.executeQuery();
	    while(result.next())
	    {
		Vector<String> newRow = new Vector<>();
		newRow.addElement(result.getString("sdate"));
		newRow.addElement(String.valueOf(result.getInt("sid")));
		newRow.addElement(String.valueOf(result.getInt("stotal")));
		sellDFMO.addRow(newRow);
		sellDFMO.fireTableDataChanged();
	    }
	    statement.close();
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
    }

    public void detaildatachange(String sid)
    {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;
	detailrowData = new Vector();    
	
	try
	{
	    cmdData = "SELECT * FROM  detail WHERE sid=? ";
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.prepareStatement(cmdData);
	    statement.setInt(1,Integer.parseInt(sid));
	    result = statement.executeQuery();
	    while(result.next())
	    {
		Vector<String> newRow = new Vector<>();
		newRow.addElement(result.getString("pname"));
		newRow.addElement(String.valueOf(result.getInt("price")));
		newRow.addElement(String.valueOf(result.getInt("samount")));
		newRow.addElement(String.valueOf(result.getInt("ptotal")));
		detailDFMO.addRow(newRow);
		detailDFMO.fireTableDataChanged();
	    }
	    statement.close();
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
    }

    public class MouseAddp extends java.awt.event.MouseAdapter
    {
	public void mouseClicked(java.awt.event.MouseEvent me)
	{
	    if(me.getClickCount()==2) //<--- this is dobule click
	    {
		int row=selltable.getSelectedRow();
		int cell= selltable.getModel().getColumnCount();
		int column=1;
		Object selectedValue = selltable.getModel().getValueAt(row,column);
		detailDFMO.getDataVector().removeAllElements();
		detaildatachange(selectedValue.toString());
		
	    }
	}
    };

} //end for: class CHCI_SR_panel

 