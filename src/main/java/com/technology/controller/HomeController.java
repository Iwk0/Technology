package com.technology.controller;

import com.technology.model.User;
import com.technology.repository.CompanyRepository;
import com.technology.repository.UserRepository;
import com.technology.service.MainService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class);

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
    private CompanyRepository companyRepository;

    @Autowired
    private MainService mainService;

/*    @Value("${date.format}")
    private String dateFormat;*/

    @RequestMapping(value = "/login")
    public String loginPage(@RequestParam(value = "loginError", required = false) String error, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            model.put("error", error);
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/controlPanel")
    public ModelAndView controlPanel(@RequestParam(value = "size", defaultValue = "1", required = false) Integer size) {
        Page<User> users = userRepository.findAll(new PageRequest(size - 1, 1));
        return new ModelAndView("controlPanel", "users", users);
    }

/*    @RequestMapping(value = "/users/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
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
    }*/

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam(value = "name") String name, @RequestParam(value = "file") MultipartFile file){
        if (!file.isEmpty() && !StringUtils.isEmpty(name)) {
            String extension = file.getContentType();

            if (extension.equals("image/jpeg") || extension.equals("image/png")) {
                File convertedFile = new File(System.getProperty("user.home") + "\\Desktop\\" + name + "." + extensions.get(extension));

                try {
                    file.transferTo(convertedFile);
                } catch (IOException e) {
                    logger.error("IOException", e);
                    return "FAILED";
                }

                logger.info("Successfully file uploaded");
                return "SUCCESS";
            } else {
                return "FORMAT";
            }
        } else {
            return "FAILED";
        }
    }

    @RequestMapping(value = "/")
    public String homePage(HttpServletRequest request, ModelMap model) {
/*        Company company = companyRepository.findOne(1L);
        company.getBranches().remove(0);
        companyRepository.save(company);*/

        if (request.isUserInRole("ADMIN")) {
            model.put("users", userRepository.findAll());
        } else if (request.isUserInRole("USER")) {
            model.put("users", userRepository.findByUsernameLike("a%"));
        }

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request1 = new HttpGet(
                "http://affiliate.finzoom.ro/default.aspx?u_=demo&c_=ro-RO&id_=ml-sr-xml&SP_LoanPurpose=1&SP_Currency=RON&SP_LoanAmount=40000&SP_PropertyValue=140000&SP_LoanTerm=180&SP_MLLoanType=1");

        try {
            HttpResponse response = client.execute(request1);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(response.getEntity().getContent());
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("row");

            final int NODE_LIST_LENGTH = nodeList.getLength();
            for (int i = 0; i < NODE_LIST_LENGTH; i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList tempList = ((Element) node).getElementsByTagName("val");

                    final int LENGTH = tempList.getLength();
                    for (int j = 0; j < LENGTH; j++) {
                        Element element = (Element) tempList.item(j);

                        System.out.println("Attribute: " + element.getAttribute("col"));
                        System.out.println("Text: " + element.getTextContent());

                        //TODO проверка кой таг е
                    }

                    System.out.println("\n\n");
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            logger.error("Exception", e);
        }

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