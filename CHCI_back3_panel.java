//匯入需要的各類套件
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

//人機互動層類別
//CHCI_back3_panel: Class HumanComputerInteraction_back3_panel (人機介面-[更新產品]操作畫面類別)

class CHCI_back3_panel extends JPanel
{
    //表格
    Vector rowData,columnNames;
    JTable table = null;
    JTableHeader head = null;
    DefaultTableModel DFMO;
    
    Font font1 = new Font("微軟正黑體" , Font.BOLD , 24);
    Font font2 = new Font("微軟正黑體" , Font.BOLD , 28);
    Font font3 = new Font("微軟正黑體" , Font.BOLD , 30);
    Color c1 = new Color(255,218,185);//粉撲桃色
    Color c2 = new Color(255,160,122);//亮鮭紅色
    Color c3 = new Color(255,128,153);//淺鮭紅色
    Color c4 = new Color(240,128,128);//亮珊瑚色
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();

    //建構子:類別CHCI_back3_panel
    public CHCI_back3_panel()
    {
	//表格部分
	columnNames=new Vector();   
        columnNames.add("編號");  
        columnNames.add("姓名");  
        columnNames.add("職位");  
  
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

    public class MouseAddp extends java.awt.event.MouseAdapter//表格點擊
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

    public void delete_employee(int eid)//員工資料刪除
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
	int ans = JOptionPane.showConfirmDialog(null,
			  "是否要刪除該筆員工資料?",
			  "確認",JOptionPane.YES_NO_OPTION,
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
	    	JOptionPane.showMessageDialog(null,"發生錯誤!");
            }
	}
    }

} //end for: class CHCI_OP_panel
