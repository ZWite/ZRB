import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class test {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6600);
        Socket socket = serverSocket.accept();
        do {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int read = inputStream.read(bytes);
            System.out.println(new String(bytes,0,read, Charset.defaultCharset()));
//            Scanner scanner = new Scanner(System.in);
//            String system = scanner.next();
//            socket.getOutputStream().write(system.getBytes(StandardCharsets.UTF_8));

        }while (true);
    }
}
