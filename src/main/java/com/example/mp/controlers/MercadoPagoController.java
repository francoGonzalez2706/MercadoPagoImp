package com.example.mp.controlers;

import com.example.mp.Entitis.OrdenCompra;
import com.example.mp.Entitis.Product;
import com.example.mp.services.OrdenCompraService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
public class MercadoPagoController {
    private final OrdenCompraService ordenCompraService;

    @PostMapping("/mp")
    public ResponseEntity<String> mp( @RequestBody List<Long> ids) throws Exception {
        MercadoPagoConfig.setAccessToken("TEST-4388047553335921-061420-111ca777e3494d390782b805c2c6962c-231327132");
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
                        .success("https://www.seu-site/success")
                        .pending("https://www.seu-site/pending")
                        .failure("https://www.seu-site/failure")
                        .build();

        List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
        excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());

        PreferencePaymentMethodsRequest paymentMethods = PreferencePaymentMethodsRequest.builder()
                .excludedPaymentTypes(excludedPaymentTypes)
                .installments(1)
                .build();

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
