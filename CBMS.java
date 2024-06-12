//匯入需要的各類套件
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import java.sql.*;
import java.text.DateFormat;//自定義日期時間格式
//系統主控類別
//CBMS: Class BakeryManagementSystem (烘焙坊管理系統-BMS)
class CBMS
{                    
    //建立本系統所需的各個物件
    CHCI_frame  myFrame = new CHCI_frame();       //人機互動層: 使用者介面物件(myFrame,裡面又含有:myFrame.myMenu,myFrame.myOP_pane,myFrame.myQR_pane)
    Clogin myLogin = new Clogin();
    Cmoney money = new Cmoney();		  //結帳畫面
    CPD_employee myemployee = new CPD_employee();    
    CDM_dbma myDBMA = new CDM_dbma();             //資料管理層: 資料庫操作存取物件(myDBMA)
    CFD_check myCheck = new CFD_check();          //基礎層: 檢查物件(myCheck)
    CHCI_backframe  mybackframe = new CHCI_backframe();
    int sid=0;

    //CBMS的建構子:
    CBMS()
    {
	//設定系統中相關物件是由哪一個[事件傾聽程式]負責處理其動作
 	myLogin.btn1.addActionListener(loginbtn);
	myLogin.btn2.addActionListener(loginbtn);
	myFrame.myMenu.btna[0][0].addActionListener(ProcessFunSelection);              //主功能選單的[點餐結帳]按鈕
	myFrame.myMenu.btna[0][1].addActionListener(ProcessFunSelection);              //主功能選單的[庫存]按鈕
	myFrame.myMenu.btna[0][2].addActionListener(ProcessFunSelection);              //主功能選單的[銷售]按鈕
	myFrame.myMenu.btna[0][3].addActionListener(ProcessFunSelection);              //主功能選單的[上下班]按鈕
	myFrame.myMenu.btna[0][4].addActionListener(ProcessFunSelection);              //主功能選單的[登出]按鈕
	myFrame.myMenu.btna[0][5].addActionListener(ProcessFunSelection);              //主功能選單的[結束]按鈕
	myFrame.myOP_pane.btnb[0][3].addActionListener(ProcessFunSelection);	       //點餐結帳頁面的[結帳]按鈕
	money.btn.addActionListener(ProcessFunSelection);
	mybackframe.mybackmenu.btna[0][0].addActionListener(ProcessFunSelection);      //後台功能選單的[更新產品]按鈕
	mybackframe.mybackmenu.btna[0][1].addActionListener(ProcessFunSelection);      //後台功能選單的[員工名單]按鈕
	mybackframe.mybackmenu.btna[0][2].addActionListener(ProcessFunSelection);      //後台功能選單的[新增員工]按鈕
	mybackframe.mybackmenu.btna[0][3].addActionListener(ProcessFunSelection);      //後台功能選單的[登出]按鈕
	mybackframe.myback2_pane.btnn.addActionListener(ProcessSaveEmployeeRecord);
    }
      
    //事件傾聽程式: 處理主功能選單選按
    public ActionListener ProcessFunSelection = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    if( e.getSource() == myFrame.myMenu.btna[0][0] )//點餐結帳
	    {
		myFrame.myOP_pane.setVisible(true);        //顯示[點餐結帳]操作畫面
		myFrame.myOP_pane.clearPane();             //清空[點餐結帳]操作畫面
		myFrame.myQR_pane.setVisible(false);       //隱藏[庫存]查詢畫面 
		myFrame.mySR_pane.setVisible(false);        //隱藏[銷售]操作畫面
		myFrame.mySR_pane.clearPane();             //清空[銷售]操作畫面     
		myFrame.myCI_pane.setVisible(false);        //隱藏[上下班]操作畫面
		myFrame.myCI_pane.clearPane();             //清空[上下班]操作畫面
            }

