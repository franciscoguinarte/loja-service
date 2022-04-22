package com.curso.service

import com.curso.dto.input.VendaInput
import com.curso.dto.output.Parcela
import com.curso.dto.output.Venda
import com.curso.http.VeiculoHttp
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
class VendaService(
    private val veiculoHttp: VeiculoHttp
) {

    fun realizarVenda(vendaInput: VendaInput) {
        val veiculo = veiculoHttp.findById(vendaInput.veiculo)
        var parcelas = ArrayList<Parcela>()
        val valorParcela = vendaInput.valor.divide(vendaInput.quantidadeParcelas.toBigDecimal())
        var dataVencimento = LocalDateTime.now().plusMonths(1)

        for (i in 1..vendaInput.quantidadeParcelas) {
            var parcela: Parcela = Parcela(
                valor = valorParcela,
                dataVencimento = dataVencimento.toString()
            )
            parcelas.add(parcela)
            dataVencimento = dataVencimento.plusMonths(1)

        }

        var venda = Venda(
            veiculo = veiculo,
            valor = vendaInput.valor,
            cliente = vendaInput.cliente,
            parcelas = parcelas
        )
        println(venda)
    }
}