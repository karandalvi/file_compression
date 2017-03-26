import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

public class BinaryFileReader {

  String inputfilename;
  StringBuilder binaryString;
  File input;
  InputStream inputStream;

  public BinaryFileReader(String filename) {
    this.inputfilename = "../output/" + filename;
    binaryString = new StringBuilder("");
    input = new File(inputfilename);
  }

  public void read() {
    try {
      inputStream = new FileInputStream(input);
      byte[] b;

      while (inputStream.available() > 0) {
        b = new byte[1];
        inputStream.read(b);
        binaryString.append(toBinaryString(b));
      }
      System.out.println(binaryString);
    }

    catch(IOException e) {
      e.printStackTrace();
    }
  }

  String toBinaryString(byte[] bytes)
  {
    StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
    for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
        sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
    return sb.toString();
  }

}
