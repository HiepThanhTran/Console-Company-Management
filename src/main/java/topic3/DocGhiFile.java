package topic3;

import static topic3.CauHinh.quanLyCauHoi;
import static topic3.CauHinh.quanLyDanhMuc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import topic3.cauhoi.CauHoi;
import topic3.cauhoi.DanhMuc;
import topic3.cauhoi.MultipleChoice;
import topic3.cauhoi.PhuongAn;

public class DocGhiFile {

    public static final File multipleChoiceFile = new File("src/main/resources/topic3/MultipleChoice.txt");
    public static final File inCompleteFile = new File("src/main/resources/topic3/InComplete.txt");
    public static final File conversationFile = new File("src/main/resources/topic3/Conversation.txt");
    public static final File thanhVienFile = new File("src/main/resources/topic3/ThanhVien.txt");

    public static void docFile() {
        List<String> temp = new ArrayList<>();
        try {
            Scanner readMultipleChoice = new Scanner(multipleChoiceFile);
            while (readMultipleChoice.hasNextLine()) {
                String line = readMultipleChoice.nextLine();
                if (!line.equals("end")) {
                    temp.add(line);
                } else {
                    List<PhuongAn> dsPhuongAn = new ArrayList<>();
                    String[] thongTinCauHoi = temp.get(0).split("/");
                    String mucDo = thongTinCauHoi[0];
                    String noiDungCauHoi = thongTinCauHoi[1];
                    String tenDanhMuc = thongTinCauHoi[2];
                    DanhMuc danhMuc = quanLyDanhMuc.traCuu(tenDanhMuc);
                    if (danhMuc == null) {
                        danhMuc = new DanhMuc(tenDanhMuc);
                    }
                    for (int i = 1; i < temp.size(); i++) {
                        String[] thongTinPhuongAn = temp.get(i).split(" - ");
                        String noiDungPhuongAn = thongTinPhuongAn[0];
                        boolean dapAn = Boolean.parseBoolean(thongTinPhuongAn[1]);
                        String giaiThich = thongTinPhuongAn[2];
                        PhuongAn phuongAn = new PhuongAn(noiDungPhuongAn, dapAn, giaiThich);
                        dsPhuongAn.add(phuongAn);
                    }
                    CauHoi multipleChoice = new MultipleChoice(mucDo, noiDungCauHoi, danhMuc, dsPhuongAn);
                    quanLyCauHoi.themCauHoi(multipleChoice);
                    quanLyDanhMuc.themDanhMuc(danhMuc);
                    temp.clear();
                }
            }
//            Scanner readInComplete = new Scanner(inCompleteFile);
//            while (readInComplete.hasNextLine()) {
//
//            }
//            Scanner readConversation = new Scanner(conversationFile);
//            while (readConversation.hasNextLine()) {
//
//            }
//            Scanner readThanhVien = new Scanner(thanhVienFile);
//            while (readThanhVien.hasNextLine()) {
//
//            }
        } catch (FileNotFoundException e) {
            System.err.println("\n** ĐỌC FILE KHÔNG THÀNH CÔNG **");
            e.printStackTrace();
        }
    }

    public static void GhiFile() {
        try {
            PrintWriter writeMultipleChoice = new PrintWriter(multipleChoiceFile);

            PrintWriter writeInComplete = new PrintWriter(inCompleteFile);

            PrintWriter writeConversation = new PrintWriter(conversationFile);

            PrintWriter writeThanhVien = new PrintWriter(thanhVienFile);

        } catch (FileNotFoundException e) {
            System.err.println("\n** GHI FILE KHÔNG THÀNH CÔNG **");
            e.printStackTrace();

        }
    }
}