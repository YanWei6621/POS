//匯入需要的各類套件
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.*;
import java.text.DateFormat;//自定義日期時間格式
import java.text.SimpleDateFormat;//自定義日期時間格式
import java.util.Date;//自定義日期時間格式
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.*;

//人機互動層類別
//CHCI_CI_panel: Class HumanComputerInteraction_ClockIn_panel (人機介面-[上下班]操作畫面類別)

class CHCI_CI_panel extends JPanel
{

    CFD_check myCheck = new CFD_check();
    CDM_dbma myDBMA = new CDM_dbma();
    CPD_employee myemployee = new CPD_employee();   
    day dayr = new day();//建立日期執行緒
    time timer = new time();//建立時間執行緒
    Date dated = new Date();//建立日期變數
    Date datet = new Date();//建立時間變數
    JPanel p1 = new JPanel();//打卡.員工編號.密碼
    JLabel lab4 = new JLabel("員工編號");
    JLabel lab5 = new JLabel("員工密碼");
    JLabel lab6 = new JLabel("員工姓名");
    JTextField tf3 = new JTextField("");
    JTextField tf4 = new JTextField();//編號
    JLabel labn = new JLabel();//姓名
    JPasswordField pf = new JPasswordField();//密碼
    JButton btn3 = new JButton("打卡");

    JPanel p2 = new JPanel();//日期
    JLabel lab1 = new JLabel("現在日期");
    JLabel lab2 = new JLabel("現在時間");
    JLabel labd = new JLabel("");
    JLabel labt = new JLabel("");

    JPanel p3 = new JPanel();//查詢欄
    JLabel labData = new JLabel("查詢日期");
    JTextField tfData = new JTextField("");
    JButton btnc = new JButton("選擇");
    JButton btny = new JButton("查詢");
    JButton btno = new JButton("清除");
    
    //左邊表格
    Vector rowData,columnNames;
    JTable table = null;
    JTableHeader head = null;
    DefaultTableModel DFMO;

    Font font1 = new Font("微軟正黑體" , Font.PLAIN , 24);
    Font font2 = new Font("微軟正黑體" , Font.BOLD , 24);
    Font font3 = new Font("微軟正黑體" , Font.BOLD , 30);
    Font font4 = new Font("微軟正黑體" , Font.PLAIN , 20);
    Color c1 = new Color(255,218,185);//粉撲桃色
    Color c2 = new Color(255,160,122);//亮鮭紅色
    Color c3 = new Color(135,206,250);//淺天藍色
    Color c4 = new Color(255,255,153);//香檳黃色
    Color c5 = new Color(255,182,193);//亮粉紅色
    Color c6 = new Color(240,128,128);//亮珊瑚色
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();
    int index = 0;
    
