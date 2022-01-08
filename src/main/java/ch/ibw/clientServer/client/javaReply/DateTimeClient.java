package ch.ibw.clientServer.client.javaReply;

import ch.ibw.clientServer.shared.DateTimeInfo;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

class DateTimeClient {
    public static void main(String[] args) {
        String hostName = "";   // Rechner-Name bzw. -Adresse
        int port;               // Port-Nummer
        Socket socket;          // Socket für die Verbindung zum Server

        try {
            hostName = args[0];
            port = Integer.parseInt(args[1]);
            socket = new Socket(hostName, port);

            BufferedReader vomServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter zumServer = new PrintWriter(socket.getOutputStream(),true);
            ObjectInputStream vomServerSerializable = new ObjectInputStream(socket.getInputStream());

            BufferedReader vonTastatur = new BufferedReader(new InputStreamReader(System.in));

            // Protokoll abwickeln
            System.out.println("Server sagt:");
            String text = vomServer.readLine(); // vom Server empfangen
            System.out.println(text);           // auf die Konsole schreiben

            text = vonTastatur.readLine();      // von Tastatur lesen
            zumServer.println(text);            // zum Server schicken

            String anzeige = "";
            // Drei Varianten:

            // 1. Server schickt ein mit Java serialisiertes Objekt:
//            DateTimeInfo antwort = (DateTimeInfo) vomServerSerializable.readObject();
//            anzeige = antwort.getInfo();

            // 2. Server schickt ein mit Jackson serialisiertes XML Objekt
            String xmlData = vomServer.readLine();
            DateTimeInfo objectFromServer = new XmlSerializer().deserialize(xmlData, new TypeReference<DateTimeInfo>() {
            });
            System.out.println(objectFromServer.getInfo());

            // 3. Server schickt ein simplen String
//            anzeige = vomServer.readLine();

            System.out.println(anzeige);         // auf die Konsole schreiben

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
