package photon;
import java.io.IOException;
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDP_Server {
    //Class variables
    byte data[] = new byte[1024];
    InetAddress ip;
    private DatagramSocket serverSocket;

    //Constructor that inits local host ip
    public UDP_Server() throws UnknownHostException, IOException {
        ip = InetAddress.getLocalHost();
        serverSocket = new DatagramSocket(7501); // Listening on port 7501
    }

    //Receive data method, (From client to server)
    public void UDP_ReceiveData() throws IOException {
        //create packet to receive the message
        DatagramPacket packetReceive = new DatagramPacket(data, data.length);
        //receive the message 
        serverSocket.receive(packetReceive);
        // Convert the received byte data to a string
        String message = new String(packetReceive.getData(), 0, packetReceive.getLength());
        System.out.println("Received code: " + message);
        //close the socket
        serverSocket.close();
    }
}
