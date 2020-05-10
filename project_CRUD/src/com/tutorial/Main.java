package com.tutorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner terminalInput = new Scanner(System.in);
        String pilihanUser;
        boolean isLanjutkan = true;


        while (isLanjutkan) {
            clearScreen();
            System.out.println("Database Perpustakaan\n");
            System.out.println("1.\tLihat Seluruh buku");
            System.out.println("2.\tCari Data Buku");
            System.out.println("3.\tTambah Data Buku");
            System.out.println("4.\tUbah Data Buku");
            System.out.println("5.\tHapus Data Buku");

            System.out.println("\n\nPilihan Anda: ");
            pilihanUser = terminalInput.next();

            switch (pilihanUser) {
                case "1":
                    System.out.println("\n=================");
                    System.out.println("LIST SELURUH BUKU");
                    System.out.println("=================");
                    // tampilkan data
                    tampilkanData();
                    break;
                case "2":
                    System.out.println("\n===============");
                    System.out.println("CARI DATA BUKU");
                    System.out.println("==============");
                    // cari data
                    cariData();
                    break;
                case "3":
                    System.out.println("\n===============");
                    System.out.println("TAMBAH DATA BUKU");
                    System.out.println("================");
                    // tambah data
                    tambahData();
                    break;
                case "4":
                    System.out.println("\n===============");
                    System.out.println("UBAH DATA BUKU");
                    System.out.println("==============");
                    // ubah data
                    break;
                case "5":
                    System.out.println("\n===============");
                    System.out.println("HAPUS DATA BUKU");
                    System.out.println("===============");
                    // hapus data
                    break;
                default:
                    System.err.println("\nInputan Anda tidak di temukan\n silahkan pilih 1 - 5");
            }

            isLanjutkan = getYesOrNo("Apakah Anda Ingin Melanjutkan");
        }
    }

    private static void tampilkanData() throws IOException {
        FileReader fileInput;
        BufferedReader bufferInput;

        try {
            fileInput = new FileReader("database.txt");
            bufferInput = new BufferedReader(fileInput);
        } catch (Exception e) {
            System.err.println("Database tidak di temukan");
            System.err.println("Silahkan tambah data terlebih dahulu");
            return;
        }

        System.out.println("\n=========================================================================================");
        System.out.println("| NO |\tTahun |\tPenulis                |\tPenerbit               |\tJudul Buku |");
        System.out.println("===========================================================================================");

        String data = bufferInput.readLine();
        int numberID = 0;
        while(data != null) {
            numberID++;
            StringTokenizer stringToken = new StringTokenizer(data, ",");

            stringToken.nextToken();
            System.out.printf("| %2d ", numberID);
            System.out.printf("|\t%4s  ", stringToken.nextToken());
            System.out.printf("|\t%-20s   ", stringToken.nextToken());
            System.out.printf("|\t%-20s   ", stringToken.nextToken());
            System.out.printf("|\t%s   ", stringToken.nextToken());
            System.out.print("\n");

            data = bufferInput.readLine();
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    private static void cariData() throws IOException {

        // membaca database ada atau tidak
        try {
            File file = new File("database.txt");

        } catch (Exception e) {
            System.err.println("Database tidak di temukan");
            System.err.println("Silahkan tambah data terlebih dahulu");
            return;
        }

        // mengambil keyword dari user
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("Masukkan Kata Kunci untuk mencari buku: ");
        String findData = terminalInput.nextLine();

        String[] keywords = findData.split("\\s+");

        // cek keyword dari database
        checkData(keywords);

    }

    private static void checkData(String[] keywords) throws IOException {

        FileReader fileInput = new FileReader("database.txt");
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        String data = bufferedInput.readLine();

        boolean isExist;
        int numberID = 0;

        System.out.println("\n=========================================================================================");
        System.out.println("| NO |\tTahun |\tPenulis                |\tPenerbit               |\tJudul Buku |");
        System.out.println("===========================================================================================");
        while( data != null) {
            // cek keyword di dalam database
            isExist = true;

            for(String keyword:keywords){
                isExist = isExist && data.toLowerCase().contains(keyword.toLowerCase());
            }

            // jika keyword cocok maka tampilkan
            if(isExist){
                numberID++;
                StringTokenizer stringToken = new StringTokenizer(data, ",");

                stringToken.nextToken();
                System.out.printf("| %2d ", numberID);
                System.out.printf("|\t%4s  ", stringToken.nextToken());
                System.out.printf("|\t%-20s   ", stringToken.nextToken());
                System.out.printf("|\t%-20s   ", stringToken.nextToken());
                System.out.printf("|\t%s   ", stringToken.nextToken());
                System.out.print("\n");
            }
            data = bufferedInput.readLine();
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    private static void tambahData(){

    }

    private static boolean getYesOrNo(String message){
        Scanner terminalInput = new Scanner(System.in);

        System.out.println("\n" +message+ "[Ya / Tidak] >");
        String pilihanUser = terminalInput.next();

        while(!pilihanUser.equalsIgnoreCase("Ya") && !pilihanUser.equalsIgnoreCase("Tidak")){
            System.err.println("Pilihan Anda Bukan Ya Atau Tidak");
            System.out.println("\n" +message+ "[Ya / Tidak] >");
            pilihanUser = terminalInput.next();
        }
        return pilihanUser.equalsIgnoreCase("Ya");
    }

    private static void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }else {
                System.out.print("\033\143");
            }
        } catch (Exception e) {
            System.out.println("Tidak bisa clear Screen: "+ e.getMessage());
        }
    }
}
