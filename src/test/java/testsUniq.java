//package Tests;

//import com.company.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.imageio.IIOException;
import java.io.*;
import java.util.Random;


public class testsUniq {
    @Test
    void autoTest() throws IOException {
        int leftLimit = 33;
        int rightLimit = 122;
        int targetStringLength = 30;
        Random random = new Random();
        File outFile = new File("file1Test.txt");
        File expected = new File("fileExpectedTest.txt");
        if (!outFile.exists()) {
            outFile.createNewFile();
        }
        if (!expected.exists()) {
            expected.createNewFile();
        }
        PrintWriter pw = new PrintWriter(outFile);
        PrintWriter pt = new PrintWriter(expected);
        for (int j = 0; j < 10; j++) {
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < targetStringLength; i++) {
                int randomLimitedInt = leftLimit + (int)
                        (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            String generatedString = buffer.toString();
            if (j == 3 || j == 7 || j == 9) {
                pw.println(generatedString);
                pw.println(generatedString);
                pw.println(generatedString);
            } else {
                pw.println(generatedString);
            }
            pt.println(generatedString);


        }
        pw.close();
        pt.close();
        Main.main("-i -o file2Exit file1Test".trim().split("\s+"));


        BufferedReader br = null;
        BufferedReader bt = null;
        br = new BufferedReader(new FileReader("fileExpectedTest.txt"));
        bt = new BufferedReader(new FileReader("file2Exit.txt"));
        String line1;
        String line2;
        while ((line1 = bt.readLine()) != null){
            line2=br.readLine();
            Assertions.assertEquals(line1,line2);
        }


    }
}
