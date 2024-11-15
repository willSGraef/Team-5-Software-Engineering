package photon;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;



public class UDP_Server implements Runnable { //implement runnable 
    //Class variables
    private byte[] data = new byte[1024];
    InetAddress ip;
    private DatagramSocket serverSocket;
    private InetAddress inetAddress;
    private Model model; // Add Model field


    //Constructor that inits local host ip
    public UDP_Server() throws IOException {
        this.serverSocket = new DatagramSocket(7501); // Listening on port 7501
        this.inetAddress = InetAddress.getByName("localhost");
        System.out.println("Server is running and ready to receive data...");
    }

    // New constructor that accepts a Model instance
    public UDP_Server(Model model) throws IOException {
        this(); // Call the original constructor
        this.model = model; // Initialize the Model reference
    }

    // The run method will be executed when the thread starts
    @Override
    public void run() {
        try {
            UDP_ReceiveData(); // Call the receive data method in the thread
        } catch (IOException e) {
            System.out.println("Error receiving UDP data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Send data method
    public void UDP_SendData(String messageToSend) throws IOException {
        try {
            // Convert the message (equipment ID) to bytes
            data = messageToSend.getBytes();
            
            // Create the packet to send, with server address and port (7501 is the server's listening port)
            DatagramPacket packetSend = new DatagramPacket(data, data.length, inetAddress, 7500);
            
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

        // Check for specific codes and use model if available
        if (message.equals("53") && model != null) {
            model.awardPointsToTeam('g', 100, "B"); // Green team scores when red base is hit
        } else if (message.equals("43") && model != null) {
            model.awardPointsToTeam('r', 100, "B"); // Red team scores when green base is hit
        } else {
            // Player -> Player hit occurred
            // Split player codes
            String[] codes = message.split("[:]");
            
            // are player's on same team?
            if (model.getPlayerTeamById((Integer.parseInt(codes[0]))) == model.getPlayerTeamById(Integer.parseInt(codes[1]))){
                // Yes -> Shut down attacking player
                UDP_SendData(codes[0]);
                // No points awarded for team kills
            } else {
                // No -> Shut down attacked player
                UDP_SendData(codes[1]);
                // Award points
                model.getPlayerByEquipmentId(Integer.parseInt(codes[0]));
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
            serverSocket.close();
            System.out.println("UDP Server closed.");
        }
    }
}
