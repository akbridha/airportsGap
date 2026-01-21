<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>postFavorite</name>
   <tag></tag>
   <elementGuidId>12345678-90ab-cdef-1234-567890abcdef</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>true</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;airport_id\&quot;: \&quot;${airport_id}\&quot;,\n  \&quot;note\&quot;: \&quot;${note}\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${token}</value>
      <webElementGuid>25d7181f-fa7e-414e-aede-70f08edf1c4b</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>24c89789-329d-4276-a98a-a659510fa0e0</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>10.4.2</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://airportgap.com/api/favorites</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'HFN'</defaultValue>
      <description></description>
      <id>4072ef3d-3674-4787-beef-92736c46fcf5</id>
      <masked>false</masked>
      <name>airport_id</name>
   </variables>
   <variables>
      <defaultValue>'My favorite airport'</defaultValue>
      <description></description>
      <id>fa126dd6-2b4c-45b2-80ff-86ed72f3f564</id>
      <masked>false</masked>
      <name>note</name>
   </variables>
   <variables>
      <defaultValue>'6rGgz23fmVE1mnW7n1RH7PsL'</defaultValue>
      <description></description>
      <id>1937c4c1-881d-421d-ae97-34c2a463ef3a</id>
      <masked>false</masked>
      <name>token</name>
   </variables>
   <variables>
      <defaultValue>1</defaultValue>
      <description></description>
      <id>a2d6e9d9-e02f-4613-b596-44c6a7e4411f</id>
      <masked>false</masked>
      <name>page</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()

assert response.getStatusCode() == 201
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
