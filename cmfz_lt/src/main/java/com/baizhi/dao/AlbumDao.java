package com.baizhi.dao;

import com.baizhi.entity.Album;


public interface AlbumDao extends BaseDao<Album> {
    void updateCover(Album album);

    void deleteAlbum(String id);
}
