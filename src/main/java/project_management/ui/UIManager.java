package project_management.ui;

import static project_management.ui.Factory.GREGORIANCALENDAR;
import static project_management.ui.Factory.SCANNER;
import static project_management.ui.Factory.SIMPLEDATEFORMAT;
import static project_management.ui.Factory.departmentManager;
import static project_management.ui.Factory.employeeManager;
import static project_management.ui.Factory.joinProjectManger;
import static project_management.ui.Factory.projectManager;
import static project_management.ui.Factory.provideInsuranceManager;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import project_management.behavior.JoinProject;
import project_management.behavior.ProvideInsurance;
import project_management.color.Color;
import project_management.entity.Department;
import project_management.entity.Designer;
import project_management.entity.Employee;
import project_management.entity.Manager;
import project_management.entity.Normal;
import project_management.entity.Person;
import project_management.entity.Programmer;
import project_management.entity.Project;
import project_management.entity.Relative;
import project_management.entity.Tester;
import project_management.exception.AmountException;
import project_management.exception.BirthDayException;
import project_management.exception.EmailException;
import project_management.exception.FullNameException;
import project_management.service.ValidatorService;
import project_management.utils.FileUtils;

public class UIManager {

    private static UIManager INSTANCE;

    private UIManager() {
        FileUtils.readFile();
    }

