package com.example.wxm.socket;

import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.io.PrintWriter;
        import java.net.Socket;
        import java.net.UnknownHostException;

        import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;

public class main extends Activity {
    private static String IpAddress = "10.0.0.130";
    private static int Port1 = 4011;
    private EditText edittext = null;
    private Button send = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        edittext = (EditText) findViewById(R.id.edit);
        send = (Button) findViewById(R.id.button);
        send.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                sendMsg();
            }
        });
    }
    // 发送信息
    public void sendMsg() {
        //联网操作必须在子线程里面进行
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                try {

                    // 创建socket对象，指定服务器端地址和端口号
                    socket = new Socket(IpAddress, Port1);
                    // 获取 Client 端的输出流
                    PrintWriter out = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())), true);
                    // 填充信息
                    out.println(edittext.getText());
                    System.out.println("msg=" + edittext.getText());
                    // 关闭

                    //接收服务器端发送的消息
                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    // 读取数据
                    String msg = buffer.readLine();
                    System.out.println("receivemsg:" + msg);

                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

