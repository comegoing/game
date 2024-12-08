package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DataBase.Login;

public class Login_JFrame extends JFrame implements ActionListener {
    public JTextField Account = new JTextField(11);// 新建账号文本区域
    public JTextField PassWord = new JTextField(11);// 新建密码文本区域
    JButton Login_Button = new JButton("确定");
    JButton Register_Button = new JButton("注册");

    public Login_JFrame() {
        // 初始化
        Initialize();
        // 功能界面初始化
        Initialize_function();
        this.setVisible(true);
    }

    private void Initialize_function() {
        // 新建两个label对象
        JLabel Account_text = new JLabel("账号:");
        Account_text.setBounds(200, 200, 30, 20);

        JLabel PassWord_text = new JLabel("密码:");
        PassWord_text.setBounds(200, 240, 30, 20);
        // 新建两个文本对象
        Account.setBounds(240, 200, 150, 20);
        PassWord.setBounds(240, 240, 150, 20);
        // 新建两个按钮对象
        Login_Button.setBounds(200, 300, 80, 30);
        Register_Button.setBounds(300, 300, 80, 30);
        // 添加对象
        this.getContentPane().add(Account_text);
        this.getContentPane().add(PassWord_text);
        this.getContentPane().add(Account);
        this.getContentPane().add(PassWord);
        this.getContentPane().add(Login_Button);
        this.getContentPane().add(Register_Button);
        // 为按钮添加事件
        Login_Button.addActionListener(this);
        Register_Button.addActionListener(this);
    }

    private void Initialize() {
        this.setSize(600, 500);
        this.setTitle("登录");
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
        Object Button_index = e.getSource();
        // 登录
        if (Button_index == Login_Button) {
            String s1 = this.Account.getText().trim();
            String s2 = this.PassWord.getText().trim();
            Login DataBase = new Login();
            if (DataBase.Search_account(s1, s2)) {
                this.setVisible(false);
                new Game_JFrame();
            } else {
                JOptionPane.showMessageDialog(this, "账号或密码错误", "错误",
                        JOptionPane.WARNING_MESSAGE);
            }

        }
        // 注册
        else if (Button_index == Register_Button) {
            
            this.setVisible(false);
            new Register_JFrame();

        }
    }
}
