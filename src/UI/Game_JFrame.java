package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

import DataBase.Login;

public class Game_JFrame extends JFrame implements KeyListener, ActionListener {
    /**
     * InnerGame_JFrame
     */
    public class InnerGame_JFrame extends TimerTask{
        int Current_Operation = 0;
        public InnerGame_JFrame(int i){
            this.Current_Operation = i;
        }
        @Override
        public void run() {
            Operation_move(this.Current_Operation);
        }
    
        
    }
    // 两种类型的原始数据
    int[][] data_for4 = new int[4][4];
    int[][] data_for3 = new int[3][3];
    // 空白格记录位置
    int x;
    int y;
    // 文件路径
    String file_path4 = "src\\image\\for_4\\";
    String file_path3 = "src\\image\\for_3\\";
    // 胜利数据格式
    int[][] win_data_for4 = {
            { 1, 2, 3, 4 },
            { 5, 6, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 14, 15, 0 }
    };
    int[][] win_data_for3 = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 0 },
    };
    // 计数
    int step = 0;
    // 下拉条目
    JMenuItem replay = new JMenuItem("重新游戏");
    JMenuItem re_login = new JMenuItem("重新登录");
    JMenuItem Level_easy = new JMenuItem("简单模式");
    JMenuItem Level_hard = new JMenuItem("困难模式");
    // 模式调换参数
    int Level = 3;
    JLabel origin_photo;

    public Game_JFrame() {
        // 初始化
        Initialize();
        // 设置菜单
        Set_menu();
        // 初始化图片位置
        InitData();
        // 初始化图片
        InitImage();
        // 展示
        this.setVisible(true);
    }

    // 底层方块移动代码
    private void Operation_move(int action) {
        if (Level == 4) {
            switch (action) {
                // 左操作
                case 0:
                    if (y == Level - 1) {
                        break;
                    }
                    data_for4[x][y] = data_for4[x][y + 1];
                    data_for4[x][y + 1] = 0;
                    y++;
                    step++;
                    InitImage();

                    break;
                // 右操作
                case 1:
                    if (y == 0) {
                        break;
                    }

                    data_for4[x][y] = data_for4[x][y - 1];
                    data_for4[x][y - 1] = 0;
                    y--;
                    step++;
                    InitImage();

                    break;
                // 上操作
                case 2:
                    if (x == Level - 1) {
                        break;
                    }

                    data_for4[x][y] = data_for4[x + 1][y];
                    data_for4[x + 1][y] = 0;
                    x++;
                    step++;
                    InitImage();

                    break;
                // 下操作
                case 3:
                    if (x == 0) {
                        break;
                    }

                    data_for4[x][y] = data_for4[x - 1][y];
                    data_for4[x - 1][y] = 0;
                    x--;
                    step++;
                    InitImage();

                    break;
                default:
                    break;
            }
        }
        else if(Level==3){
            switch (action) {
                // 左操作
                case 0:
                    if (y == Level - 1) {
                        break;
                    }
                    data_for3[x][y] = data_for3[x][y + 1];
                    data_for3[x][y + 1] = 0;
                    y++;
                    step++;
                    InitImage();
    
                    break;
                // 右操作
                case 1:
                    if (y == 0) {
                        break;
                    }
    
                    data_for3[x][y] = data_for3[x][y - 1];
                    data_for3[x][y - 1] = 0;
                    y--;
                    step++;
                    InitImage();
    
                    break;
                // 上操作
                case 2:
                    if (x == Level - 1) {
                        break;
                    }
    
                    data_for3[x][y] = data_for3[x + 1][y];
                    data_for3[x + 1][y] = 0;
                    x++;
                    step++;
                    InitImage();
    
                    break;
                // 下操作
                case 3:
                    if (x == 0) {
                        break;
                    }
    
                    data_for3[x][y] = data_for3[x - 1][y];
                    data_for3[x - 1][y] = 0;
                    x--;
                    step++;
                    InitImage();
    
                    break;
                default:
                    break;
            }
        }
    }

    private void InitData() {
        Random random_seed = new Random();

        int[] random_array = new int[Level * Level];
        for (int i = 0; i < random_array.length; i++) {
            random_array[i] = i;
            // System.out.println(random_array[i]);
        }

        for (int i = 0; i < random_array.length; i++) {
            // 利用随机数生成数组下标，并将数组下标的值与当前值互换
            int random_number = random_seed.nextInt(random_array.length);
            int temp = random_array[i];
            random_array[i] = random_array[random_number];
            random_array[random_number] = temp;
            
        }
        
        // 二维数组赋值
        if (Level == 3) {
            for (int i = 0; i < random_array.length; i++) {
                // 找到空白格的位置
                if (random_array[i] == 0) {
                    x = i / Level;
                    y = i % Level;
                }
                data_for3[i / Level][i % Level] = random_array[i];
                // System.out.println(random_array[i]);
            }
        }
        else if (Level == 4) {
            for (int i = 0; i < random_array.length; i++) {
                // 找到空白格的位置
                if (random_array[i] == 0) {
                    x = i / Level;
                    y = i % Level;
                }
                data_for4[i / Level][i % Level] = random_array[i];
            }
        }

    }

    private void InitImage() {
        // 清空
        this.getContentPane().removeAll();
        if (Level == 4) {
            // 游戏图片
            for (int i = 0; i < Level; i++) {
                for (int j = 0; j < Level; j++) {
                    int number = data_for4[i][j];
                    JLabel label = new JLabel(new ImageIcon(file_path4 + number + ".jpg"));
                    label.setBounds(120 * j, 120 * i, 120, 120);
                    // 添加边框
                    label.setBorder(new BevelBorder(1));
                    this.getContentPane().add(label);
                    number++;
                }
            }
            // 右侧原图显示
            origin_photo = new JLabel(new ImageIcon(file_path4 + "origin.jpg"));
        } else if (Level == 3) {
            // 游戏图片
            for (int i = 0; i < Level; i++) {
                for (int j = 0; j < Level; j++) {
                    int number = data_for3[i][j];
                    JLabel label = new JLabel(new ImageIcon(file_path3 + number + ".jpg"));
                    label.setBounds(160 * j, 160 * i, 160, 160);
                    // 添加边框
                    label.setBorder(new BevelBorder(1));
                    this.getContentPane().add(label);
                    number++;
                }
            }
            // 右侧原图显示
            origin_photo = new JLabel(new ImageIcon(file_path3 + "origin.jpg"));
        }

        // 设置
        origin_photo.setBounds(500, 140, 100, 100);
        origin_photo.setBorder(new BevelBorder(1));
        this.getContentPane().add(origin_photo);

        // 计数模块
        JLabel step_module = new JLabel("步数:" + step);
        step_module.setBounds(525, 100, 100, 20);

        this.getContentPane().add(step_module);

        // 刷新
        this.getContentPane().repaint();
        if (victory()) {
            JOptionPane.showMessageDialog(this, "胜利!", "提示框", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void Set_menu() {
        // 初始化菜单容器
        JMenuBar container_JMenuBar = new JMenuBar();
        // 菜单对象
        JMenu function_JMenu = new JMenu("功能");
        JMenu choose_level = new JMenu("更换难度");
        // 给条目绑定事件
        replay.addActionListener(this);
        Level_easy.addActionListener(this);
        Level_hard.addActionListener(this);
        re_login.addActionListener(this);

        // 容器添加菜单对象
        container_JMenuBar.add(function_JMenu);
        // 菜单添加下拉条目对象
        function_JMenu.add(replay);
        function_JMenu.add(re_login);
        function_JMenu.add(choose_level);
        // 对内嵌难度菜单单独操作
        choose_level.add(Level_easy);
        choose_level.add(Level_hard);
        // 窗口添加菜单容器对象
        this.setJMenuBar(container_JMenuBar);
    }

    private void Initialize() {
        // 设置宽高
        this.setSize(750, 545);
        this.setTitle("益智拼图游戏");
        // 置顶
        this.setAlwaysOnTop(true);
        // 居中
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        // 关闭结束 3表示退出后关闭
        this.setDefaultCloseOperation(3);
        this.addKeyListener(this);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }
    // 作弊码
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            if (Level == 3) {
                for (int i = 0; i < Level; i++) {
                    for (int j = 0; j < Level; j++) {
                        data_for3[i][j] = win_data_for3[i][j];
                    }
                }
            } else if (Level == 4) {
                for (int i = 0; i < Level; i++) {
                    for (int j = 0; j < Level; j++) {
                        data_for4[i][j] = win_data_for4[i][j];
                    }
                }
            }
            InitImage();
        }
        else if(code==88){
            Login DataBase = new Login();
            int[] Actions = DataBase.Search_operation(10);
            Timer timer = new Timer();
            for(int i=0;i<Actions.length;i++){
                timer.schedule(new InnerGame_JFrame(Actions[i]), i*1000);
            }
           
        }
        
    }
    // 键盘控制
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            // 左
            case 37:
                Operation_move(0);
                break;
            // 上
            case 38:
                Operation_move(2);
                break;
            // 右
            case 39:
                Operation_move(1);
                break;
            // 下
            case 40:
                Operation_move(3);
                break;
            default:
                break;
        }
    }
    // 胜利判断
    private Boolean victory() {
        if (Level == 4) {
            for (int i = 0; i < Level; i++) {
                for (int j = 0; j < Level; j++) {
                    if (data_for4[i][j] != win_data_for4[i][j]) {
                        return false;
                    }
                }
            }
        }
        else if (Level == 3) {
            for (int i = 0; i < Level; i++) {
                for (int j = 0; j < Level; j++) {
                    if (data_for3[i][j] != win_data_for3[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    // 重新游戏
    @Override
    public void actionPerformed(ActionEvent e) {
        Object item_index = e.getSource();
        // 重新游戏
        if (item_index == replay) {
            step = 0;
            InitData();
            InitImage();
        }
        // 重新登录
        else if (item_index == re_login) {
            this.setVisible(false);
            new Login_JFrame();
        }
        // 切换难度
        else if(item_index==Level_easy){
            Level = 3;
            step = 0;
            InitData();
            InitImage();
        }
        else if(item_index==Level_hard){
            Level = 4;
            step = 0;
            InitData();
            InitImage();
        }
    }
}
