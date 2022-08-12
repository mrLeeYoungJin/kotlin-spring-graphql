package com.lyjguy.kotlinspringgraphql.config

import graphql.language.StringValue
import graphql.schema.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Configuration
class GraphQlConfig {

//    @Bean
//    fun graphQlSourceBuilderCustomizer(): GraphQlSourceBuilderCustomizer {
//        return GraphQlSourceBuilderCustomizer { builder: SchemaResourceBuilder ->
//            if (NativeDetector.inNativeImage()) builder.schemaResources(ClassPathResource("graphql/schema.graphqls"))
//        }
//    }
//
//    @Bean
//    fun json(): GraphQLScalarType {
//        return ExtendedScalars.Json
//    }
//
//    @Bean
//    fun dateTime(): GraphQLScalarType {
//        return ExtendedScalars.DateTime
//    }

    @Bean
    fun dateTime(): GraphQLScalarType {
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        return GraphQLScalarType.newScalar()
            .name("DateTime")
            .description("Java 8 LocalDateTime as Scalar")
            .coercing(object : Coercing<LocalDateTime, String> {
                @Throws(CoercingSerializeException::class)
                override fun serialize(dataFetcherResult: Any): String {
                    return (dataFetcherResult as LocalDateTime).format(format)
                }

                @Throws(CoercingParseValueException::class)
                override fun parseValue(input: Any): LocalDateTime {
                    return try {
                        if (input is String) {
                            LocalDateTime.parse(input, format)
                        } else throw CoercingParseValueException("Expected a String")
                    } catch (e: DateTimeParseException) {
                        throw CoercingParseValueException(String.format("Not a valid date: %s", input), e)
                    }
                }

                @Throws(CoercingParseLiteralException::class)
                override fun parseLiteral(input: Any): LocalDateTime {
                    return if (input is StringValue) {
                        try {
                            LocalDateTime.parse((input).value)
                        } catch (e: DateTimeParseException) {
                            throw CoercingParseLiteralException()
                        }
                    } else throw CoercingParseLiteralException("Expected a StringValue")
                }
            }).build()
    }
}
