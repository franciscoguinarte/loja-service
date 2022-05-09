package com.curso.service

import com.curso.dto.output.Veiculo
import com.curso.http.VeiculoHttp
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.inject.Singleton
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

@Singleton
class VeiculoService(
    private val veiculoHttp: VeiculoHttp,
    private val objectMapper: ObjectMapper
) {

    fun getVeiculo(id: Long): Veiculo{
        return veiculoHttp.findById(id)
    }
    fun gravarCache(veiculo: Veiculo){

        val jedisPool =  JedisPool(JedisPoolConfig(),"127.0.0.1",6379)
        var jedis = jedisPool.resource
        var veiculoJSON = objectMapper.writeValueAsString(veiculo)
        jedis.set(veiculo.id.toString(),veiculoJSON)
    }
}