package com.technology.controller;

import com.technology.model.Department;
import com.technology.model.Employee;
import com.technology.model.User;
import com.technology.model.json.Rows;
import com.technology.model.json.TableSettings;
import com.technology.repository.DepartmentRepository;
import com.technology.repository.EmployeeRepository;
import com.technology.repository.FileRepository;
import com.technology.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Controller
public class HomeController {

    //private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private static final Map<String, String> extensions;

    static {
        Map<String, String> aMap = new HashMap<>();
        aMap.put("image/jpeg", "jpg");
        aMap.put("image/png", "png");
        extensions = Collections.unmodifiableMap(aMap);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

/*    @Value("${date.format}")
    private String dateFormat;*/

    @RequestMapping(value = "/login")
    public String loginPage(@RequestParam(value = "loginError", required = false) String error, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("test");
        if (authentication instanceof AnonymousAuthenticationToken) {
            model.put("error", error);
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/testt/{id}", method = RequestMethod.GET)
    public String getTest(@PathVariable(value = "id") String id, ModelMap model) {
        model.put("users", userRepository.findAll());
        model.put("user", userRepository.findOne(Long.valueOf(id)));
        model.put("files", fileRepository.findAll());
        model.put("id", "5");

        return "test";
    }

    @RequestMapping(value = "/testt", method = RequestMethod.POST)
    public String getTestPost(@ModelAttribute("user") User user, ModelMap model) {
        model.put("users", userRepository.findAll());
        model.put("user", user);
        return "redirect:/testt";
    }

    @RequestMapping(value = "/controlPanel")
    public String controlPanel(ModelMap model) {
        model.put("users", userRepository.findAll());
        return "controlPanel";
    }

    @RequestMapping(value = "/users/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    TableSettings getListOfUsers() {
        List<User> users = userRepository.findAll();
        TableSettings tableSettings = new TableSettings("1", 1, String.valueOf(users.size()));

        users.forEach(user -> {
            Rows row = new Rows();
            row.setId(String.valueOf(user.getId()));
            row.setCell(Arrays.asList(user.getUsername(), user.getPassword(), String.valueOf(user.getStatus())));
            tableSettings.addRow(row);
        });

        return tableSettings;
    }

    @RequestMapping(value = "/")
    public String homePage(HttpServletRequest request, ModelMap model) {
        if (request.isUserInRole("ADMIN")) {
            model.put("users", userRepository.findAll());
        } else if (request.isUserInRole("USER")) {
            model.put("users", userRepository.findByUsernameLike("a%"));
        }

        Department department = departmentRepository.findOne(1L);
        Employee employee = employeeRepository.findOne(1L);

        return "index";
    }

/*    @RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    String getListOfUsers() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(userRepository.findAll());
    }*/

    /*@RequestMapping(value = "/description", method = RequestMethod.POST)
    public @ResponseBody
    StringBuilder getDescription(@Valid @RequestBody User stats, BindingResult result) {
        StringBuilder sb = new StringBuilder();
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                logger.info(error.getField());
                sb.append(error.getField()).append(" ");
            }
            logger.error("ERROR");
        }
        return sb;
    }*/
}