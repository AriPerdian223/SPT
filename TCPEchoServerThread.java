/*
Nama : Ari Perdian
NIM  : 20220040072 
Kelas: TI22J
*/
import java.io.IOException;
import java.net.ServerSocket;

public class TCPEchoServerThread {
    private static ServerSocket servSock;
    private static final int PORT = 12345;

    public TCPEchoServerThread() {
    }

    public void start() {
        try {
            servSock = new ServerSocket(PORT);

            while (true) {
                Thread clientThread = new Thread(new clientHandler(servSock.accept()));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Menutup koneksi....");
                servSock.close();
            } catch (IOException e) {
                System.out.println("Tidak dapat memutuskan koneksi");
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        TCPEchoServerThread es = new TCPEchoServerThread();
        System.out.println("Server telah berjalan di komputer ini pada port " + PORT);
        es.start();
    }
}

