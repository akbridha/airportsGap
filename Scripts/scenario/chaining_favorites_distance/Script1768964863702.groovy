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



println("   CHAINING TEST - FAVORITES & DISTANCE")
println("========================================\n")

// ==========================================
// STEP 0: Clean Up - Delete All Favorites
// ==========================================
println("STEP 0: Menghapus semua Favorite...")
WebUI.callTestCase(findTestCase('service/deleteFavoritesAllTest'), [:], FailureHandling.STOP_ON_FAILURE)
println("✓ Semua favorites berhasil dihapus\n")

// ==========================================
// STEP 1: Add First Favorite (JFK - New York)
// ==========================================
println("STEP 1: Menambahkan Favorite Pertama (JFK - New York)...")
def response1 = WS.sendRequest(findTestObject('postFavorite', [
	('airport_id') : 'JFK',
	('note') : 'John F Kennedy International Airport - New York',
	('token') : GlobalVariable.authToken
]))

WS.verifyResponseStatusCode(response1, 201)

def jsonSlurper = new JsonSlurper()
def jsonResponse1 = jsonSlurper.parseText(response1.getResponseBodyContent())

assert jsonResponse1.data != null : "Data favorite 1 tidak boleh null"
assert jsonResponse1.data.id != null : "Favorite 1 ID harus ada"

def favorite1Id = jsonResponse1.data.id
def airport1Code = jsonResponse1.data.attributes.airport.iata
def airport1Name = jsonResponse1.data.attributes.airport.name

println("✓ Favorite 1 berhasil ditambahkan")
println("  - ID: ${favorite1Id}")
println("  - Airport: ${airport1Name} (${airport1Code})")
println("  - Note: ${jsonResponse1.data.attributes.note}\n")

// ==========================================
// STEP 2: Add Second Favorite (LAX - Los Angeles)
// ==========================================
println("STEP 2: Menambahkan Favorite Kedua (LAX - Los Angeles)...")
def response2 = WS.sendRequest(findTestObject('postFavorite', [
	('airport_id') : 'LAX',
	('note') : 'Los Angeles International Airport - California',
	('token') : GlobalVariable.authToken
]))

WS.verifyResponseStatusCode(response2, 201)

def jsonResponse2 = jsonSlurper.parseText(response2.getResponseBodyContent())

assert jsonResponse2.data != null : "Data favorite 2 tidak boleh null"
assert jsonResponse2.data.id != null : "Favorite 2 ID harus ada"

def favorite2Id = jsonResponse2.data.id
def airport2Code = jsonResponse2.data.attributes.airport.iata
def airport2Name = jsonResponse2.data.attributes.airport.name

println("✓ Favorite 2 berhasil ditambahkan")
println("  - ID: ${favorite2Id}")
println("  - Airport: ${airport2Name} (${airport2Code})")
println("  - Note: ${jsonResponse2.data.attributes.note}\n")

// ==========================================
// STEP 3: Calculate Distance Between 2 Favorites
// ==========================================
println("STEP 3: Menghitung jarak antara ${airport1Code} dan ${airport2Code}...")
def distanceResponse = WS.sendRequest(findTestObject('postDistance', [
	('from') : airport1Code,
	('to') : airport2Code
]))

WS.verifyResponseStatusCode(distanceResponse, 200)

def distanceJson = jsonSlurper.parseText(distanceResponse.getResponseBodyContent())

// Debug: Print full response untuk melihat struktur
println("DEBUG - Full Distance Response:")
println(distanceResponse.getResponseBodyContent())

assert distanceJson.data != null : "Data distance tidak boleh null"
assert distanceJson.data.attributes != null : "Distance attributes harus ada"
assert distanceJson.data.attributes.kilometers != null : "Kilometers harus ada"
assert distanceJson.data.attributes.miles != null : "Miles harus ada"

def kilometers = distanceJson.data.attributes.kilometers
def miles = distanceJson.data.attributes.miles

// ======== PERCOBAAN SEBELUMNYA (ERROR) ========
// Percobaan 1: Akses langsung tanpa safe navigation - GAGAL (NullPointerException)
// def fromAirport = distanceJson.data.attributes.from.name
// def toAirport = distanceJson.data.attributes.to.name
// Alasan Error: Field 'from' dan 'to' tidak ada di response API
// Response sebenarnya menggunakan 'from_airport' dan 'to_airport'
// ===============================================

// ✅ SOLUSI AKHIR: Gunakan safe navigation operator dan fallback
def fromAirport = distanceJson.data.attributes.from_airport?.name ?: airport1Code
def toAirport = distanceJson.data.attributes.to_airport?.name ?: airport2Code

println("✓ Jarak berhasil dihitung")
println("\n========== HASIL PERHITUNGAN JARAK ==========")
println("Dari: ${fromAirport} (${airport1Code})")
println("Ke  : ${toAirport} (${airport2Code})")
println("Jarak: ${kilometers} km / ${miles} miles")
println("==============================================\n")

// ==========================================
// STEP 4: Verify All Favorites in List
// ==========================================
println("STEP 4: Verifikasi daftar favorites...")
WebUI.callTestCase(findTestCase('service/getFavoritesTest'), [:], FailureHandling.STOP_ON_FAILURE)

println("\n========================================")
println("   TEST SELESAI - SEMUA STEP BERHASIL")
println("========================================\n")

