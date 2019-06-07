package ua.taxi.best.servlet.util;

import javax.servlet.http.HttpServletRequest;

public final class Paginator {
    private Paginator() {
    }

    public static int getCurrentPage(HttpServletRequest req) {
        int currentPage;
        if (req.getParameter("currentPage") == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }
        return currentPage;
    }

    public static int getRecordsPerPage(HttpServletRequest req) {
        int recordsPerPage;
        if (req.getParameter("recordsPerPage") == null) {
            recordsPerPage = 5;
        } else {
            recordsPerPage = Integer.valueOf(req.getParameter("recordsPerPage"));
        }
        return recordsPerPage;
    }

    public static int getNumberOfPages(int numberOfRows, int recordsPerPage) {
        int nOfPages = numberOfRows / recordsPerPage;
        if (numberOfRows % recordsPerPage > 0) {
            nOfPages++;
        }
        return nOfPages;
    }

    public static void setAttributes(HttpServletRequest req, int nOfPages, int currentPage, int recordsPerPage) {
        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
    }
}
