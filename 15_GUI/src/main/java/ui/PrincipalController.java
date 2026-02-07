package ui;

import dao.ConductorDAO;

import javax.swing.*;

public class PrincipalController {
    private final PrincipalView pv;
    private final ConductorDAO cDAO;

    public PrincipalController(PrincipalView pv, ConductorDAO cDAO){
        this.pv = pv;
        this.cDAO = cDAO;
    }


}

