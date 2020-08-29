package com.common.record;

import java.util.Date;

import jpcap.PacketReceiver;
import jpcap.packet.ICMPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

public class getPacket implements PacketReceiver {
	String ipAddr;// 本机ip地址数组

	private double upLoadSpeed = 0;
	private double downLoadSpeed = 0;
	private double upLoadTcpSpeed = 0;
	private double downLoadTcpSpeed = 0;
	private double upLoadUdpSpeed = 0;
	private double downLoadUdpSpeed = 0;
	private double upLoadIcmSpeed = 0;
	private double downLoadIcmSpeed = 0;

	private static Packet currentPacket = new Packet();// 表示最新抓取的包

	public getPacket(String ipAddr) {
		this.ipAddr = ipAddr.substring(1);
	}

	// 判断当前抓的包是否为空，若为空则表示断网，否则没断网
	public static boolean isPacketEmpty() {
		if (currentPacket == null) {
			return true;
		} else {
			return false;
		}
	}

	Date bedore = new Date();
	long upLoadPacketSize = 0;
	long downLoadPacketSize = 0;
	long upLoadTcpPacketSize = 0;
	long downLoadTcpPacketSize = 0;
	long upLoadUdpPacketSize = 0;
	long downLoadUdpPacketSize = 0;
	long upLoadIcmPacketSize = 0;
	long downLoadIcmPacketSize = 0;

	// 获得截取的数据包
	public void receivePacket(Packet p) {
		Date now = new Date();

		if (p instanceof TCPPacket) {// 如果截取的是TCP包
			TCPPacket ip = (TCPPacket) p;
			if (ip.src_ip.getHostAddress().equals(ipAddr)) {// 如果TCP包的源IP地址等于本机ip则此包为上传包
				upLoadPacketSize += ip.len;
				upLoadTcpPacketSize += ip.len;
			} else if (ip.dst_ip.getHostAddress().equals(ipAddr)) {// 如果TCP包的目的IP地址等于本机ip则此包为下载包
				downLoadPacketSize += ip.len;
				downLoadTcpPacketSize += ip.len;
			}
		}
		if (p instanceof UDPPacket) {// 如果截取的是TCP包
			UDPPacket ip = (UDPPacket) p;
			if (ip.src_ip.getHostAddress().equals(ipAddr)) {// 如果TCP包的源IP地址等于本机ip则此包为上传包
				upLoadPacketSize += ip.len;
				upLoadUdpPacketSize += ip.len;
			} else if (ip.dst_ip.getHostAddress().equals(ipAddr)) {// 如果TCP包的目的IP地址等于本机ip则此包为下载包
				downLoadPacketSize += ip.len;
				downLoadUdpPacketSize += ip.len;
			}
		}
		if (p instanceof ICMPPacket) {// 如果截取的是TCP包
			ICMPPacket ip = (ICMPPacket) p;
			if (ip.src_ip.getHostAddress().equals(ipAddr)) {// 如果TCP包的源IP地址等于本机ip则此包为上传包
				upLoadPacketSize += ip.len;
				upLoadIcmPacketSize += ip.len;
			} else if (ip.dst_ip.getHostAddress().equals(ipAddr)) {// 如果TCP包的目的IP地址等于本机ip则此包为下载包
				downLoadPacketSize += ip.len;
				downLoadIcmPacketSize += ip.len;
			}
		}

		if (now.getTime() - bedore.getTime() >= 1000 * 1) {
			bedore = now;

			double upLoadTcpPacketSizes = upLoadTcpPacketSize / (1024 * 1024 * 1f);
			double downLoadTcpPacketSizes = downLoadTcpPacketSize / (1024 * 1024 * 1f);

			double upLoadUdpPacketSizes = upLoadUdpPacketSize / (1024 * 1024 * 1f);
			double downLoadUdpPacketSizes = downLoadUdpPacketSize / (1024 * 1024 * 1f);

			double upLoadIcmPacketSizes = upLoadIcmPacketSize / (1024 * 1024 * 1f);
			double downLoadIcmPacketSizes = downLoadIcmPacketSize / (1024 * 1024 * 1f);

			double upLoadPacketSizes = upLoadPacketSize / (1024 * 1024 * 1f);
			double downLoadPacketSizes = downLoadPacketSize / (1024 * 1024 * 1f);

			System.out.printf("TCP上传速度： %.2f mb/s\n", upLoadTcpPacketSizes);
			System.out.printf("TCP下载速度：%.2f mb/s\n", downLoadTcpPacketSizes);
			System.out.printf("UDP上传速度： %.2f mb/s\n", upLoadUdpPacketSizes);
			System.out.printf("UDP下载速度：%.2f mb/s\n", downLoadUdpPacketSizes);
			System.out.printf("ICM上传速度： %.2f mb/s\n", upLoadIcmPacketSizes);
			System.out.printf("ICM下载速度：%.2f mb/s\n", downLoadIcmPacketSizes);
			System.out.printf("总计上传速度： %.2f mb/s\n", upLoadPacketSizes);
			System.out.printf("总计下载速度：%.2f mb/s\n", downLoadPacketSizes);

			if (upLoadTcpPacketSizes > upLoadTcpSpeed) {
				upLoadTcpSpeed = upLoadTcpPacketSizes;
			}
			if (downLoadTcpPacketSizes > downLoadTcpSpeed) {
				downLoadTcpSpeed = downLoadTcpPacketSizes;
			}
			if (upLoadUdpPacketSizes > upLoadUdpSpeed) {
				upLoadUdpSpeed = upLoadUdpPacketSizes;
			}
			if (downLoadUdpPacketSizes > downLoadUdpSpeed) {
				downLoadUdpSpeed = downLoadUdpPacketSizes;
			}
			if (upLoadIcmPacketSizes > upLoadIcmSpeed) {
				upLoadIcmSpeed = upLoadIcmPacketSizes;
			}
			if (downLoadIcmPacketSizes > downLoadIcmSpeed) {
				downLoadIcmSpeed = downLoadIcmPacketSizes;
			}
			if (upLoadPacketSizes > upLoadSpeed) {
				upLoadSpeed = upLoadPacketSizes;
			}
			if (downLoadPacketSizes > downLoadSpeed) {
				downLoadSpeed = downLoadPacketSizes;
			}

			System.out.printf("TCP最大上传速度： %.2f mb/s\n", upLoadTcpSpeed);
			System.out.printf("TCP最大下载速度：%.2f mb/s\n", downLoadTcpSpeed);
			System.out.printf("UDP最大上传速度： %.2f mb/s\n", upLoadUdpSpeed);
			System.out.printf("UDP最大下载速度：%.2f mb/s\n", downLoadUdpSpeed);
			System.out.printf("ICM最大上传速度： %.2f mb/s\n", upLoadIcmSpeed);
			System.out.printf("ICM最大下载速度：%.2f mb/s\n", downLoadIcmSpeed);
			System.out.printf("总计最大上传速度： %.2f mb/s\n", upLoadSpeed);
			System.out.printf("总计最大下载速度：%.2f mb/s\n", downLoadSpeed);

			System.out.println();

			upLoadPacketSize = 0;
			downLoadPacketSize = 0;
			upLoadTcpPacketSize = 0;
			downLoadTcpPacketSize = 0;
			upLoadUdpPacketSize = 0;
			downLoadUdpPacketSize = 0;
			upLoadIcmPacketSize = 0;
			downLoadIcmPacketSize = 0;
		}
	}
}
