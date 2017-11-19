import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;


public class SolutionTest {
    @Test
    public void tesInput00() throws Exception {
        String infile = SolutionTest.class.getClassLoader().getResource("input00.txt").getFile();
        InputStream is = new FileInputStream(infile);
        System.setIn(is);
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Solution.main(new String[0]);
        String outfile = SolutionTest.class.getClassLoader().getResource("output00.txt").getFile();
        InputStream eis = new FileInputStream(outfile);
        Scanner s = new Scanner(eis).useDelimiter("\\A");
        String expectedResult =  s.hasNext() ? s.next() : "";

        String result = out.toString().trim();
        assertEquals(result, expectedResult);
    }

    @Test
    public void tesInput01() throws Exception {
        String infile = SolutionTest.class.getClassLoader().getResource("input01.txt").getFile();
        InputStream is = new FileInputStream(infile);
        System.setIn(is);
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Solution.main(new String[0]);
        String outfile = SolutionTest.class.getClassLoader().getResource("output01.txt").getFile();
        InputStream eis = new FileInputStream(outfile);
        Scanner s = new Scanner(eis).useDelimiter("\\A");
        String expectedResult =  s.hasNext() ? s.next() : "";

        String result = out.toString().trim();
        assertEquals(result, expectedResult);
    }

    @Test
    public void tesInput02() throws Exception {
        String infile = SolutionTest.class.getClassLoader().getResource("input02.txt").getFile();
        InputStream is = new FileInputStream(infile);
        System.setIn(is);
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Solution.main(new String[0]);
        String outfile = SolutionTest.class.getClassLoader().getResource("output02.txt").getFile();
        InputStream eis = new FileInputStream(outfile);
        Scanner s = new Scanner(eis).useDelimiter("\\A");
        String expectedResult =  s.hasNext() ? s.next() : "";

        String result = out.toString().trim();
        assertEquals(result, expectedResult);
    }

    @Test
    public void tesInput03() throws Exception {
        String infile = SolutionTest.class.getClassLoader().getResource("input03.txt").getFile();
        InputStream is = new FileInputStream(infile);
        System.setIn(is);
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Solution.main(new String[0]);
        String outfile = SolutionTest.class.getClassLoader().getResource("output03.txt").getFile();
        InputStream eis = new FileInputStream(outfile);
        Scanner s = new Scanner(eis).useDelimiter("\\A");
        String expectedResult =  s.hasNext() ? s.next() : "";

        String result = out.toString().trim();
        assertEquals(result, expectedResult);
    }

    @Test
    public void tesInput04() throws Exception {
        String infile = SolutionTest.class.getClassLoader().getResource("input04.txt").getFile();
        InputStream is = new FileInputStream(infile);
        System.setIn(is);
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Solution.main(new String[0]);
        String outfile = SolutionTest.class.getClassLoader().getResource("output04.txt").getFile();
        InputStream eis = new FileInputStream(outfile);
        Scanner s = new Scanner(eis).useDelimiter("\\A");
        String expectedResult =  s.hasNext() ? s.next() : "";

        String result = out.toString().trim();
        assertEquals(result, expectedResult);
    }
}