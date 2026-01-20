import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper

def response = WS.sendRequest(findTestObject('Object Repository/getAirports'))
WS.verifyResponseStatusCode(response, 200)

def json = new JsonSlurper().parseText(response.getResponseText())
//assert json != null
println("isi json : ")
println(json)

// Extract first item's id and type
def first = json.data[0]
def firstId = first.id
def firstType = first.type
println("First airport id=${firstId}, type=${firstType}")

// Verify and collect
assert firstId != null && firstId instanceof String
WS.verifyElementPropertyValue(response, 'data[0].type', 'airport')

// Optional: collect all ids/types
def allIds = json.data.collect { it.id }
def allTypes = json.data.collect { it.type }
println("Total airports: ${allIds.size()}")

// --- Test favorites endpoint with proper Authorization header ---
def token = (GlobalVariable.hasProperty('apiToken') ? GlobalVariable.apiToken : null)
if (!token) {
	token = System.getenv('AIRPORTGAP_TOKEN') ?: '<PUT_YOUR_TOKEN_HERE>'
}

def favRequest = findTestObject('Object Repository/getFavorite')
def headerProps = favRequest.getHttpHeaderProperties()
// Remove any existing Authorization to avoid wrong formats
headerProps = headerProps?.findAll { it.name?.toLowerCase() != 'authorization' } ?: []
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.model.ConditionType
headerProps.add(new TestObjectProperty('Authorization', ConditionType.EQUALS, "Token token=${token}"))
headerProps.add(new TestObjectProperty('Accept', ConditionType.EQUALS, 'application/json'))
favRequest.setHttpHeaderProperties(headerProps)

def favResponse = WS.sendRequest(favRequest)
println("Favorites status: ${favResponse.getStatusCode()}")
if (favResponse.getStatusCode() == 401) {
	println('Unauthorized: periksa token atau format Authorization header (Token token=...)')
} else {
	WS.verifyResponseStatusCode(favResponse, 200)
	def favJson = new JsonSlurper().parseText(favResponse.getResponseText())
	println("Favorites count: ${favJson?.data?.size()}")
}
println("All airport ids: ${allIds}")
println("All airport types: ${allTypes}")