    public static UIManager getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UIManager();
        }
        return INSTANCE;
    }

    /**
     * Phương thức kiểm tra dữ liệu đầu vào của đối tượng
     *
     * @param person Đối tượng
     * @throws FullNameException Ngoại lệ Họ tên sai định dạng
     * @throws BirthDayException Ngoại lệ Ngày sinh sai định dạng
     * @throws EmailException    Ngoại lệ Email sai định dạng
     */
    private void checkData(Person person) throws FullNameException, BirthDayException, EmailException {
        ValidatorService.checkFullName(person.getName());
        ValidatorService.checkBirthDay(person.getDob());
        if (person instanceof Employee employee) {
            ValidatorService.checkEmail(employee.getEmail());
        }
    }

    /**
     * <strong>Giao diện hệ thống quản lý dự án</strong>
     */
    public void UIProjectManager() {
        System.out.println("\n*** HỆ THỐNG QUẢN LÝ DỰ ÁN ***");
        System.out.printf("%-3s Thêm dự án\n", "1.");
        System.out.printf("%-3s Sửa dự án\n", "2.");
        System.out.printf("%-3s Xóa dự án\n", "3.");
        System.out.printf("%-3s Thêm chủ nhiệm vào dự án\n", "4.");
        System.out.printf("%-3s Thêm nhân viên vào dự án\n", "5.");
        System.out.printf("%-3s Tìm kiếm dự án theo tên\n", "6.");
        System.out.printf("%-3s Tìm kiếm dự án theo ngày bắt đầu\n", "7.");
        System.out.printf("%-3s Xem danh sách tất cả dự án\n", "8.");
        System.out.printf("%-3s Xem danh sách nhân viên của dự án\n", "9.");
        System.out.printf("%-3s Sắp xếp dự án theo kinh phí đầu tư\n", "10.");
        System.out.printf("%-3s Hoàn tất\n", "11.");
        System.out.print("- Chọn chức năng: ");
        String choice = SCANNER.nextLine();
        switch (choice) {
            case "1" -> {
                try {
                    System.out.println("\n\t+----- Nhập thông tin dự án -----+");
                    Project project = newProject();
                    projectManager.add(project);
                    System.out.println("\n\t+----- Thêm dự án thành công -----+");
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIProjectManager();
                } catch (ParseException | NumberFormatException e) {
                    System.err.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    Project.decreaseProjectAmount(1);
                    UIProjectManager();
                } catch (AmountException e) {
                    Project.decreaseProjectAmount(1);
                    System.err.println(e.getMessage());
                    UIProjectManager();
                }
            }
            case "2" -> {
                try {
                    System.out.print("- Nhập mã dự án hoặc tên dự án cần sửa: ");
                    Project project = projectManager.search(SCANNER.nextLine());
                    System.out.print("- Nhập mã nhân viên sẽ làm chủ nhiệm mới của dự án: ");
                    Employee employee = employeeManager.searchById(SCANNER.nextLine());
                    if (!joinProjectManger.check(project, employee)) {
                        joinProjectManger.add(new JoinProject(project, employee));
                    }
                    project.setInfo();
                    project.setManager(employee);
                    System.out.println("\n\t+----- Cập nhật dự án thành công -----+");
                } catch (NullPointerException | AmountException e) {
                    System.err.println(e.getMessage());
                    UIProjectManager();
                } catch (ParseException e) {
                    System.err.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    UIProjectManager();
                }
            }
            case "3" -> {
                System.out.print("- Nhập mã dự án hoặc tên dự án cần xóa: ");
                String keyword = SCANNER.nextLine();
                try {
                    Project project = projectManager.search(keyword);
                    Project.decreaseProjectAmount(1);
                    projectManager.remove(project);
                    joinProjectManger.removeAll(project);
                    System.out.println("\n\t+----- Xóa dự án thành công -----+");
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIProjectManager();
                }
            }
            case "4" -> {
                System.out.print("- Nhập mã dự án hoặc tên dự án: ");
                String keyword = SCANNER.nextLine();
                System.out.print("- Nhập mã nhân viên: ");
                String id = SCANNER.nextLine();
                try {
                    Project project = projectManager.search(keyword);
                    Employee manager = employeeManager.searchById(id);
                    if (project.getManager() == null) {
                        project.setManager(manager);
                    } else {
                        System.out.println("\n\t+----- Dự án đã có chủ nhiệm -----+");
                    }
                    joinProjectManger.add(new JoinProject(project, manager));
                } catch (NullPointerException | AmountException e) {
                    System.err.println(e.getMessage());
                    UIProjectManager();
                }
            }
            case "5" -> {
                System.out.print("- Nhập mã dự án hoặc tên dự án: ");
                String keyword = SCANNER.nextLine();
                System.out.print("- Nhập mã nhân viên: ");
                String id = SCANNER.nextLine();
                try {
                    Project project = projectManager.search(keyword);
                    Employee employee = employeeManager.searchById(id);
                    joinProjectManger.add(new JoinProject(project, employee));
                    System.out.printf("\n\t+----- Thêm nhân viên %s vào dự án %s thành công -----+\n", id,
                        project.getProjectName().toUpperCase());
                } catch (NullPointerException | AmountException e) {
                    System.err.println(e.getMessage());
                    UIProjectManager();
                }
            }
            case "6" -> {
                System.out.print("- Nhập tên dự án cần tìm: ");
                String name = SCANNER.nextLine();
                try {
                    Project project = projectManager.search(name);
                    Factory.projectMenuHeader();
                    System.out.println(project);
                    Factory.printLine(157, "-");
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIProjectManager();
                }
            }
            case "7" -> {
                try {
                    System.out.print("- Nhập ngày bắt đầu của dự án cần tìm: ");
                    Date startDate = SIMPLEDATEFORMAT.parse(SCANNER.nextLine());
                    System.out.printf("\n\t+----- DANH SÁCH DỰ ÁN CÓ NGÀY BẮT ĐẦU %s -----+\n",
                        SIMPLEDATEFORMAT.format(startDate));
                    Factory.projectMenuHeader();
                    projectManager.search(startDate).forEach(project -> {
                        System.out.println(project);
                        Factory.printLine(157, "-");
                    });
                } catch (ParseException e) {
                    System.err.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    UIProjectManager();
                }
            }
            case "8" -> {
                System.out.println("\n\t+----- DANH SÁCH TẤT CẢ DỰ ÁN -----+");
                projectManager.showList();
            }
            case "9" -> {
                System.out.print("- Nhập mã dự án hoặc tên dự án cần xem danh sách nhân viên: ");
                String keyword = SCANNER.nextLine();
                try {
                    Project project = projectManager.search(keyword);
                    System.out.printf("\n\t+----- DANH SÁCH NHÂN VIÊN CỦA DỰ ÁN %s -----+\n",
                        project.getProjectName().toUpperCase());
                    Factory.employeeMenuHeader();
                    joinProjectManger.getList(project).forEach(employee -> {
                        System.out.println(employee);
                        Factory.printLine(140, "-");
                    });
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIProjectManager();
                }
            }
            case "10" -> {
                projectManager.sort();
                System.out.println("\n\t+----- Sắp xếp thành công. Chọn xem danh sách để kiểm tra -----+");
            }
            case "11" -> System.out.printf("%s\n== Hoàn tất quản lý dự án ==\n%s", Color.YELLOW_BRIGHT, Color.RESET);
            default -> System.err.println("\n== CHỨC NĂNG HIỆN CHƯA CÓ ==");
        }
    }

    private Project newProject() throws ParseException, AmountException {
        Project project = new Project();
        project.setInfo();
        return project;
    }

    /**
     * <strong>Giao diện hệ thống quản lý nhân viên</strong>
     */
    public void UIEmployeeManager() {
        System.out.println("\n*** HỆ THỐNG QUẢN LÝ NHÂN VIÊN ***");
        System.out.printf("%-3s Thêm nhân viên\n", "1.");
        System.out.printf("%-3s Xóa nhân viên\n", "2.");
        System.out.printf("%-3s Tham gia dự án\n", "3.");
        System.out.printf("%-3s Thăng chức nhân viên\n", "4.");
        System.out.printf("%-3s Tính lương cho các nhân viên\n", "5.");
        System.out.printf("%-3s Xem danh sách tất cả nhân viên\n", "6.");
        System.out.printf("%-3s Xem danh sách dự án của nhân viên\n", "7.");
        System.out.printf("%-3s Tìm kiếm nhân viên theo họ tên\n", "8.");
        System.out.printf("%-3s Tìm kiếm nhân viên theo ngày sinh\n", "9.");
        System.out.printf("%-3s Tìm kiếm nhân viên theo phòng ban\n", "10.");
        System.out.printf("%-3s Tìm kiếm nhân viên theo độ tuổi cụ thể\n", "11.");
        System.out.printf("%-3s Tìm kiếm nhân viên theo khoảng độ tuổi\n", "12.");
        System.out.printf("%-3s Hoàn tất\n", "13.");
        System.out.print("- Chọn chức năng: ");
        String choice = SCANNER.nextLine();
        switch (choice) {
            case "1" -> {
                System.out.println("\n1.. Nhân viên bình thường (N-xxxxx)");
                System.out.println("\n2.. Lập trình viên (P-xxxxx)");
                System.out.println("\n3.. Thiết kế viên (D-xxxxx)");
                System.out.println("\n4.. Kiểm thứ viên (T-xxxxx)");
                System.out.print("\n- Chọn loại nhân viên cần thêm: ");
                String type = SCANNER.nextLine();
                try {
                    System.out.println("\n\t+----- Nhập thông tin nhân viên -----+");
                    Employee employee = newEmployee(type);
                    employeeManager.add(employee);
                    System.out.println("\n\t+----- Thêm nhân viên thành công -----+");
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIEmployeeManager();
                } catch (ParseException | NumberFormatException e) {
                    System.err.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    Employee.decreaseEmployeeAmount(1);
                    UIEmployeeManager();
                } catch (FullNameException | EmailException | BirthDayException e) {
                    Employee.decreaseEmployeeAmount(1);
                    System.err.print(e.getMessage());
                    UIEmployeeManager();
                }
            }
            case "2" -> {
                System.out.print("- Nhập mã nhân viên cần xóa: ");
                String id = SCANNER.nextLine();
                try {
                    Employee employee = employeeManager.searchById(id);
                    Employee.decreaseEmployeeAmount(1);
                    Department department = departmentManager.search(employee);
                    department.removeEmployee(employee);
                    if (employee instanceof Manager manager) {
                        manager.getDepartmentList().forEach(department1 -> {
                            department1.setDateTakeOffice(null);
                            department1.setManager(null);
                        });
                    }
                    employeeManager.remove(employee);
                    joinProjectManger.removeAll(employee);
                    provideInsuranceManager.getList(employee).forEach(provideInsuranceManager::remove);
                    System.out.println("\n\t+----- Xóa nhân viên thành công -----+");
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIEmployeeManager();
                }
            }
            case "3" -> {
                System.out.print("- Nhập mã dự án hoặc tên dự án nhân viên muốn tham gia: ");
                String keyword = SCANNER.nextLine();
                System.out.print("- Nhập mã nhân viên: ");
                String id = SCANNER.nextLine();
                try {
                    Project project = projectManager.search(keyword);
                    Employee employee = employeeManager.searchById(id);
                    joinProjectManger.add(new JoinProject(project, employee));
                    System.out.printf("\n\t+----- Nhân viên %s tham gia dự án %s thành công -----+\n", employee.getId(),
                        project.getProjectName().toUpperCase());
                } catch (NullPointerException | AmountException e) {
                    System.err.println(e.getMessage());
                    UIEmployeeManager();
                }
            }
            case "4" -> {
                System.out.print("- Nhập mã nhân viên muốn thăng chức: ");
                String id = SCANNER.nextLine();
                System.out.print("- Nhập tên phòng ban: ");
                String name = SCANNER.nextLine();
                try {
                    Employee employee = employeeManager.searchById(id);
                    Department department = departmentManager.search(name);
                    if (!department.hasEmployee(employee)) {
                        System.err.println("\n\t+----- Nhân viên không thuộc phòng ban này -----+");
                    } else if (department.hasManager()) {
                        System.err.println("\n\t+----- Phòng ban này đã có quản lý -----+");
                    } else {
                        Employee manager = employeeManager.promoted(employee);
                        ((Manager) manager).addDepartment(department);
                        department.removeEmployee(employee);
                        department.addEmployee(manager);
                        department.setManager(manager);
                        department.setDateTakeOffice(GREGORIANCALENDAR.getTime());
                        System.out.println("\n\t+----- Thăng chức thành công -----+");
                    }
                } catch (NullPointerException | AmountException e) {
                    System.err.println(e.getMessage());
                    UIEmployeeManager();
                }
            }
            case "5" -> {
                System.out.println("\n\t+----- TIỀN LƯƠNG CỦA CÁC NHÂN VIÊN -----+");
                employeeManager.calculateSalaryOfList();
            }
            case "6" -> {
                System.out.println("\n\t+----- DANH SÁCH TẤT CẢ NHÂN VIÊN -----+");
                employeeManager.showList();
            }
            case "7" -> {
                System.out.print("- Nhập mã nhân viên cần xem danh sách dự án: ");
                String id = SCANNER.nextLine();
                try {
                    Employee employee = employeeManager.searchById(id);
                    System.out.printf("\n\t+----- DANH SÁCH DỰ ÁN NHÂN VIÊN %s ĐANG THỰC HIỆN -----+\n", employee.getId());
                    Factory.projectMenuHeader();
                    joinProjectManger.getList(employee).forEach(project -> {
                        System.out.println(project);
                        Factory.printLine(157, "-");
                    });
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIEmployeeManager();
                }
            }
            case "8" -> {
                System.out.print("- Nhập họ tên cần tìm kiếm: ");
                String name = SCANNER.nextLine();
                System.out.printf("\n\t+----- DANH SÁCH NHÂN VIÊN CÓ HỌ TÊN %s -----+\n", name.toUpperCase());
                Factory.employeeMenuHeader();
                employeeManager.search(name).forEach(employee -> {
                    System.out.println(employee);
                    Factory.printLine(140, "-");
                });
            }
            case "9" -> {
                System.out.print("- Nhập ngày sinh cần tìm kiếm: ");
                try {
                    Date dob = SIMPLEDATEFORMAT.parse(SCANNER.nextLine());
                    System.out.printf("\n\t+----- DANH SÁCH NHÂN VIÊN CÓ NGÀY SINH %s -----+\n", SIMPLEDATEFORMAT.format(dob));
                    Factory.employeeMenuHeader();
                    employeeManager.search(dob).forEach(employee -> {
                        System.out.println(employee);
                        Factory.printLine(140, "-");
                    });
                } catch (ParseException e) {
                    System.err.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    UIEmployeeManager();
                }
            }
            case "10" -> {
                System.out.print("- Nhập tên phòng ban: ");
                String name = SCANNER.nextLine();
                try {
                    Department department = departmentManager.search(name);
                    System.out.printf("\n\t+----- DANH SÁCH CÁC NHÂN VIÊN THUỘC PHÒNG BAN %s -----+\n", name.toUpperCase());
                    department.showEmployeeList();
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIEmployeeManager();
                }
            }
            case "11" -> {
                try {
                    System.out.print("- Nhập độ tuổi cần tìm kiếm: ");
                    int age = Integer.parseInt(SCANNER.nextLine());
                    System.out.printf("\n\t+----- DANH SÁCH NHÂN VIÊN CÓ TUỔI %d -----+\n", age);
                    Factory.employeeMenuHeader();
                    employeeManager.search(age).forEach(employee -> {
                        System.out.println(employee);
                        Factory.printLine(140, "-");
                    });
                } catch (NumberFormatException e) {
                    System.err.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    UIEmployeeManager();
                }
            }
            case "12" -> {
                try {
                    System.out.print("- Nhập độ tuổi thứ 1: ");
                    int fromAge = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("- Nhập độ tuổi thứ 2: ");
                    int toAge = Integer.parseInt(SCANNER.nextLine());
                    System.out.printf("\n\t+----- DANH SÁCH NHÂN VIÊN CÓ TUỔI TỪ %d ĐẾN %d -----+\n", fromAge, toAge);
                    Factory.employeeMenuHeader();
                    employeeManager.search(fromAge, toAge).forEach(employee -> {
                        System.out.println(employee);
                        Factory.printLine(140, "-");
                    });
                } catch (NumberFormatException e) {
                    System.err.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    UIEmployeeManager();
                } catch (InputMismatchException e) {
                    System.err.println(e.getMessage());
                    UIEmployeeManager();
                }
            }
            case "13" -> System.out.printf("%s\n== Hoàn tất quản lý nhân viên ==\n%s", Color.YELLOW_BRIGHT, Color.RESET);
            default -> System.err.println("\n== CHỨC NĂNG HIỆN CHƯA CÓ ==");
        }
    }

    private Employee newEmployee(String type) throws ParseException, FullNameException, EmailException, BirthDayException {
        Employee employee;
        switch (type) {
            case "1" -> employee = new Normal();
            case "2" -> employee = new Programmer();
            case "3" -> employee = new Designer();
            case "4" -> employee = new Tester();
            default -> throw new NullPointerException("\n\t+----- Không tìm thấy nhân viên -----+\n");
        }
        employee.setInfo();
        checkData(employee);
        return employee;
    }

    /**
     * <strong>Giao diện hệ thống quản lý phòng ban</strong>
     */
    public void UIDepartmentManager() {
        System.out.println("\n*** HỆ THỐNG QUẢN LÝ PHÒNG BAN ***");
        System.out.printf("%-3s Thêm phòng ban\n", "1.");
        System.out.printf("%-3s Xóa phòng ban\n", "2.");
        System.out.printf("%-3s Thêm nhân viên vào phòng ban\n", "3.");
        System.out.printf("%-3s Hiển thị tất cả phòng ban\n", "4.");
        System.out.printf("%-3s Hoàn tất\n", "5.");
        System.out.print("- Chọn chức năng: ");
        String choice = SCANNER.nextLine();
        switch (choice) {
            case "1" -> {
                System.out.println("\n\t+----- Nhập thông tin phòng ban -----+");
                Department department = newDepartment();
                departmentManager.add(department);
                System.out.println("\n\t+----- Thêm phòng ban thành công -----+");
            }
            case "2" -> {
                System.out.print("- Nhập tên phòng ban cần xóa: ");
                String name = SCANNER.nextLine();
                try {
                    Department department = departmentManager.search(name);
                    Department.decreaseDepartmentAmount(1);
                    departmentManager.remove(department);
                    System.out.println("\n\t+----- Xóa phòng ban thành công -----+");
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIDepartmentManager();
                }
            }
            case "3" -> {
                System.out.print("- Nhập tên phòng ban cần thêm nhân viên: ");
                String name = SCANNER.nextLine();
                System.out.print("- Nhập mã nhân viên muốn thêm: ");
                String id = SCANNER.nextLine();
                try {
                    Department department = departmentManager.search(name);
                    Employee employee = employeeManager.searchById(id);
                    if (employee instanceof Manager && department.hasManager()) {
                        System.err.println("\n\t+----- Phòng ban này đã có quản lý -----+");
                    } else if (department.hasEmployee(employee)) {
                        System.err.println("\n\t+----- Nhân viên đã tồn tại trong phòng ban này -----+");
                    } else {
                        if (employee instanceof Manager manager) {
                            manager.addDepartment(department);
                            department.setManager(manager);
                            department.setDateTakeOffice(GREGORIANCALENDAR.getTime());
                        }
                        department.addEmployee(employee);
                        System.out.println("\n\t+----- Thêm nhân viên vào phòng ban thành công -----+");
                    }
                } catch (NullPointerException | AmountException e) {
                    System.err.println(e.getMessage());
                    UIDepartmentManager();
                }
            }
            case "4" -> {
                System.out.println("\n\t+----- DANH SÁCH TẤT CẢ PHÒNG BAN -----+");
                Factory.printLine(180, "~");
                departmentManager.showList();
            }
            case "5" -> System.out.printf("%s\n== Hoàn tất quản lý phòng ban ==\n%s", Color.YELLOW_BRIGHT, Color.RESET);
            default -> System.err.println("\n== CHỨC NĂNG HIỆN CHƯA CÓ ==");
        }
    }

    private Department newDepartment() {
        Department department = new Department();
        department.setInfo();
        return department;
    }

    /**
     * <strong>Giao diện hệ thống quản lý nhân thân của nhân viên</strong>
     */
    public void UIRelativeManager() {
        System.out.println("\n*** HỆ THỐNG QUẢN LÝ NHÂN THÂN ***");
        System.out.printf("%-3s Cung cấp thông tin thân thân của nhân viên\n", "1.");
        System.out.printf("%-3s Hiển thị danh sách nhân thân của nhân viên\n", "2.");
        System.out.printf("%-3s Hoàn tất\n", "3.");
        System.out.print("- Chọn chức năng: ");
        String choice = SCANNER.nextLine();
        switch (choice) {
            case "1" -> {
                System.out.print("- Nhập mã nhân viên: ");
                String id = SCANNER.nextLine();
                try {
                    Employee employee = employeeManager.searchById(id);
                    System.out.println("\n\t+----- Nhập thông tin nhân thân -----+");
                    Relative relative = newRelative();
                    provideInsuranceManager.add(new ProvideInsurance(relative, employee));
                    System.out.println("\n\t+----- Cung cấp thông tin nhân thân thành công -----+");
                } catch (NullPointerException | FullNameException | BirthDayException e) {
                    System.err.println(e.getMessage());
                    UIRelativeManager();
                } catch (ParseException | EmailException e) {
                    System.err.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    UIRelativeManager();
                }
            }
            case "2" -> {
                System.out.print("- Nhập mã nhân viên: ");
                String id = SCANNER.nextLine();
                try {
                    Employee employee = employeeManager.searchById(id);
                    System.out.printf("\n\t+----- DANH SÁCH NHÂN THÂN CỦA NHÂN VIÊN %s -----+\n", employee.getId());
                    Factory.relativeMenuHeader();
                    provideInsuranceManager.getList(employee).forEach(provideInsurance -> {
                        System.out.print(provideInsurance.getRelative());
                        System.out.printf(" %s |\n", provideInsurance.getInsNumber());
                        Factory.printLine(112, "-");
                    });
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIRelativeManager();
                }
            }
            case "3" -> System.out.printf("%s\n== Hoàn tất quản lý nhân thân ==\n%s", Color.YELLOW_BRIGHT, Color.RESET);
            default -> System.err.println("\n== CHỨC NĂNG HIỆN CHƯA CÓ ==");
        }
    }

    private Relative newRelative() throws ParseException, FullNameException, EmailException, BirthDayException {
        Relative relative = new Relative();
        relative.setInfo();
        checkData(relative);
        return relative;
    }
}