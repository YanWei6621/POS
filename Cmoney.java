//���b��s
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

class Cmoney
{
    JFrame f = new JFrame();
    Container ct;//�u�������
    JPanel pbg = new JPanel();//�I����
    Color c1 = new Color(255,218,185);//�������
    Color c2 = new Color(255,160,122);//�G�D����
    JLabel lb1 = new JLabel("���O���B");
    JLabel lb2 = new JLabel("");

    JLabel lb3 = new JLabel("�����{��");
    JTextField tf = new JTextField("");

    JLabel lb4 = new JLabel("��s");
    JLabel lb5 = new JLabel("");

    JLabel lbt = new JLabel("");
    
    JButton btn = new JButton("�T�w");
    
    ImageIcon pic = new ImageIcon("line.png");
    JLabel b = new JLabel(pic);
    
    Font font1 = new Font("�L�n������",Font.BOLD ,35);
    Font font2 = new Font("�L�n������",Font.BOLD ,25);
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();

    Cmoney()
    {
	lb1.setBounds(70,30,150,40);
	lb1.setFont(font1);
        lb2.setBounds(240,30,170,40);
	lb2.setHorizontalAlignment(JTextField.CENTER);
	lb2.setFont(font1);
	lb2.setBorder(BorderFactory.createLoweredBevelBorder());
	f.add(lb1);
	f.add(lb2);

	lb3.setBounds(70,90,150,40);
	lb3.setFont(font1);
        tf.setBounds(240,90,170,40);
	tf.setHorizontalAlignment(JTextField.CENTER);
	tf.setFont(font1);
	tf.setBorder(BorderFactory.createLoweredBevelBorder());
	tf.addActionListener(enter);
	f.add(lb3);
	f.add(tf);

	lb4.setBounds(70,145,150,40);
	lb4.setFont(font1);
	lb5.setBounds(240,145,170,40);
	lb5.setHorizontalAlignment(JTextField.CENTER);
	lb5.setFont(font1);
	lb5.setBorder(BorderFactory.createLoweredBevelBorder());
	f.add(lb4);
	f.add(lb5);
	
	lbt.setBounds(70,200,360,40);
	lbt.setFont(font2);
	lbt.setForeground(Color.red);
	f.add(lbt);

	b.setBounds(15,240,450,20);
	f.add(b);

	btn.setBounds(15,275,450,50);
	btn.setFont(font1);
	btn.setBackground(c2);
	btn.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	f.add(btn);

	//�]�w����
	ct=f.getContentPane();
	pbg.setLayout(null);
	pbg.setBounds(0,0,500,400);
	pbg.setBackground(c1);
	ct.add(pbg);
	f.setLayout(null);
	f.setBounds(667,160,500,400);
	f.setVisible(false);
	f.setTitle("���b");
    }

    public ActionListener enter = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    int sum;
	    String sumStr;
	    if (e.getSource() == tf) 
	    {
		sum=0;
		sum=Integer.parseInt(tf.getText())-Integer.parseInt(lb2.getText());
		if (sum < 0) 
	        {
		    lbt.setText("  ��J���B���~�A�Э��s��J�I");
		    tf.setText("");
		}
		else
		{
		    lbt.setText("���Ⱦ����P�w�}�ҡA�`�N��s�I");
		    sumStr=String.valueOf(sum);
		    lb5.setText(sumStr);
		}
	    }
	}
    };
}