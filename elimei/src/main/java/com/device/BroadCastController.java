package com.device;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 * Created by chin-weiyeh on 2/23/17.
 */

public class BroadCastController {


    public interface OnScanListener {
        public void onScaned(List<JSONObject> list);
    }
    private final int UDP_SEND_TIMES=10;
    public final static int DEVICE_UDP_PORT = 12345;
    public final static int SOCKET_TIMEOUT = 1000;
    public final static int SCAN_TIMEOUT = 120000;//120*1000
    private DatagramSocket mBCsocket = null;
    private List<JSONObject> mDeviceList = new ArrayList<JSONObject>();
    private boolean mIsScan = false;
    private byte[] BROADCAST_CMD = {
            0x53, 0x4f, 0x4e, 0x69, 0x58, (byte) 192, (byte) 168, 99, 1,
    };
    Thread mScanThread;
    private OnScanListener mScanListener;
    private boolean mIsHotSpot = false;
    private String tmpIP="";

    public BroadCastController(OnScanListener scanListener) {
        mDeviceList.clear();
        mScanListener = scanListener;
        mIsHotSpot = false;
    }

    public void setTimeout(final int timeout) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mBCsocket != null) {
                    try {
                        mBCsocket.setSoTimeout(timeout);
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

    }

    public InetAddress getBroadcast(InetAddress inetAddr) {

        NetworkInterface temp;
        InetAddress iAddr = null;
        try {
            temp = NetworkInterface.getByInetAddress(inetAddr);
            List<InterfaceAddress> addresses = temp.getInterfaceAddresses();

            for (InterfaceAddress inetAddress : addresses)

                iAddr = inetAddress.getBroadcast();
//            Log.d(TAG, "iAddr=" + iAddr);
            return iAddr;

        } catch (SocketException e) {

            e.printStackTrace();
//            Log.d(TAG, "getBroadcast" + e.getMessage());
        }
        return null;
    }

    public InetAddress getIpAddress() {
        InetAddress inetAddress = null;
        InetAddress myAddr = null;

        try {
            for (Enumeration<NetworkInterface> networkInterface = NetworkInterface
                    .getNetworkInterfaces(); networkInterface.hasMoreElements(); ) {

                NetworkInterface singleInterface = networkInterface.nextElement();

                for (Enumeration<InetAddress> IpAddresses = singleInterface.getInetAddresses(); IpAddresses
                        .hasMoreElements(); ) {
                    inetAddress = IpAddresses.nextElement();

                    if (!inetAddress.isLoopbackAddress() && (singleInterface.getDisplayName()
                            .contains("wlan0") ||
                            singleInterface.getDisplayName().contains("eth0") ||
                            singleInterface.getDisplayName().contains("ap0"))) {

                        myAddr = inetAddress;
                    }
                }
            }

        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return myAddr;
    }

    public int getSocketTimeout() throws SocketException {

        if (mBCsocket != null) {
            return mBCsocket.getSoTimeout();
        }
        return -1;
    }

    public void closeSocket() {
        if (mBCsocket != null) {
            mBCsocket.close();
            mBCsocket = null;
        }
    }

    /**
     * Create udp socket
     *
     * @param isHotSpot
     * @return
     */
    public boolean createSocket(boolean isHotSpot) {
        mIsHotSpot = isHotSpot;
        try {
            if (mBCsocket == null) {
                mBCsocket = new DatagramSocket();
                mBCsocket.setBroadcast(true);
                mBCsocket.setSoTimeout(SOCKET_TIMEOUT);
//                mBCsocket.connect();
            }

            return true;
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    public void setHotSpotFlag(boolean isHotSpot){
        mIsHotSpot=isHotSpot;
    }

    public List<JSONObject>  startScanSync() {
        mDeviceList.clear();
        Thread t= new Thread(new Runnable() {
            @Override
            public void run() {
                if (mBCsocket != null) {
                    InetAddress inetAddress;
                    try {
                        if (!mIsHotSpot) {

                            inetAddress = InetAddress.getByName("255.255.255.255");

                        } else {

                            InetAddress addr1 = getIpAddress();

                            InetAddress addr2 = getBroadcast(addr1);

                            inetAddress = addr2;
                        }
                        DatagramPacket datagramPacket = new DatagramPacket(BROADCAST_CMD, BROADCAST_CMD.length, inetAddress, DEVICE_UDP_PORT);
                        for(int i=1;i<=UDP_SEND_TIMES;i++){
                            mBCsocket.send(datagramPacket);
                        }

                        byte[] buf = new byte[1024];
                        DatagramPacket recv_packet = new DatagramPacket(buf, buf.length);
                        mBCsocket.receive(recv_packet);

                        byte[] buf_recv = recv_packet.getData();

                        byte[] recv = new byte[recv_packet.getLength()];
                        System.arraycopy(buf_recv, 0, recv, 0, recv_packet.getLength());
                        if (recv.length >= 9) {
                            if (recv[0] == BROADCAST_CMD[0] && recv[1] == BROADCAST_CMD[1] && recv[2] ==
                                    BROADCAST_CMD[2] && recv[3] == BROADCAST_CMD[3] && recv[4] == BROADCAST_CMD[4]) {

                                JSONObject deviceObj = new JSONObject();
                                try {
                                    deviceObj.put("ip", (int) (recv[5] & 0xff) + "." +
                                            (int) (recv[6] & 0xff) + "." +
                                            (int) (recv[7] & 0xff) + "." +
                                            (int) (recv[8] & 0xff));
                                    mDeviceList.add(deviceObj);

//                                    if (mScanListener != null) {
//                                        mScanListener.onScaned(mDeviceList);
//                                    }
//                                    break;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                        }

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mDeviceList;
    }


    public void startScanAsync() {
        Log.d("Allen", "startScan 0000");
        if (mIsScan == true || mBCsocket == null) {
            return;
        }
        mIsScan = true;
        mScanThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mDeviceList.clear();
                while (mIsScan) {
                    Log.d("Allen", "scan ***");
                    try {
                        InetAddress inetAddress;
                        if (!mIsHotSpot) {
                            inetAddress = InetAddress.getByName("255.255.255.255");
                        } else {

                            InetAddress addr1 = getIpAddress();

                            InetAddress addr2 = getBroadcast(addr1);

                            inetAddress = addr2;
                        }

                        DatagramPacket datagramPacket = new DatagramPacket(BROADCAST_CMD, BROADCAST_CMD.length, inetAddress, DEVICE_UDP_PORT);
                        mBCsocket.send(datagramPacket);
                        byte[] buf = new byte[1024];
                        DatagramPacket recv_packet = new DatagramPacket(buf, buf.length);
                        mBCsocket.receive(recv_packet);

                        byte[] buf_recv = recv_packet.getData();

                        byte[] recv = new byte[recv_packet.getLength()];
                        System.arraycopy(buf_recv, 0, recv, 0, recv_packet.getLength());
                        if (recv.length >= 9) {
                            if (recv[0] == BROADCAST_CMD[0] && recv[1] == BROADCAST_CMD[1] && recv[2] ==
                                    BROADCAST_CMD[2] && recv[3] == BROADCAST_CMD[3] && recv[4] == BROADCAST_CMD[4]) {


                                JSONObject deviceObj = new JSONObject();
                                try {
                                    deviceObj.put("ip", (int) (recv[5] & 0xff) + "." +
                                            (int) (recv[6] & 0xff) + "." +
                                            (int) (recv[7] & 0xff) + "." +
                                            (int) (recv[8] & 0xff));
                                    mDeviceList.add(deviceObj);

                                    if (mScanListener != null) {
                                        mScanListener.onScaned(mDeviceList);
                                    }
                                    break;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                        }


                        Log.d("Allen", "recv length=" + recv_packet.getLength());
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (mIsScan == false) {
                            break;
                        }
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mScanThread.start();
    }

    public void stopScan() {
        if (mIsScan == false) {
            return;
        }

//            setTimeout(10);

        Log.d("Allen", "stopscan 11111");
        mIsScan = false;
        waitForThreadStop(mScanThread);
        mScanThread = null;
        Log.d("Allen", "stopscan");
    }

    private boolean waitForThreadStop(Thread t) {

        int waitCount = 0;

        if (t == null) {

            return true;

        }

        t.interrupt();

        while (waitCount < 2) {

            if (t.isAlive() == false) {

                return true;

            }

            try {

                t.sleep(20);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

            waitCount++;

        }

        if (t.isAlive() == true) {

            try {

                t.stop();

                return true;

            } catch (Exception e) {


            }

        }

        return false;

    }


}
