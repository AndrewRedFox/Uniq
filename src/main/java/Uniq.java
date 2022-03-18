
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

    private ArrayList<Pair> arrOfPair = new ArrayList<>();


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
                Pair pair = new Pair();
                pair.set(current, 0);
                arrOfPair.add(pair);
            }
            in.close();
        } else {
            try (Scanner in = new Scanner(new File(file + ".txt"));) {
                File inputFile = new File(file + ".txt");
                if (!inputFile.exists()) {
                    inputFile.createNewFile();
                }
                while (in.hasNextLine()) {
                    String current = in.nextLine();
                    if (current.equals("")) break;
                    Pair pair = new Pair();
                    pair.set(current, 0);
                    arrOfPair.add(pair);
                }
            } catch (IOException e) {
                System.out.println("ERROR " + e);
            }
        }
    }

    public void writeOutputTextToFile() {
        try (PrintWriter pw = new PrintWriter(new File(ofile + ".txt"))) {
            File outFile = new File(ofile + ".txt");
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            if (unic) {
                for (int i = 0; i < arrOfPair.size(); i++) {
                    if (prefics) {
                        if (arrOfPair.get(i).second == 0) {
                            pw.println(arrOfPair.get(i).printWithPrefics());
                        }
                    } else {
                        if (arrOfPair.get(i).second == 0) {
                            pw.println(arrOfPair.get(i).printNonPrefics());
                        }
                    }
                }
            } else {
                for (int i = 0; i < arrOfPair.size(); i++) {
                    if (prefics) {
                        pw.println(arrOfPair.get(i).printWithPrefics());
                    } else {
                        pw.println(arrOfPair.get(i).printNonPrefics());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR " + e);
        }
    }

    public void stringEditor() {

        boolean flag = false;
        boolean flagInLine=true;

        if (register) {
            flag = true;
        }
        while (flagInLine) {
            flagInLine=false;
            for (int i = 0; i < arrOfPair.size() - 1; i++) {
                if (funcCaseIgnore(arrOfPair.get(i).first, arrOfPair.get(i + 1).first, numberOfSymbolsIgnore, flag)) {
                    arrOfPair.remove(i + 1);
                    flagInLine = true;
                    arrOfPair.get(i).set(arrOfPair.get(i).second + 1);
                    break;
                }
            }
        }

        for (int i = 0; i < arrOfPair.size(); i++) {
            if (arrOfPair.get(i).second != 0) {
                arrOfPair.get(i).set(arrOfPair.get(i).second + 1);
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
        for (int i = 0; i < arrOfPair.size(); i++) {
            StringBuilder str = new StringBuilder();
            if (prefics) {
                str.append(arrOfPair.get(i).printWithPrefics());
            } else {
                str.append(arrOfPair.get(i).printNonPrefics());
            }
            System.out.println(str);
        }
    }

}

