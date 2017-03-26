import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;

public class Encoder {

  String[] huffCodes;
  String inputfilename;
  String outputfilename;

  Scanner scan;
  FileReader filereader;
  File file;
  OutputStream opStream;
  StringBuilder binaryString;

  public Encoder (String inputfilename, String outputfilename, String[] huffCodes) {
    this.inputfilename = inputfilename;
    this.outputfilename = "../output/" + outputfilename;
    this.huffCodes = huffCodes;
    binaryString = new StringBuilder("");
  }

  public void encode() {

    try {
      filereader = new FileReader(inputfilename);
      scan = new Scanner(filereader);

      opStream = new FileOutputStream(outputfilename);
      while (scan.hasNext())
      {
        binaryString.append(String.valueOf(huffCodes[scan.nextInt()]));
      }
      scan.close();

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
