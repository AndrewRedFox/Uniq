
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Scanner;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Uniq {

    private ArrayList<String> arr = new ArrayList<>();
    private ArrayList<Integer> argsCandU = new ArrayList<>();

    @Option(name = "-i")
    private boolean register;
    @Option(name = "-u")
    private boolean unic;
    @Option(name = "-c")
    private boolean prefics;
    @Option(name = "-s")
    private Integer numberOfSymbolsIgnore = 0;
    @Option(name = "-o")
    private String ofile;
    @Argument()
    private String file;

    public void doMain(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);//писать сюда аргументы
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java SampleMain [options...] arguments...");
        }

        readInput(file);
        stringEditor();
        if (ofile != null) {
            writeOutputTextToFile();
        } else {
            outputStrToConsole();
        }
    }

    public void readInput(String fileName) {
        if (fileName == null) {
            Scanner in = new Scanner(System.in);
            while (in.hasNextLine()) {
                String current = in.nextLine();
                if (current.equals("")) break;
                arr.add(current);
                argsCandU.add(0);
            }
            in.close();
        } else {
            try(Scanner in = new Scanner(new File(file+".txt"));) {
                File inputFile = new File(file + ".txt");
                if (!inputFile.exists()) {
                    inputFile.createNewFile();
                }
                while (in.hasNextLine()) {
                    String current = in.nextLine();
                    if (current.equals("")) break;
                    arr.add(current);
                    argsCandU.add(0);
                }
            } catch (IOException e) {
                System.out.println("ERROR " + e);
            }
        }
    }

    public void writeOutputTextToFile() {
        try(PrintWriter pw = new PrintWriter(new File(ofile+".txt"))) {
            File outFile = new File(ofile + ".txt");
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            if (unic) {
                for (int i = 0; i < arr.size(); i++) {
                    if (prefics) {
                        if (argsCandU.get(i) == 0) {
                            pw.println(arr.get(i) + " " + "\"" + argsCandU.get(i).toString() + "\"");
                        }
                    } else {
                        if (argsCandU.get(i) == 0) {
                            pw.println(arr.get(i));
                        }
                    }
                }
            } else {
                for (int i = 0; i < arr.size(); i++) {
                    if (prefics) {
                        pw.println(arr.get(i) + " " + "\"" + argsCandU.get(i).toString() + "\"");
                    } else {
                        pw.println(arr.get(i));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR " + e);
        }
    }

    public void stringEditor() {
        int count = 5;
        boolean flag = false;

        if (register) {
            flag = true;
        }
        while (count != 0) {
            count = 0;
            for (int i = 0; i < arr.size() - 1; i++) {
                if (funcCaseIgnore(arr.get(i), arr.get(i + 1), numberOfSymbolsIgnore, flag)) {
                    arr.remove(i + 1);
                    count += 1;
                    argsCandU.set(i, argsCandU.get(i) + 1);
                    break;
                }
            }
        }

        for (int i = 0; i < argsCandU.size(); i++) {
            if (argsCandU.get(i) != 0) {
                argsCandU.set(i, argsCandU.get(i) + 1);
            }
        }
    }

    private boolean funcCaseIgnore(String str1, String str2, int count, boolean flag) {
        if (flag) {
            return str1.substring(count).equalsIgnoreCase(str2.substring(count));
        } else {
            return str1.substring(count).equals(str2.substring(count));
        }
    }

    public void outputStrToConsole() {
        for (int i = 0; i < arr.size(); i++) {
            StringBuilder str = new StringBuilder();
            str.append(arr.get(i)).append(" " + "\"" + argsCandU.get(i).toString() + "\"");
            System.out.println(str);
        }
    }

}
