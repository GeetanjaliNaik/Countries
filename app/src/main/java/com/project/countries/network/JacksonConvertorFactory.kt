package com.project.countries.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
/**
 * Created by Geeta on 04/02/19.
 */
class JacksonConverterFactory(val mapper: ObjectMapper) : Converter.Factory() {
    companion object {
        fun create(): JacksonConverterFactory {
            return create(ObjectMapper())
        }

        fun create(mapper: ObjectMapper): JacksonConverterFactory {
            return JacksonConverterFactory(mapper!!)
        }
    }



    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<out kotlin.Annotation>?, methodAnnotations: Array<out kotlin.Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
        val javaType = mapper.typeFactory.constructType(type!!)
        val writer = mapper.writerWithType(javaType)
        return JacksonRequestBodyConverter<Any>(writer)
    }

    override fun responseBodyConverter(type: Type?, annotations: Array<out kotlin.Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        val javaType = mapper.typeFactory.constructType(type!!)
        val reader = mapper.readerFor(javaType)
        return JacksonResponseBodyConverter<Any>(reader)
    }

    internal class JacksonResponseBodyConverter<T>(private val adapter: ObjectReader) : Converter<ResponseBody, T> {

        @Throws(IOException::class)
        override fun convert(value: ResponseBody): T {
            try {
                return adapter.readValue(value.charStream())
            } finally {
                value.close()
            }
        }
    }

    private class JacksonRequestBodyConverter<T> internal constructor(private val adapter: ObjectWriter) : Converter<T, RequestBody> {

        @Throws(IOException::class)
        override fun convert(value: T): RequestBody {
            val bytes = adapter.writeValueAsBytes(value)
            return RequestBody.create(MEDIA_TYPE, bytes)
        }

        companion object {
            private val MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8")
        }
    }
}