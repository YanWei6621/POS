//�פJ�ݭn���U���M��
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.*;
import java.text.DateFormat;//�۩w�q����ɶ��榡
import java.text.SimpleDateFormat;//�۩w�q����ɶ��榡
import java.util.Date;//�۩w�q����ɶ��榡
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.*;

//�H�����ʼh���O
//CHCI_CI_panel: Class HumanComputerInteraction_ClockIn_panel (�H������-[�W�U�Z]�ާ@�e�����O)

class CHCI_CI_panel extends JPanel
{

    CFD_check myCheck = new CFD_check();
    CDM_dbma myDBMA = new CDM_dbma();
    CPD_employee myemployee = new CPD_employee();   
    day dayr = new day();//�إߤ�������
    time timer = new time();//�إ߮ɶ������
    Date dated = new Date();//�إߤ���ܼ�
    Date datet = new Date();//�إ߮ɶ��ܼ�
    JPanel p1 = new JPanel();//���d.���u�s��.�K�X
    JLabel lab4 = new JLabel("���u�s��");
    JLabel lab5 = new JLabel("���u�K�X");
    JLabel lab6 = new JLabel("���u�m�W");
    JTextField tf3 = new JTextField("");
    JTextField tf4 = new JTextField();//�s��
    JLabel labn = new JLabel();//�m�W
    JPasswordField pf = new JPasswordField();//�K�X
    JButton btn3 = new JButton("���d");

    JPanel p2 = new JPanel();//���
    JLabel lab1 = new JLabel("�{�b���");
    JLabel lab2 = new JLabel("�{�b�ɶ�");
    JLabel labd = new JLabel("");
    JLabel labt = new JLabel("");

    JPanel p3 = new JPanel();//�d����
    JLabel labData = new JLabel("�d�ߤ��");
    JTextField tfData = new JTextField("");
    JButton btnc = new JButton("���");
    JButton btny = new JButton("�d��");
    JButton btno = new JButton("�M��");
    
    //������
    Vector rowData,columnNames;
    JTable table = null;
    JTableHeader head = null;
    DefaultTableModel DFMO;

    Font font1 = new Font("�L�n������" , Font.PLAIN , 24);
    Font font2 = new Font("�L�n������" , Font.BOLD , 24);
    Font font3 = new Font("�L�n������" , Font.BOLD , 30);
    Font font4 = new Font("�L�n������" , Font.PLAIN , 20);
    Color c1 = new Color(255,218,185);//�������
    Color c2 = new Color(255,160,122);//�G�D����
    Color c3 = new Color(135,206,250);//�L���Ŧ�
    Color c4 = new Color(255,255,153);//���b����
    Color c5 = new Color(255,182,193);//�G������
    Color c6 = new Color(240,128,128);//�G�����
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();
    int index = 0;
    
