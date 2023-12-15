package com.shell.weatheralerts.utils.serialization

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeTypeAdapter(
    private val dateFormat: DateTimeFormatter
) : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    override fun serialize(
        src: LocalDateTime?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement? {
        return runCatching {
            src?.let { JsonPrimitive(dateFormat.format(it)) }
        }.getOrElse { null }
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDateTime? {
        return runCatching {
            json?.asString?.let {
                ZonedDateTime.parse(it).toLocalDateTime()
            }
        }.getOrElse { null }
    }
}