            if( e.getSource() == myFrame.myMenu.btna[0][1] )//庫存
	    {
		myFrame.myQR_pane.DFMO.getDataVector().removeAllElements();
		myFrame.myQR_pane.datachange();
		myFrame.myOP_pane.setVisible(false);        //隱藏[點餐結帳]操作畫面
		myFrame.myOP_pane.clearPane();             //清空[點餐結帳]操作畫面
		myFrame.myQR_pane.setVisible(true);       //顯示[庫存]查詢畫面
		myFrame.mySR_pane.setVisible(false);        //隱藏[銷售]操作畫面
		myFrame.mySR_pane.clearPane();             //清空[銷售]操作畫面      
		myFrame.myCI_pane.setVisible(false);        //隱藏[上下班]操作畫面
		myFrame.myCI_pane.clearPane();             //清空[上下班]操作畫面
            }

	    if( e.getSource() == myFrame.myMenu.btna[0][2] )//銷售
	    {
		myFrame.mySR_pane.sellDFMO.getDataVector().removeAllElements();
		myFrame.mySR_pane.detailDFMO.setRowCount(0);
		myFrame.mySR_pane.selldatachange();
		myFrame.myOP_pane.setVisible(false);        //隱藏[點餐結帳]操作畫面
		myFrame.myOP_pane.clearPane();             //清空[點餐結帳]操作畫面
		myFrame.myQR_pane.setVisible(false);       //隱藏[庫存]查詢畫面
		myFrame.mySR_pane.setVisible(true);        //顯示[銷售]操作畫面
		myFrame.mySR_pane.clearPane();             //清空[銷售]操作畫面      
		myFrame.myCI_pane.setVisible(false);        //隱藏[上下班]操作畫面
		myFrame.myCI_pane.clearPane();             //清空[上下班]操作畫面
            }

	    if( e.getSource() == myFrame.myMenu.btna[0][3] )//上下班
	    {
		myFrame.myOP_pane.setVisible(false);        //隱藏[點餐結帳]操作畫面
		myFrame.myOP_pane.clearPane();             //清空[點餐結帳]操作畫面
                myFrame.myQR_pane.setVisible(false);       //隱藏[庫存]查詢畫面
		myFrame.mySR_pane.setVisible(false);        //隱藏[銷售]操作畫面
		myFrame.mySR_pane.clearPane();             //清空[銷售]操作畫面      
		myFrame.myCI_pane.setVisible(true);        //顯示[上下班]操作畫面
                myFrame.myCI_pane.clearPane();             //清空[上下班]操作畫面
            }

	    if(e.getSource() == myFrame.myMenu.btna[0][4] )//登出
	    {
		int ans = JOptionPane.showConfirmDialog(null,
			  "是否要登出?",
			  "確認",JOptionPane.YES_NO_OPTION,
			  JOptionPane.QUESTION_MESSAGE);
	    	if(ans == JOptionPane.YES_OPTION)
	    	{
		    myLogin.f.setVisible(true);
		    myFrame.setVisible(false);
		    myFrame.myOP_pane.setVisible(false);
                    myFrame.myQR_pane.setVisible(false);       
		    myFrame.mySR_pane.setVisible(false);
		    myFrame.myCI_pane.setVisible(false);
		    myFrame.myOP_pane.clearPane();
			
		    
	   	}
	    }

            if( e.getSource() == myFrame.myMenu.btna[0][5] ) //結束
            {
		int ans = JOptionPane.showConfirmDialog(null,
			  "是否要結束?",
			  "確認",JOptionPane.YES_NO_OPTION,
			  JOptionPane.QUESTION_MESSAGE);
	    	if(ans == JOptionPane.YES_OPTION)
	    	{
		    System.exit(0);
	   	}                            
            }

