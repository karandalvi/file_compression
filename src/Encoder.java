import java.io.IOException;

import java.util.Scanner;
import java.io.FileReader;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.FileOutputStream;
//import java.io.BufferedWriter;

public class Encoder {

  String[] huffCodes;
  String inputfilename;
  String outputfilename;

  Scanner scan;
  FileReader filereader;

  File file;
  OutputStream opStream;
  //OutputStreamWriter opWriter;
  //BufferedWriter output;

  String s;

  public Encoder (String inputfilename, String outputfilename, String[] huffCodes) {
    this.inputfilename = inputfilename;
    this.outputfilename = outputfilename;
    this.huffCodes = huffCodes;
    s = "";
  }

  public void encode() {

    try {
      filereader = new FileReader(inputfilename);
      scan = new Scanner(filereader);

      //file = new File(outputfilename);
      opStream = new FileOutputStream(outputfilename);
      //opWriter = new OutputStreamWriter(opStream, "UTF-16BE");
      //output = new BufferedWriter(new FileWriter(file));
      while (scan.hasNext())
      {
        opStream.write(huffCodes[scan.nextInt()].getBytes());
      }
      //System.out.println(s);
      //byte[] d = s.getBytes();
      //System.out.println(d);

      opStream.flush();
      opStream.close();
      //output.close();
    }
    catch(IOException e) {
      e.printStackTrace();
    }

    scan.close();
  }

}
