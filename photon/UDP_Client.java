package photon;
import java.io.IOException;
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress;


public class UDP_Client {
    //Class variables
    private byte[] data;
    private InetAddress inetAddress;
    private DatagramSocket clientSocket;

    //Constructor that inits local host ip
    public UDP_Client() throws IOException {
        this.clientSocket = new DatagramSocket(); //7500
        this.inetAddress = InetAddress.getByName("localhost");
    }
    
    //Send data method, (From client to server)
    public void UDP_SendData(String messageToSend) throws IOException {
        try {
            // Convert the message (equipment ID) to bytes
            data = messageToSend.getBytes();
            
            // Create the packet to send, with server address and port (7501 is the server's listening port)
            DatagramPacket packetSend = new DatagramPacket(data, data.length, inetAddress, 7501);
            
            // Send the packet
            clientSocket.send(packetSend);
            
            System.out.println("Sent code: " + messageToSend);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the socket after sending the data
            //clientSocket.close();
        }
    }
}
