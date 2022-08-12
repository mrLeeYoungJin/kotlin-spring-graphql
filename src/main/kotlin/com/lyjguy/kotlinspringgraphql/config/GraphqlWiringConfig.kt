package com.lyjguy.kotlinspringgraphql.config

import graphql.scalars.ExtendedScalars
import graphql.schema.GraphQLScalarType
import graphql.schema.idl.RuntimeWiring
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer
import java.time.LocalDateTime

@Configuration
class GraphqlWiringConfig(
    private val dateTime: GraphQLScalarType
) : RuntimeWiringConfigurer {

    override fun configure(builder: RuntimeWiring.Builder) {
        builder
            .scalar(dateTime)
            .build()
    }
}