	    int sum;
	    int sid;
    	    String sumStr;
            if( e.getSource() == myFrame.myOP_pane.btnb[0][3] ) //結帳
            {
		sum=0;		
		String[] detail=new String[5];
		String pname=new String();
		for(int i=0;i<=myFrame.myOP_pane.index;i++)
		{
		    sum=sum+Integer.parseInt((String)myFrame.myOP_pane.table.getValueAt(i,3)); 
		}
		sumStr=String.valueOf(sum);
		myFrame.myOP_pane.lb2.setText(sumStr);
		money.f.setVisible(true);
		money.lb2.setText(sumStr);
		String[] sell=new String[2];
		sell[0]=myFrame.myCI_pane.labd.getText();
		sell[1]=sumStr;
		myDBMA.insert_sell(sell);
		sid=myDBMA.findsid();

		for(int i=0;i<=myFrame.myOP_pane.index;i++)
		{
		    detail[0]=String.valueOf(sid);
		    detail[1]=(String)myFrame.myOP_pane.table.getValueAt(i,0); 
		    detail[2]=(String)myFrame.myOP_pane.table.getValueAt(i,1); 
		    detail[3]=(String)myFrame.myOP_pane.table.getValueAt(i,2); 
		    detail[4]=(String)myFrame.myOP_pane.table.getValueAt(i,3);
		    myDBMA.insert_detail(detail);
		    myDBMA.update_product(detail[0],detail[1]);	
		}

	    }

	    //結帳找零
	    if(e.getSource() == money.btn)//確定
	    {
		myFrame.myOP_pane.lb2.setText("");
		money.lb2.setText("");
		money.tf.setText("");
		money.lb5.setText("");
		money.lbt.setText("");
	    	money.f.setVisible(false);
		for(int i=0 ; i<=myFrame.myOP_pane.index ; i++)
		{
		    for(int j=0;j<4;j++)
		    {
			myFrame.myOP_pane.table.setValueAt(null,i,j);
		    }
		}
		myFrame.myOP_pane.index=0;

		
	    }	    

	    if( e.getSource() == mybackframe.mybackmenu.btna[0][0] )//更新產品
	    {
		mybackframe.myback1_pane.setVisible(true);        //顯示[更新產品]操作畫面
		mybackframe.myback3_pane.setVisible(false);       //隱藏[員工名單]操作畫面   
		mybackframe.myback2_pane.setVisible(false);       //隱藏[新增員工]操作畫面
		mybackframe.myback2_pane.clearPane();
            }

	    if( e.getSource() == mybackframe.mybackmenu.btna[0][1] )//員工名單
	    {

		mybackframe.myback3_pane.DFMO.getDataVector().removeAllElements();
		mybackframe.myback3_pane.datachange();
		mybackframe.myback1_pane.setVisible(false);       //隱藏[更新產品]操作畫面 
		mybackframe.myback3_pane.setVisible(true);        //顯示[員工名單]操作畫面  
		mybackframe.myback2_pane.setVisible(false);       //隱藏[新增員工]操作畫面
		mybackframe.myback2_pane.clearPane();
            }

	    if( e.getSource() == mybackframe.mybackmenu.btna[0][2] )//新增員工
	    {
		mybackframe.myback1_pane.setVisible(false);       //隱藏[更新產品]操作畫面
		mybackframe.myback3_pane.setVisible(false);       //隱藏[員工名單]操作畫面    
		mybackframe.myback2_pane.setVisible(true);        //顯示[新增員工]操作畫面
		
            }

