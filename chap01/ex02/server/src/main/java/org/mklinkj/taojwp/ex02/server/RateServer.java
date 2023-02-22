package org.mklinkj.taojwp.ex02.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RateServer {
  private static final float USD_RATE = 1124.70F;
  private static final float JPY_RATE = 10.113F;
  private static final float CNY_RATE = 163.30F;
  private static final float GBP_RATE = 1444.35F;
  private static final float EUR_RATE = 1295.97F;

  public static void startServer() {
    InputStream is;
    BufferedReader br;
    BufferedWriter bw;
    PrintWriter pw;
    OutputStream os;
    ServerSocket serverSocket;
    Socket s1;
    try {
      serverSocket = new ServerSocket(5434);
      System.out.println("서버 실행 중...");

      while (true) {
        // 클라이언트의 접속을 인지 시에 accept()메소드를 호출해서 소켓 객체를 생성한다.
        s1 = serverSocket.accept();
        is = s1.getInputStream();
        os = s1.getOutputStream();
        br = new BufferedReader(new InputStreamReader(is));
        String data = br.readLine();
        System.out.println("서버 수신 데이터:" + data);
        String result = calculate(data);
        System.out.println(result);

        bw = new BufferedWriter(new OutputStreamWriter(os));
        pw = new PrintWriter(bw, true);
        pw.println(result);
        pw.close();
      }
    } catch (IOException ie) {
      ie.printStackTrace();
    }
  }

  public static void main(String[] args) {
    startServer();
    // 서버의 시작 중단을 할 수 있게 개선되면 좋을 것 같다.
    // 스래드를 열어서 어떻게 해야할 것 같은데... 잘 모르겠다.. 😓
  }

  private static String calculate(String data) {
    String[] token = data.split(",");

    float won = Float.parseFloat(token[0]);
    String operator = token[1];
    String result =
        switch (operator) {
          case "달러" -> String.format("%.6f", won / USD_RATE);
          case "엔화" -> String.format("%.6f", won / JPY_RATE);
          case "위안" -> String.format("%.6f", won / CNY_RATE);
          case "파운드" -> String.format("%.6f", won / GBP_RATE);
          case "유로" -> String.format("%.6f", won / EUR_RATE);
          default -> null;
        };

    return result;
  }
}
