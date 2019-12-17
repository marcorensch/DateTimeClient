package ch.ibw.clientServer.client.javaReply;

import ch.ibw.clientServer.shared.DateTimeInfo;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

class DateTimeClient {
    public static void main(String[] args) {
        String hostName = "";   // Rechner-Name bzw. -Adresse
        int port;               // Port-Nummer
        Socket socket;               // Socket für die Verbindung zum Server

        try {
            hostName = args[0];
            port = Integer.parseInt(args[1]);
            socket = new Socket(hostName, port);

            BufferedReader vomServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter zumServer = new PrintWriter(socket.getOutputStream(),true);

            BufferedReader vonTastatur = new BufferedReader(new InputStreamReader(System.in));

            // Protokoll abwickeln
            System.out.println("Server sagt:");
            String text = vomServer.readLine(); // vom Server empfangen
            System.out.println(text);           // auf die Konsole schreiben

            text = vonTastatur.readLine();      // von Tastatur lesen
            zumServer.println(text);            // zum Server schicken

            DateTimeInfo antwort = new XmlSerializer().deserialize(vomServer.readLine(), new TypeReference<DateTimeInfo>() {});
            System.out.println(antwort);         // auf die Konsole schreiben

            // Socket (und damit auch Streams) schliessen
            socket.close();
        } catch (ArrayIndexOutOfBoundsException ae) {
            System.out.println("Aufruf:");
            System.out.println("java DateTimeClient <HostName> <PortNr>");
        } catch (UnknownHostException ue) {
            System.out.println("Kein DNS-Eintrag für " + hostName);
        } catch (IOException e) {
            System.out.println("IO-Error");
            e.printStackTrace();
        }
    }
}