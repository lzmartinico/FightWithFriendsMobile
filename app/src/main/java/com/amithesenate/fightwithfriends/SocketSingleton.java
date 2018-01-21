package com.amithesenate.fightwithfriends;

import java.net.Socket;

/**
 * Created by seaney11 on 21/01/2018.
 */
public class SocketSingleton {
    private static Socket socket;

    public static synchronized Socket getSocket(){
        return socket;
    }

    public static synchronized void setSocket(Socket socket){
        SocketSingleton.socket = socket;
    }
}
