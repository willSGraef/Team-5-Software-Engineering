package photon;
import java.io.IOException;
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress;
import java.util.Scanner;

public class UDP_Client {
    //Class variables
    private byte[] data;
    private InetAddress inetAddress;
    private DatagramSocket clientSocket;

    //Constructor that inits local host ip
    public UDP_Client(DatagramSocket clientSocket, InetAddress inetAddress) {
        this.clientSocket = clientSocket;
        this.inetAddress = inetAddress;
        //clientSocket = new DatagramSocket(7500); // Binding to port 7500
    }
    
    //Send data method, (From client to server)
    public void UDP_SendData(String messageToSend) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while(true){
            try{
                messageToSend = scanner.nextLine();
                data = messageToSend.getBytes();
                DatagramPacket packetSend = new DatagramPacket(data, data.length, inetAddress, 7501);
                clientSocket.send(packetSend);
                //clientSocket.receive(packetSend);
                System.out.println("Sent code: " + messageToSend);
            } catch(IOException e){
                e.printStackTrace();
                break;
            }
        }
    }
}
