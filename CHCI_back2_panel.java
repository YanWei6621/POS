//匯入需要的各類套件
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.sql.*;

//人機互動層類別
//CHCI_back2_panel: Class HumanComputerInteraction_back2_panel (人機介面-[新增員工]操作畫面類別)

class CHCI_back2_panel extends JPanel
{
    JTextField[] tf = new JTextField[4];
    JLabel[] tfl = new JLabel[4];
    String[] tfstr = {"員工編號","員工姓名","員工職位","員工密碼"};
    JButton btnn = new JButton("新增");
    JButton btnc = new JButton("清除");

    Font font2 = new Font("微軟正黑體" , Font.BOLD , 24);
    Font font3 = new Font("微軟正黑體" , Font.BOLD , 30);
    Color c1 = new Color(255,218,185);//粉撲桃色
    Color c2 = new Color(240,128,128);//亮珊瑚色
    Color c3 = new Color(255,160,122);//亮鮭紅色
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();                  
    
    //建構子:類別CHCI_back2_panel
    public CHCI_back2_panel()
    {
	for(int i = 0; i < tf.length; i++)
	{
	    tfl[i] = new JLabel(tfstr[i]);
	    tfl[i].setFont(font2);
	    tfl[i].setBounds(150,50+125*i,120,50);
	    tf[i] =new JTextField();
	    tf[i].setBounds(300,50+125*i,250,50);
	    tf[i].setFont(font2);
	    add(tfl[i]);
	    add(tf[i]);
	}

	btnn.setBounds(150,550,190,60);//新增
	btnn.setBackground(c2);
	btnn.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btnn.setFont(font3);
	add(btnn);
	btnc.setBounds(360,550,190,60);//清除
	btnc.setBackground(c3);
	btnc.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btnc.setFont(font3);
	btnc.addActionListener(ProcessClearFields);
	add(btnc);

	setBounds(0,30,700,720);
	setBackground(c1);
        setLayout(null);
        setVisible(false);
    }

    //方法:清空容器內欄位
    public void clearPane()
    {
    	for(int i = 0; i < tf.length; i++)
	    tf[i].setText("");
    }

    //事件傾聽程式: 處理點按[清除]按鈕
    public ActionListener ProcessClearFields = new ActionListener()
    {
    	public void actionPerformed(ActionEvent e)
	{
            clearPane();
        }    
    }; 

} //end for: class CHCI_back2_panel

