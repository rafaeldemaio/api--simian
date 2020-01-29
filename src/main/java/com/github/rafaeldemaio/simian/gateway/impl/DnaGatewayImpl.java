package com.github.rafaeldemaio.simian.gateway.impl;

import com.github.rafaeldemaio.simian.gateway.DnaGateway;
import com.github.rafaeldemaio.simian.gateway.data.request.DnaGatewayRequest;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.github.rafaeldemaio.simian.config.FirestoreConfiguration;
import com.github.rafaeldemaio.simian.gateway.data.response.DnaGatewayResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class DnaGatewayImpl implements DnaGateway {

    private final FirestoreConfiguration firestore;

    @Override
    public void saveDna(final DnaGatewayRequest request) {
        try {
            final ApiFuture<WriteResult> future =
                    firestore.getDb().collection("dna").document(request.getDna()).set(request);
            log.info("Incluído dna no firestore em {}", future.get().getUpdateTime());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Erro ao salvar dados no banco", e);
        }
    }

    @Override
    public DnaGatewayResponse getDna(final String dnaRequest) {
        try {
            final DocumentSnapshot document = firestore.getDb().collection("dna").document(dnaRequest).get().get();
            if (document.exists()) {
                return document.toObject(DnaGatewayResponse.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Erro ao recuperar dados do banco", e);
        }
    }

    @Override
    public List<DnaGatewayResponse> getAll() {
        try {
            final ApiFuture<QuerySnapshot> future = firestore.getDb().collection("dna").get();
            final List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            final List<DnaGatewayResponse> response = new ArrayList<>();
            documents.forEach(d -> response.add(d.toObject(DnaGatewayResponse.class)));
            return response;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Erro ao recuperar os dados do banco", e);
        }
    }
}
