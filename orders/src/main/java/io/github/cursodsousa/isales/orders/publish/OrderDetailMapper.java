package io.github.cursodsousa.isales.orders.publish;

import io.github.cursodsousa.isales.orders.model.Order;
import io.github.cursodsousa.isales.orders.publish.representation.OrderDetailRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    @Mapping(source = "id", target = "id") //quando for igual as fields, não precisa colocar,
    // coloquei so para fim didatico
    @Mapping(source = "date", target = "date", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "total", target = "total")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "customerId", target = "customerId")
    @Mapping(source = "urlNF", target = "urlInvoiceFile")
    @Mapping(source = "trackingCode", target = "trackingCode")
    // A partir daqui é obrigatorio pois são fields de nomes diferentes
    @Mapping(source = "customerRepresentation.name", target = "customerName")
    @Mapping(source = "customerRepresentation.document", target = "customerCpf")
    @Mapping(source = "customerRepresentation.street", target = "addressCustomerStreet")
    @Mapping(source = "customerRepresentation.number", target = "addressCustomerNumber")
    @Mapping(source = "customerRepresentation.area", target = "addressCustomerArea")
    @Mapping(source = "customerRepresentation.email", target = "customerEmail")
    @Mapping(source = "customerRepresentation.phone", target = "customerPhone")
    @Mapping(source = "orderItems", target = "items")
    OrderDetailRepresentation map(Order dto);

}
