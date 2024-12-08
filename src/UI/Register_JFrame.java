package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DataBase.Login;

public class Register_JFrame extends JFrame implements ActionListener{
    JTextField Account_new = new JTextField(11);// 新建账号文本区域
    JTextField PassWord_new = new JTextField(11);// 新建密码文本区域
    JTextField PassWord_Confirm = new JTextField(11);//确认密码
    JButton Register_Button = new JButton("注册");
    public Register_JFrame(){
        // 初始化
        Initialize();
        

        Initialize_function();
        this.setVisible(true);
    }

    private void Initialize_function() {
        // 新建两个label对象
        JLabel Account_text = new JLabel("新账号:");
        Account_text.setBounds(200, 140, 60, 20);

        JLabel PassWord_text = new JLabel("新密码:");
        PassWord_text.setBounds(200, 180, 60, 20);

        JLabel PassWord_Confirm_text = new JLabel("确认密码:");
        PassWord_Confirm_text.setBounds(200, 220, 60, 20);
        // 新建两个文本对象
        Account_new.setBounds(260, 140, 150, 20);
        PassWord_new.setBounds(260, 180, 150, 20);
        PassWord_Confirm.setBounds(260, 220, 150, 20);
        // 新建两个按钮对象
        Register_Button.setBounds(280, 300, 80, 30);
        // 添加对象
        this.getContentPane().add(Account_text);
        this.getContentPane().add(PassWord_text);
        this.getContentPane().add(PassWord_Confirm_text);

        this.getContentPane().add(Account_new);
        this.getContentPane().add(PassWord_new);
        this.getContentPane().add(PassWord_Confirm);


        this.getContentPane().add(Register_Button);
        // 为按钮添加事件
        Register_Button.addActionListener(this);
    }

    private void Initialize() {
        this.setSize(600, 500);
        this.setTitle("注册");
        // 置顶
        this.setAlwaysOnTop(true);
        // 居中
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        // 关闭结束 3表示退出后关闭
        this.setDefaultCloseOperation(3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s1 = this.Account_new.getText().trim();
        String s2 = this.PassWord_new.getText().trim();
        String s3 = this.PassWord_Confirm.getText().trim();
        if(s2.equals(s3)){
            Login DataBase = new Login();
            DataBase.Insert_account(s1, s2, s3);
            this.setVisible(false);
            new Login_JFrame();
        }
        else{
            JOptionPane.showMessageDialog(this, "请核对密码", "错误",
                        JOptionPane.WARNING_MESSAGE);
        }
    }
}
