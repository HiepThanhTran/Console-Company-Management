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
import topic3.cauhoi.Conversation;
import topic3.cauhoi.DanhMuc;
import topic3.cauhoi.InComplete;
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
            Scanner multipleChoice = new Scanner(multipleChoiceFile);
            while (multipleChoice.hasNextLine()) {
                String line = multipleChoice.nextLine();
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
                    CauHoi cauHoi = new MultipleChoice(mucDo, noiDungCauHoi, danhMuc, dsPhuongAn);
                    danhMuc.themCauHoi(cauHoi);
                    quanLyCauHoi.themCauHoi(cauHoi);
                    quanLyDanhMuc.themDanhMuc(danhMuc);
                    temp.clear();
                }
            }

            Scanner inComplete = new Scanner(inCompleteFile);
            while (inComplete.hasNextLine()) {
                String line = inComplete.nextLine();
                if (!line.equals("end")) {
                    temp.add(line);
                } else {
                    List<MultipleChoice> dsCauHoi = new ArrayList<>();
                    String[] thongTinCauHoi = temp.get(0).split("/");
                    String mucDo = thongTinCauHoi[0];
                    String noiDungCauHoi = thongTinCauHoi[1];
                    String tenDanhMuc = thongTinCauHoi[2];
                    DanhMuc danhMuc = quanLyDanhMuc.traCuu(tenDanhMuc);
                    if (danhMuc == null) {
                        danhMuc = new DanhMuc(tenDanhMuc);
                    }
                    List<PhuongAn> dsPhuongAn = new ArrayList<>();
                    for (int i = 1; i < temp.size(); i += 5) {
                        String[] ttch = temp.get(i).split("/");
                        String md = ttch[0];
                        String nd = ttch[1];
                        String tdm = ttch[2];
                        for (int j = 1; j < 5; j++) {
                            String[] thongTinPhuongAn = temp.get(i + j).split(" - ");
                            String noiDungPhuongAn = thongTinPhuongAn[0];
                            boolean dapAn = Boolean.parseBoolean(thongTinPhuongAn[1]);
                            String giaiThich = thongTinPhuongAn[2];
                            PhuongAn phuongAn = new PhuongAn(noiDungPhuongAn, dapAn, giaiThich);
                            dsPhuongAn.add(phuongAn);
                        }
                        CauHoi cauHoi = new MultipleChoice(md, nd, new DanhMuc(tdm), dsPhuongAn);
                        CauHoi.setDem(CauHoi.getDem() - 1);
                        dsCauHoi.add((MultipleChoice) cauHoi);
                        dsPhuongAn = new ArrayList<>();
                    }
                    CauHoi cauHoi = new InComplete(mucDo, noiDungCauHoi, danhMuc, dsCauHoi);
                    danhMuc.themCauHoi(cauHoi);
                    quanLyCauHoi.themCauHoi(cauHoi);
                    quanLyDanhMuc.themDanhMuc(danhMuc);
                    temp.clear();
                }
            }

            Scanner conversation = new Scanner(conversationFile);
            while (conversation.hasNextLine()) {
                String line = conversation.nextLine();
                if (!line.equals("end")) {
                    temp.add(line);
                } else {
                    List<MultipleChoice> dsCauHoi = new ArrayList<>();
                    String[] thongTinCauHoi = temp.get(0).split("/");
                    String mucDo = thongTinCauHoi[0];
                    String noiDungCauHoi = thongTinCauHoi[1];
                    String tenDanhMuc = thongTinCauHoi[2];
                    DanhMuc danhMuc = quanLyDanhMuc.traCuu(tenDanhMuc);
                    if (danhMuc == null) {
                        danhMuc = new DanhMuc(tenDanhMuc);
                    }
                    List<PhuongAn> dsPhuongAn = new ArrayList<>();
                    for (int i = 1; i < temp.size(); i += 5) {
                        String[] ttch = temp.get(i).split("/");
                        String md = ttch[0];
                        String nd = ttch[1];
                        String tdm = ttch[2];
                        for (int j = 1; j < 5; j++) {
                            String[] thongTinPhuongAn = temp.get(i + j).split(" - ");
                            String noiDungPhuongAn = thongTinPhuongAn[0];
                            boolean dapAn = Boolean.parseBoolean(thongTinPhuongAn[1]);
                            String giaiThich = thongTinPhuongAn[2];
                            PhuongAn phuongAn = new PhuongAn(noiDungPhuongAn, dapAn, giaiThich);
                            dsPhuongAn.add(phuongAn);
                        }
                        CauHoi cauHoi = new MultipleChoice(md, nd, new DanhMuc(tdm), dsPhuongAn);
                        CauHoi.setDem(CauHoi.getDem() - 1);
                        dsCauHoi.add((MultipleChoice) cauHoi);
                        dsPhuongAn = new ArrayList<>();
                    }
                    CauHoi cauHoi = new Conversation(mucDo, noiDungCauHoi, danhMuc, dsCauHoi);
                    danhMuc.themCauHoi(cauHoi);
                    quanLyCauHoi.themCauHoi(cauHoi);
                    quanLyDanhMuc.themDanhMuc(danhMuc);
                    temp.clear();
                }
            }
