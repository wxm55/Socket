import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSocketText {

        public static void main(String[] args) {
                new ServerThread().start();

        }

}

// ����һ���߳��ں�̨����
class ServerThread extends Thread {
        private static int Port1 = 4011;
        ServerSocket serversocket = null;

        public void run() {

                try {
                        // ����һ��serversocket���󣬲�������Port�˿ڼ���
                        serversocket = new ServerSocket(Port1);
                        while (true) {
                                // ����serversocket��accept()���������տͻ��˷��͵�����
                                Socket socket = serversocket.accept();
                                BufferedReader buffer = new BufferedReader(
                                                new InputStreamReader(socket.getInputStream()));
                                // ��ȡ����
                                String msg = buffer.readLine();
                                
                                PrintWriter out = new PrintWriter(new BufferedWriter(
                                        new OutputStreamWriter(socket.getOutputStream())), true);
                                
                                switch (msg) {
								case "00 00 00 00 00 06 01 01 00 00 00 18":
							
									// �����Ϣ
									out.println("00 00 00 00 00 06 01 01 03 01 00 00\r\n");
									break;

								default:
									break;
								}
                                System.out.println("msg:" + msg);        
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                } finally {
                        try {
                        	serversocket.close();

                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }
}