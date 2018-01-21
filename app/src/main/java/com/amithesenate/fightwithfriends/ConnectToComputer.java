package com.amithesenate.fightwithfriends;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectToComputer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_computer);
        Button button = (Button) findViewById(R.id.connectButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startConnection();
            }
        });
    }

    public void startConnection(){
        //Intent intent = new Intent(this, ChooseRoleActivity.class);
        EditText editText = (EditText) findViewById(R.id.ipAddress);
        final String ipAddress = editText.getText().toString();
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println(ipAddress);
                    Socket client = new Socket(ipAddress, 5000);
                    System.out.println("connected");
                    OutputStream outToServer = client.getOutputStream();
                    DataOutputStream out = new DataOutputStream(outToServer);
                    out.writeUTF("test");
                    InputStream inFromServer = client.getInputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(inFromServer));
                    SocketSingleton.setSocket(client);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
