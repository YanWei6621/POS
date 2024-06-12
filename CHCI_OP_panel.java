//�פJ�ݭn���U���M��
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;

//�H�����ʼh���O
//CHCI_OP_panel: Class HumanComputerInteraction_OPeration_panel (�H������-[�I�\���b]�ާ@�e�����O)

class CHCI_OP_panel extends JPanel
{
    CDM_dbma myDBMA = new CDM_dbma(); 
    
    JPanel p1 = new JPanel();//�k�U(�s�W.�R��.�M��,���b)
    JButton[][] btnb = new JButton[1][4];    
    String[][] btnbStr={{"�s�W","�R��","�M��","���b"}}; 
    
    JPanel p2 = new JPanel();//���U�Ʀr����
    JButton[][] numberbtn = new JButton[4][3];    
    String[][] numberbtnStr={{"7","8","9"},{"4","5","6"},{"1","2","3"},{"0","�M��","�T�w"}};

    JPanel p3 = new JPanel();//�������b���B
    JLabel lb1 = new JLabel("�`���B");
    JLabel lb2 = new JLabel("");
    
    //������
    String[] title = { "�~��" , "���" , "�ƶq" , "�p�p" };
    String[][] data = new String[100][4];
    JTable table = new JTable(data,title);
    JTableHeader head = table.getTableHeader();

    //�k�䭶��
    JTabbedPane tbp = new JTabbedPane();    
    JPanel[] p = new JPanel[2];             
    JButton[][] btn1 = new JButton[4][4];   
    String[][] btn1Str = { {"���F�ѥ]","�����ѥ]","�����ѥ]","�A���R�q"},
			   {"������","�\�]","�_�p�ѥ]","�����ѥ]"},
			   {"���G","�զR�q","���P�ѥ]","���[���j"}, 
			   {"�k���ѥ]","��������","�M���_�q","��������"}}; 
    JButton[][] btn2 = new JButton[3][3];   
    String[][] btn2Str = { {"�´˪L�J�|","�ŹT�J�|","�i�h�y�J�|"},
			   {"����J�|","�����J�|","���Ԧ�Ĭ"},
			   {"�_�q�J�|","�S�J�|","���d�J�|"}};

    Font font1 = new Font("�L�n������" , Font.BOLD , 24);
    Font font2 = new Font("�L�n������" , Font.BOLD , 28);
    Color c1 = new Color(255,218,185);//�������
    Color c2 = new Color(255,160,122);//�G�D����
    Color c3 = new Color(255,128,153);//�L�D����
    Color c4 = new Color(240,128,128);//�G�����
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();
    int index = 0;
    int sum;
    String sumStr;                  
    
