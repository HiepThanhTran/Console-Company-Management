package topic2.ui;

import static topic2.ui.Factory.SCANNER;
import static topic2.ui.Factory.SIMPLEDATEFORMAT;
import static topic2.ui.Factory.departmentManager;
import static topic2.ui.Factory.employeeManager;
import static topic2.ui.Factory.joinDepartmentManager;
import static topic2.ui.Factory.joinProjectManger;
import static topic2.ui.Factory.projectManager;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import topic2.behavior.JoinDepartment;
import topic2.behavior.JoinProject;
import topic2.behavior.ProvideInsurance;
import topic2.color.Color;
import topic2.entity.Department;
import topic2.entity.Designer;
import topic2.entity.Employee;
import topic2.entity.Manager;
import topic2.entity.Normal;
import topic2.entity.Person;
import topic2.entity.Programmer;
import topic2.entity.Project;
import topic2.entity.Relative;
import topic2.entity.Tester;
import topic2.exception.AmountException;
import topic2.exception.BirthDayException;
import topic2.exception.EmailException;
import topic2.exception.FullNameException;
import topic2.service.ValidatorService;

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
        System.out.println("1. Thêm dự án");
        System.out.println("2. Sửa dự án");
        System.out.println("3. Xóa dự án");
        System.out.println("4. Thêm nhân viên vào dự án");
        System.out.println("5. Tìm kiếm dự án theo tên");
        System.out.println("6. Tìm kiếm dự án theo ngày bắt đầu");
        System.out.println("7. xem danh sách tất cả dự án");
        System.out.println("8. Xem danh sách nhân viên của dự án");
        System.out.println("9. Sắp xếp dự án theo kinh phí đầu tư");
        System.out.println("10. Hoàn tất");
        System.out.print("- Chọn chức năng: ");
        String choice = SCANNER.nextLine();
        switch (choice) {
            case "1" -> {
                try {
                    Project project = newProject();
                    projectManager.add(project);
                    System.out.println("\n== Thêm dự án thành công ==");
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
                    System.out.println("\n== Cập nhật dự án thành công ==");
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIProjectManager();
                } catch (AmountException e) {
                    System.err.println(e.getMessage());
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
                    System.out.println("\n== Xóa dự án thành công ==");
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIProjectManager();
                }
            }
            case "4" -> {
                System.out.print("- Nhập mã dự án hoặc tên dự án: ");
                String keyword = SCANNER.nextLine();
                System.out.print("- Nhập mã nhân viên cần thêm vào dự án: ");
                String id = SCANNER.nextLine();
                try {
                    Project project = projectManager.search(keyword);
                    Employee employee = employeeManager.searchById(id);
                    joinProjectManger.add(new JoinProject(project, employee));
                    System.out.printf("\n== Thêm nhân viên %s vào dự án %s thành công ==\n", id,
                        project.getProjectName().toUpperCase());
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIProjectManager();
                } catch (AmountException e) {
                    System.err.println(e.getMessage());
                    UIProjectManager();
                }
            }
            case "5" -> {
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
            case "6" -> {
                try {
                    System.out.print("- Nhập ngày bắt đầu của dự án cần tìm: ");
                    Date startDate = SIMPLEDATEFORMAT.parse(SCANNER.nextLine());
                    System.out.printf("\n*** DANH SÁCH DỰ ÁN CÓ NGÀY BẮT ĐẦU %s ***\n", SIMPLEDATEFORMAT.format(startDate));
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
            case "7" -> {
                System.out.println("\n*** DANH SÁCH TẤT CẢ DỰ ÁN ***");
                projectManager.showList();
            }
            case "8" -> {
                System.out.print("- Nhập mã dự án hoặc tên dự án cần xem danh sách nhân viên: ");
                String keyword = SCANNER.nextLine();
                try {
                    Project project = projectManager.search(keyword);
                    System.out.printf("\n*** DANH SÁCH NHÂN VIÊN CỦA DỰ ÁN %s ***\n", project.getProjectName().toUpperCase());
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
            case "9" -> {
                projectManager.sort();
                System.out.println("\n== Sắp xếp thành công. Chọn xem danh sách để kiểm tra ==");
            }
            case "10" -> System.out.printf("%s\n== Hoàn tất quản lý dự án ==\n%s", Color.YELLOW_BRIGHT, Color.RESET);
            default -> System.err.println("\n== CHỨC NĂNG HIỆN CHƯA CÓ ==");
        }
    }

    private Project newProject() throws ParseException, AmountException {
        System.out.print("- Nhập mã nhân viên (Chủ nhiệm dự án): ");
        Employee manager = employeeManager.searchById(SCANNER.nextLine());
        Project project = new Project(manager);
        project.setInfo();
        joinProjectManger.add(new JoinProject(project, manager));
        return project;
    }

    /**
     * <strong>Giao diện hệ thống quản lý nhân viên</strong>
     */
    public void UIEmployeeManager() {
        System.out.println("\n*** HỆ THỐNG QUẢN LÝ NHÂN VIÊN ***");
        System.out.println("1. Thêm nhân viên");
        System.out.println("2. Xóa nhân viên");
        System.out.println("3. Tham gia dự án");
        System.out.println("4. Thăng chức nhân viên");
        System.out.println("5. Tính lương cho các nhân viên");
        System.out.println("6. Xem danh sách tất cả nhân viên");
        System.out.println("7. Xem danh sách dự án của nhân viên");
        System.out.println("8. Tìm kiếm nhân viên theo họ tên");
        System.out.println("9. Tìm kiếm nhân viên theo ngày sinh");
        System.out.println("10. Tìm kiếm nhân viên theo phòng ban");
        System.out.println("11. Tìm kiếm nhân viên theo độ tuổi cụ thể");
        System.out.println("12. Tìm kiếm nhân viên theo khoảng độ tuổi");
        System.out.println("13. Hoàn tất");
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
                    Employee employee = newEmployee(type);
                    employeeManager.add(employee);
                    System.out.println("\n== Thêm nhân viên thành công ==");
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIEmployeeManager();
                } catch (ParseException | NumberFormatException e) {
                    System.err.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    Employee.decreaseEmployeeAmount(1);
                    UIEmployeeManager();
                } catch (FullNameException e) {
                    Employee.decreaseEmployeeAmount(1);
                    System.err.print(e.getMessage());
                    UIEmployeeManager();
                } catch (BirthDayException e) {
                    Employee.decreaseEmployeeAmount(1);
                    System.err.print(e.getMessage());
                    UIEmployeeManager();
                } catch (EmailException e) {
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
                    employeeManager.remove(employee);
                    joinProjectManger.removeAll(employee);
                    joinDepartmentManager.removeAll(employee);
                    employeeManager.getList(employee).forEach(provideInsurance -> employeeManager.remove(provideInsurance));
                    System.out.println("\n== Xóa nhân viên thành công ==");
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
                    System.out.printf("\n== Nhân viên %s tham gia dự án %s thành công ==\n", employee.getId(),
                        project.getProjectName().toUpperCase());
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIEmployeeManager();
                } catch (AmountException e) {
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
                        System.err.println("\n== Nhân viên không thuộc phòng ban này ==");
                    } else if (department.hasManager()) {
                        System.err.println("\n== Phòng ban này đã có quản lý ==");
                    } else {
                        Employee manager = employeeManager.promote(employee);
                        department.removeEmployee(employee);
                        department.addEmployee(manager);
                        joinDepartmentManager.remove(joinDepartmentManager.search(employee));
                        joinDepartmentManager.add(new JoinDepartment(manager, department));
                        System.out.println("\n== Thăng chức thành công ==");
                    }
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIEmployeeManager();
                }
            }
            case "5" -> {
                System.out.println("\n*** TIỀN LƯƠNG CỦA CÁC NHÂN VIÊN ***");
                employeeManager.calculateSalaryOfList();
            }
            case "6" -> {
                System.out.println("\n*** DANH SÁCH TẤT CẢ NHÂN VIÊN ***");
                employeeManager.showList();
            }
            case "7" -> {
                System.out.print("- Nhập mã nhân viên cần xem danh sách dự án: ");
                String id = SCANNER.nextLine();
                try {
                    Employee employee = employeeManager.searchById(id);
                    System.out.printf("\n*** DANH SÁCH DỰ ÁN NHÂN VIÊN %s ĐANG THỰC HIỆN ***\n", employee.getId());
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
                System.out.printf("\n*** DANH SÁCH NHÂN VIÊN CÓ HỌ TÊN %s ***\n", name.toUpperCase());
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
                    System.out.printf("\n*** DANH SÁCH NHÂN VIÊN CÓ NGÀY SINH %s ***\n", SIMPLEDATEFORMAT.format(dob));
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
                    System.out.printf("\n*** DANH SÁCH CÁC NHÂN VIÊN THUỘC PHÒNG BAN %s ***\n", name.toUpperCase());
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
                    System.out.printf("\n*** DANH SÁCH NHÂN VIÊN CÓ TUỔI %d ***\n", age);
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
                    System.out.printf("\n*** DANH SÁCH NHÂN VIÊN CÓ TUỔI TỪ %d ĐẾN %d ***\n", fromAge, toAge);
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
        System.out.print("- Nhập tên phòng ban (Nhân viên sẽ trực thuộc): ");
        Department department = departmentManager.search(SCANNER.nextLine());
        Employee employee;
        switch (type) {
            case "1" -> employee = new Normal();
            case "2" -> employee = new Programmer();
            case "3" -> employee = new Designer();
            case "4" -> employee = new Tester();
            default -> throw new NullPointerException("\n== Không tìm thấy nhân viên ==\n");
        }
        employee.setInfo();
        checkData(employee);
        department.addEmployee(employee);
        joinDepartmentManager.add(new JoinDepartment(employee, department));
        return employee;
    }

    /**
     * <strong>Giao diện hệ thống quản lý phòng ban</strong>
     */
    public void UIDepartmentManager() {
        System.out.println("\n*** HỆ THỐNG QUẢN LÝ PHÒNG BAN ***");
        System.out.println("1. Thêm phòng ban");
        System.out.println("2. Xóa phòng ban");
        System.out.println("3. Thêm nhân viên vào phòng ban");
        System.out.println("4. Hiển thị tất cả phòng ban");
        System.out.println("5. Hiển thị danh sách phòng ban của nhân viên");
        System.out.println("6. Hoàn tất");
        System.out.print("- Chọn chức năng: ");
        String choice = SCANNER.nextLine();
        switch (choice) {
            case "1" -> {
                Department department = newDepartment();
                departmentManager.add(department);
                System.out.println("\n== Thêm phòng ban thành công ==");
            }
            case "2" -> {
                System.out.print("- Nhập tên phòng ban cần xóa: ");
                String name = SCANNER.nextLine();
                try {
                    Department department = departmentManager.search(name);
                    Department.decreaseDepartmentAmount(1);
                    departmentManager.remove(department);
                    joinDepartmentManager.removeAll(department);
                    System.out.println("\n== Xóa phòng ban thành công ==");
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
                        System.err.println("\n== Phòng ban này đã có quản lý ==");
                    } else if (department.hasEmployee(employee)) {
                        System.err.println("\n== Nhân viên đã tồn tại trong phòng ban này ==");
                    } else {
                        department.addEmployee(employee);
                        joinDepartmentManager.add(new JoinDepartment(employee, department));
                        System.out.println("\n== Thêm nhân viên vào phòng ban thành công ==");
                    }
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIDepartmentManager();
                }
            }
            case "4" -> {
                System.out.println("\n*** DANH SÁCH TẤT CẢ PHÒNG BAN ***");
                Factory.printLine(180, "~");
                departmentManager.showList();
            }
            case "5" -> {
                System.out.print("- Nhập mã nhân viên: ");
                String id = SCANNER.nextLine();
                try {
                    Employee employee = employeeManager.searchById(id);
                    System.out.printf("\n*** DANH SÁCH PHÒNG BAN CỦA NHÂN VIÊN %s ***\n", employee.getId());
                    Factory.printLine(180, "~");
                    joinDepartmentManager.getList(employee).forEach(department -> {
                        department.showInfo();
                        Factory.printLine(180, "~");
                    });
                } catch (NullPointerException e) {
                    System.err.println("\n== Không tìm thấy nhân viên ==");
                    UIDepartmentManager();
                }
            }
            case "6" -> System.out.printf("%s\n== Hoàn tất quản lý phòng ban ==\n%s", Color.YELLOW_BRIGHT, Color.RESET);
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
        System.out.println("1. Cung cấp thông tin thân thân của nhân viên");
        System.out.println("2. Hiển thị danh sách nhân thân của nhân viên");
        System.out.println("3. Hoàn tất");
        System.out.print("- Chọn chức năng: ");
        String choice = SCANNER.nextLine();
        switch (choice) {
            case "1" -> {
                System.out.print("- Nhập mã nhân viên: ");
                String id = SCANNER.nextLine();
                try {
                    Employee employee = employeeManager.searchById(id);
                    System.out.println("\n== Nhập thông tin nhân thân ==");
                    Relative relative = newRelative();
                    employeeManager.add(new ProvideInsurance(relative, employee));
                    System.out.println("\n== Cung cấp thông tin nhân thân thành công ==");
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                    UIRelativeManager();
                } catch (ParseException | EmailException e) {
                    System.err.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    UIRelativeManager();
                } catch (FullNameException e) {
                    System.err.println(e.getMessage());
                    UIRelativeManager();
                } catch (BirthDayException e) {
                    System.err.println(e.getMessage());
                    UIRelativeManager();
                }
            }
            case "2" -> {
                System.out.print("- Nhập mã nhân viên: ");
                String id = SCANNER.nextLine();
                try {
                    Employee employee = employeeManager.searchById(id);
                    System.out.printf("\n*** DANH SÁCH NHÂN THÂN CỦA NHÂN VIÊN %s ***\n", employee.getId());
                    Factory.relativeMenuHeader();
                    employeeManager.getList(employee).forEach(provideInsurance -> {
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