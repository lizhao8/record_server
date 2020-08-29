package com.common.record;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;

public class NetFetcher implements PacketReceiver {

	@Override
	public void receivePacket(Packet arg0) {

		// 将抓取的包进行输出
		System.out.println(arg0);
		TCPPacket tcp = (TCPPacket) arg0;
		System.out.println("请求ip：" + tcp.src_ip + ",目标ip：" + tcp.dst_ip);
		System.out.println("数据：");
		System.out.println("**********************************");
		/*
		 * for (int i = 0; i < tcp.data.length; i++) { System.out.print((char)
		 * tcp.data[i]); }
		 */
		System.out.println(new String(tcp.data, Charset.forName("UTF-8")));
	}

	public static void main(String[] args) {

		// 获得网卡设备列表
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		if (devices.length == 0) {

			System.out.println("无网卡信息！");
			return;
		}
		// 输出网卡信息
		for (int i = 0; i < devices.length; i++) {

			System.out.println("网卡" + i + "信息:" + devices[i].name);
			for (NetworkInterfaceAddress address : devices[i].addresses) {
				System.out.print(address.address + " ");
			}
			System.out.println("\n");
		}

		Scanner scan = new Scanner(System.in);
		System.out.println("请选择您要监听的网卡序号：");
		int index = 1;

		// 监听选中的网卡
		try {
			JpcapCaptor jpcapCaptor = JpcapCaptor.openDevice(devices[index], 65535, false, 20);
			jpcapCaptor.loopPacket(-1, new getPacket(devices[index].addresses[0].address.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}