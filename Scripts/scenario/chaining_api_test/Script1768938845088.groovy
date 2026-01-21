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


println("   API CHAINING TEST - AIRPORT FAVORITE")
println("=====#################=======\n")

// ==========================================
// STEP 0: Get Authentication Token
// ==========================================
// Token sudah dibuat sebelumnya, gunakan GlobalVariable.authToken
// Jika belum ada token, uncomment code di bawah:
/*
println("STEP 0: Mendapatkan Authentication Token...")
WebUI.callTestCase(findTestCase('service/postTokensTest'), [:], FailureHandling.STOP_ON_FAILURE)
println("✓ Token berhasil didapatkan: ${GlobalVariable.authToken}\n")
*/

println("STEP 0: Menghapus semua Favorite... karena tidak boleh duplikat")
WebUI.callTestCase(findTestCase('service/deleteFavoritesAllTest'), [:], FailureHandling.STOP_ON_FAILURE)
println()

//=======
println(" 1: Mengambil data Airport...")
WebUI.callTestCase(findTestCase('service/getAirportByIdTest'), [:], FailureHandling.STOP_ON_FAILURE)
println()


// 2 Add Airport to Favorite
// ==========================================
println("2: Menambahkan Airport ke Favorite...")
WebUI.callTestCase(findTestCase('service/postFavorite'), [:], FailureHandling.STOP_ON_FAILURE)
//println()

// 3 Edit Note pada Favorite
// ==========================================
println("3: Mengupdate Note pada Favorite...")
WebUI.callTestCase(findTestCase('service/patchFavoritesById'), [:], FailureHandling.STOP_ON_FAILURE)
//println()



// 4: GET Favorite
println("STEP 4: Mengambil semua Favorite...")
WebUI.callTestCase(findTestCase('service/getFavoritesTest'), [:], FailureHandling.STOP_ON_FAILURE)
println()

//P 5: Delete Semua Favorite



//
println(" TEST SELESAI")
