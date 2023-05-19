package project_management.utils;

import static project_management.ui.Factory.SIMPLEDATEFORMAT;
import static project_management.ui.Factory.departmentFile;
import static project_management.ui.Factory.departmentManager;
import static project_management.ui.Factory.employeeFile;
import static project_management.ui.Factory.employeeManager;
import static project_management.ui.Factory.joinProjectManger;
import static project_management.ui.Factory.projectFile;
import static project_management.ui.Factory.projectManager;
import static project_management.ui.Factory.provideInsuranceManager;
import static project_management.ui.Factory.relativeFile;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import project_management.behavior.JoinProject;
import project_management.behavior.ProvideInsurance;
import project_management.entity.Department;
import project_management.entity.Designer;
import project_management.entity.Employee;
import project_management.entity.Manager;
import project_management.entity.Normal;
import project_management.entity.Programmer;
import project_management.entity.Project;
import project_management.entity.Relative;
import project_management.entity.Tester;
import project_management.exception.AmountException;

public final class FileUtils {

    private FileUtils() {
    }

    public static void readFile() {
        try {
            /**
             * Đọc danh sách nhân viên từ file EmployeeList
             * tokens[0] - ID nhân viên
             * tokens[1] - Tên nhân viên
             * tokens[2] - Giới tính
             * tokens[3] - Ngày sinh
             * tokens[4] - Email
             * tokens[5] - Lương OT (Programmer), Bonus (Designer), Số lỗi tìm được (Tester)
             */
            Scanner readEmployee = new Scanner(employeeFile);
            while (readEmployee.hasNextLine()) {
                String[] tokens = readEmployee.nextLine().split(", ");
                Employee employee = null;
                String id = tokens[0];
                String name = tokens[1];
                String gender = tokens[2];
                Date dob = SIMPLEDATEFORMAT.parse(tokens[3]);
                String email = tokens[4];
                switch (id.substring(0, 1)) {
                    case "N" -> employee = new Normal(name, gender, dob, id, email);
                    case "P" -> employee = new Programmer(name, gender, dob, id, email, Double.parseDouble(tokens[5]));
                    case "D" -> employee = new Designer(name, gender, dob, id, email, Double.parseDouble(tokens[5]));
                    case "T" -> employee = new Tester(name, gender, dob, id, email, Integer.parseInt(tokens[5]));
                    case "M" -> employee = new Manager(name, gender, dob, id, email);
                }
                employeeManager.add(employee);
            }
            /**
             * Đọc danh sách phòng ban từ file DepartmentList
             * tokens[0] - Tên phòng ban
             * tokens[1] - Mã nhân viên quản lý
             * tokens[2] - Ngày nhậm chức của nhân viên quản lý
             * tokens[...] - ID của các nhân viên trực thuộc phòng ban
             */
            Scanner readDepartment = new Scanner(departmentFile);
            while (readDepartment.hasNextLine()) {
                String[] tokens = readDepartment.nextLine().split(", ");
                int index = 1;
                Department department = new Department(tokens[0]);
                if (tokens[2].matches("[0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{4}")) {
                    Employee manager = employeeManager.searchById(tokens[1]);
                    Date dateTakeOffice = SIMPLEDATEFORMAT.parse(tokens[2]);
                    ((Manager) manager).addDepartment(department);
                    department.addEmployee(manager);
                    department.setManager(manager);
                    department.setDateTakeOffice(dateTakeOffice);
                    index = 3;
                }
                for (int i = index; i < tokens.length; i++) {
                    Employee employee = employeeManager.searchById(tokens[i]);
                    if (employee instanceof Manager manager) {
                        manager.addDepartment(department);
                    }
                    department.addEmployee(employee);
                }
                departmentManager.add(department);
            }
            /**
             * Đọc danh sách nhân thân từ file RelativeList
             * tokens[0] - Tên nhân thân
             * tokens[1] - Giới tính
             * tokens[2] - Ngày sinh
             * tokens[3] - Mối quan hệ
             * tokens[4] - Mã số bảo hiểm
             * tokens[5] - ID của nhân viên
             */
            Scanner readRelative = new Scanner(relativeFile);
            while (readRelative.hasNextLine()) {
                String[] tokens = readRelative.nextLine().split(", ");
                String name = tokens[0];
                String gender = tokens[1];
                Date dob = SIMPLEDATEFORMAT.parse(tokens[2]);
                String relationship = tokens[3];
                String insNumber = tokens[4];
                String id = tokens[5];
                Employee employee = employeeManager.searchById(id);
                Relative relative = new Relative(name, gender, dob, relationship);
                provideInsuranceManager.add(new ProvideInsurance(insNumber, relative, employee));
            }
            /**
             * Đọc danh sách dự án từ file ProjectList
             * tokens[0] - Mã dự án
             * tokens[1] - Tên dự án
             * tokens[2] - Ngày bắt đầu
             * tokens[3] - Ngày dự kiến kết thúc
             * tokens[4] - Kinh phí đầu tư
             * tokens[5] - Mã nhân viên làm chủ nhiệm dự án
             * tokens[...] - ID của các nhân viên tham gia dự án
             */
            Scanner readProject = new Scanner(projectFile);
            while (readProject.hasNextLine()) {
                String[] tokens = readProject.nextLine().split(", ");
                String id = tokens[0];
                String name = tokens[1];
                Date startDate = SIMPLEDATEFORMAT.parse(tokens[2]);
                Date endDate = SIMPLEDATEFORMAT.parse(tokens[3]);
                double cost = Double.parseDouble(tokens[4]);
                Employee manager = employeeManager.searchById(tokens[5]);
                Project project = new Project(id, name, startDate, endDate, cost, manager);
                joinProjectManger.add(new JoinProject(project, manager));
                for (int i = 6; i < tokens.length; i++) {
                    Employee employee = employeeManager.searchById(tokens[i]);
                    joinProjectManger.add(new JoinProject(project, employee));
                }
                projectManager.add(project);
            }
            readProject.close();
            readRelative.close();
            readEmployee.close();
            readDepartment.close();
        } catch (FileNotFoundException | ParseException | AmountException e) {
            System.err.println("\n** ĐỌC FILE KHÔNG THÀNH CÔNG **");
            e.printStackTrace();
        }
    }

