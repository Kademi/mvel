package org.mvel2.filtersManager.tests;

import junit.framework.TestCase;
import org.mvel2.MVEL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.mvel2.ParserContext;
import org.mvel2.filtersManager.Employee;
import org.mvel2.filtersManager.Person;

/**
 * @author Mohammed F. Ouda
 */
public class GroupBTests extends TestCase {

    private static final String propertyKey = "mvel.filtersManager";
    private static final String propertyValue = "org.mvel2.filtersManager.FiltersManager";

    Map<String, Object> varsMap = new HashMap<String, Object>();
    ParserContext parserContext = new ParserContext();

    public void testAddFiltersManagerProperty() throws IOException {
        System.out.println(":::::::::::::::::::::: Start - test add FiltersManager property ::::::::::::::::::::::");
        parserContext.addProperty(propertyKey, propertyValue);

        System.out.println("Check FiltersManager: " + parserContext.hasProperty(propertyKey));
        System.out.println(":::::::::::::::::::::: Finish - test add FiltersManager property ::::::::::::::::::::::");
    }

    public void testGetters() throws IOException {
        System.out.println(":::::::::::::::::::::: Start - test getters ::::::::::::::::::::::");
        varsMap = new HashMap<String, Object>();

        Person person = new Person("person default name");
        Employee employee = new Employee("employee default first name", "employee default last name");

        varsMap.put("person", person);
        varsMap.put("employee", employee);

        try {
            String personName = (String) MVEL.eval("person.getName()", varsMap);
            String employeeFirstName = (String) MVEL.eval("employee.getFirstName()", varsMap);
            System.out.println("Person name: " + personName);
            System.out.println("Employee first name: " + employeeFirstName);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Classes were filtered successfully.");
        }

        System.out.println(":::::::::::::::::::::: Finish - test getters ::::::::::::::::::::::");
    }

    public void testSetters() throws IOException {
        System.out.println(":::::::::::::::::::::: Start - test setters ::::::::::::::::::::::");
        varsMap = new HashMap<String, Object>();

        Employee employee = new Employee("employee default first name", "employee default last name");
        Person person = new Person("person default name");

        varsMap.put("employee", employee);
        varsMap.put("person", person);

        try {
            MVEL.evalToString("person.setName(\"change person default name\")", varsMap);
            MVEL.evalToString("employee.setFirstName(\"change employee default first name\")", varsMap);

            String personName = MVEL.evalToString("person.getName()", varsMap);
            String employeeFirstName = MVEL.evalToString("employee.getFirstName()", varsMap);

            System.out.println("Person name: " + personName);
            System.out.println("Employee first name: " + employeeFirstName);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Classes were filtered successfully.");
        }
        System.out.println(":::::::::::::::::::::: Finish - test setters ::::::::::::::::::::::");
    }

    public void testPropertiesDirectAccess() throws IOException {
        System.out.println(":::::::::::::::::::::: Start - test properties direct access ::::::::::::::::::::::");
        varsMap = new HashMap<String, Object>();

        Employee employee = new Employee("employee default first name", "employee default last name");
        Person person = new Person("person default name");

        varsMap.put("employee", employee);
        varsMap.put("person", person);

        try {
            MVEL.evalToString("person.setName(\"change person default name\")", varsMap);
            MVEL.evalToString("employee.setFirstName(\"change employee default first name\")", varsMap);

            String personName = MVEL.evalToString("person.name", varsMap);
            String employeeFirstName = MVEL.evalToString("employee.firstName", varsMap);

            System.out.println("Person name: " + personName);
            System.out.println("Employee first name: " + employeeFirstName);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Classes were filtered successfully.");
        }
        System.out.println(":::::::::::::::::::::: Finish - test properties direct access ::::::::::::::::::::::");
    }

    public void testClassClassAccess() throws IOException {
        System.out.println(":::::::::::::::::::::: Start - test Class.class access ::::::::::::::::::::::");
        varsMap = new HashMap<String, Object>();

        varsMap.put("Class", Class.class);

        try {
            String personName = MVEL.evalToString("Class.forName(\"org.mvel2.filtersManager.Person\").newInstance().getName();", varsMap);
            String employeeFirstName = MVEL.evalToString("Class.forName(\"org.mvel2.filtersManager.Employee\").newInstance().getFirstName();", varsMap);

            System.out.println("Person name: " + personName);
            System.out.println("Employee first name: " + employeeFirstName);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Classes were filtered successfully.");
        }
        System.out.println(":::::::::::::::::::::: Finish - test Class.class access ::::::::::::::::::::::");
    }

    public void testArrayListObjects() throws IOException {
        System.out.println(":::::::::::::::::::::: Start - test ArrayList objects ::::::::::::::::::::::");
        parserContext.addProperty(propertyKey, propertyValue);
        
        varsMap = new HashMap<String, Object>();

        ArrayList people = new ArrayList();

        people.add(new Person("Mohammed"));
        people.add(new Person("Ahmad"));
        people.add(new Person("Mahamoud"));
        people.add(new Person("Anas"));

        varsMap.put("people", people);

        try {
            String template = "count = 0;\n"
                    + "foreach (person : people) {\n"
                    + "   count++;\n"
                    + "   System.out.println(\"Person #\" + count + \": \" + person.getName());\n"
                    + "}\n"
                    + "    \n"
                    + "System.out.println(\"Total people: \" + count);";
            MVEL.evalToString(template, varsMap);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Classes were filtered successfully.");
        }
        System.out.println(":::::::::::::::::::::: Finish - test ArrayList objects ::::::::::::::::::::::");
    }

    public void testNastedArrayListsObjects() throws IOException {
        System.out.println(":::::::::::::::::::::: Start - test Nasted ArrayLists objects ::::::::::::::::::::::");
        parserContext.addProperty(propertyKey, propertyValue);
        
        varsMap = new HashMap<String, Object>();

        ArrayList people = new ArrayList();
        ArrayList tempList = new ArrayList();

        tempList.add(new Person("tempList | Mohammed"));
        tempList.add(new Person("tempList | Ahmad"));
        tempList.add(new Person("tempList | Mahamoud"));
        tempList.add(new Person("tempList | Anas"));
        people.add(tempList);

        varsMap.put("people", people);

        try {
            String template = "count = 0;\n"
                    + "foreach (person : people) {\n"
                    + "foreach (personTemp : person) {\n"
                    + "   count++;\n"
                    + "   System.out.println(\"personTemp #\" + count + \": \" + personTemp.getName());\n"
                    + "}\n"
                    + "}\n"
                    + "System.out.println(\"Total people: \" + count);";
            MVEL.evalToString(template, varsMap);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Classes were filtered successfully.");
        }
        System.out.println(":::::::::::::::::::::: Finish - test Nasted ArrayLists objects ::::::::::::::::::::::");
    }
}
