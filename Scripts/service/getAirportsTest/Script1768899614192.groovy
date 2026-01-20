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
def response = WS.sendRequest(findTestObject('getAirports', [
	('page') : 1,
	('size') : 30
]))

// Assertion sederhana
WS.verifyResponseStatusCode(response, 200)

// Parse response JSON
def jsonSlurper = new JsonSlurper()
def jsonResponse = jsonSlurper.parseText(response.getResponseBodyContent())

// Verifikasi data airports ada
assert jsonResponse.data != null : "Data airports tidak boleh null"
assert jsonResponse.data.size() > 0 : "Data airports harus ada minimal 1"

// Verifikasi  response statis dahulu pakai index epertama
assert jsonResponse.data[0].id != null : "Airport ID harus ada"

assert jsonResponse.data[0].attributes.name != null : "Airport name harus ada"

assert jsonResponse.data[0].attributes.iata != null : "Airport IATA code harus ada"

println("Test getAirports berhasil - Total airports: " + jsonResponse.data.size())

