/*
Created by: Karan Dalvi
BinaryFileWriter is a helper class that takes as input string data and writes it
to a file in binary format.
*/
import java.io.IOException;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;

public class BinaryFileWriter {

  String[] huffCodes;
  String inputfilename;
  String outputfilename;
  StringBuilder binaryString;

  public BinaryFileWriter (String inputfilename, String outputfilename, String[] huffCodes) {
    this.inputfilename = inputfilename;
    this.outputfilename = outputfilename;
    this.huffCodes = huffCodes;
    binaryString = new StringBuilder("");
  }

  public void encode() {

    try {
      BufferedReader bf = new BufferedReader(new FileReader(inputfilename));

      String str;
      while(((str=bf.readLine())!=null) && (!(str.replace(" ","").equals(""))))
      {
        binaryString.append(huffCodes[Integer.parseInt(str)]);
      }

      OutputStream opStream = new FileOutputStream(outputfilename);
      opStream.write(formBinary(binaryString.toString()));
      opStream.flush();
      opStream.close();
    }

    catch(IOException e) {
      e.printStackTrace();
    }
  }

  protected byte[] formBinary(String s)
  {
      int sLen = s.length();
      byte[] toReturn = new byte[(sLen + Byte.SIZE - 1) / Byte.SIZE];
      char c;
      for( int i = 0; i < sLen; i++ )
          if( (c = s.charAt(i)) == '1' )
              toReturn[i / Byte.SIZE] = (byte) (toReturn[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
          else if ( c != '0' )
              throw new IllegalArgumentException();
      return toReturn;
  }

}