    //�غc�l:���OCHCI_OP_panel
    public CHCI_OP_panel()
    {
	//�]�w�e��p1�k�U
	p1.setBounds(585,553,666,105);
	p1.setLayout(new GridLayout(1,4,10,5));
	p1.setBackground(c1);
	for(int i=0 ; i<btnbStr.length ; i++)
	{
	    for(int j=0 ; j<btnbStr[0].length ; j++)
	    {
		btnb[i][j] = new JButton(btnbStr[i][j]);
		btnb[i][j].setFont(font2);
		btnb[i][j].setBackground(Color.white);
		btnb[i][j].setBorder(BorderFactory.createCompoundBorder(b1,b2));
		btnb[i][j].addActionListener(ClickBtn);
		p1.add(btnb[i][j]); 
	    }
	}
	btnb[0][0].setBackground(c3);
	btnb[0][1].setBackground(c4);
	btnb[0][2].setBackground(c3);
	btnb[0][3].setBackground(c4);
	add(p1);

	//�]�w�e��p2���U�Ʀr��
	p2.setBounds(35,460,511,200);
	p2.setLayout(new GridLayout(4,3,5,5));
	p2.setBackground(c1);
	for(int i=0 ; i<3 ; i++)
	{
	    for(int j=0 ; j<3 ; j++)
	    {
		numberbtn[i][j] = new JButton(numberbtnStr[i][j]);
		numberbtn[i][j].setFont(font1);
		numberbtn[i][j].setBackground(Color.white);
		numberbtn[i][j].setBorder(BorderFactory.createLineBorder(Color.black,2));
		numberbtn[i][j].addActionListener(numBtn);
		p2.add(numberbtn[i][j]); 
	    }
	}
	for(int i=3 ; i<4 ; i++)
	{
	    for(int j=0 ; j<3 ; j++)
	    {
		numberbtn[i][j] = new JButton(numberbtnStr[i][j]);
		numberbtn[i][j].setFont(font1);
		numberbtn[i][j].setBackground(Color.white);
		numberbtn[i][j].setBorder(BorderFactory.createLineBorder(Color.black,2));
		p2.add(numberbtn[i][j]); 
	    }
	}
	numberbtn[3][0].addActionListener(numBtn);
	numberbtn[3][1].addActionListener(ClickBtn);
	numberbtn[3][2].addActionListener(ClickBtn);
	add(p2);

	//�]�w�e��p3�������b���B
	p3.setLayout(null);
	p3.setBounds(35,405,510,50);
	p3.setBackground(Color.white);
	p3.setBorder(BorderFactory.createLineBorder(Color.black,2));
	lb1.setBounds(125,10,100,30);
	lb1.setFont(font1);
	p3.add(lb1);
	lb2.setBounds(225,10,150,30);
	lb2.setHorizontalAlignment(JTextField.CENTER);
	lb2.setFont(font1);
	lb2.setBorder(BorderFactory.createLoweredBevelBorder());
	p3.add(lb2);
	add(p3);

	//�]�w�e��p[0]�ѥ]
	p[0] = new JPanel();
	p[0].setLayout(new GridLayout(4,4,5,5));
	p[0].setBackground(c1);
	for(int i=0 ; i<btn1.length ; i++)
	{
	    for(int j=0 ; j<btn1[0].length ; j++)
	    {
		btn1[i][j] = new JButton(btn1Str[i][j]);
		btn1[i][j].setFont(font1);
		btn1[i][j].setBackground(Color.white);
		btn1[i][j].setBorder(BorderFactory.createLineBorder(Color.black,2));
		btn1[i][j].addActionListener(ClickBtn);
		p[0].add(btn1[i][j]); 
	    }
	}
	
	//�]�w�e��p[1]�J�|
	p[1] = new JPanel();
	p[1].setLayout(new GridLayout(3,3,5,5));
	p[1].setBackground(c1);
	for(int i=0 ; i<btn2.length ; i++)
	{
	    for(int j=0 ; j<btn2[0].length ; j++)
	    {
		btn2[i][j] = new JButton(btn2Str[i][j]);
		btn2[i][j].setFont(font1);
		btn2[i][j].setBackground(Color.white);
		btn2[i][j].setBorder(BorderFactory.createLineBorder(Color.black,2));
		btn2[i][j].addActionListener(ClickBtn);
		p[1].add(btn2[i][j]); 
	    }
	}

	//���ͭ���
	tbp.add("�ѥ]",p[0]); 
	tbp.add("�J�|",p[1]);  
	tbp.setFont(font1);
	tbp.setBackground(c2);
	tbp.setBounds(585,13,665,530);
	add(tbp);	

	//�����泡��
	table.setFont(font1);		
	table.setRowHeight(40);		
	head.setFont(font1);		
	JScrollPane sp = new JScrollPane( table,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	sp.setBounds(35,15,510,380);
	add(sp);

	setBounds(0,30,1300,720);
	setBackground(c1);
	setLayout(null);
	setVisible(true);
     }

    //��k:�M�Ůe�������
    public void clearPane()
    {
	for(int i=0 ; i<=index ; i++)
	{
	    for(int j=0;j<4;j++)
	    {
		table.setValueAt(null,i,j);
	    }
	}
	index=0;
	lb2.setText("");
    }

    //�Ʀr��ƥ��ť��
    public ActionListener numBtn = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    String text=String.valueOf(table.getValueAt(index,2));
	    if(table.getValueAt(index,0)!=null)
	    {
	    	if( "".equals(text) )  //���r�줤�S��ƪ��ɭԡA���e�I�諸�Ʀr��ܤW�h
	   	    table.setValueAt(e.getActionCommand(),index,2);
	    	else
	    	{
		    text+=e.getActionCommand();
	    	    table.setValueAt(text,index,2);
	    	}
	    }
	}
    };

    //�ƥ��ť���{��
    public ActionListener ClickBtn = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    //�Ʀr��
	    if(e.getSource() == numberbtn[3][1])//�M��
	    {
		table.setValueAt("",index, 2);
		table.setValueAt("",index, 3);
	    }

	    int price , amount , total;
	    String totalStr;
	    if(e.getSource() == numberbtn[3][2])//�T�w
	    {
		price=Integer.parseInt((String)table.getValueAt(index,1));
		amount=Integer.parseInt((String)table.getValueAt(index,2));
		total=price*amount;
		totalStr=String.valueOf(total);
		table.setValueAt(totalStr,index, 3);
	    } 

	    //�ѥ]
	    if(e.getSource() == btn1[0][0])//���F�ѥ]
	    {
		productQuery(1);	
	    }
	    if(e.getSource() == btn1[0][1])//�����ѥ]
	    {
		productQuery(2);
	    }
	    if(e.getSource() == btn1[0][2])//����
	    {
		productQuery(3);
	    }
	    if(e.getSource() == btn1[0][3])//�A���R�q
	    {
		productQuery(4);
	    }  	
	    if(e.getSource() == btn1[1][0])//������
	    {
		productQuery(5);
	    }
	    if(e.getSource() == btn1[1][1])//�\�]
	    {
		productQuery(6);
	    }
	    if(e.getSource() == btn1[1][2])//�_�p
	    {
		productQuery(7);
	    }
	    if(e.getSource() == btn1[1][3])//����
	    {
		productQuery(8);
	    }
	    if(e.getSource() == btn1[2][0])//���G
	    {
		productQuery(9);
	    }
	    if(e.getSource() == btn1[2][1])//�զR�q
	    {
		productQuery(10);
	    }
	    if(e.getSource() == btn1[2][2])//���P
	    {
		productQuery(11);
	    }
	    if(e.getSource() == btn1[2][3])//���[
	    {
		productQuery(12);
	    }
	    if(e.getSource() == btn1[3][0])//�k���ѥ]
	    {
		productQuery(13);
	    }
	    if(e.getSource() == btn1[3][1])//����
	    {
		productQuery(14);
	    }
	    if(e.getSource() == btn1[3][2])//�M��
	    {
		productQuery(15);
	    }
	    if(e.getSource() == btn1[3][3])//����
	    {
		productQuery(16);
	    }

	    //�J�| 
	    if(e.getSource() == btn2[0][0])//�´˪L
	    {
		productQuery(17);
	    }
	    if(e.getSource() == btn2[0][1])//�ŹT
	    {
		productQuery(18);
	    }  	
	    if(e.getSource() == btn2[0][2])//�i�h�y
	    {
		productQuery(19);
	    }
	    if(e.getSource() == btn2[1][0])//���
	    {
		productQuery(20);
	    }
	    if(e.getSource() == btn2[1][1])//����
	    {
		productQuery(21);
	    }
	    if(e.getSource() == btn2[1][2])//���Ԧ�Ĭ
	    {
		productQuery(22);
	    }
	    if(e.getSource() == btn2[2][0])//�_�q
	    {
		productQuery(23);
	    }
	    if(e.getSource() == btn2[2][1])//�S
	    {
		productQuery(24);
	    }
	    if(e.getSource() == btn2[2][2])//���d
	    {
		productQuery(25);
	    }  
	  
	    //�k�U���s
	    
	    if(e.getSource() == btnb[0][0])//�s�W
	    {
		if(table.getValueAt(index,0)!=null && table.getValueAt(index,3)!=null)
		{
		    index++;
		}
	    }
	    if(e.getSource() == btnb[0][1])//�R��
	    {
		for(int j=0;j<4;j++)
		{
		    table.setValueAt(null,index,j);
		}
	    }
	    if(e.getSource() == btnb[0][2])//�M��
	    {
		for(int i=0 ; i<=index ; i++)
		{
		    for(int j=0;j<4;j++)
		    {
			table.setValueAt(null,i,j);
		    }
		}
		index=0;
		lb2.setText("");
	    } 
	}
    };

    //�ƥ��ť�{��: �B�z���~�d��
    public void productQuery(int x)
    {     
        String[] findResult = myDBMA.findRD_in_TB_product(x);//�I�s[��Ʈw�ާ@�s������(myDBMA)]���d�߾ǥͬ�����k(findRD_into_TB_student())�h�d�߾ǥͬ���,�æ^���x�s��findResult��
        table.setValueAt(findResult[0],index,0);    //�NfindResult�}�C�˪�����1������(�Y���妨�Z�r��)��ܦb�d�ߵ��G���
        table.setValueAt(findResult[1],index,1);    //�NfindResult�}�C�˪�����2������(�Y�^�妨�Z�r��)��ܦb�d�ߵ��G���     
	table.setValueAt("",index,2); 
    }

} //end for: class CHCI_OP_panel
