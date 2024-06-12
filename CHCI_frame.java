
 //匯入需要的各類套件
 import javax.swing.*;
 import java.awt.event.*;
 import java.awt.*;
 import javax.swing.border.*;
 import javax.swing.table.*;
 import java.sql.*;


 
 //人機互動層類別
 //CHCI_frame: Class HumanComputerInteraction_frame (人機介面-框架類別)

 class CHCI_frame extends JFrame{      //系統的視窗

     CHCI_menu  myMenu = new CHCI_menu();                  //主功能選單物件(為JPanel,內含5個按鈕)     
     CHCI_OP_panel  myOP_pane = new CHCI_OP_panel();       //點餐結帳畫面物件(為JPanel,內含標籤,文字欄位,按鈕等)
     CHCI_QR_panel  myQR_pane = new CHCI_QR_panel();       //庫存畫面物件(為JPanel,內含標籤,文字欄位,按鈕等)
     CHCI_SR_panel  mySR_pane = new CHCI_SR_panel();       //銷售班畫面物件(為JPanel,內含標籤,文字欄位,按鈕等)
     CHCI_CI_panel  myCI_pane = new CHCI_CI_panel();       //上下班畫面物件(為JPanel,內含標籤,文字欄位,按鈕等)   
           
     Color c1 = new Color(255,218,185);//粉撲桃色
     //建構子:類別CHCI_frame
     public CHCI_frame(){

         add(myMenu);        //將[主功能選單物件]加到此視窗中
         add(myOP_pane);     //將[點餐結帳畫面物件]加到此視窗中
         add(myQR_pane);     //將[庫存畫面物件]加到此視窗中
         add(mySR_pane);     //將[庫存畫面物件]加到此視窗中
	 add(myCI_pane);     //將[上下班畫面物件]加到此視窗中

         myOP_pane.setVisible(true);   //預設[點餐結帳]畫面顯示
         myQR_pane.setVisible(false);  //預設[庫存]畫面隱藏
         mySR_pane.setVisible(false);  //預設[庫存]畫面隱藏
	 myCI_pane.setVisible(false);  //預設[上下班]畫面隱藏

         //系統視窗的基本設定
         setTitle("鄉香烘焙坊管理系統");
	 getContentPane().setBackground(c1);
         setLocation(0,0);
         setSize(1300,750);
         setLayout(null);
         setVisible(false);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

     }

 } //end for: class CHCI_frame

 
 