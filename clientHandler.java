/*
Nama : Ari Perdian
NIM  : 20220040072 
Kelas: TI22J
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

class clientHandler implements Runnable {
    private static int numConnections;
    private int connectionId = 0;
    Socket link;

    public clientHandler(Socket s) {
        connectionId = numConnections++;
        System.out.println("Melayani koneksi ke-" + connectionId);
        link = s;
    }

    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        int numMessages = 0;

        try {
            out = new PrintWriter(link.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(link.getInputStream()));
            String message = in.readLine();
            while (message != null && !message.equals("close")) {
                System.out.println("Pesan diterima: [" + message.toString() + "] dari client " + connectionId + " dalam " + message.length() + " bytes");
                numMessages++;
                out.println("Isi Pesan " + numMessages + ":" + message);
                message = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
            try {
                in.close();
                link.close();
                System.out.println("Menutup koneksi, #" + connectionId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
