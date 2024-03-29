package com.example.application;

import com.example.search.EsConfiguration;
import io.appform.dropwizard.sharding.config.ShardedHibernateFactory;
import io.dropwizard.Configuration;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceConfiguration extends Configuration {

    @NotNull
    @Valid
    private ShardedHibernateFactory sharded;

    @NotNull
    @Valid
    private EsConfiguration esConfiguration;
}
