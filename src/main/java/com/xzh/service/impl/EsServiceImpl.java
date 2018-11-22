package com.xzh.service.impl;

import com.xzh.controller.Search;
import com.xzh.entity.EsEntity;
import com.xzh.repository.EsRepository;
import com.xzh.service.EsService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 向振华
 * @date 2018/11/22 10:08
 */
@Service
public class EsServiceImpl implements EsService {

    @Resource
    private EsRepository esRepository;

    @Override
    public void save(EsEntity esEntity) {
        esRepository.save(esEntity);
    }

    @Override
    public List<EsEntity> select(Search search) {

        //创建builder
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //builder下有must、should以及mustNot 相当于sql中的and、or以及not
        //设置“名称”模糊搜索
        if (search.getName()!=null) {
            builder.must(QueryBuilders.matchPhraseQuery("name", search.getName()));
        }
        //设置“性别”
        if (search.getSex()!=null) {
            builder.must(new QueryStringQueryBuilder(search.getSex()).field("sex"));
        }
        //按照年龄从高到低
        FieldSortBuilder sort = SortBuilders.fieldSort("age").order(SortOrder.DESC);
        //设置分页(拿第一页，一页显示两条)。注意!es的分页api是从第0页开始的(坑)
        PageRequest page = new PageRequest(0, 2);

        //构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(page);
        //将排序设置到构建中
        nativeSearchQueryBuilder.withSort(sort);
        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        //执行
        Page<EsEntity> searchs = esRepository.search(query);

        //获取总条数(前端分页需要使用)
        int total = (int) searchs.getTotalElements();

        //获取查询到的数据内容
        List<EsEntity> content = searchs.getContent();

        return content;
    }
}
