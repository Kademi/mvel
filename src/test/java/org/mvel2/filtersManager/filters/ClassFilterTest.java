/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvel2.filtersManager.filters;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openjdk.nashorn.api.scripting.ClassFilter;

/**
 *
 * @author Mohammed F. Ouda
 */
public class ClassFilterTest implements ClassFilter {

    HashSet<Class> registeredClasses;

    public ClassFilterTest() {
        registeredClasses = new HashSet<Class>();

        try {
            this.registerClass("org.mvel2.filtersManager.Employee");
            this.registerClass("org.mvel2.filtersManager.Person");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClassFilterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registerClass(String className) throws ClassNotFoundException {
        registeredClasses.add(Class.forName(className));
    }

    public boolean exposeToScripts(String className) {
        try {
            Class toCheck = Class.forName(className);

            for (Class filteredClass : registeredClasses) {
                if (filteredClass.isAssignableFrom(toCheck)) {
                    return false;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClassFilterTest.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }

        return true;
    }

}
