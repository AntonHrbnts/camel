/*
 * Camel EndpointConfiguration generated by camel-api-component-maven-plugin
 */
package org.apache.camel.component.braintree;

import org.apache.camel.spi.ApiMethod;
import org.apache.camel.spi.ApiParam;
import org.apache.camel.spi.ApiParams;
import org.apache.camel.spi.Configurer;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;

/**
 * Camel endpoint configuration for {@link com.braintreegateway.TransactionGateway}.
 */
@ApiParams(apiName = "transaction", 
           description = "Provides methods to interact with Transactions",
           apiMethods = {@ApiMethod(methodName = "adjustAuthorization", description="Submits the transaction with the given id to be adjusted for the given amount which must be less than or equal to the authorization amount", signatures={"com.braintreegateway.Result<com.braintreegateway.Transaction> adjustAuthorization(String id, java.math.BigDecimal amount)", "com.braintreegateway.Result<com.braintreegateway.Transaction> adjustAuthorization(String id, com.braintreegateway.TransactionRequest request)"}), @ApiMethod(methodName = "cancelRelease", description="Cancels a pending release of a transaction with the given id from escrow", signatures={"com.braintreegateway.Result<com.braintreegateway.Transaction> cancelRelease(String id)"}), @ApiMethod(methodName = "cloneTransaction", signatures={"com.braintreegateway.Result<com.braintreegateway.Transaction> cloneTransaction(String id, com.braintreegateway.TransactionCloneRequest request)"}), @ApiMethod(methodName = "credit", description="Creates a credit Transaction", signatures={"com.braintreegateway.Result<com.braintreegateway.Transaction> credit(com.braintreegateway.TransactionRequest request)"}), @ApiMethod(methodName = "find", description="Finds a Transaction by id", signatures={"com.braintreegateway.Transaction find(String id)"}), @ApiMethod(methodName = "packageTracking", description="Supplement the transaction with package tracking details", signatures={"com.braintreegateway.Result<com.braintreegateway.Transaction> packageTracking(String id, com.braintreegateway.PackageTrackingRequest packageTrackingRequest)"}), @ApiMethod(methodName = "refund", description="Refunds all or part of a previous sale Transaction", signatures={"com.braintreegateway.Result<com.braintreegateway.Transaction> refund(String id)", "com.braintreegateway.Result<com.braintreegateway.Transaction> refund(String id, java.math.BigDecimal amount)", "com.braintreegateway.Result<com.braintreegateway.Transaction> refund(String id, com.braintreegateway.TransactionRefundRequest request)"}), @ApiMethod(methodName = "releaseFromEscrow", description="Submits the transaction with the given id for release", signatures={"com.braintreegateway.Result<com.braintreegateway.Transaction> releaseFromEscrow(String id)"}), @ApiMethod(methodName = "sale", description="Creates a sale Transaction", signatures={"com.braintreegateway.Result<com.braintreegateway.Transaction> sale(com.braintreegateway.TransactionRequest request)"}), @ApiMethod(methodName = "search", description="Finds all Transactions that match the query and returns a ResourceCollection", signatures={"com.braintreegateway.ResourceCollection<com.braintreegateway.Transaction> search(com.braintreegateway.TransactionSearchRequest query)"}), @ApiMethod(methodName = "submitForPartialSettlement", description="Submits a partial settlement transaction for the given id", signatures={"com.braintreegateway.Result<com.braintreegateway.Transaction> submitForPartialSettlement(String id, java.math.BigDecimal amount)", "com.braintreegateway.Result<com.braintreegateway.Transaction> submitForPartialSettlement(String id, com.braintreegateway.TransactionRequest request)"}), @ApiMethod(methodName = "submitForSettlement", description="Submits the transaction with the given id to be settled along with a TransactionRequest object", signatures={"com.braintreegateway.Result<com.braintreegateway.Transaction> submitForSettlement(String id)", "com.braintreegateway.Result<com.braintreegateway.Transaction> submitForSettlement(String id, java.math.BigDecimal amount)", "com.braintreegateway.Result<com.braintreegateway.Transaction> submitForSettlement(String id, com.braintreegateway.TransactionRequest request)"}), @ApiMethod(methodName = "updateCustomFields", description="Updates custom field values for a given transaction", signatures={"com.braintreegateway.Result<com.braintreegateway.Transaction> updateCustomFields(String id, com.braintreegateway.TransactionRequest request)"}), @ApiMethod(methodName = "updateDetails", description="Updates details for a transaction that has been submitted for settlement", signatures={"com.braintreegateway.Result<com.braintreegateway.Transaction> updateDetails(String id, com.braintreegateway.TransactionRequest request)"}), @ApiMethod(methodName = "voidTransaction", description="Voids the transaction with the given id", signatures={"com.braintreegateway.Result<com.braintreegateway.Transaction> voidTransaction(String id)"})}, aliases = {})
