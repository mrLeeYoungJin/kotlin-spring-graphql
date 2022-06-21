package com.lyjguy.kotlinspringgraphql.config

import graphql.language.StringValue
import graphql.schema.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


@Configuration
class GraphQlConfig {
    @Bean
    fun dateScalar(): GraphQLScalarType? {
        return GraphQLScalarType.newScalar()
            .name("Date")
            .description("Java 8 LocalDate as Scalar")
            .coercing(object : Coercing<LocalDateTime?, String?> {
                @Throws(CoercingSerializeException::class)
                override fun serialize(dataFetcherResult: Any): String {
                    return (dataFetcherResult as? Timestamp)?.toString()
                        ?: throw CoercingSerializeException("Expected a Timestamp object.")
                }

                @Throws(CoercingParseValueException::class)
                override fun parseValue(input: Any): LocalDateTime {
                    return try {
                        if (input is String) LocalDateTime.parse(
                            input,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
                        ) else throw CoercingParseValueException("Expected a String")
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
