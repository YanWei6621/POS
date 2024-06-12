//匯入需要的各類套件
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;


 
//人機互動層類別
//CHCI_backframe: Class HumanComputerInteraction_frame (人機介面-框架類別)

class CHCI_backframe extends JFrame  //系統的視窗
{      
    CHCI_backmenu  mybackmenu = new CHCI_backmenu();           //主功能選單物件(為JPanel,內含3個按鈕)     
    CHCI_back1_panel  myback1_pane = new CHCI_back1_panel();    //更改畫面物件(為JPanel,內含標籤,文字欄位,按鈕等)
    CHCI_back2_panel  myback2_pane = new CHCI_back2_panel();    //新增畫面物件(為JPanel,內含標籤,文字欄位,按鈕等)
    CHCI_back3_panel  myback3_pane = new CHCI_back3_panel();    //人事畫面物件(為JPanel,內含標籤,文字欄位,按鈕等)

    Color c1 = new Color(255,218,185);//粉撲桃色
    //建構子:類別CHCI_frame
    public CHCI_backframe()
    {
	add(mybackmenu);        //將[主功能選單物件]加到此視窗中
        add(myback1_pane);     //將[點餐結帳畫面物件]加到此視窗中
        add(myback2_pane);     //將[庫存畫面物件]加到此視窗中
	add(myback3_pane);     //將[庫存畫面物件]加到此視窗中

        myback1_pane.setVisible(true);   //預設[更改]畫面顯示
        myback2_pane.setVisible(false);  //預設[新增]畫面隱藏
	myback3_pane.setVisible(false);  //預設[新增]畫面隱藏

        //系統視窗的基本設定
        setTitle("鄉香烘焙坊管理系統後台");
	getContentPane().setBackground(c1);
        setLocation(0,0);
        setSize(700,750);
        setLayout(null);
        setVisible(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

} //end for: class CHCI_backframe

 
 