@UriParams
@Configurer(extended = true)
public final class TransactionGatewayEndpointConfiguration extends BraintreeConfiguration {
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "adjustAuthorization", description="To be adjusted"), @ApiMethod(methodName = "refund"), @ApiMethod(methodName = "submitForPartialSettlement", description="Of the partial settlement"), @ApiMethod(methodName = "submitForSettlement", description="To settle. must be less than or equal to the authorization amount.")})
    private java.math.BigDecimal amount;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "cloneTransaction")})
    private com.braintreegateway.TransactionCloneRequest cloneRequest;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "adjustAuthorization", description="Of the transaction to to be adjusted"), @ApiMethod(methodName = "cancelRelease", description="Of the transaction to cancel release from escrow of"), @ApiMethod(methodName = "cloneTransaction"), @ApiMethod(methodName = "find", description="The id of the Transaction"), @ApiMethod(methodName = "packageTracking", description="Of the transaction to supplement the package details for"), @ApiMethod(methodName = "refund"), @ApiMethod(methodName = "releaseFromEscrow", description="Of the transaction to submit for release"), @ApiMethod(methodName = "submitForPartialSettlement", description="Of the transaction to add the partial settlement transaction for"), @ApiMethod(methodName = "submitForSettlement", description="Of the transaction to submit for settlement"), @ApiMethod(methodName = "updateCustomFields", description="Of the transaction being updated"), @ApiMethod(methodName = "updateDetails", description="Of the transaction to update the details for"), @ApiMethod(methodName = "voidTransaction", description="Of the transaction to void")})
    private String id;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "packageTracking", description="The package tracking request related to the transaction")})
    private com.braintreegateway.PackageTrackingRequest packageTrackingRequest;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "search", description="The search query")})
    private com.braintreegateway.TransactionSearchRequest query;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "refund")})
    private com.braintreegateway.TransactionRefundRequest refundRequest;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "adjustAuthorization", description="Is the TransactionRequest object with amount details"), @ApiMethod(methodName = "credit", description="The request"), @ApiMethod(methodName = "sale", description="The request"), @ApiMethod(methodName = "submitForPartialSettlement", description="The request"), @ApiMethod(methodName = "submitForSettlement", description="The request"), @ApiMethod(methodName = "updateCustomFields", description="A TransactionRequest object containing custom field info.."), @ApiMethod(methodName = "updateDetails", description="The request")})
    private com.braintreegateway.TransactionRequest request;

    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    public com.braintreegateway.TransactionCloneRequest getCloneRequest() {
        return cloneRequest;
    }

    public void setCloneRequest(com.braintreegateway.TransactionCloneRequest cloneRequest) {
        this.cloneRequest = cloneRequest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public com.braintreegateway.PackageTrackingRequest getPackageTrackingRequest() {
        return packageTrackingRequest;
    }

    public void setPackageTrackingRequest(com.braintreegateway.PackageTrackingRequest packageTrackingRequest) {
        this.packageTrackingRequest = packageTrackingRequest;
    }

    public com.braintreegateway.TransactionSearchRequest getQuery() {
        return query;
    }

    public void setQuery(com.braintreegateway.TransactionSearchRequest query) {
        this.query = query;
    }

    public com.braintreegateway.TransactionRefundRequest getRefundRequest() {
        return refundRequest;
    }

    public void setRefundRequest(com.braintreegateway.TransactionRefundRequest refundRequest) {
        this.refundRequest = refundRequest;
    }

    public com.braintreegateway.TransactionRequest getRequest() {
        return request;
    }

    public void setRequest(com.braintreegateway.TransactionRequest request) {
        this.request = request;
    }
}