//
            Scanner thanhVien = new Scanner(thanhVienFile);
            while (thanhVien.hasNextLine()) {

            }

            multipleChoice.close();
            inComplete.close();
            conversation.close();
            thanhVien.close();
        } catch (FileNotFoundException e) {
            System.err.println("\n** ĐỌC FILE KHÔNG THÀNH CÔNG **");
            e.printStackTrace();
        }
    }

    public static void GhiFile() {
        try {
            PrintWriter multipleChoice = new PrintWriter(multipleChoiceFile);
            quanLyCauHoi.getDsCauHoi().stream().filter(cauHoi -> cauHoi instanceof MultipleChoice).forEach(ch -> {
                multipleChoice.println(ch.getMucDo() + "/" + ch.getNoiDung() + "/" + ch.getDanhMuc().getTenDanhMuc());
                ((MultipleChoice) ch).getDsPhuongAn().forEach(phuongAn -> multipleChoice.println(
                    phuongAn.getNoiDung() + " - " + phuongAn.isDapAn() + " - " + phuongAn.getGiaiThich()));
                multipleChoice.println("end");
            });

            PrintWriter inComplete = new PrintWriter(inCompleteFile);
            quanLyCauHoi.getDsCauHoi().stream().filter(cauHoi -> cauHoi instanceof InComplete).forEach(ch -> {
                inComplete.println(ch.getMucDo() + "/" + ch.getNoiDung() + "/" + ch.getDanhMuc().getTenDanhMuc());
                ch.getDsCauHoi().forEach(mc -> {
                    inComplete.println(mc.getMucDo() + "/" + mc.getNoiDung() + "/" + mc.getDanhMuc().getTenDanhMuc());
                    mc.getDsPhuongAn().forEach(phuongAn -> inComplete.println(
                        phuongAn.getNoiDung() + " - " + phuongAn.isDapAn() + " - " + phuongAn.getGiaiThich()));
                });
                inComplete.println("end");
            });

            PrintWriter conversation = new PrintWriter(conversationFile);
            quanLyCauHoi.getDsCauHoi().stream().filter(cauHoi -> cauHoi instanceof Conversation).forEach(ch -> {
                conversation.println(ch.getMucDo() + "/" + ch.getNoiDung() + "/" + ch.getDanhMuc().getTenDanhMuc());
                ch.getDsCauHoi().forEach(mc -> {
                    conversation.println(mc.getMucDo() + "/" + mc.getNoiDung() + "/" + mc.getDanhMuc().getTenDanhMuc());
                    mc.getDsPhuongAn().forEach(phuongAn -> conversation.println(
                        phuongAn.getNoiDung() + " - " + phuongAn.isDapAn() + " - " + phuongAn.getGiaiThich()));
                });
                conversation.println("end");
            });

            PrintWriter thanhVien = new PrintWriter(thanhVienFile);

            multipleChoice.close();
            inComplete.close();
            conversation.close();
            thanhVien.close();
        } catch (FileNotFoundException e) {
            System.err.println("\n** GHI FILE KHÔNG THÀNH CÔNG **");
            e.printStackTrace();
        }
    }
}