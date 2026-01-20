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

// Kirim request dengan parameter dinamis
def response = WS.sendRequest(findTestObject('patchFavoritesId', [
	('favoriteId') : GlobalVariable.favoriteId,
	('airport_id') : 'NRT',
	('note') : 'Updated - Tokyo Narita Airport',
	('token') : GlobalVariable.authToken
]))

// Assertion sederhana
WS.verifyResponseStatusCode(response, 200)

// Parse response JSON
def jsonSlurper = new JsonSlurper()
def jsonResponse = jsonSlurper.parseText(response.getResponseBodyContent())

// Verifikasi update berhasil
assert jsonResponse.data != null : "Data favorite tidak boleh null"
assert jsonResponse.data.attributes.airport.iata == 'NRT' : "Airport IATA harus terupdate"
assert jsonResponse.data.attributes.note == 'Updated - Tokyo Narita Airport' : "Note harus terupdate"

println("Test patchFavoritesById berhasil - Updated to: " + jsonResponse.data.attributes.airport.name)