	    if( e.getSource() == mybackframe.mybackmenu.btna[0][3] )//登出
	    {
		int ans = JOptionPane.showConfirmDialog(null,
			  "是否要登出?",
			  "確認",JOptionPane.YES_NO_OPTION,
			  JOptionPane.QUESTION_MESSAGE);
	    	if(ans == JOptionPane.YES_OPTION)
	    	{
		    myLogin.f.setVisible(true);
		    mybackframe.setVisible(false);
	   	}
	    }
 	}    
    };//end ProcessFunSelection

    //登入
    public ActionListener loginbtn = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    if(e.getSource() == myLogin.btn1)//登入
	    {
		String s1 = myLogin.tf.getText().trim();
		String s2 = myLogin.pf.getText().trim();
		if(s1.equals("front") && s2.equals("front"))//前台
		{  
		    myLogin.f.setVisible(false);
		    myFrame.setVisible(true);
		    myFrame.myOP_pane.setVisible(true);
		    myLogin.pf.setText("");
		    myLogin.b0.setText("");
		}
		else if(s1.equals("back") && s2.equals("back"))//後台
		{  
		    myLogin.f.setVisible(false);
		    mybackframe.setVisible(true);
		    myLogin.pf.setText("");
		    myLogin.b0.setText("");
		}
		else if (s1.length() == 0 && s2.length() == 0)
		    myLogin.b0.setText("未輸入帳號跟密碼");
		else if (s1.length() == 0)
		    myLogin.b0.setText("未輸入帳號");
		else if (s2.length() == 0)
		    myLogin.b0.setText("未輸入密碼"); 
		else 
		    myLogin.b0.setText("帳號或密碼錯誤"); 
		
	    }
	    if(e.getSource() == myLogin.btn2)//結束
	    {
		int ans = JOptionPane.showConfirmDialog(null,
			  "是否要結束?",
			  "確認",JOptionPane.YES_NO_OPTION,
			  JOptionPane.QUESTION_MESSAGE);
	    	if(ans == JOptionPane.YES_OPTION)
	    	{
		    System.exit(0);
	   	}
	    }
	}
    }; //end for : loginbtn

    //事件傾聽程式: 處理員工資料儲存
    public ActionListener ProcessSaveEmployeeRecord = new ActionListener()
    {
    	public void actionPerformed(ActionEvent e)
	{
            boolean checkPass = true;            //用來記錄[輸入的資料]檢查結果
            String eidString = mybackframe.myback2_pane.tf[0].getText().trim();  //取得[輸入的資料]中的[員工編號字串] (註:trim()方法會把字串後面空白除掉)
            String enameString = mybackframe.myback2_pane.tf[1].getText();    //取得[輸入的資料]中的[員工姓名字串]
            String positionString = mybackframe.myback2_pane.tf[2].getText();    //取得[輸入的資料]中的[員工職位字串]
            String passwordString = mybackframe.myback2_pane.tf[3].getText().trim();  //取得[輸入的資料]中的[員工密碼字串]

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

            if(  enameString.length() == 0 )
            {    //檢查enameString是否有輸入任何字元,length()為計算字串長度方法,若長度為0則未輸入資料
            	checkPass = false;
                JOptionPane.showMessageDialog(null,"[員工姓名] 空白未輸入資料!","操作警訊",JOptionPane.ERROR_MESSAGE);
            }

	    if(  positionString.length() == 0 )
            {    //檢查positionString是否有輸入任何字元,length()為計算字串長度方法,若長度為0則未輸入資料
            	checkPass = false;
                JOptionPane.showMessageDialog(null,"[員工職位] 空白未輸入資料!","操作警訊",JOptionPane.ERROR_MESSAGE);
            }

            //如果上述三個檢查都沒發現錯誤,則checkPass會維持true,即通過檢查,因此將員工的資料設定到myemployee物件中對應屬性質  
            if( checkPass == true )
	    {
                myemployee.seteid( Integer.parseInt(eidString) ); //Integer.parseInt()是將字串轉成整數的方法
            	myemployee.setename(enameString);
                myemployee.setposition(positionString);    
                myemployee.setpassword( Integer.parseInt(passwordString) );
		myDBMA.insertRD_into_TB_employee(myemployee);   //將物件傳入儲存員工紀錄方法(insertRD_into_TB_employee())去儲存員工紀錄到資料庫
		mybackframe.myback2_pane.clearPane();
            }
	}    
    };//end for :ProcessSaveEmployeeRecord

} //end for: class CBMS
    

 