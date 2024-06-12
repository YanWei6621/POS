//匯入需要的各類套件
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;

//人機互動層類別
//CHCI_OP_panel: Class HumanComputerInteraction_OPeration_panel (人機介面-[點餐結帳]操作畫面類別)

class CHCI_OP_panel extends JPanel
{
    CDM_dbma myDBMA = new CDM_dbma(); 
    
    JPanel p1 = new JPanel();//右下(新增.刪除.清空,結帳)
    JButton[][] btnb = new JButton[1][4];    
    String[][] btnbStr={{"新增","刪除","清空","結帳"}}; 
    
    JPanel p2 = new JPanel();//左下數字按鍵
    JButton[][] numberbtn = new JButton[4][3];    
    String[][] numberbtnStr={{"7","8","9"},{"4","5","6"},{"1","2","3"},{"0","清除","確定"}};

    JPanel p3 = new JPanel();//左中結帳金額
    JLabel lb1 = new JLabel("總金額");
    JLabel lb2 = new JLabel("");
    
    //左邊表格
    String[] title = { "品項" , "單價" , "數量" , "小計" };
    String[][] data = new String[100][4];
    JTable table = new JTable(data,title);
    JTableHeader head = table.getTableHeader();

    //右邊頁籤
    JTabbedPane tbp = new JTabbedPane();    
    JPanel[] p = new JPanel[2];             
    JButton[][] btn1 = new JButton[4][4];   
    String[][] btn1Str = { {"豆沙麵包","牛角麵包","菠蘿麵包","鮮奶吐司"},
			   {"甜甜圈","餐包","起酥麵包","椰香麵包"},
			   {"貝果","白吐司","肉鬆麵包","香蒜金磚"}, 
			   {"法國麵包","丹麥玫瑰","和風起司","丹麥飛碟"}}; 
    JButton[][] btn2 = new JButton[3][3];   
    String[][] btn2Str = { {"黑森林蛋糕","乳酪蛋糕","波士頓蛋糕"},
			   {"草莓蛋糕","海綿蛋糕","提拉米蘇"},
			   {"起司蛋糕","磅蛋糕","芋泥蛋糕"}};

    Font font1 = new Font("微軟正黑體" , Font.BOLD , 24);
    Font font2 = new Font("微軟正黑體" , Font.BOLD , 28);
    Color c1 = new Color(255,218,185);//粉撲桃色
    Color c2 = new Color(255,160,122);//亮鮭紅色
    Color c3 = new Color(255,128,153);//淺鮭紅色
    Color c4 = new Color(240,128,128);//亮珊瑚色
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();
    int index = 0;
    int sum;
    String sumStr;                  
    
