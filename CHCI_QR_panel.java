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
//CHCI_QR_panel: Class HumanComputerInteraction_QueRy_panel (�H������-[�w�s]�ާ@�e�����O)

class CHCI_QR_panel extends JPanel
{
    String[] curstr = new String[3]; //JComboBox���W���x�s�A�d�ߤU�@��JComboBox
    Boolean flag = true;
    String search;

    int index = 0;
    JPanel p1 = new JPanel();//�W��d����
    JLabel lab1 = new JLabel("����");
    JLabel lab2 = new JLabel("�~��");

    JComboBox[] cb = new JComboBox[2];
    String[] CB = {"class","pname","product"};
   
    JButton btn1 = new JButton("�d��");
    JButton btn2 = new JButton("�M��");

    //�U����
    Vector rowData,columnNames;
    JTable table = null;
    JTableHeader head = null;
    DefaultTableModel DFMO;

    Font font2 = new Font("�L�n������" , Font.BOLD , 24);
    Font font3 = new Font("�L�n������" , Font.BOLD , 30);
    Color c1 = new Color(255,218,185);//�������
    Color c2 = new Color(240,128,128);//�G�����
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();                  
    
    //�غc�l:���OCHCI_QR_panel
    public CHCI_QR_panel()
    {
	//�]�w�e��p1�k�W�d����.�ܧ�
	p1.setLayout(null);
	p1.setBounds(30,35,1220,155);
	p1.setBackground(Color.white);
	p1.setBorder(BorderFactory.createLineBorder(Color.black,2));

	lab1.setFont(font3);//����
	lab1.setBounds(325,15,60,60);
	p1.add(lab1);

	lab2.setFont(font3);//�~��
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
                                                  
	btn1.setBounds(325,90,270,50);//�d��
	btn1.setBackground(c2);
	btn1.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btn1.setFont(font3);
	btn1.addActionListener(ClickBtn);
	p1.add(btn1);
	btn2.setBounds(625,90,270,50);//�M��
	btn2.setBackground(c1);
	btn2.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btn2.setFont(font3);
	btn2.addActionListener(ClickBtn);
	p1.add(btn2);
	add(p1);
	
	

	//��泡��
	columnNames=new Vector();   
        columnNames.add("�s��");  
        columnNames.add("����");  
        columnNames.add("�~��");  
        columnNames.add("���");  
        columnNames.add("�ƶq");  
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
	    if(e.getSource() == btn1)//�d��
	    {
		DFMO.getDataVector().removeAllElements();
		String product=new String();
		product=(String)cb[1].getSelectedItem();
		productsearch(product);
	    }

	    if(e.getSource() == btn2)//�M��
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


    //�ǤJComboBox����������Ӹ�ƪ�A�ǤJ�ƭȥN��ĴX��ComboBOx�A �إ�ComboBox����k 
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

    //�ǤJ�@�Ӽƭȿ�O�O���������A�ǤJ�@�Ӧr������������W�٨åB�h��Ʈw�M��A���ComboBox���e�ç󴫤U�@��ComboBox���e����k 
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

    //���oComboBox������ơA�M��^�Ǥ@�Ӧr��}�C
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

    

    //��k:�M�Ůe�������
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

