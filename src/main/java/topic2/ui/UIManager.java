package topic2.ui;

import static topic2.ui.Factory.MIN_EMPLOYEE;
import static topic2.ui.Factory.SCANNER;
import static topic2.ui.Factory.SIMPLEDATEFORMAT;
import static topic2.ui.Factory.departmentFile;
import static topic2.ui.Factory.employeeFile;
import static topic2.ui.Factory.projectFile;
import static topic2.ui.Factory.relativeFile;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import topic2.behavior.JoinDepartment;
import topic2.behavior.JoinProject;
import topic2.behavior.ProvideInsurance;
import topic2.entity.other.Department;
import topic2.entity.other.Project;
import topic2.entity.people.Designer;
import topic2.entity.people.Employee;
import topic2.entity.people.Manager;
import topic2.entity.people.Normal;
import topic2.entity.people.Person;
import topic2.entity.people.Programmer;
import topic2.entity.people.Relative;
import topic2.entity.people.Tester;
import topic2.exception.AmountException;
import topic2.exception.BirthDayException;
import topic2.exception.EmailException;
import topic2.exception.FullNameException;
import topic2.exception.GenderException;
import topic2.service.DepartmentManager;
import topic2.service.EmployeeManager;
import topic2.service.ProjectManager;
import topic2.service.ValidatorService;

public class UIManager {

    private static UIManager INSTANCE;
    private final DepartmentManager departmentManager = new DepartmentManager();
    private final EmployeeManager employeeManager = new EmployeeManager();
    private final ProjectManager projectManager = new ProjectManager();

