//�פJ�ݭn���U���M��
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.sql.*;

//�H�����ʼh���O
//CHCI_back2_panel: Class HumanComputerInteraction_back2_panel (�H������-[�s�W���u]�ާ@�e�����O)

class CHCI_back2_panel extends JPanel
{
    JTextField[] tf = new JTextField[4];
    JLabel[] tfl = new JLabel[4];
    String[] tfstr = {"���u�s��","���u�m�W","���u¾��","���u�K�X"};
    JButton btnn = new JButton("�s�W");
    JButton btnc = new JButton("�M��");

    Font font2 = new Font("�L�n������" , Font.BOLD , 24);
    Font font3 = new Font("�L�n������" , Font.BOLD , 30);
    Color c1 = new Color(255,218,185);//�������
    Color c2 = new Color(240,128,128);//�G�����
    Color c3 = new Color(255,160,122);//�G�D����
    Border b1 =BorderFactory.createLineBorder(Color.black,2);
    Border b2 =BorderFactory.createRaisedBevelBorder();                  
    
    //�غc�l:���OCHCI_back2_panel
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

	btnn.setBounds(150,550,190,60);//�s�W
	btnn.setBackground(c2);
	btnn.setBorder(BorderFactory.createCompoundBorder(b1,b2));
	btnn.setFont(font3);
	add(btnn);
	btnc.setBounds(360,550,190,60);//�M��
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

    //��k:�M�Ůe�������
    public void clearPane()
    {
    	for(int i = 0; i < tf.length; i++)
	    tf[i].setText("");
    }

    //�ƥ��ť�{��: �B�z�I��[�M��]���s
    public ActionListener ProcessClearFields = new ActionListener()
    {
    	public void actionPerformed(ActionEvent e)
	{
            clearPane();
        }    
    }; 

} //end for: class CHCI_back2_panel

