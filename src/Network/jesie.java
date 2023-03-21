package Network;
import Model2.ResponModel;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class jesie extends JFrame{
    private JPanel vitacomel;
    private JTextField textStatus;
    private JTextField textComment;
    private JButton closeButton;
    private JButton submitButton;
    private JLabel Message;
    private JTextField textMessage;
    private JFrame vita;

    public jesie(){
        vita=new JFrame("TM1_22090030_VitaYuniasari");
        vita.setDefaultCloseOperation(EXIT_ON_CLOSE);
        vita.setPreferredSize(new Dimension(400,400));
        vita.setResizable(true);
        vita.add(vitacomel);
        vita.pack();
        vita.setLocationRelativeTo(null);
        vita.setVisible(true);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()== submitButton){
                    try {
                        URL url = new URL("http://harber.mimoapps.xyz/api/getaccess.php");
                        HttpURLConnection connection =(HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");

                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine())!=null){
                           response.append(inputLine);
                        }
                        in.close();

                        JSONArray jhsonArray = new JSONArray(response.toString());

                        ArrayList<ResponModel>parsedList = new ArrayList<>();
                        for (int i = 0;i<jhsonArray.length();i++){
                            ResponModel resModel = new ResponModel();
                            JSONObject myJSONObject = jhsonArray.getJSONObject(i);
                            resModel.setMessage(myJSONObject.getString("message"));
                            resModel.setStatus(myJSONObject.getString("status"));
                            resModel.setComment(myJSONObject.getString("comment"));
                            parsedList.add(resModel);

                        }
                        for (int index = 0 ; index < parsedList.size();index++){
                            textMessage.setText(parsedList.get(index).getMessage());
                            textStatus.setText(parsedList.get(index).getStatus());
                            textComment.setText(parsedList.get(index).getComment());
                        }


                } catch (ProtocolException ex) {
                        throw new RuntimeException(ex);
                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectURI koneksisaya = new ConnectURI();
                URL myAddress = koneksisaya.builduRl("http://harber.mimoapps.xyz/api/getaccess.php");
                String response = null;
                try {
                    response = koneksisaya.getResponseFromHttpUrl(myAddress);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("");
                System.out.println(response);
                System.out.println("");
                System.out.println("");
                System.out.println("* terimakasih *");
                System.exit(0);

            }
        });
    }


    public static void main(String[] args) {
        new jesie();

    }
}