    /**
     * <p>Lưu thông tin hệ thống ra file</p>
     * <p>Các file có dạng:</p>
     * <p><strong>Phòng ban</strong>: Tên phòng ban, [ID các nhân viên thuộc phòng ban]</p>
     * <p><strong>Nhân viên</strong>: ID, Tên, Giới tính, Ngày sinh, Email</p>
     * <p><strong>Nhân thân</strong>: Tên, Giới tính, Ngày sinh, Mối quan hệ, Số bảo hiểm, ID nhân viên</p>
     * <p><strong>Dự án</strong>: ID, Tên, Ngày bắt đầu, Ngày dự kiến kết thúc, Kinh phí đầu tư, [ID các nhân viên tham gia dự
     * án]</p>
     */
    public static void writeFile() {
        try {
            /**
             * Ghi danh sách phòng ban ra file DepartmentList
             */
            PrintWriter writeDepartment = new PrintWriter(departmentFile);
            departmentManager.getDepartmentList().forEach(department -> {
                StringBuilder departmentInfo = new StringBuilder();
                departmentInfo.append(department.getDepartmentName());
                int index = 0;
                if (department.getManager() != null) {
                    departmentInfo.append(String.format(", %s, %s", department.getManager().getId(),
                        SIMPLEDATEFORMAT.format(department.getDateTakeOffice())));
                    index = 1;
                }
                for (int i = index; i < department.getEmployeeList().size(); i++) {
                    departmentInfo.append(", " + department.getEmployeeList().get(i).getId());
                }
                writeDepartment.println(departmentInfo);
            });
            /**
             * Ghi danh sách nhân viên ra file EmployeeList
             */
            PrintWriter writeEmployee = new PrintWriter(employeeFile);
            employeeManager.getEmployeeList().forEach(employee -> {
                StringBuilder employeeInfo = new StringBuilder();
                employeeInfo.append(
                    String.format("%s, %s, %s, %s, %s", employee.getId(), employee.getName(), employee.getGender(),
                        SIMPLEDATEFORMAT.format(employee.getDob()), employee.getEmail()));
                switch (employee.getId().substring(0, 1)) {
                    case "P" -> employeeInfo.append(", " + ((Programmer) employee).getSalaryOT());
                    case "D" -> employeeInfo.append(", " + ((Designer) employee).getBonus());
                    case "T" -> employeeInfo.append(", " + ((Tester) employee).getErrors());
                }
                writeEmployee.println(employeeInfo);
            });
            /**
             * Ghi danh sách nhân thân ra file RelativeList
             */
            PrintWriter writeRelative = new PrintWriter(relativeFile);
            provideInsuranceManager.getRelativeList().forEach(provideInsurance -> {
                Relative relative = provideInsurance.getRelative();
                writeRelative.printf("%s, %s, %s, %s, %s, %s\n", relative.getName(), relative.getGender(),
                    SIMPLEDATEFORMAT.format(relative.getDob()), relative.getRelationship(), provideInsurance.getInsNumber(),
                    provideInsurance.getEmployee().getId());
            });
            /**
             * Ghi danh sách dự án ra file ProjectList
             */
            PrintWriter writeProject = new PrintWriter(projectFile);
            projectManager.getProjectList().forEach(project -> {
                StringBuilder projectInfo = new StringBuilder();
                projectInfo.append(String.format("%s, %s, %s, %s, %f", project.getProjectId(), project.getProjectName(),
                    SIMPLEDATEFORMAT.format(project.getStartDate()), SIMPLEDATEFORMAT.format(project.getEndDate()),
                    project.getCost()));
                joinProjectManger.getList(project).forEach(employee -> projectInfo.append(", " + employee.getId()));
                writeProject.println(projectInfo);
            });
            writeProject.close();
            writeRelative.close();
            writeEmployee.close();
            writeDepartment.close();
        } catch (FileNotFoundException e) {
            System.err.println("\n** GHI FILE KHÔNG THÀNH CÔNG **");
            e.printStackTrace();
        }
    }
}