    //建構子:類別CHCI_CI_panel
    public CHCI_CI_panel()
    {
	dayr.start();//呼叫日期執行緒start()方法
	timer.start();//呼叫時間執行緒start()方法

	//設定容器p1日期.打卡.員工編號.密碼
	p1.setLayout(null);
	p1.setBounds(810,340,450,315);
	p1.setBackground(Color.white);
	p1.setBorder(BorderFactory.createLineBorder(Color.black,2));
	
	lab4.setFont(font3);//員工編號
	lab4.setBounds(20,10,125,65);

	p1.add(lab4);
	lab5.setFont(font3);//員工密碼
	lab5.setBounds(20,85,125,65);
	p1.add(lab5);
	lab6.setFont(font3);//員工姓名
	lab6.setBounds(20,160,125,65);
	p1.add(lab6);
	tf4.setBounds(155,10,280,65);//編號欄
	tf4.setFont(font3);
	tf4.setBorder(BorderFactory.createLineBorder(Color.black,2));
	p1.add(tf4);
	pf.setBounds(155,85,280,65);//密碼欄
	pf.setFont(font3);
	pf.setBorder(BorderFactory.createLineBorder(Color.black,2));
	pf.addActionListener(ClickBtn);
	p1.add(pf);
	labn.setBounds(155,160,280,65);//姓名欄
	labn.setFont(font3);
	labn.setBorder(BorderFactory.createLineBorder(Color.black,2));
	p1.add(labn);
	
	btn3.setBounds(20,235,415,70);//打卡
	btn3.setBackground(c5);
	btn3.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btn3.setFont(font3);
	btn3.addActionListener(ClickBtn);
	p1.add(btn3);
	add(p1);
	
	//設定容器p2日期
	p2.setLayout(null);
	p2.setBounds(810,20,450,150);
	p2.setBackground(Color.white);
	p2.setBorder(BorderFactory.createLineBorder(Color.black,2));
	lab1.setFont(font3);//現在日期
	lab1.setBounds(20,10,120,60);
	p2.add(lab1);
	lab2.setFont(font3);//現在時間
	lab2.setBounds(20,75,120,60);
	p2.add(lab2);
	labd.setBounds(150,15,285,55);//日期欄
	labd.setFont(font3);
	labd.setBorder(BorderFactory.createLineBorder(Color.black,2));
	p2.add(labd);
	labt.setBounds(150,80,285,55);//時間欄
	labt.setFont(font3);
	labt.setBorder(BorderFactory.createLineBorder(Color.black,2));
	p2.add(labt);
	add(p2);
	
	//設定容器p3查詢
	p3.setLayout(null);
	p3.setBounds(810,180,450,150);
	p3.setBackground(Color.white);
	p3.setBorder(BorderFactory.createLineBorder(Color.black,2));
	labData.setFont(font3);//查詢日期
	labData.setBounds(20,10,120,60);
	p3.add(labData);
	tfData.setBounds(150,15,285,55);//日期欄
	tfData.setFont(font3);
	tfData.setBackground(Color.white);
	tfData.setBorder(BorderFactory.createLineBorder(Color.black,2));
	p3.add(tfData);
	btnc.setBounds(20,80,135,50);//選擇
	btnc.setBackground(c3);
	btnc.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btnc.setFont(font3);
	btnc.addActionListener(SelectDate);
	p3.add(btnc);
	btny.setBounds(160,80,135,50);//查詢
	btny.setBackground(c6);
	btny.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btny.setFont(font3);
	btny.addActionListener(ClickBtn);
	p3.add(btny);
	btno.setBounds(300,80,135,50);//清除
	btno.setBackground(c1);
	btno.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btno.setFont(font3);
	btno.addActionListener(ClickBtn);
	p3.add(btno);
	add(p3);

	
	//左邊表格部分
	columnNames=new Vector();   
        columnNames.add("日期");  
        columnNames.add("編號");  
        columnNames.add("姓名");  
        columnNames.add("職位");  
        columnNames.add("上班時間"); 
	columnNames.add("下班時間");
	DFMO = new DefaultTableModel(rowData, columnNames)
	{
	    public boolean isCellEditable(int row, int column)
	    {
		return false;
	    }
	}; 
	table = new JTable(DFMO);
	table.setFont(font4);		
	table.setRowHeight(40);
	head=table.getTableHeader();			
	head.setFont(font1);		
	JScrollPane sp = new JScrollPane( table,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	sp.setBounds(25,20,750,635);
	add(sp);

        setBounds(0,30,1300,720);
	setBackground(c1);
        setLayout(null);
        setVisible(false);
	datachange();

    }

    //方法:清空容器內欄位
    public void clearPane()
    {
	tfData.setText("");
        tf4.setText("");  
	labn.setText("");
	pf.setText("");
    }

    //選擇日期
    public ActionListener SelectDate = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    if(e.getSource() == btnc)
	    {
		DatePopup dp = new DatePopup(tfData);
		dp.showDialog();
	    }	  
	}
    }; 

    //日期執行緒
    public class day extends Thread
    {
	public void run()
	{
	    SimpleDateFormat day = new SimpleDateFormat("yyyy/MM/dd");
	    while(true)
	    {
		Date dated = new Date();
		labd.setText(day.format(dated));
	    }
	}
    }

    //時間執行緒
    public class time extends Thread
    {
	public void run()
	{
	    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
	    while(true)
	    {
		Date datet = new Date();
		labt.setText(time.format(datet));
	    }
	}
    }

    //上下班狀態
    public ActionListener ClickBtn = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    if (e.getSource() == pf) 
	    {
                boolean checkPass = true;            //用來記錄[輸入的資料]檢查結果
		String eidString = tf4.getText().trim();
	        String passwordString = pf.getText().trim();
	        String s3 = labn.getText().trim();
		int[] eidpf=new int[2];
	    	if( myCheck.checkNumber( eidString ) == false )//利用檢查物件(myCheck)的checkNumber()方法,檢查chineseString是否為正確的數值格式,如:98,80,...等
            	{  
            	    checkPass = false;
                    JOptionPane.showMessageDialog(null,"[員工編號] 輸入資料錯誤!","操作警訊",JOptionPane.ERROR_MESSAGE);
                }

            	if( myCheck.checkNumber( passwordString ) == false )
            	{  //利用檢查物件(myCheck)的checkNumber()方法,檢查englishString是否為正確的數值格式,如:98,80,...等
            	    checkPass = false;
                    JOptionPane.showMessageDialog(null,"[員工密碼] 輸入資料錯誤!","操作警訊",JOptionPane.ERROR_MESSAGE);
            	}
		
		//如果上述檢查都沒發現錯誤,則checkPass會維持true,即通過檢查,因此將員工的資料設定到myemployee物件中對應屬性質  
            	if( checkPass == true )
	    	{
                    eidpf[0]= Integer.parseInt(eidString); //Integer.parseInt()是將字串轉成整數的方法  
                    eidpf[1]= Integer.parseInt(passwordString);
		    String findResult = myDBMA.findRD_in_TB_employee(eidpf);//將物件傳入查詢員工的方法(findRD_in_TB_employee())
		    labn.setText(findResult);
 		}
            }

	    if(e.getSource() == btn3)
	    {
		String[] clock=new String[3];
		clock[0]=labd.getText();//日期
		clock[1]=tf4.getText();//編號
		clock[2]=labt.getText();//時間
		myDBMA.clockin(clock);
		DFMO.getDataVector().removeAllElements();
		datachange();	
		tf4.setText("");
		pf.setText("");
		labn.setText("");
	    }
	    if(e.getSource() == btny)//查詢
	    {
		    String date=new String();
		    date=tfData.getText();//日期
		    DFMO.getDataVector().removeAllElements();
		    search(date);
	    }
	    if(e.getSource() == btno)//清除
	    {
		DFMO.getDataVector().removeAllElements();
		datachange();
		tfData.setText("");	
		    
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
	    cmdData = "SELECT clockin.* , employee.ename , employee.position FROM  employee INNER JOIN clockin ON employee.eid = clockin.eid ";
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.prepareStatement(cmdData);
	    result = statement.executeQuery();
	    while(result.next())
	    {
		Vector<String> newRow = new Vector<>();
		newRow.addElement(result.getString("date"));
		newRow.addElement(String.valueOf(result.getInt("eid")));
		newRow.addElement(result.getString("ename"));
		newRow.addElement(result.getString("position"));
		newRow.addElement(result.getString("startwork"));
		newRow.addElement(result.getString("offwork"));
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

    public void search(String date)
    {
	Connection connection;
        PreparedStatement statement;
        ResultSet result;
        String cmdData;
	rowData = new Vector();    
	
	try
	{
	    cmdData = "SELECT clockin.* , employee.ename , employee.position FROM  employee INNER JOIN clockin ON employee.eid = clockin.eid WHERE date=?";
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    statement = connection.prepareStatement(cmdData);
	    statement.setString(1,date);
	    result = statement.executeQuery();
	    while(result.next())
	    {
		Vector<String> newRow = new Vector<>();
		newRow.addElement(result.getString("date"));
		newRow.addElement(String.valueOf(result.getInt("eid")));
		newRow.addElement(result.getString("ename"));
		newRow.addElement(result.getString("position"));
		newRow.addElement(result.getString("startwork"));
		newRow.addElement(result.getString("offwork"));
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


 } //end for: class CHCI_CI_panel

 