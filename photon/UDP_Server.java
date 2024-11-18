package photon;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;



public class UDP_Server implements Runnable { //implement runnable 
    //Class variables
    private byte[] data = new byte[1024];
    private byte[] sendData = new byte[1024];
    InetAddress ip;
    private DatagramSocket serverSocket;
    private InetAddress inetAddress;
    private Controller controller; // Add controller object
    private Model model;


    //Constructor that inits local host ip
    public UDP_Server() throws IOException {
        this.serverSocket = new DatagramSocket(7501); // Listening on port 7501
        this.inetAddress = InetAddress.getByName("localhost");
        System.out.println("Server is running and ready to receive data...");
    }

    // New constructor that accepts a Model instance
    public UDP_Server(Controller c) throws IOException {
        this(); // Call the original constructor
        this.controller = c; // Initialize the Model reference
        this.model = controller.getModel();
    }

    // The run method will be executed when the thread starts
    @Override
    public void run() {
		while(true) {
			try {
				UDP_ReceiveData(); // Call the receive data method in the thread
			} catch (IOException e) {
				System.out.println("Error receiving UDP data: " + e.getMessage());
				e.printStackTrace();
			}
		}
    }

    //Send data method
    public void UDP_SendData(String messageToSend) throws IOException {
        try {
            // Convert the message (equipment ID) to bytes
            sendData = messageToSend.getBytes();
            
            // Create the packet to send, with server address and port (7501 is the server's listening port)
            DatagramPacket packetSend = new DatagramPacket(sendData, sendData.length, inetAddress, 7500);
            
            // Send the packet
            serverSocket.send(packetSend);
            
            System.out.println("Sent code: " + messageToSend);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the socket after sending the data
            //serverSocket.close();
        }
    }


    // Method to receive data
    public void UDP_ReceiveData() throws IOException {
        DatagramPacket packetReceive = new DatagramPacket(data, data.length);
        serverSocket.receive(packetReceive);

        String message = new String(packetReceive.getData(), 0, packetReceive.getLength()).trim();
        System.out.println("Received code: " + message);
        // Split player codes
        String[] codes = message.split("[:]");

        // Check for specific codes and use model if available

        if (codes.length > 1) {
            // Is it a base hit?
            if ((codes[1].equals("53") || codes[1].equals("43")) && controller != null) {
                controller.handleBaseTag(Integer.parseInt(codes[0]), Integer.parseInt(codes[1]));
                UDP_SendData(codes[1]);
            } else {
                // Player -> player hit
                controller.handlePlayerTag(Integer.parseInt(codes[0]), Integer.parseInt(codes[1]));
                if(model.getPlayerTeamById(Integer.parseInt(codes[0])) == model.getPlayerTeamById(Integer.parseInt(codes[1]))) {
                    // team-member hit, broadcast shooter
                    UDP_SendData(codes[0]);
                } else {
                    UDP_SendData(codes[1]);
                }
            }
        }
    }

    // Send close code and close the server socket
    public void close() throws IOException{
        if (serverSocket != null && !serverSocket.isClosed()) {
            String closeMsg = "221";
            data = closeMsg.getBytes();
            DatagramPacket packetSend = new DatagramPacket(data, data.length, inetAddress, 7500);
            for(int i = 0; i < 3; ++i) {
                serverSocket.send(packetSend);
            }
        }
    }
}
