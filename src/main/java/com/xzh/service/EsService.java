package com.xzh.service;

import com.xzh.controller.Search;
import com.xzh.entity.EsEntity;

import java.util.List;

/**
 * @author 向振华
 * @date 2018/11/21 15:54
 */
public interface EsService {

    void save(EsEntity esEntity);

    List<EsEntity> select(Search search);

}
