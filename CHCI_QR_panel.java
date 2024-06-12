//匯入需要的各類套件
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

//人機互動層類別
//CHCI_QR_panel: Class HumanComputerInteraction_QueRy_panel (人機介面-[庫存]操作畫面類別)

class CHCI_QR_panel extends JPanel
{
    String[] curstr = new String[3]; //JComboBox的名稱儲存，查詢下一個JComboBox
    Boolean flag = true;
    String search;

    int index = 0;
    JPanel p1 = new JPanel();//上方查詢欄
    JLabel lab1 = new JLabel("分類");
    JLabel lab2 = new JLabel("品項");

    JComboBox[] cb = new JComboBox[2];
    String[] CB = {"class","pname","product"};
   
    JButton btn1 = new JButton("查詢");
    JButton btn2 = new JButton("清除");

    //下方表格
    Vector rowData,columnNames;
    JTable table = null;
    JTableHeader head = null;
    DefaultTableModel DFMO;

    Font font2 = new Font("微軟正黑體" , Font.BOLD , 24);
    Font font3 = new Font("微軟正黑體" , Font.BOLD , 30);
    Color c1 = new Color(255,218,185);//粉撲桃色
    Color c2 = new Color(240,128,128);//亮珊瑚色
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();                  
    
    //建構子:類別CHCI_QR_panel
    public CHCI_QR_panel()
    {
	//設定容器p1右上查詢欄.變更
	p1.setLayout(null);
	p1.setBounds(30,35,1220,155);
	p1.setBackground(Color.white);
	p1.setBorder(BorderFactory.createLineBorder(Color.black,2));

	lab1.setFont(font3);//分類
	lab1.setBounds(325,15,60,60);
	p1.add(lab1);

	lab2.setFont(font3);//品項
	lab2.setBounds(625,15,60,60);
	p1.add(lab2);

	for(int i = 0; i < cb.length; i++)
	{
	    cb[i] = new JComboBox(createCB(CB,i));
	    cb[i].addActionListener(combobox);
	    curstr[i] = cb[i].getSelectedItem().toString();
	    cb[i].setFont(font2);
	    p1.add(cb[i]);
	    
	}
	cb[0].setBounds(395,15,200,60);
        cb[1].setBounds(695,15,200,60);
                                                  
	btn1.setBounds(325,90,270,50);//查詢
	btn1.setBackground(c2);
	btn1.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btn1.setFont(font3);
	btn1.addActionListener(ClickBtn);
	p1.add(btn1);
	btn2.setBounds(625,90,270,50);//清除
	btn2.setBackground(c1);
	btn2.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btn2.setFont(font3);
	btn2.addActionListener(ClickBtn);
	p1.add(btn2);
	add(p1);
	
	

	//表格部分
	columnNames=new Vector();   
        columnNames.add("編號");  
        columnNames.add("分類");  
        columnNames.add("品項");  
        columnNames.add("單價");  
        columnNames.add("數量");  
	DFMO = new DefaultTableModel(rowData, columnNames)
	{
	    public boolean isCellEditable(int row, int column)
	    {
		return false;
	    }
	};
	table = new JTable(DFMO);
	table.setFont(font2);		
	table.setRowHeight(40);
	head=table.getTableHeader();
	head.setFont(font2);		
	JScrollPane sp = new JScrollPane( table,
					   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	sp.setBounds(35,210,1220,450);
	add(sp);

	setBounds(0,30,1300,720);
	setBackground(c1);
        setLayout(null);
        setVisible(false);
	datachange();
    }

    public ActionListener ClickBtn = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    if(e.getSource() == btn1)//查詢
	    {
		DFMO.getDataVector().removeAllElements();
		String product=new String();
		product=(String)cb[1].getSelectedItem();
		productsearch(product);
	    }

	    if(e.getSource() == btn2)//清除
	    {
		DFMO.getDataVector().removeAllElements();
		datachange();	
	    }

	}
    };

    public void productsearch(String pname)
    {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;
	rowData = new Vector();    
	
	try
	{
	    cmdData = "SELECT * FROM  product WHERE pname=?";
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.prepareStatement(cmdData);
	    statement.setString(1,pname);
	    result = statement.executeQuery();
	    while(result.next())
	    {
		Vector<String> newRow = new Vector<>();
		newRow.addElement(String.valueOf(result.getInt("pid")));
		newRow.addElement(result.getString("class"));
		newRow.addElement(result.getString("pname"));
		newRow.addElement(String.valueOf(result.getInt("price")));
		newRow.addElement(String.valueOf(result.getInt("amount")));
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


    //傳入ComboBox的分類跟哪個資料表，傳入數值代表第幾個ComboBOx， 建立ComboBox的方法 
    public String[] createCB(String[] name,int num)
    {
	Connection con;
	Statement sta;
	ResultSet res;

	ArrayList myList = new ArrayList();
	String CB;
	try
	{
	    con = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
	    sta=con.createStatement();
	    if(num == 0)
		CB = "SELECT * FROM "+name[name.length-1]+" GROUP BY "+name[0]+ " ORDER BY 1 ASC";
	    else
		CB = "SELECT * FROM "+name[name.length-1]+" WHERE "+name[0]+"='"+curstr[0]+"'"+" GROUP BY "+name[1]+ " ORDER BY 2 ASC";
	    res=sta.executeQuery(CB);
	    while(res.next())
		myList.add(res.getString(name[num]));
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	Object list[] = myList.toArray();
	String[] stringArray = Arrays.copyOf(list,list.length,String[].class);
	return stringArray;
    }

    //傳入一個數值辨別是哪個類型，傳入一個字串紀錄類型的名稱並且去資料庫尋找，選擇ComboBox內容並更換下一個ComboBox內容的方法 
    public void selectCB(int num,String[] name, String[] type, JComboBox cb)
    {
	Connection con;
	Statement sta;
	ResultSet res;

	ArrayList myList = new ArrayList();
	String CB;
	try
	{
	    con = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb" + "?user=root&password=mysql");
	    sta=con.createStatement();
	    if(num == 0)
	    {
		CB = "SELECT * FROM "+type[type.length-1]+" WHERE "+type[num]+"='"+name[num]+"'"+" GROUP BY "+type[num+1]+ " ORDER BY 1 ASC";
	    }
	    else
	    {
		CB = "SELECT * FROM "+type[type.length-1]+" WHERE "+type[num-1]+"='"+name[num-1]+"'"+" AND "+type[num]+"='"+name[num]+"'"+" GROUP BY "+type[num+1]+ " ORDER BY 1 ASC";
	    }
	    res=sta.executeQuery(CB);
	    while(res.next())
		myList.add(res.getString(type[num+1]));
	    res.close();
	    Object list[] = myList.toArray();
	    String[] stringArray = Arrays.copyOf(list,list.length,String[].class);
	    for(int i= 0; i <stringArray.length; i++)
		cb.addItem(stringArray[i]);
	    int a = cb.getItemCount();
	    for(int i=0;i < a-list.length;i++)
		cb.removeItemAt(0);
	    flag =true;
	} 
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    public ActionListener combobox = new ActionListener()
    {
	public void actionPerformed(ActionEvent a)
	{
	    if(a.getSource() == cb[0]) 
	    {
		if(flag)
		{
		    curstr[0] = cb[0].getSelectedItem().toString();
		    flag = false;
		    if(a.getSource() == cb[0])
			selectCB(0,curstr,CB,cb[1]);
		    curstr[1] = cb[1].getSelectedItem().toString();
		}
	    }
	}
    };

    public void CB_setSelectedIndex()
    {
	for(int i = 0 ; i < 2; i++)
	    cb[i].setSelectedIndex(0);
    }

    //取得ComboBox內的資料，然後回傳一個字串陣列
    String[] getCB_text()
    {
	String[] CB_text = new String[2];
	for(int i = 0; i < CB_text.length; i++)
	    CB_text[i] = cb[i].getSelectedItem().toString();
	return CB_text;
    }

    public void datachange()
    {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;
	rowData = new Vector();    
	
	try
	{
	    cmdData = "SELECT * FROM  product ";
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.prepareStatement(cmdData);
	    result = statement.executeQuery();
	    while(result.next())
	    {
		Vector<String> newRow = new Vector<>();
		newRow.addElement(String.valueOf(result.getInt("pid")));
		newRow.addElement(result.getString("class"));
		newRow.addElement(result.getString("pname"));
		newRow.addElement(String.valueOf(result.getInt("price")));
		newRow.addElement(String.valueOf(result.getInt("amount")));
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

    

    //方法:清空容器內欄位
    public void clearPane()
    {
	for(int i=0 ; i<25 ; i++)
	{
	    for(int j=0;j<5;j++)
	    {
		table.setValueAt(null,i,j);
	    }
	}
    }

} //end for: class CHCI_QR_panel

