package org.mvel2.filtersManager.tests;

import junit.framework.TestCase;
import org.mvel2.MVEL;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.mvel2.filtersManager.Employee;

/**
 * @author Mohammed F. Ouda
 */
public class GroupATests extends TestCase {

    private static final String propertyKey = "mvel.filtersManager";
    private static final String propertyValue = "org.mvel2.filtersManager.FiltersManager";

    public void testWithoutFiltersManagerProperty() throws IOException {
        System.out.println(":::::::::::::::::::::: Start - test without FiltersManager property ::::::::::::::::::::::");
        Map<String, Object> varsMap = new HashMap<String, Object>();

        varsMap.put("employee", new Employee());

        String employeeFirstName = MVEL.evalToString("employee.getFirstName()", varsMap);

        System.out.println("Employee first name: " + employeeFirstName);
        System.out.println(":::::::::::::::::::::: Finish - test without FiltersManager property ::::::::::::::::::::::");
    }
}
