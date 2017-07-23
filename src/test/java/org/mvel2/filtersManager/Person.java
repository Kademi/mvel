/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvel2.filtersManager;

/**
 *
 * @author Mohammed F. Ouda
 */
public class Person {

    public String name;

    public Person() {
        this("default constructor Person name");
    }
    
    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
