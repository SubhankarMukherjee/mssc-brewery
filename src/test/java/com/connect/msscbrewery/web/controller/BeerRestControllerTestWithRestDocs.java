package com.connect.msscbrewery.web.controller;

import com.connect.msscbrewery.web.controller.V2.BeerRestControllerV2;
import com.connect.msscbrewery.web.model.BeerDto;
import com.connect.msscbrewery.web.model.V2.BeerDTOV2;
import com.connect.msscbrewery.web.service.V2.BeerServiceV2;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// All static methods are from RestDocumnetResult buuilder
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "http",uriHost = "anyserver.com.sa",uriPort = 8810)
@WebMvcTest(BeerRestControllerV2.class)
@ComponentScan(basePackages = "package com.connect.msscbrewery.domain.Mapper")
class BeerRestControllerTestWithRestDocs {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerServiceV2 service;

    @Autowired
    ObjectMapper objectMapper;

    BeerDTOV2 validBeer;

    @BeforeEach
    public void setUp() {
        validBeer = BeerDTOV2.builder().id(UUID.randomUUID()).beerStyle("ale").beerName("heniken").build();
    }


    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get("/api/v2/beer/{beerId}", UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v2/beer", pathParameters(

                        parameterWithName("beerId").description("UUID of a desired Beer to get.")
                )));
    }

    // Document query param
    @Test
    void getBeerById_query() throws Exception {
        given(service.getBeerById(any())).willReturn(validBeer);
        mockMvc.perform(get("/api/v2/beer/{beerId}", UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON)
                        .param("isCold", "Yes"))
                .andExpect(status().isOk())
                .andDo(document("v2/beer-get", pathParameters(

                                parameterWithName("beerId").description("UUID of a desired Beer to get.")
                        ),
                        requestParameters(
                                parameterWithName("isCold").description("IsCold is request param")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Beer UUID"),

                                fieldWithPath("creationDate").description("Date Created"),
                                fieldWithPath("lastModifiedDate").description("Date Updated"),
                                fieldWithPath("beerName").description("Beer Name"),
                                fieldWithPath("beerStyle").description("Beer Style"),
                                fieldWithPath("upc").description("Beer UPC")


                        )
                        ));
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDTOV2 beerDTOV2 = validBeer;
        beerDTOV2.setId(null);
        BeerDTOV2 savedDTO = BeerDTOV2.builder().id(UUID.randomUUID()).beerName("New Beer").beerStyle("bitter").build();
        String jsonString = objectMapper.writeValueAsString(beerDTOV2);

        ConstrainedFields fields= new ConstrainedFields(BeerDTOV2.class);
        given(service.addBeer(any())).willReturn(savedDTO);


        mockMvc.perform(post("/api/v2/beer").content(jsonString).accept(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isCreated()).
                andDo(document("v2/beer-create",
                        requestFields(
                                fields.withPath("id").ignored(),

                                fields.withPath("creationDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("beerName").description("Beer Name"),
                                fields.withPath("beerStyle").description("Beer Style"),
                                fields.withPath("upc").description("Beer UPC")


                )));
    }

    @Test
    void updateBeer() throws Exception {
        BeerDto beerDto = BeerDto.builder().beerName("abc").beerStyle("bitter").build();
        String jsonString = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("/api/v2/beer/" + UUID.randomUUID().toString()).content(jsonString).accept(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/v2/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    public static class ConstrainedFields{
        private final ConstraintDescriptions constraintDescriptions;
        ConstrainedFields(Class<?> input)
        {
            this.constraintDescriptions = new ConstraintDescriptions(input);

        }
        private FieldDescriptor withPath(String path)
        {

            return fieldWithPath(path).attributes(key("constraints").value(StringUtils.collectionToDelimitedString(this.constraintDescriptions
                    .descriptionsForProperty(path),". ")));

        }


    }
}