    //建構子:類別CHCI_OP_panel
    public CHCI_OP_panel()
    {
	//設定容器p1右下
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

	//設定容器p2左下數字鍵
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

	//設定容器p3左中結帳金額
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

	//設定容器p[0]麵包
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
	
	//設定容器p[1]蛋糕
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

	//產生頁籤
	tbp.add("麵包",p[0]); 
	tbp.add("蛋糕",p[1]);  
	tbp.setFont(font1);
	tbp.setBackground(c2);
	tbp.setBounds(585,13,665,530);
	add(tbp);	

	//左邊表格部分
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

    //方法:清空容器內欄位
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

    //數字鍵事件傾聽器
    public ActionListener numBtn = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    String text=String.valueOf(table.getValueAt(index,2));
	    if(table.getValueAt(index,0)!=null)
	    {
	    	if( "".equals(text) )  //當文字域中沒資料的時候，把當前點選的數字顯示上去
	   	    table.setValueAt(e.getActionCommand(),index,2);
	    	else
	    	{
		    text+=e.getActionCommand();
	    	    table.setValueAt(text,index,2);
	    	}
	    }
	}
    };

    //事件傾聽器程式
    public ActionListener ClickBtn = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    //數字鍵
	    if(e.getSource() == numberbtn[3][1])//清除
	    {
		table.setValueAt("",index, 2);
		table.setValueAt("",index, 3);
	    }

	    int price , amount , total;
	    String totalStr;
	    if(e.getSource() == numberbtn[3][2])//確定
	    {
		price=Integer.parseInt((String)table.getValueAt(index,1));
		amount=Integer.parseInt((String)table.getValueAt(index,2));
		total=price*amount;
		totalStr=String.valueOf(total);
		table.setValueAt(totalStr,index, 3);
	    } 

	    //麵包
	    if(e.getSource() == btn1[0][0])//豆沙麵包
	    {
		productQuery(1);	
	    }
	    if(e.getSource() == btn1[0][1])//牛角麵包
	    {
		productQuery(2);
	    }
	    if(e.getSource() == btn1[0][2])//菠蘿
	    {
		productQuery(3);
	    }
	    if(e.getSource() == btn1[0][3])//鮮奶吐司
	    {
		productQuery(4);
	    }  	
	    if(e.getSource() == btn1[1][0])//甜甜圈
	    {
		productQuery(5);
	    }
	    if(e.getSource() == btn1[1][1])//餐包
	    {
		productQuery(6);
	    }
	    if(e.getSource() == btn1[1][2])//起酥
	    {
		productQuery(7);
	    }
	    if(e.getSource() == btn1[1][3])//椰香
	    {
		productQuery(8);
	    }
	    if(e.getSource() == btn1[2][0])//貝果
	    {
		productQuery(9);
	    }
	    if(e.getSource() == btn1[2][1])//白吐司
	    {
		productQuery(10);
	    }
	    if(e.getSource() == btn1[2][2])//肉鬆
	    {
		productQuery(11);
	    }
	    if(e.getSource() == btn1[2][3])//香蒜
	    {
		productQuery(12);
	    }
	    if(e.getSource() == btn1[3][0])//法國麵包
	    {
		productQuery(13);
	    }
	    if(e.getSource() == btn1[3][1])//丹玫
	    {
		productQuery(14);
	    }
	    if(e.getSource() == btn1[3][2])//和風
	    {
		productQuery(15);
	    }
	    if(e.getSource() == btn1[3][3])//丹飛
	    {
		productQuery(16);
	    }

	    //蛋糕 
	    if(e.getSource() == btn2[0][0])//黑森林
	    {
		productQuery(17);
	    }
	    if(e.getSource() == btn2[0][1])//乳酪
	    {
		productQuery(18);
	    }  	
	    if(e.getSource() == btn2[0][2])//波士頓
	    {
		productQuery(19);
	    }
	    if(e.getSource() == btn2[1][0])//草莓
	    {
		productQuery(20);
	    }
	    if(e.getSource() == btn2[1][1])//海綿
	    {
		productQuery(21);
	    }
	    if(e.getSource() == btn2[1][2])//提拉米蘇
	    {
		productQuery(22);
	    }
	    if(e.getSource() == btn2[2][0])//起司
	    {
		productQuery(23);
	    }
	    if(e.getSource() == btn2[2][1])//磅
	    {
		productQuery(24);
	    }
	    if(e.getSource() == btn2[2][2])//芋泥
	    {
		productQuery(25);
	    }  
	  
	    //右下按鈕
	    
	    if(e.getSource() == btnb[0][0])//新增
	    {
		if(table.getValueAt(index,0)!=null && table.getValueAt(index,3)!=null)
		{
		    index++;
		}
	    }
	    if(e.getSource() == btnb[0][1])//刪除
	    {
		for(int j=0;j<4;j++)
		{
		    table.setValueAt(null,index,j);
		}
	    }
	    if(e.getSource() == btnb[0][2])//清空
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

    //事件傾聽程式: 處理產品查詢
    public void productQuery(int x)
    {     
        String[] findResult = myDBMA.findRD_in_TB_product(x);//呼叫[資料庫操作存取物件(myDBMA)]的查詢學生紀錄方法(findRD_into_TB_student())去查詢學生紀錄,並回傳儲存到findResult中
        table.setValueAt(findResult[0],index,0);    //將findResult陣列裝的索引1的元素(即中文成績字串)顯示在查詢結果欄位
        table.setValueAt(findResult[1],index,1);    //將findResult陣列裝的索引2的元素(即英文成績字串)顯示在查詢結果欄位     
	table.setValueAt("",index,2); 
    }

} //end for: class CHCI_OP_panel
