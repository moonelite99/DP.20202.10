package dao.abstracts;

import entity.media.Media;

public interface GenericMediaDAO<T extends Media> extends GenericDAO<Integer, T>{
}
