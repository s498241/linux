package com.ElaticMapper;


import com.entity.UserInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsUserRepository extends ElasticsearchRepository<UserInfo, Long> {
}   