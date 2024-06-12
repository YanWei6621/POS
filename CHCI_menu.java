
 //匯入需要的各類套件
 import javax.swing.*;
 import java.awt.event.*;
 import java.awt.*;
 import javax.swing.border.*;
 import java.sql.*;


 //人機互動層類別
 //CHCI_menu: Class HumanComputerInteraction_menu (人機介面-主功能選單類別)

 class CHCI_menu extends JPanel
{    

    JButton[][] btna = new JButton[1][6];    
    String[][] btnaStr={{"點餐結帳","庫存","銷售","上下班","登出","結束"}};
    Font font1 = new Font("微軟正黑體" , Font.BOLD , 24);
    Color c1 = new Color(255,160,122);//亮鮭紅色
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();                   
    
    //建構子:類別CHCI_menu
    public CHCI_menu()
    {	
        for(int i=0 ; i<btnaStr.length ; i++)
	{
	    for(int j=0 ; j<btnaStr[0].length ; j++)
	    {
		btna[i][j] = new JButton(btnaStr[i][j]);
		btna[i][j].setFont(font1);
		btna[i][j].setBackground(c1);
		btna[i][j].setBorder(BorderFactory.createCompoundBorder(b1,b2));
		add(btna[i][j]); 
	    }
	}

         setBackground(c1);
         setLocation(0,0);
         setSize(1282,30);
         setLayout(new GridLayout(1,6,20,5));
         setVisible(true);

     }

 } //end for: class CHCI_menu

 
