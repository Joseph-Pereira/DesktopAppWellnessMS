package controller;

import model.Counselor;
import dao.counselorDAO;
import java.util.List;

public class counselorController {
    private counselorDAO dao = new counselorDAO();

    public void create(Counselor c) {
        dao.addCounselor(c);
    }

    public void listAll() {
        List<Counselor> all = dao.getAllCounselors();
        for (Counselor c : all) {
            System.out.println(c.getSummary());
        }
    }

    public void delete(String name) {
        dao.deleteCounselorByName(name);
    }

    public void update(Counselor updated) {
        dao.updateCounselor(updated);
    }
}
