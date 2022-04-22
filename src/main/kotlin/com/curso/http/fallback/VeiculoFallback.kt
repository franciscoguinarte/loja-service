package com.curso.http.fallback

import com.curso.dto.output.Veiculo
import com.curso.http.VeiculoHttp
import io.micronaut.retry.annotation.Fallback

@Fallback
class VeiculoFallback: VeiculoHttp{
    override fun findById(id: Long): Veiculo {
        return Veiculo(123, "ModeloMock", "MarcaMock", "PlacaMock")
    }
}