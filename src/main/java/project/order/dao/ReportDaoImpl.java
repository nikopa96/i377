package project.order.dao;

import project.order.model.Report;
import project.utils.Util;

public class ReportDaoImpl implements ReportDao {

    @Override
    public Report find() {
        Report report = new Report();

        String sql = Util.readFile("report.sql");


        return report;
    }
}
