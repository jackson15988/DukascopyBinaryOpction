package com.binary.run;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class Servers {
	// �N�����쪺socket�ܦ��@�Ӷ��X
	protected static List<Socket> sockets = new Vector<>();

	public static void main(String[] args) throws IOException {
		// �إߪA�Ⱥ�
		ServerSocket server = new ServerSocket(9877);
		boolean flag = true;
		// �����Ȥ�ݽШD
		while (flag) {
			try {
				// ���뵥�ݫȤ�ݪ��s�u
				Socket accept = server.accept();
				synchronized (sockets) {
					sockets.add(accept);
				}
				// �h�Ӧ��A��������i���Ȥ�ݪ��T��
				Thread thread = new Thread(new ServerThead(accept));
				thread.start();
				// ���򲧱`�C
			} catch (Exception e) {
				flag = false;
				e.printStackTrace();
			}
		}
		// �������A��
		server.close();
	}

}