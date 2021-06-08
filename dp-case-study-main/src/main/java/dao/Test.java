package dao;

import dao.abstracts.GenericMediaDAO;
import dao.abstracts.media.BookDAO;
import dao.abstracts.media.MediaDAO;
import dao.impl.media.BookDAOImpl;
import entity.media.Book;
import entity.media.Media;

import java.sql.SQLException;

public class Test {
    static MediaDAO genericMediaDAO = new BookDAOImpl();
    public static void main(String[] args) throws SQLException {

        System.out.println(((BookDAO) genericMediaDAO).getAllMedias());
    }
}
