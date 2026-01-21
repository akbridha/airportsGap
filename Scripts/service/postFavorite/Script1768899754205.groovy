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
def response = WS.sendRequest(findTestObject('postFavorite', [
	('airport_id') : 'AEY',
	('note') : 'akureyri',
	('token') : GlobalVariable.authToken
]))

//error apakah gara2 passing datanya atau testnyacase tidak sesuai
//bisa jadi juga memang objectnya tidak betul.
//

// Assertion sederhana
WS.verifyResponseStatusCode(response, 201)

// Parse response JSON
def jsonSlurper = new JsonSlurper()
def jsonResponse = jsonSlurper.parseText(response.getResponseBodyContent())

// Verifikasi favorite dibuat
assert jsonResponse.data != null : "Data favorite tidak boleh null"
assert jsonResponse.data.id != null : "Favorite ID harus ada"
assert jsonResponse.data.attributes.airport.iata == 'AEY' : "Airport IATA mesti  sesuai"
assert jsonResponse.data.attributes.note == 'akureyri' : "wajib sama"

//jaga jga simpan id favorite
GlobalVariable.favoriteId = jsonResponse.data.id

println("Test postFavorite berhasil - Favorite ID: " + jsonResponse.data.id)

