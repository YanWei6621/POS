//切換頁面
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Cpos
{
    Clogin mylogin = new Clogin();//登入
  
    Cpos()
    {
	mylogin.btn1.addActionListener(BtnPress);//登入
	mylogin.btn2.addActionListener(BtnPress);//取消
    }

    public ActionListener BtnPress = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    //登入
	    if(e.getSource() == login.btn1)//登入
	    {
		String s1 = login.tf.getText().trim();
		String s2 = login.pf.getText().trim();
		if(s1.equals("boss") && s2.equals("bo123"))
		{  
		    login.f.setVisible(false);
		    front.f.setVisible(true);
		    back1.f.setVisible(false);
		    back2.f.setVisible(false);
		    login.pf.setText("");
		    login.b0.setText("");
		}
		else if(s1.equals("manager") && s2.equals("ma456"))
		{  
		    login.f.setVisible(false);
		    front.f.setVisible(true);
		    back1.f.setVisible(false);
		    back2.f.setVisible(false);
		    login.pf.setText("");
		    login.b0.setText("");
		}
		else if(s1.equals("clerk") && s2.equals("cl789"))
		{  
		    login.f.setVisible(false);
		    front.f.setVisible(true);
		    back1.f.setVisible(false);
		    back2.f.setVisible(false);
		    login.pf.setText("");
		    login.b0.setText("");
		}
		else if (s1.length() == 0 && s2.length() == 0)
		    login.b0.setText("未輸入帳號跟密碼");
		else if (s1.length() == 0)
		    login.b0.setText("未輸入帳號");
		else if (s2.length() == 0)
		    login.b0.setText("未輸入密碼"); 
		else 
		    login.b0.setText("帳號或密碼錯誤"); 
		
	    }
	    if(e.getSource() == login.btn2)//結束
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
    };//end:BtnPress
}


