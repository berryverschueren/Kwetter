package controller;

import javax.faces.bean.ManagedBean;

/**
 * Created by Berry-PC on 15/03/2017.
 */
@ManagedBean(name = "testController", eager = true)
public class testController {
    private String s1;

    public testController () {
        setS1("TEST CONTROLLER YO");
    }

    public String getS1() {
        return s1;
    }

    public void setS1 (String s) {
        s1 = s;
    }
}
