package homework6;

import java.io.*;
import java.net.Socket;
                                    // Same Exchanger for server & client
public class Exchanger extends Thread {
    private final Socket socket;
    private final DataInput in;
    private final DataOutput out;
    private final BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

    public Exchanger(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new DataInputStream(this.socket.getInputStream());
        this.out = new DataOutputStream(this.socket.getOutputStream());
    }

    @Override
    public void run() {
        try (socket){
            String message;
            while (true) {
                message = this.in.readUTF();
                if (!"/end".equals(message)){
                    System.out.printf("%s : %s\r\n", this.socket.getInetAddress(), message);
                } else {
                    out.writeUTF(message);
                    System.out.println("end cmd received. Exchanger stopped");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startMessaging() throws IOException {
        String message;
        while (true) {
            message = consoleIn.readLine();
            if (this.isAlive()) {
                if ("/end".equals(message)) {
                    out.writeUTF(message);
                    break;
                }
                out.writeUTF(message);
            } else {
                break;
            }
        }
    }
}
