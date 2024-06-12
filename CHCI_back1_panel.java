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

//�H�����ʼh���O
//CHCI_back1_panel: Class HumanComputerInteraction_back1_panel (�H������-[��s���~]�ާ@�e�����O)

class CHCI_back1_panel extends JPanel
{
    String[] curstr = new String[3]; //JComboBox���W���x�s�A�d�ߤU�@��JComboBox
    Boolean flag = true;
    String search;
    JPanel p1 = new JPanel();//�W������
    JLabel lab1 = new JLabel("����");
    JLabel lab2 = new JLabel("�~��");
    JButton btn1 = new JButton("���");
    
    JComboBox[] cb = new JComboBox[2];
    String[] CB = {"class","pname","product"};

    JPanel p2 = new JPanel();//�U�������
    JTextField[] tf = new JTextField[5];
    JLabel[] tfl = new JLabel[5];
    String[] tfstr = {"�s��","���O","�W��","���","�ƶq"};
    JButton btnf = new JButton("����");
    
    Font font1 = new Font("�L�n������" , Font.BOLD , 24);
    Font font2 = new Font("�L�n������" , Font.BOLD , 28);
    Font font3 = new Font("�L�n������" , Font.BOLD , 30);
    Color c1 = new Color(255,218,185);//�������
    Color c2 = new Color(255,160,122);//�G�D����
    Color c3 = new Color(255,128,153);//�L�D����
    Color c4 = new Color(240,128,128);//�G�����
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();

    //�غc�l:���OCHCI_back1_panel
    public CHCI_back1_panel()
    {
	//�]�w�e��p1�W���ܧ�
	p1.setLayout(null);
	p1.setBounds(10,10,660,80);
	p1.setBackground(Color.white);
	p1.setBorder(BorderFactory.createLineBorder(Color.black,2));

	lab1.setFont(font3);//����
	lab1.setBounds(10,5,60,60);
	p1.add(lab1);

	lab2.setFont(font3);//�~��
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
                                          
	btn1.setBounds(560,10,80,60);//���
	btn1.setBackground(c2);
	btn1.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btn1.setFont(font3);
	btn1.addActionListener(update);
	p1.add(btn1);
	add(p1);

	//�]�w�e��p2�U�貣�~����
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

	btnf.setBounds(165,480,330,60);//����
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
	    else if(a.getSource() == cb[1])
	    {
		show_pd_detail(getCB_text());
	    }
	}
    };
    public void show_pd_detail(String[] CB_text)//��ܰӫ~�ԲӸ��
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
		tf[0].setText(String.valueOf(res.getInt("pid")));//��ƪ��s��
		tf[3].setText(String.valueOf(res.getInt("price")));//��ƪ�����
		tf[4].setText(String.valueOf(res.getInt("amount")));//��ƪ��ƶq
		tf[1].setText(CB_text[0]);//�������O
		tf[2].setText(CB_text[1]);//���첣�~�W��
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

    //���oComboBox������ơA�M��^�Ǥ@�Ӧr��}�C
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
	

	//��Ʈw�e�m�@�~
        try
	{
            Class.forName("com.mysql.jdbc.Driver");
        }
	catch(Exception e)
	{
            JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!"); 
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
            JOptionPane.showMessageDialog(null,"���~�w��s");
            statement.close();
	}
	catch(SQLException e)
	{
            JOptionPane.showMessageDialog(null,"��s�o�Ϳ��~!");
        }
    }

} //end for: class CHCI_OP_panel
