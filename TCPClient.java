import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "192.168.1.4"; // Gantilah alamat_server dengan alamat IP atau host server Anda.
        int serverPort = 12345; // Gantilah port_server dengan nomor port yang sesuai.

        try {
            // Membuat koneksi ke server
            Socket socket = new Socket(serverAddress, serverPort);

            // Membuat objek PrintWriter untuk mengirim pesan ke server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Membaca pesan dari pengguna
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Masukkan pesan: ");
            String message = userInput.readLine();

            // Mengirim pesan ke server
            out.println(message);

            // Membaca respon dari server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = in.readLine();
            System.out.println("Respon dari server: " + serverResponse);

            // Menutup koneksi
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}