    //�غc�l:���OCHCI_CI_panel
    public CHCI_CI_panel()
    {
	dayr.start();//�I�s��������start()��k
	timer.start();//�I�s�ɶ������start()��k

	//�]�w�e��p1���.���d.���u�s��.�K�X
	p1.setLayout(null);
	p1.setBounds(810,340,450,315);
	p1.setBackground(Color.white);
	p1.setBorder(BorderFactory.createLineBorder(Color.black,2));
	
	lab4.setFont(font3);//���u�s��
	lab4.setBounds(20,10,125,65);

	p1.add(lab4);
	lab5.setFont(font3);//���u�K�X
	lab5.setBounds(20,85,125,65);
	p1.add(lab5);
	lab6.setFont(font3);//���u�m�W
	lab6.setBounds(20,160,125,65);
	p1.add(lab6);
	tf4.setBounds(155,10,280,65);//�s����
	tf4.setFont(font3);
	tf4.setBorder(BorderFactory.createLineBorder(Color.black,2));
	p1.add(tf4);
	pf.setBounds(155,85,280,65);//�K�X��
	pf.setFont(font3);
	pf.setBorder(BorderFactory.createLineBorder(Color.black,2));
	pf.addActionListener(ClickBtn);
	p1.add(pf);
	labn.setBounds(155,160,280,65);//�m�W��
	labn.setFont(font3);
	labn.setBorder(BorderFactory.createLineBorder(Color.black,2));
	p1.add(labn);
	
	btn3.setBounds(20,235,415,70);//���d
	btn3.setBackground(c5);
	btn3.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btn3.setFont(font3);
	btn3.addActionListener(ClickBtn);
	p1.add(btn3);
	add(p1);
	
	//�]�w�e��p2���
	p2.setLayout(null);
	p2.setBounds(810,20,450,150);
	p2.setBackground(Color.white);
	p2.setBorder(BorderFactory.createLineBorder(Color.black,2));
	lab1.setFont(font3);//�{�b���
	lab1.setBounds(20,10,120,60);
	p2.add(lab1);
	lab2.setFont(font3);//�{�b�ɶ�
	lab2.setBounds(20,75,120,60);
	p2.add(lab2);
	labd.setBounds(150,15,285,55);//�����
	labd.setFont(font3);
	labd.setBorder(BorderFactory.createLineBorder(Color.black,2));
	p2.add(labd);
	labt.setBounds(150,80,285,55);//�ɶ���
	labt.setFont(font3);
	labt.setBorder(BorderFactory.createLineBorder(Color.black,2));
	p2.add(labt);
	add(p2);
	
	//�]�w�e��p3�d��
	p3.setLayout(null);
	p3.setBounds(810,180,450,150);
	p3.setBackground(Color.white);
	p3.setBorder(BorderFactory.createLineBorder(Color.black,2));
	labData.setFont(font3);//�d�ߤ��
	labData.setBounds(20,10,120,60);
	p3.add(labData);
	tfData.setBounds(150,15,285,55);//�����
	tfData.setFont(font3);
	tfData.setBackground(Color.white);
	tfData.setBorder(BorderFactory.createLineBorder(Color.black,2));
	p3.add(tfData);
	btnc.setBounds(20,80,135,50);//���
	btnc.setBackground(c3);
	btnc.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btnc.setFont(font3);
	btnc.addActionListener(SelectDate);
	p3.add(btnc);
	btny.setBounds(160,80,135,50);//�d��
	btny.setBackground(c6);
	btny.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btny.setFont(font3);
	btny.addActionListener(ClickBtn);
	p3.add(btny);
	btno.setBounds(300,80,135,50);//�M��
	btno.setBackground(c1);
	btno.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btno.setFont(font3);
	btno.addActionListener(ClickBtn);
	p3.add(btno);
	add(p3);

	
	//�����泡��
	columnNames=new Vector();   
        columnNames.add("���");  
        columnNames.add("�s��");  
        columnNames.add("�m�W");  
        columnNames.add("¾��");  
        columnNames.add("�W�Z�ɶ�"); 
	columnNames.add("�U�Z�ɶ�");
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

    //��k:�M�Ůe�������
    public void clearPane()
    {
	tfData.setText("");
        tf4.setText("");  
	labn.setText("");
	pf.setText("");
    }

    //��ܤ��
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

    //��������
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

    //�ɶ������
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

    //�W�U�Z���A
    public ActionListener ClickBtn = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    if (e.getSource() == pf) 
	    {
                boolean checkPass = true;            //�ΨӰO��[��J�����]�ˬd���G
		String eidString = tf4.getText().trim();
	        String passwordString = pf.getText().trim();
	        String s3 = labn.getText().trim();
		int[] eidpf=new int[2];
	    	if( myCheck.checkNumber( eidString ) == false )//�Q���ˬd����(myCheck)��checkNumber()��k,�ˬdchineseString�O�_�����T���ƭȮ榡,�p:98,80,...��
            	{  
            	    checkPass = false;
                    JOptionPane.showMessageDialog(null,"[���u�s��] ��J��ƿ��~!","�ާ@ĵ�T",JOptionPane.ERROR_MESSAGE);
                }

            	if( myCheck.checkNumber( passwordString ) == false )
            	{  //�Q���ˬd����(myCheck)��checkNumber()��k,�ˬdenglishString�O�_�����T���ƭȮ榡,�p:98,80,...��
            	    checkPass = false;
                    JOptionPane.showMessageDialog(null,"[���u�K�X] ��J��ƿ��~!","�ާ@ĵ�T",JOptionPane.ERROR_MESSAGE);
            	}
		
		//�p�G�W�z�ˬd���S�o�{���~,�hcheckPass�|����true,�Y�q�L�ˬd,�]���N���u����Ƴ]�w��myemployee���󤤹����ݩʽ�  
            	if( checkPass == true )
	    	{
                    eidpf[0]= Integer.parseInt(eidString); //Integer.parseInt()�O�N�r���ন��ƪ���k  
                    eidpf[1]= Integer.parseInt(passwordString);
		    String findResult = myDBMA.findRD_in_TB_employee(eidpf);//�N����ǤJ�d�߭��u����k(findRD_in_TB_employee())
		    labn.setText(findResult);
 		}
            }

	    if(e.getSource() == btn3)
	    {
		String[] clock=new String[3];
		clock[0]=labd.getText();//���
		clock[1]=tf4.getText();//�s��
		clock[2]=labt.getText();//�ɶ�
		myDBMA.clockin(clock);
		DFMO.getDataVector().removeAllElements();
		datachange();	
		tf4.setText("");
		pf.setText("");
		labn.setText("");
	    }
	    if(e.getSource() == btny)//�d��
	    {
		    String date=new String();
		    date=tfData.getText();//���
		    DFMO.getDataVector().removeAllElements();
		    search(date);
	    }
	    if(e.getSource() == btno)//�M��
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

 