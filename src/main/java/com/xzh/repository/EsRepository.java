package com.xzh.repository;

import com.xzh.entity.EsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 向振华
 * @date 2018/11/21 16:53
 */
public interface EsRepository extends ElasticsearchRepository<EsEntity, Integer> {
}
