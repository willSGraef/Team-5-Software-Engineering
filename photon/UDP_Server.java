package photon;
import java.io.IOException;
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 


public class UDP_Server implements Runnable { //implement runnable 
    //Class variables
    private byte[] data = new byte[1024];
    private DatagramSocket serverSocket;

    //Constructor that inits local host ip
    public UDP_Server() throws IOException {
        this.serverSocket = new DatagramSocket(7501); // Listening on port 7501
    }

    // The run method will be executed when the thread starts
    @Override
    public void run() {
        UDP_ReceiveData(); // Call the receive data method in the thread
    }

    //Receive data method, (From client to server)
    public void UDP_ReceiveData(){ //create run class for thread here 
        while(true){
            try{
                //create packet to receive the message
                DatagramPacket packetReceive = new DatagramPacket(data, data.length);
                //receive the message 
                serverSocket.receive(packetReceive);
                // Convert the received byte data to a string
                String message = new String(packetReceive.getData(), 0, packetReceive.getLength());
                System.out.println("Received code from client: " + message);
            } catch(IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
