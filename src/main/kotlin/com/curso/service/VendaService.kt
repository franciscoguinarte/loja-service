package com.curso.service

import com.curso.dto.input.VendaInput
import com.curso.http.VeiculoHttp
import jakarta.inject.Singleton

@Singleton
class VendaService (
    private val veiculoHttp: VeiculoHttp
    ){

    fun realizarVenda(vendaInput: VendaInput){
        val veiculo = veiculoHttp.findById(vendaInput.veiculo)
        println(veiculo.toString())
    }
}