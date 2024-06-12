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

//人機互動層類別
//CHCI_back1_panel: Class HumanComputerInteraction_back1_panel (人機介面-[更新產品]操作畫面類別)

class CHCI_back1_panel extends JPanel
{
    String[] curstr = new String[3]; //JComboBox的名稱儲存，查詢下一個JComboBox
    Boolean flag = true;
    String search;
    JPanel p1 = new JPanel();//上方選擇欄
    JLabel lab1 = new JLabel("分類");
    JLabel lab2 = new JLabel("品項");
    JButton btn1 = new JButton("更改");
    
    JComboBox[] cb = new JComboBox[2];
    String[] CB = {"class","pname","product"};

    JPanel p2 = new JPanel();//下方明細欄
    JTextField[] tf = new JTextField[5];
    JLabel[] tfl = new JLabel[5];
    String[] tfstr = {"編號","類別","名稱","單價","數量"};
    JButton btnf = new JButton("完成");
    
    Font font1 = new Font("微軟正黑體" , Font.BOLD , 24);
    Font font2 = new Font("微軟正黑體" , Font.BOLD , 28);
    Font font3 = new Font("微軟正黑體" , Font.BOLD , 30);
    Color c1 = new Color(255,218,185);//粉撲桃色
    Color c2 = new Color(255,160,122);//亮鮭紅色
    Color c3 = new Color(255,128,153);//淺鮭紅色
    Color c4 = new Color(240,128,128);//亮珊瑚色
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();

    //建構子:類別CHCI_back1_panel
    public CHCI_back1_panel()
    {
	//設定容器p1上方變更
	p1.setLayout(null);
	p1.setBounds(10,10,660,80);
	p1.setBackground(Color.white);
	p1.setBorder(BorderFactory.createLineBorder(Color.black,2));

	lab1.setFont(font3);//分類
	lab1.setBounds(10,5,60,60);
	p1.add(lab1);

	lab2.setFont(font3);//品項
	lab2.setBounds(280,5,60,60);
	p1.add(lab2);
	
	for(int i = 0; i < cb.length; i++)
	{
	    cb[i] = new JComboBox(createCB(CB,i));
	    curstr[i] = cb[i].getSelectedItem().toString();
	    cb[i].addActionListener(combobox);
	    cb[i].setFont(font2);
	    p1.add(cb[i]);
	}
	cb[0].setBounds(75,10,200,60);
        cb[1].setBounds(345,10,200,60);
                                          
	btn1.setBounds(560,10,80,60);//更改
	btn1.setBackground(c2);
	btn1.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btn1.setFont(font3);
	btn1.addActionListener(update);
	p1.add(btn1);
	add(p1);

	//設定容器p2下方產品明細
	p2.setLayout(null);
	p2.setBounds(10,100,660,565);
	p2.setBackground(Color.white);
	p2.setBorder(BorderFactory.createLineBorder(Color.black,2));

	for(int i = 0; i < tf.length; i++)
	{
	    tfl[i] = new JLabel(tfstr[i]);
	    tfl[i].setFont(font2);
	    tfl[i].setBounds(165,15+100*i,60,50);
	    tf[i] =new JTextField();
	    tf[i].setBounds(245,15+100*i,250,50);
	    tf[i].setFont(font2);
	    tf[i].setEditable(false);
	    p2.add(tfl[i]);
	    p2.add(tf[i]);
	    if(i > 2)
		tf[i].setBackground(new Color(169,169,169));
	}

	btnf.setBounds(165,480,330,60);//完成
	btnf.setBackground(c2);
	btnf.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btnf.setFont(font3);
	btnf.setVisible(false);
	btnf.addActionListener(update);
	p2.add(btnf);
	add(p2);
	
	setBounds(0,30,700,720);
	setBackground(c1);
	setLayout(null);
	setVisible(true);
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
	    else if(a.getSource() == cb[1])
	    {
		show_pd_detail(getCB_text());
	    }
	}
    };
    public void show_pd_detail(String[] CB_text)//顯示商品詳細資料
    { 
	Connection con;
	ResultSet res;
	PreparedStatement psta;

	try
	{
	    search = "SELECT * FROM product WHERE class=? AND pname=?";
	    con = DriverManager.getConnection("jdbc:mysql://localhost/bmsdb"+"?user=root&password=mysql");
	    psta = con.prepareStatement(search);
	    for(int i = 0; i < 2; i++)
		psta.setString(i+1,CB_text[i]);
	    res = psta.executeQuery();
	    if(res.next())
	    {
		tf[0].setText(String.valueOf(res.getInt("pid")));//資料表抓編號
		tf[3].setText(String.valueOf(res.getInt("price")));//資料表抓價錢
		tf[4].setText(String.valueOf(res.getInt("amount")));//資料表抓數量
		tf[1].setText(CB_text[0]);//選單抓類別
		tf[2].setText(CB_text[1]);//選單抓產品名稱
	    }
	} 
	catch(Exception e)
	{
	    e.printStackTrace();
	}
    }
    
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

    public ActionListener update = new ActionListener()
    {
	public void actionPerformed(ActionEvent a)
	{
	    if(a.getSource() == btn1 )
	    {
		for(int i = 3; i < 5; i++)
		{
		    tf[i].setEditable(true);
		    tf[i].setBackground(Color.white);
		}
		btnf.setVisible(true);
	    }
	    else if(a.getSource() == btnf)
	    {
		String[] data = new String[5];
		for(int i=0;i<5;i++)
		{
		    data[i]=tf[i].getText();
		}
		update_product(data);
		btnf.setVisible(false);
		for(int i = 3; i < 5; i++)
		{
		    tf[i].setEditable(false);
		    tf[i].setBackground(new Color(169,169,169));
		} 
	    }
	}
    };

    public void update_product(String[] name)
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
	    cmdData = " UPDATE product SET class=? , pname=? , price=? , amount=? WHERE pid=? ";
            statement = connection.prepareStatement(cmdData);
	    statement.setString(1,name[1]);
	    statement.setString(2,name[2]);
            statement.setInt(3,Integer.parseInt(name[3]));
	    statement.setInt(4,Integer.parseInt(name[4]));
	    statement.setInt(5,Integer.parseInt(name[0]));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null,"產品已更新");
            statement.close();
	}
	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"更新發生錯誤!");
        }
    }

} //end for: class CHCI_OP_panel
