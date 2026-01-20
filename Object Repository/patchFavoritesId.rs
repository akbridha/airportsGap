<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>patchFavoritesId</name>
   <tag></tag>
   <elementGuidId>02b750b0-5a8d-447e-b236-8929c6aabeee</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>true</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;airport_id\&quot;: \&quot;JFK\&quot;,\n  \&quot;note\&quot;: \&quot;Airport Produksi Yayabelah\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;text/plain&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>309aaa2f-3ac0-43d3-814c-d7e323fefe1d</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${token}</value>
      <webElementGuid>793b073e-1c27-4f41-80e0-0ec6fa2ac5e6</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>10.4.2</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>PATCH</restRequestMethod>
   <restUrl>https://airportgap.com/api/favorites/${favoriteId}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
   <variables>
      <defaultValue>1</defaultValue>
      <description></description>
      <id>favoriteId</id>
      <masked>false</masked>
      <name>favoriteId</name>
   </variables>
   <variables>
      <defaultValue>KIX</defaultValue>
      <description></description>
      <id>airport_id</id>
      <masked>false</masked>
      <name>airport_id</name>
   </variables>
   <variables>
      <defaultValue>Updated note</defaultValue>
      <description></description>
      <id>note</id>
      <masked>false</masked>
      <name>note</name>
   </variables>
   <variables>
      <defaultValue>your_token_here</defaultValue>
      <description></description>
      <id>token</id>
      <masked>false</masked>
      <name>token</name>
   </variables>
</WebServiceRequestEntity>
