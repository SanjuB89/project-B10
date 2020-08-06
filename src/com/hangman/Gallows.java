package com.hangman;

import java.io.*;

class Gallows {
    // INSTANCE VARIABLES
    private File originalFile = new File("/Users/murpslaw/Code/StudentWork/IntmJ/workspace/project-B10_Hangman/src/com/hangman/txt/OriginalBluePrint.txt");
    private File player1File = new File("/Users/murpslaw/Code/StudentWork/IntmJ/workspace/project-B10_Hangman/src/com/hangman/txt/Player1-file.txt");
    private File player2File = new File("/Users/murpslaw/Code/StudentWork/IntmJ/workspace/project-B10_Hangman/src/com/hangman/txt/Player2-file.txt");

    // BUSINESS METHODS

    // SWITCH PHASE
    void updateGallows(int numOfMistakes) {

        try {
            RandomAccessFile bw = new RandomAccessFile(player1File, "rw");

//            File originalFile = new File("C:\\StudentWork\\MiniProject\\project-B10\\src\\OriginalBluePrint.txt");
            BufferedReader originalBuffReader = new BufferedReader(new FileReader(originalFile));

//            File player1File = new File("C:\\StudentWork\\MiniProject\\project-B10\\src\\Player1-file.txt");
            BufferedWriter player1BuffWriter = new BufferedWriter(new FileWriter(player1File, true));
            BufferedReader player1BuffReader = new BufferedReader(new FileReader(player1File));


            String line;
            switch (numOfMistakes) {
                case 0:
                    while ((line = originalBuffReader.readLine()) != null) {
                        System.out.println(line);
                        player1BuffWriter.write(line);
                        player1BuffWriter.newLine();
                    }

                    player1BuffWriter.flush();
                    player1BuffWriter.close();
                    break;
                case 1:
                    bw.seek(240);
                    bw.write("(O)".getBytes());
                    while ((line = player1BuffReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    player1BuffReader.close();
                    break;
                case 2:
                    bw.seek(288);
                    bw.write("|".getBytes());
                    bw.seek(332);
                    bw.write("|".getBytes());
                    while ((line = player1BuffReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    player1BuffReader.close();
                    break;
                case 3:
                    bw.seek(285);
                    bw.write("//|".getBytes());

                    while ((line = player1BuffReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    player1BuffReader.close();
                    //}
                    break;
                case 4:
                    bw.seek(289);
                    bw.write("|\\\\".getBytes());

                    while ((line = player1BuffReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    player1BuffReader.close();
                    break;
                case 5:
                    bw.seek(374);
                    bw.write("//".getBytes());

                    while ((line = player1BuffReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    player1BuffReader.close();
                    break;
                case 6:
                    bw.seek(377);
                    bw.write("\\\\".getBytes());

                    while ((line = player1BuffReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    player1BuffReader.close();
            }
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("No Files Found!" + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}