    private UIManager() {
        this.readFile();
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
     * @throws GenderException   Ngoại lệ Giới tính sai định dạng
     */
    private void checkData(Person person)
        throws FullNameException, BirthDayException, EmailException, GenderException {
        ValidatorService.checkFullName(person.getName());
        ValidatorService.checkGender(person.getGender());
        ValidatorService.checkBirthDay(person.getDob());
        if (person instanceof Employee employee) {
            ValidatorService.checkEmail(employee.getEmail());
        }
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
                    departmentManager.removeAll(department);
                    System.out.println("\n== Xóa phòng ban thành công ==");
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
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
                        System.out.println("\n== Phòng ban này đã có quản lý ==");
                    } else if (department.hasEmployee(employee)) {
                        System.out.println("\n== Nhân viên đã tồn tại trong phòng ban này ==");
                    } else {
                        department.addEmployee(employee);
                        departmentManager.add(new JoinDepartment(employee, department));
                        System.out.println("\n== Thêm nhân viên vào phòng ban thành công ==");
                    }
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    UIDepartmentManager();
                }
            }
            case "4" -> {
                System.out.println("\n*** DANH SÁCH TẤT CẢ PHÒNG BAN ***");
                departmentManager.showList();
            }
            case "5" -> {
                System.out.print("- Nhập mã nhân viên: ");
                String id = SCANNER.nextLine();
                try {
                    Employee employee = employeeManager.searchById(id);
                    System.out.printf("\n*** DANH SÁCH PHÒNG BAN CỦA NHÂN VIÊN %s ***\n", employee.getId());
                    departmentManager.getList(employee).forEach(department -> {
                        Factory.printLine(120, "~");
                        department.showInfo();
                    });
                } catch (NullPointerException e) {
                    System.out.println("\n== Không tìm thấy nhân viên ==");
                    UIDepartmentManager();
                }
            }
            case "6" -> System.out.println("\n== Hoàn tất quản lý phòng ban ==");
            default -> System.out.println("\n== CHỨC NĂNG HIỆN CHƯA CÓ ==");
        }
    }

    private Department newDepartment() {
        Department department = null;
        department.setInfo();
        return department;
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
                    System.out.println(e.getMessage());
                    UIEmployeeManager();
                } catch (ParseException | NumberFormatException e) {
                    System.out.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    Employee.decreaseEmployeeAmount(1);
                    UIEmployeeManager();
                } catch (FullNameException e) {
                    Employee.decreaseEmployeeAmount(1);
                    System.out.print(e.getMessage());
                    UIEmployeeManager();
                } catch (GenderException e) {
                    Employee.decreaseEmployeeAmount(1);
                    System.out.println(e.getMessage());
                    UIEmployeeManager();
                } catch (BirthDayException e) {
                    Employee.decreaseEmployeeAmount(1);
                    System.out.print(e.getMessage());
                    UIEmployeeManager();
                } catch (EmailException e) {
                    Employee.decreaseEmployeeAmount(1);
                    System.out.print(e.getMessage());
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
                    projectManager.removeAll(employee);
                    departmentManager.removeAll(employee);
                    employeeManager.getList(employee).forEach(provideInsurance -> employeeManager.remove(provideInsurance));
                    System.out.println("\n== Xóa nhân viên thành công ==");
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
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
                    projectManager.add(new JoinProject(project, employee));
                    System.out.printf("\n== Nhân viên %s tham gia dự án %s thành công ==\n", employee.getId(),
                        project.getName().toUpperCase());
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    UIEmployeeManager();
                } catch (AmountException e) {
                    System.out.println(e.getMessage());
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
                        System.out.println("\n== Nhân viên không thuộc phòng ban này ==");
                    } else if (department.hasManager()) {
                        System.out.println("\n== Phòng ban này đã có quản lý ==");
                    } else {
                        Employee manager = employeeManager.promote(employee);
                        department.removeEmployee(employee);
                        department.addEmployee(manager);
                        departmentManager.add(new JoinDepartment(manager, department));
                        System.out.println("\n== Thăng chức thành công ==");
                    }
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    UIEmployeeManager();
                }
            }
            case "5" -> {
                System.out.println("\n*** TIỀN LƯƠNG CỦA CÁC NHÂN VIÊN ***");
                employeeManager.calculateSalary();
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
                    projectManager.getList(employee).forEach(project -> {
                        Factory.printLine(120, "~");
                        project.showInfo();
                    });
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    UIEmployeeManager();
                }
            }
            case "8" -> {
                System.out.print("- Nhập họ tên cần tìm kiếm: ");
                String name = SCANNER.nextLine();
                System.out.printf("\n*** DANH SÁCH NHÂN VIÊN CÓ HỌ TÊN %s ***\n", name.toUpperCase());
                employeeManager.search(name).forEach(employee -> {
                    Factory.printLine(120, "~");
                    employee.showInfo();
                });
            }
            case "9" -> {
                System.out.print("- Nhập ngày sinh cần tìm kiếm: ");
                try {
                    Date dob = SIMPLEDATEFORMAT.parse(SCANNER.nextLine());
                    System.out.printf("\n*** DANH SÁCH NHÂN VIÊN CÓ NGÀY SINH %s ***\n", SIMPLEDATEFORMAT.format(dob));
                    employeeManager.search(dob).forEach(employee -> {
                        Factory.printLine(120, "~");
                        employee.showInfo();
                    });
                } catch (ParseException e) {
                    System.out.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    UIEmployeeManager();
                }
            }
            case "10" -> {
                System.out.print("- Nhập tên phòng ban: ");
                String name = SCANNER.nextLine();
                try {
                    Department department = departmentManager.search(name);
                    System.out.printf("\n*** DANH SÁCH CÁC NHÂN VIÊN THUỘC PHÒNG BAN %s ***\n", name.toUpperCase());
                    department.showList();
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    UIEmployeeManager();
                }
            }
            case "11" -> {
                try {
                    System.out.print("- Nhập độ tuổi cần tìm kiếm: ");
                    int age = Integer.parseInt(SCANNER.nextLine());
                    System.out.printf("\n*** DANH SÁCH NHÂN VIÊN CÓ TUỔI %d ***\n", age);
                    employeeManager.search(age).forEach(employee -> {
                        Factory.printLine(120, "~");
                        employee.showInfo();
                    });
                } catch (NumberFormatException e) {
                    System.out.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
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
                    employeeManager.search(fromAge, toAge).forEach(employee -> {
                        Factory.printLine(120, "~");
                        employee.showInfo();
                    });
                } catch (NumberFormatException e) {
                    System.out.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    UIEmployeeManager();
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                    UIEmployeeManager();
                }
            }
            case "13" -> System.out.println("\n== Hoàn tất quản lý nhân viên ==");
            default -> System.out.println("\n== CHỨC NĂNG HIỆN CHƯA CÓ ==");
        }
    }

    private Employee newEmployee(String type)
        throws ParseException, FullNameException, EmailException, BirthDayException, GenderException {
        System.out.print("- Nhập tên phòng ban (Nhân viên sẽ trực thuộc): ");
        Department department = departmentManager.search(SCANNER.nextLine());
        Employee employee = null;
        switch (type) {
            case "1" -> employee = new Normal();
            case "2" -> employee = new Programmer();
            case "3" -> employee = new Designer();
            case "4" -> employee = new Tester();
        }
        employee.setInfo();
        checkData(employee);
        department.addEmployee(employee);
        departmentManager.add(new JoinDepartment(employee, department));
        return employee;
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
                    System.out.println(e.getMessage());
                    UIRelativeManager();
                } catch (ParseException | EmailException e) {
                    System.out.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    UIRelativeManager();
                } catch (FullNameException e) {
                    System.out.println(e.getMessage());
                    UIRelativeManager();
                } catch (GenderException e) {
                    System.out.println(e.getMessage());
                    UIRelativeManager();
                } catch (BirthDayException e) {
                    System.out.println(e.getMessage());
                    UIRelativeManager();
                }
            }
            case "2" -> {
                System.out.print("- Nhập mã nhân viên: ");
                String id = SCANNER.nextLine();
                try {
                    Employee employee = employeeManager.searchById(id);
                    System.out.printf("\n*** DANH SÁCH NHÂN THÂN CỦA NHÂN VIÊN %s ***\n", employee.getId());
                    employeeManager.getList(employee).forEach(provideInsurance -> {
                        Factory.printLine(120, "~");
                        provideInsurance.getRelative().showInfo();
                        System.out.printf("- Mã bảo hiểm xã hội: %s\n", provideInsurance.getInsNumber());
                    });
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    UIRelativeManager();
                }
            }
            case "3" -> System.out.println("\n== Hoàn tất quản lý dự án ==");
            default -> System.out.println("\n== CHỨC NĂNG HIỆN CHƯA CÓ ==");
        }
    }

    private Relative newRelative()
        throws ParseException, FullNameException, EmailException, BirthDayException, GenderException {
        Relative relative = new Relative();
        relative.setInfo();
        checkData(relative);
        return relative;
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
                    System.out.println(e.getMessage());
                    UIProjectManager();
                } catch (ParseException e) {
                    System.out.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
                    Project.decreaseProjectAmount(1);
                    UIProjectManager();
                } catch (AmountException e) {
                    Project.decreaseProjectAmount(1);
                    System.out.println(e.getMessage());
                    UIProjectManager();
                }
            }
            case "2" -> {
                try {
                    System.out.print("- Nhập mã dự án hoặc tên dự án cần sửa: ");
                    Project project = projectManager.search(SCANNER.nextLine());
                    System.out.print("- Nhập mã nhân viên sẽ làm chủ nhiệm mới của dự án: ");
                    Employee employee = employeeManager.searchById(SCANNER.nextLine());
                    if (projectManager.check(project, employee)) {
                        project.setInfo();
                        project.setManager(employee);
                        System.out.println("\n== Cập nhật dự án thành công ==");
                    } else {
                        System.out.println("\n== Nhân viên không thuộc dự án này ==");
                    }
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    UIProjectManager();
                } catch (ParseException e) {
                    System.out.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
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
                    projectManager.removeAll(project);
                    System.out.println("\n== Xóa dự án thành công ==");
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
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
                    projectManager.add(new JoinProject(project, employee));
                    System.out.printf("\n== Thêm nhân viên %s vào dự án %s thành công ==\n", id, project.getName().toUpperCase());
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    UIProjectManager();
                } catch (AmountException e) {
                    System.out.println(e.getMessage());
                    UIProjectManager();
                }
            }
            case "5" -> {
                System.out.print("- Nhập tên dự án cần tìm: ");
                String name = SCANNER.nextLine();
                try {
                    Project project = projectManager.search(name);
                    project.showInfo();
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    UIProjectManager();
                }
            }
            case "6" -> {
                try {
                    System.out.print("- Nhập ngày bắt đầu của dự án cần tìm: ");
                    Date startDate = SIMPLEDATEFORMAT.parse(SCANNER.nextLine());
                    System.out.printf("\n*** DANH SÁCH DỰ ÁN CÓ NGÀY BẮT ĐẦU %s ***\n", SIMPLEDATEFORMAT.format(startDate));
                    projectManager.search(startDate).forEach(project -> {
                        Factory.printLine(120, "~");
                        project.showInfo();
                    });
                } catch (ParseException e) {
                    System.out.println("\n** DỮ LIỆU ĐẦU VÀO KHÔNG HỢP LỆ **");
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
                    System.out.printf("\n*** DANH SÁCH NHÂN VIÊN CỦA DỰ ÁN %s ***\n", project.getName().toUpperCase());
                    projectManager.getList(project).forEach(employee -> {
                        Factory.printLine(120, "~");
                        employee.showInfo();
                    });
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    UIProjectManager();
                }
            }
            case "9" -> {
                projectManager.sort();
                System.out.println("\n== Sắp xếp thành công. Chọn xem danh sách để kiểm tra ==");
            }
            case "10" -> System.out.println("\n== Hoàn tất quản lý dự án ==");
            default -> System.out.println("\n== CHỨC NĂNG HIỆN CHƯA CÓ ==");
        }
    }

    private Project newProject() throws ParseException, AmountException {
        System.out.print("- Nhập mã nhân viên (Chủ nhiệm dự án): ");
        Employee manager = employeeManager.searchById(SCANNER.nextLine());
        Project project = new Project(manager);
        project.setInfo();
        projectManager.add(new JoinProject(project, manager));
        for (int i = 1; i < MIN_EMPLOYEE; i++) {
            addEmployeeToProject(project);
        }
        if (projectManager.getAmount(project) < MIN_EMPLOYEE) {
            projectManager.removeAll(project);
            throw new AmountException("\n== Dự án phải có tối thiểu 5 thành viên ==\n");
        }
        return project;
    }

    private void addEmployeeToProject(Project project) {
        try {
            System.out.print("- Nhập mã nhân viên cần thêm vào dự án: ");
            Employee employee = employeeManager.searchById(SCANNER.nextLine());
            if (!projectManager.check(project, employee)) {
                projectManager.add(new JoinProject(project, employee));
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (AmountException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Đọc thông tin hệ thống từ file
     * <p>Các file có dạng:</p>
     * <p><strong>Nhân viên</strong>: ID, Tên, Giới tính, Ngày sinh, Email</p>
     * <p><strong>Phòng ban</strong>: Tên phòng ban, [ID các nhân viên thuộc phòng ban]</p>
     * <p><strong>Nhân thân</strong>: Tên, Giới tính, Ngày sinh, Mối quan hệ, ID nhân viên</p>
     * <p><strong>Dự án</strong>: ID, Tên, Ngày bắt đầu, Ngày dự kiến kết thúc, Kinh phí đầu tư, [ID các nhân viên tham gia
     * dự án]</p>
     */
    public void readFile() {
        try {
            /**
             * Đọc danh sách nhân viên từ file EmployeeList
             * tokens[0] - ID nhân viên
             * tokens[1] - Tên nhân viên
             * tokens[2] - Giới tính
             * tokens[3] - Ngày sinh
             * tokens[4] - Email
             * tokens[5] - Lương OT (Programmer), Bonus (Designer), Số lỗi tìm được (Tester), hoặc ngày nhậm chức (Manager)
             */
            Scanner readEmployee = new Scanner(employeeFile);
            while (readEmployee.hasNextLine()) {
                String[] tokens = readEmployee.nextLine().split(", ");
                Employee employee = null;
                String id = tokens[0];
                String name = tokens[1];
                String gender = (tokens[2].equals("false")) ? "1" : "0";
                Date dob = SIMPLEDATEFORMAT.parse(tokens[3]);
                String email = tokens[4];
                switch (id.substring(0, 1)) {
                    case "N" -> employee = new Normal(name, gender, dob, id, email);
                    case "P" -> {
                        double salaryOT = Double.parseDouble(tokens[5]);
                        employee = new Programmer(name, gender, dob, id, email, salaryOT);
                    }
                    case "D" -> {
                        double bonus = Double.parseDouble(tokens[5]);
                        employee = new Designer(name, gender, dob, id, email, bonus);
                    }
                    case "T" -> {
                        int errors = Integer.parseInt(tokens[5]);
                        employee = new Tester(name, gender, dob, id, email, errors);
                    }
                    case "M" -> {
                        Date takeOfficeDate = SIMPLEDATEFORMAT.parse(tokens[5]);
                        employee = new Manager(name, gender, dob, id, email, takeOfficeDate);
                    }
                }
                employeeManager.add(employee);
            }
            /**
             * Đọc danh sách phòng ban từ file DepartmentList
             * tokens[0] - Tên phòng ban
             * tokens[...] - ID của các nhân viên trực thuộc phòng ban
             */
            Scanner readDepartment = new Scanner(departmentFile);
            while (readDepartment.hasNextLine()) {
                String[] tokens = readDepartment.nextLine().split(", ");
                Department department = new Department(tokens[0]);
                for (int i = 1; i < tokens.length; i++) {
                    Employee employee = employeeManager.searchById(tokens[i]);
                    if (employee instanceof Manager manager) {
                        manager.addDepartment(department);
                    }
                    department.addEmployee(employee);
                    departmentManager.add(new JoinDepartment(employee, department));
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
                String gender = (tokens[1].equals("false")) ? "1" : "0";
                Date dob = SIMPLEDATEFORMAT.parse(tokens[2]);
                String relationship = tokens[3];
                String insNumber = tokens[4];
                String id = tokens[5];
                Employee employee = employeeManager.searchById(id);
                Relative relative = new Relative(name, gender, dob, relationship);
                ProvideInsurance provideInsurance = new ProvideInsurance(insNumber, relative, employee);
                employeeManager.add(provideInsurance);
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
                projectManager.add(new JoinProject(project, manager));
                for (int i = 6; i < tokens.length; i++) {
                    Employee employee = employeeManager.searchById(tokens[i]);
                    projectManager.add(new JoinProject(project, employee));
                }
                projectManager.add(project);
            }
            readProject.close();
            readRelative.close();
            readEmployee.close();
            readDepartment.close();
        } catch (FileNotFoundException e) {
            System.out.printf("\n** KHÔNG TÌM THẤY FILE %s **\n", e.getMessage());
        } catch (ParseException | AmountException e) {
            throw new RuntimeException();
        }
    }

    /**
     * <p>Lưu thông tin hệ thống ra file</p>
     * <p>Các file có dạng:</p>
     * <p><strong>Phòng ban</strong>: Tên phòng ban, [ID các nhân viên thuộc phòng ban]</p>
     * <p><strong>Nhân viên</strong>: ID, Tên, Giới tính, Ngày sinh, Email</p>
     * <p><strong>Nhân thân</strong>: Tên, Giới tính, Ngày sinh, Mối quan hệ, ID nhân viên</p>
     * <p><strong>Dự án</strong>: ID, Tên, Ngày bắt đầu, Ngày dự kiến kết thúc, Kinh phí đầu tư, [ID các nhân viên tham gia dự
     * án]</p>
     */
    public void writeFile() {
        try {
            /**
             * Ghi danh sách phòng ban ra file DepartmentList
             */
            PrintWriter writeDepartment = new PrintWriter(departmentFile);
            departmentManager.getDepartmentList().forEach(department -> {
                StringBuilder builder = new StringBuilder();
                department.getEmployeeList().forEach(employee -> builder.append(", " + employee.getId()));
                writeDepartment.println(department.getName() + builder);
            });
            /**
             * Ghi danh sách nhân viên ra file EmployeeList
             */
            PrintWriter writeEmployee = new PrintWriter(employeeFile);
            employeeManager.getEmployeeList().forEach(employee -> {
                StringBuilder builder = new StringBuilder();
                boolean gender = !employee.getGender().equals("1");
                builder.append(String.format("%s, %s, %s, %s, %s", employee.getId(), employee.getName(), gender,
                    SIMPLEDATEFORMAT.format(employee.getDob()), employee.getEmail()));
                switch (employee.getId().substring(0, 1)) {
                    case "P" -> builder.append(", " + ((Programmer) employee).getSalaryOT());
                    case "D" -> builder.append(", " + ((Designer) employee).getBonus());
                    case "T" -> builder.append(", " + ((Tester) employee).getErrors());
                    case "M" -> builder.append(", " + SIMPLEDATEFORMAT.format(((Manager) employee).getTakeOfficeDate()));
                }
                writeEmployee.println(builder);
            });
            /**
             * Ghi danh sách nhân thân ra file RelativeList
             */
            PrintWriter writeRelative = new PrintWriter(relativeFile);
            employeeManager.getRelativeList().forEach(provideInsurance -> {
                Relative relative = provideInsurance.getRelative();
                boolean gender = !relative.getGender().equals("1");
                writeRelative.printf("%s, %s, %s, %s, %s, %s\n", relative.getName(), gender,
                    SIMPLEDATEFORMAT.format(relative.getDob()), relative.getRelationship(), provideInsurance.getInsNumber(),
                    provideInsurance.getEmployee().getId());
            });
            /**
             * Ghi danh sách dự án ra file ProjectList
             */
            PrintWriter writeProject = new PrintWriter(projectFile);
            projectManager.getProjectList().forEach(project -> {
                StringBuilder builder = new StringBuilder();
                builder.append(String.format("%s, %s, %s, %s, %f", project.getId(), project.getName(),
                    SIMPLEDATEFORMAT.format(project.getStartDate()), SIMPLEDATEFORMAT.format(project.getEndDate()),
                    project.getCost()));
                projectManager.getList(project).forEach(employee -> builder.append(", " + employee.getId()));
                writeProject.println(builder);
            });
            writeProject.close();
            writeRelative.close();
            writeEmployee.close();
            writeDepartment.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n** KHÔNG TÌM THẤY FILE **");
        } finally {
            System.out.println("\n*** HOÀN TẤT QUÁ TRÌNH LƯU THÔNG TIN HỆ THỐNG VÀO FILE ***");
        }
    }
}