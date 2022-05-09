package com.curso.http.fallback

import com.curso.dto.output.Veiculo
import com.curso.http.VeiculoHttp
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.retry.annotation.Fallback
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

@Fallback
class VeiculoFallback(
    private val objectMapper: ObjectMapper
): VeiculoHttp{
    override fun findById(id: Long): Veiculo {
        val jedisPool =  JedisPool(JedisPoolConfig(),"127.0.0.1",6379)
        var jedis = jedisPool.resource

        val veiculoJSON = jedis.get(id.toString())
        val veiculo = objectMapper.readValue(veiculoJSON,Veiculo::class.java)
        return veiculo

    }
}