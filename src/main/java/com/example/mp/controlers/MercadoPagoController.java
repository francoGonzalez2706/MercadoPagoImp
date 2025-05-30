package com.example.mp.controlers;

import com.example.mp.Entitis.OrdenCompra;
import com.example.mp.Entitis.Product;
import com.example.mp.services.OrdenCompraService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
public class MercadoPagoController {
    private final OrdenCompraService ordenCompraService;

    @Value("${mercadopago.access-token}")
    private String mercadoPagoAccessToken;

    @PostMapping("/mp")
    @CrossOrigin("*")
    public ResponseEntity<String> mp( @RequestBody Map<String, List<Long>> body) throws Exception {
        List<Long> ids = body.get("id");
        MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);
        List<PreferenceItemRequest> items = new ArrayList<>();

        OrdenCompra ordenCompra = ordenCompraService.generarOrdenCompra(ids);

        for (Product detalle : ordenCompra.getProductlist()) {
            Double precioFinal = detalle.getPrecio();
            PreferenceItemRequest item = PreferenceItemRequest.builder()
                    .id(detalle.getId().toString())
                    .title(detalle.getNombre())
                    .description(detalle.getDescripcion())
                    .quantity(1)
                    .currencyId("ARS")
                    .unitPrice(BigDecimal.valueOf(precioFinal))
                    .build();
            items.add(item);
        }
        PreferenceBackUrlsRequest backUrls =
                PreferenceBackUrlsRequest.builder()
                        .success("https://localhost:5173/paymentSuccess")
                        .pending("https://localhost:5173/")
                        .failure("https://localhost:5173/paymentFailure")
                        .build();

        List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
        excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());

           /*
              *Otros ejemplos de id que podrías excluir:
              * "credit_card": tarjetas de crédito
              * "debit_card": tarjetas de débito
              * "ticket": pagos en efectivo como Rapipago o Pago Fácil
              * "atm": pagos a través de cajero automático
       */

        PreferencePaymentMethodsRequest paymentMethods = PreferencePaymentMethodsRequest.builder()
                .excludedPaymentTypes(excludedPaymentTypes)
                .installments(1)
                .build();
        //el campo installments(1) se refiere a la cantidad máxima de cuotas permitidas para realizar el pago

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .backUrls(backUrls)
                .paymentMethods(paymentMethods)
                .autoReturn("approved")
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);


        String prefId = preference.getId();

        System.out.println("URL de pago: " + preference.getInitPoint());

        return ResponseEntity.status(HttpStatus.OK).body("{\"preferenceId\":\""+prefId+"\"}");

    }

}
