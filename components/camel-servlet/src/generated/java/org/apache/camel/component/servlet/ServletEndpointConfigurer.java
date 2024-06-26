/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.servlet;

import javax.annotation.processing.Generated;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.ExtendedPropertyConfigurerGetter;
import org.apache.camel.spi.PropertyConfigurerGetter;
import org.apache.camel.spi.ConfigurerStrategy;
import org.apache.camel.spi.GeneratedPropertyConfigurer;
import org.apache.camel.util.CaseInsensitiveMap;
import org.apache.camel.support.component.PropertyConfigurerSupport;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.EndpointSchemaGeneratorMojo")
@SuppressWarnings("unchecked")
public class ServletEndpointConfigurer extends PropertyConfigurerSupport implements GeneratedPropertyConfigurer, PropertyConfigurerGetter {

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        ServletEndpoint target = (ServletEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "async": target.setAsync(property(camelContext, boolean.class, value)); return true;
        case "attachmentmultipartbinding":
        case "attachmentMultipartBinding": target.setAttachmentMultipartBinding(property(camelContext, boolean.class, value)); return true;
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": target.setBridgeErrorHandler(property(camelContext, boolean.class, value)); return true;
        case "chunked": target.setChunked(property(camelContext, boolean.class, value)); return true;
        case "disablestreamcache":
        case "disableStreamCache": target.setDisableStreamCache(property(camelContext, boolean.class, value)); return true;
        case "eagercheckcontentavailable":
        case "eagerCheckContentAvailable": target.setEagerCheckContentAvailable(property(camelContext, boolean.class, value)); return true;
        case "exceptionhandler":
        case "exceptionHandler": target.setExceptionHandler(property(camelContext, org.apache.camel.spi.ExceptionHandler.class, value)); return true;
        case "exchangepattern":
        case "exchangePattern": target.setExchangePattern(property(camelContext, org.apache.camel.ExchangePattern.class, value)); return true;
        case "filenameextwhitelist":
        case "fileNameExtWhitelist": target.setFileNameExtWhitelist(property(camelContext, java.lang.String.class, value)); return true;
        case "headerfilterstrategy":
        case "headerFilterStrategy": target.setHeaderFilterStrategy(property(camelContext, org.apache.camel.spi.HeaderFilterStrategy.class, value)); return true;
        case "httpbinding":
        case "httpBinding": target.setHttpBinding(property(camelContext, org.apache.camel.http.common.HttpBinding.class, value)); return true;
        case "httpmethodrestrict":
        case "httpMethodRestrict": target.setHttpMethodRestrict(property(camelContext, java.lang.String.class, value)); return true;
        case "logexception":
        case "logException": target.setLogException(property(camelContext, boolean.class, value)); return true;
        case "maphttpmessagebody":
        case "mapHttpMessageBody": target.setMapHttpMessageBody(property(camelContext, boolean.class, value)); return true;
        case "maphttpmessageformurlencodedbody":
        case "mapHttpMessageFormUrlEncodedBody": target.setMapHttpMessageFormUrlEncodedBody(property(camelContext, boolean.class, value)); return true;
        case "maphttpmessageheaders":
        case "mapHttpMessageHeaders": target.setMapHttpMessageHeaders(property(camelContext, boolean.class, value)); return true;
        case "matchonuriprefix":
        case "matchOnUriPrefix": target.setMatchOnUriPrefix(property(camelContext, boolean.class, value)); return true;
        case "muteexception":
        case "muteException": target.setMuteException(property(camelContext, boolean.class, value)); return true;
        case "optionsenabled":
        case "optionsEnabled": target.setOptionsEnabled(property(camelContext, boolean.class, value)); return true;
        case "responsebuffersize":
        case "responseBufferSize": target.setResponseBufferSize(property(camelContext, java.lang.Integer.class, value)); return true;
        case "servletname":
        case "servletName": target.setServletName(property(camelContext, java.lang.String.class, value)); return true;
        case "traceenabled":
        case "traceEnabled": target.setTraceEnabled(property(camelContext, boolean.class, value)); return true;
        case "transferexception":
        case "transferException": target.setTransferException(property(camelContext, boolean.class, value)); return true;
        default: return false;
        }
    }

    @Override
    public Class<?> getOptionType(String name, boolean ignoreCase) {
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "async": return boolean.class;
        case "attachmentmultipartbinding":
        case "attachmentMultipartBinding": return boolean.class;
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return boolean.class;
        case "chunked": return boolean.class;
        case "disablestreamcache":
        case "disableStreamCache": return boolean.class;
        case "eagercheckcontentavailable":
        case "eagerCheckContentAvailable": return boolean.class;
        case "exceptionhandler":
        case "exceptionHandler": return org.apache.camel.spi.ExceptionHandler.class;
        case "exchangepattern":
        case "exchangePattern": return org.apache.camel.ExchangePattern.class;
        case "filenameextwhitelist":
        case "fileNameExtWhitelist": return java.lang.String.class;
        case "headerfilterstrategy":
        case "headerFilterStrategy": return org.apache.camel.spi.HeaderFilterStrategy.class;
        case "httpbinding":
        case "httpBinding": return org.apache.camel.http.common.HttpBinding.class;
        case "httpmethodrestrict":
        case "httpMethodRestrict": return java.lang.String.class;
        case "logexception":
        case "logException": return boolean.class;
        case "maphttpmessagebody":
        case "mapHttpMessageBody": return boolean.class;
        case "maphttpmessageformurlencodedbody":
        case "mapHttpMessageFormUrlEncodedBody": return boolean.class;
        case "maphttpmessageheaders":
        case "mapHttpMessageHeaders": return boolean.class;
        case "matchonuriprefix":
        case "matchOnUriPrefix": return boolean.class;
        case "muteexception":
        case "muteException": return boolean.class;
        case "optionsenabled":
        case "optionsEnabled": return boolean.class;
        case "responsebuffersize":
        case "responseBufferSize": return java.lang.Integer.class;
        case "servletname":
        case "servletName": return java.lang.String.class;
        case "traceenabled":
        case "traceEnabled": return boolean.class;
        case "transferexception":
        case "transferException": return boolean.class;
        default: return null;
        }
    }

    @Override
    public Object getOptionValue(Object obj, String name, boolean ignoreCase) {
        ServletEndpoint target = (ServletEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "async": return target.isAsync();
        case "attachmentmultipartbinding":
        case "attachmentMultipartBinding": return target.isAttachmentMultipartBinding();
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return target.isBridgeErrorHandler();
        case "chunked": return target.isChunked();
        case "disablestreamcache":
        case "disableStreamCache": return target.isDisableStreamCache();
        case "eagercheckcontentavailable":
        case "eagerCheckContentAvailable": return target.isEagerCheckContentAvailable();
        case "exceptionhandler":
        case "exceptionHandler": return target.getExceptionHandler();
        case "exchangepattern":
        case "exchangePattern": return target.getExchangePattern();
        case "filenameextwhitelist":
        case "fileNameExtWhitelist": return target.getFileNameExtWhitelist();
        case "headerfilterstrategy":
        case "headerFilterStrategy": return target.getHeaderFilterStrategy();
        case "httpbinding":
        case "httpBinding": return target.getHttpBinding();
        case "httpmethodrestrict":
        case "httpMethodRestrict": return target.getHttpMethodRestrict();
        case "logexception":
        case "logException": return target.isLogException();
        case "maphttpmessagebody":
        case "mapHttpMessageBody": return target.isMapHttpMessageBody();
        case "maphttpmessageformurlencodedbody":
        case "mapHttpMessageFormUrlEncodedBody": return target.isMapHttpMessageFormUrlEncodedBody();
        case "maphttpmessageheaders":
        case "mapHttpMessageHeaders": return target.isMapHttpMessageHeaders();
        case "matchonuriprefix":
        case "matchOnUriPrefix": return target.isMatchOnUriPrefix();
        case "muteexception":
        case "muteException": return target.isMuteException();
        case "optionsenabled":
        case "optionsEnabled": return target.isOptionsEnabled();
        case "responsebuffersize":
        case "responseBufferSize": return target.getResponseBufferSize();
        case "servletname":
        case "servletName": return target.getServletName();
        case "traceenabled":
        case "traceEnabled": return target.isTraceEnabled();
        case "transferexception":
        case "transferException": return target.isTransferException();
        default: return null;
        }
    }
}

