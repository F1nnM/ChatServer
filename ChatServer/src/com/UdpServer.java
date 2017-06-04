package com;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer implements Runnable {

	@Override
	public void run() {

	    DatagramSocket socket = new DatagramSocket( 4711 );

	    while ( true )
	    {
	      // Auf Anfrage warten

	      DatagramPacket packet = new DatagramPacket( new byte[1024], 1024 );
	      socket.receive( packet );

	      // Empfänger auslesen

	      InetAddress address = packet.getAddress();
	      int         port    = packet.getPort();
	      int         len     = packet.getLength();
	      byte[]      data    = packet.getData();
	      
	      String msg = new String(data);
	      
	      String s = "";
			byte[] data1 = s.getBytes();
			DatagramPacket packet1 = new DatagramPacket(data1, data1.length, address, port);
			socket.send(packet1);
		
	}

	
	
}
