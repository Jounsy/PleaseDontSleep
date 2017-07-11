/**
 * Created by Work-TESTER on 11.07.2017.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class Main {


    /**
     * Created by jouns on 26.03.2017.
     */

        JFrame frame;
        //static Font font = new Font();
        static JButton button;
        static JLabel label;
        static String nameSave = "Могу заснуть";
        static String buttonText = "";
        static boolean isStop = false;
        static int timeOut = 0;


    static
    {


        label = new JLabel(nameSave);
        button = new JButton("НЕ СПИ!");

        //label.setText(nameSave);
    }

        public static void main(String[] args) throws IOException {
            Main gui = new Main();
            gui.go();


        }
        public void go()
        {

            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            button.addActionListener(new ButtonListener());

            frame.getContentPane().add(BorderLayout.CENTER,button);
            frame.getContentPane().add(BorderLayout.NORTH,label);
            frame.setSize(250,150);
            frame.setVisible(true);
        }


        class ButtonListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                Sleep sleep = new Sleep();
                Thread sleepThread = new Thread(sleep);

                if(nameSave.equals("Могу заснуть")){
                    nameSave = "Компьютер бдит!";
                    button.setText("ВКЛЮЧЕНО");
                    isStop = false;
                    sleepThread.start();
                    button.setBackground(Color.GREEN);

                }
                else{
                    nameSave = "Могу заснуть";
                    button.setText("ОТКЛЮЧЕНО");
//                    System.out.println(sleepThread.getName() + " stop = " + isStop);
//                    sleepThread.interrupt();
                    isStop = true;
                    button.setBackground(Color.RED);
                }
                label.setText(nameSave);
            }
        }


    class Sleep extends Thread{

            @Override
            public void run() {

                int tenMinutes = 600000;
                int timer = 0;
                String dotsVisible = "";

                while(!isStop){

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(timer <= 1){
                        if(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)) {
                            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, false);
                            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                        }
                        else{
                            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, false);
                        }
                        timeOut = 0;
                        button.setText("ТАЙМЕР СБРОШЕН");
                        button.setBackground(Color.YELLOW);
                    }
                    timer = (tenMinutes-timeOut)/1000/60;
                    timeOut +=1000;
                    if(dotsVisible.equals(":")){dotsVisible = "";}
                    else{dotsVisible = ":";}
                    buttonText = "сброс через " + dotsVisible + " " + (timer) + " мин";
                    //buttonText = "сброс через : " + timeOut;
                    button.setText(buttonText);
                    //System.out.println(buttonText);
                    //нажатие капс лока
                }
                button.setText("ОТКЛЮЧЕНО");
            }
        }
}
