/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvel2.filtersManager;

import org.mvel2.filtersManager.filters.ClassFilterTest;
import java.util.ArrayList;
import jdk.nashorn.api.scripting.ClassFilter;

/**
 *
 * @author Mohammed F. Ouda
 */
public class FiltersManager implements org.mvel2.FiltersManager {

    private ArrayList<ClassFilter> classFilters;

    public FiltersManager() {
        classFilters = new ArrayList<ClassFilter>();

        this.registerClassFilter(new ClassFilterTest());
    }

    public void registerClassFilter(ClassFilter filter) {
        classFilters.add(filter);
    }

    public void deregisterClassFilter(ClassFilter filter) {
        classFilters.remove(filter);
    }

    public boolean exposeToScripts(String classNameString) {
        return this.classFilters.get(0).exposeToScripts(classNameString);
    }

    @Override
    public boolean exposeObjectToScripts(Object o) {
        return true;
    }

}
