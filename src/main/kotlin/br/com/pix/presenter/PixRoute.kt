package br.com.pix.presenter

import br.com.pix.application.PixCommandHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
class PixRoute(
    private val handler: PixCommandHandler,
) {
    @PostMapping("/pix")
    fun create(@RequestBody request: RequestReplyRequest): ResponseEntity<RequestReplyRequest> {
        val correlationId = UUID.randomUUID()
        handler.handler(request.toCommand(correlationId.toString()))
        return ResponseEntity<RequestReplyRequest>(request, HttpStatus.CREATED)
    }
}