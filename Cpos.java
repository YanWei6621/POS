//��������
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Cpos
{
    Clogin mylogin = new Clogin();//�n�J
  
    Cpos()
    {
	mylogin.btn1.addActionListener(BtnPress);//�n�J
	mylogin.btn2.addActionListener(BtnPress);//����
    }

    public ActionListener BtnPress = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    //�n�J
	    if(e.getSource() == login.btn1)//�n�J
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
		    login.b0.setText("����J�b����K�X");
		else if (s1.length() == 0)
		    login.b0.setText("����J�b��");
		else if (s2.length() == 0)
		    login.b0.setText("����J�K�X"); 
		else 
		    login.b0.setText("�b���αK�X���~"); 
		
	    }
	    if(e.getSource() == login.btn2)//����
	    {
		int ans = JOptionPane.showConfirmDialog(null,
			  "�O�_�n����?",
			  "�T�{",JOptionPane.YES_NO_OPTION,
			  JOptionPane.QUESTION_MESSAGE);
	    	if(ans == JOptionPane.YES_OPTION)
	    	{
		    System.exit(0);
	   	}
	    }

	}	   
    };//end:BtnPress
}


