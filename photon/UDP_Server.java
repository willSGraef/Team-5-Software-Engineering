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

    //Constructor that inits local host ip
    public UDP_Server() throws UnknownHostException {
        ip = InetAddress.getLocalHost();
    }

    //Receive data method, (From client to server)
    public void UDP_ReceiveData() throws IOException {
        //create server socket to listen for client messages
        DatagramSocket serverSocket = new DatagramSocket(67);
        //create packet to receive the message
        DatagramPacket packetReceive = new DatagramPacket(data, data.length);
        //receive the message 
        serverSocket.receive(packetReceive);
        //close the socket
        serverSocket.close();
    }
}
